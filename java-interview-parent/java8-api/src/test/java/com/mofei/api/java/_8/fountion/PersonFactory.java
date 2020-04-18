package com.mofei.api.java._8.fountion;

import com.mofei.api.Person;

@FunctionalInterface
public interface PersonFactory<P extends Person> {
    P create2(String name, int age);
}
