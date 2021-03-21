package com.apulbere.is2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ComposingCollectorsTest {

    @Test
    void groupByTopicsWithBookTitlesInBrackets() {
        var bookList = List.of(
                new Book("TMC", List.of(new Topic("fiction"), new Topic("mars"))),
                new Book("TCitR", List.of(new Topic("fiction"), new Topic("young")))
        );

        var topicGroups = bookList.stream()
                .flatMap(b -> b.topics().stream().map(t -> new Pair<>(b, t)))
                .collect(groupingBy(p -> p.right().name(),
                        collectingAndThen(mapping(p -> p.left().title(), joining(", ")), "[%s]"::formatted)));

        assertThat(topicGroups)
                .containsEntry("fiction", "[TMC, TCitR]")
                .containsEntry("mars", "[TMC]")
                .containsEntry("young", "[TCitR]");
    }

    record Book(String title, List<Topic> topics) {}

    record Topic(String name) {}

    record Pair<L, R>(L left, R right) {}

}
