package com.apulbere.is2021;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SortedStreamTest {

    @Test
    void sortedStream() {
        var people = Stream.of(new Person("John"), new Person("Jack"))
                .sorted()
                .collect(toList());

        assertFalse(people.isEmpty());
    }

    record Person(String name) {}
}
