package com.jad.show;

import java.util.Map;

final class StreetShowHandler extends CreateShowHandler {
    StreetShowHandler(final CreateShowHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected String getShowType() {
        return "STREET_SHOW";
    }

    @Override
    protected IShow createShow(final Map<String, String> parameters) {
        rejectUnexpectedParameters(parameters, "name", "description", "performers");
        return new StreetShow(requiredParameter(parameters, "name"),
                requiredParameter(parameters, "description"),
                requiredListParameter(parameters, "performers"));
    }
}