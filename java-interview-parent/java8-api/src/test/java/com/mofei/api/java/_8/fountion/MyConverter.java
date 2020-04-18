package com.mofei.api.java._8.fountion;

@FunctionalInterface
public interface MyConverter<T, F> {
    T convert(F from);
}
