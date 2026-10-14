package com.jad.show;

import java.util.Map;

final class ConcertShowHandler extends CreateShowHandler {
    ConcertShowHandler(final CreateShowHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected String getShowType() {
        return "CONCERT";
    }

    @Override
    protected IShow createShow(final Map<String, String> parameters) {
        rejectUnexpectedParameters(parameters, "name", "description", "artist");
        return new ConcertShow(requiredParameter(parameters, "name"),
                requiredParameter(parameters, "description"),
                requiredParameter(parameters, "artist"));
    }
}