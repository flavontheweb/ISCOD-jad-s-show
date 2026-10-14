package com.jad.show;

public abstract class ShowFactory {
    private ShowFactory() {
    }

    public static IShow makeShow(final String showDescription) {
        return MovieShowHandler.getInstance().handle(showDescription);
    }
}