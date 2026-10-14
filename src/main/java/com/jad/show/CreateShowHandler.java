package com.jad.show;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class CreateShowHandler {
    private final CreateShowHandler nextHandler;

    protected CreateShowHandler(final CreateShowHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public final IShow handle(final String showDescription) {
        if (showDescription == null) {
            throw new IllegalArgumentException("Show description must not be null");
        }

        final int typeSeparator = showDescription.indexOf(':');
        if (typeSeparator < 0) {
            throw new IllegalArgumentException("Show description must start with a show type followed by ':'");
        }

        final String showType = showDescription.substring(0, typeSeparator).trim();
        if (showType.equals(getShowType())) {
            return createShow(parseParameters(showDescription.substring(typeSeparator + 1)));
        }
        if (this.nextHandler != null) {
            return this.nextHandler.handle(showDescription);
        }
        throw new IllegalArgumentException("Unsupported show type: " + showType);
    }

    protected abstract String getShowType();

    protected abstract IShow createShow(Map<String, String> parameters);

    protected final String requiredParameter(final Map<String, String> parameters, final String name) {
        final String value = parameters.get(name);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required parameter: " + name);
        }
        return value;
    }

    protected final String[] requiredListParameter(final Map<String, String> parameters, final String name) {
        final String[] values = requiredParameter(parameters, name).split(",", -1);
        for (int index = 0; index < values.length; index++) {
            values[index] = values[index].trim();
            if (values[index].isEmpty()) {
                throw new IllegalArgumentException("Parameter '" + name + "' must contain non-empty values");
            }
        }
        return values;
    }

    protected final void rejectUnexpectedParameters(final Map<String, String> parameters,
                                                    final String... allowedNames) {
        final Set<String> allowed = Set.of(allowedNames);
        for (final String name : parameters.keySet()) {
            if (!allowed.contains(name)) {
                throw new IllegalArgumentException("Unexpected parameter: " + name);
            }
        }
    }

    private Map<String, String> parseParameters(final String parameterDescription) {
        final Map<String, String> parameters = new LinkedHashMap<>();
        for (final String parameter : parameterDescription.split(";")) {
            final int valueSeparator = parameter.indexOf('=');
            if (valueSeparator <= 0) {
                throw new IllegalArgumentException("Invalid parameter: " + parameter);
            }

            final String name = parameter.substring(0, valueSeparator).trim();
            final String value = parameter.substring(valueSeparator + 1).trim();
            if (name.isEmpty() || value.isEmpty()) {
                throw new IllegalArgumentException("Invalid parameter: " + parameter);
            }
            if (parameters.putIfAbsent(name, value) != null) {
                throw new IllegalArgumentException("Duplicate parameter: " + name);
            }
        }
        return parameters;
    }
}