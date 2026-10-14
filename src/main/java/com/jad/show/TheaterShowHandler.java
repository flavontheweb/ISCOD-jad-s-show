package com.jad.show;

import java.util.Map;

final class TheaterShowHandler extends CreateShowHandler {
    TheaterShowHandler(final CreateShowHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected String getShowType() {
        return "THEATER";
    }

    @Override
    protected IShow createShow(final Map<String, String> parameters) {
        rejectUnexpectedParameters(parameters, "name", "description", "director", "actors");
        return new TheaterShow(requiredParameter(parameters, "name"),
                requiredParameter(parameters, "description"),
                requiredParameter(parameters, "director"),
                requiredListParameter(parameters, "actors"));
    }
}