package com.m.jcp.chapter_8.decorator_pattern;

import java.util.function.Function;
import java.util.stream.Stream;

public class CakeDecorator {
    /**
     * The decorator function is a function that takes a Cake and returns a Cake.
     */
    private Function<Cake, Cake> decorator;

    public CakeDecorator(Function<Cake, Cake>... decorators) {
        decorator = Stream.of(decorators).reduce(Function.identity(), Function::andThen);
    }

    public Cake decorate(Cake cake) {
        return decorator.apply(cake);
    }
}