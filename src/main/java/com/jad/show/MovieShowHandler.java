package com.jad.show;

import java.util.Map;

final class MovieShowHandler extends CreateShowHandler {
    private static final MovieShowHandler INSTANCE = new MovieShowHandler();

    private MovieShowHandler() {
        super(new TheaterShowHandler(new StreetShowHandler(new ConcertShowHandler(null))));
    }

    static MovieShowHandler getInstance() {
        return INSTANCE;
    }

    @Override
    protected String getShowType() {
        return "MOVIE";
    }

    @Override
    protected IShow createShow(final Map<String, String> parameters) {
        rejectUnexpectedParameters(parameters, "name", "description", "director", "yearOfRelease", "movieType");
        return new MovieShow(requiredParameter(parameters, "name"),
                requiredParameter(parameters, "description"),
                requiredParameter(parameters, "director"),
                requiredParameter(parameters, "yearOfRelease"),
                MovieType.valueOf(requiredParameter(parameters, "movieType")));
    }
}