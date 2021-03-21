package com.apulbere.is2021;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PoorOptionalUsageTest {

    @Test
    void doNotReplaceIf() {
        Person person = null;

        String name = Optional.ofNullable(person)
                .map(Person::name)
                .orElse(null);

        assertNull(name);
    }

    @Test
    void specialisedOptional() {
        Optional<Integer> ageOpt = Optional.of(50);

        assertTrue(ageOpt.isPresent());
    }

    record Person(String name) {}
}
