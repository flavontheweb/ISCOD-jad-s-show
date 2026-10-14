package com.jad.customer;

import com.jad.show.IShow;
import com.jad.show.IShowVisitor;
import com.jad.show.MovieShow;
import com.jad.show.TheaterShow;
import com.jad.show.StreetShow;
import com.jad.show.ConcertShow;

import java.util.List;

@SuppressWarnings("java:S106")
public class Spectator implements ISpectator, IShowVisitor {
    private final String firstName;
    private final String lastName;

    public Spectator(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void watch(final IShow show) {
        show.accept(this);
    }

    @Override
    public void visit(final MovieShow show) {
        System.out.printf("J'ai assisté au film %s de %s sorti en %s%n%n",
                show.getName(), show.getDirector(), show.getYearOfRelease());
    }

    @Override
    public void visit(final TheaterShow show) {
        printShowWithPerformers("à la pièce de théâtre " + show.getName() + " de " + show.getDirector(),
                show.getActors());
    }

    @Override
    public void visit(final StreetShow show) {
        printShowWithPerformers("au spectacle de rue " + show.getName(), show.getPerformers());
    }

    @Override
    public void visit(final ConcertShow show) {
        System.out.printf("J'ai assisté au concert %s de %s%n%n", show.getName(), show.getArtist());
    }

    private void printShowWithPerformers(final String showDescription, final List<String> performers) {
        System.out.printf("J'ai assisté %s.%nIl y avait : %s%n%n",
                showDescription, String.join(", ", performers));
    }
}
