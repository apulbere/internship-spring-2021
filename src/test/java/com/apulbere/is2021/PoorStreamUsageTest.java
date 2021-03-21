package com.apulbere.is2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

public class PoorStreamUsageTest {

    @Test
    void streamWithSideEffects() {
        var actualPeople = new ArrayList<Person>();

        Stream.of("Jack", "John", "Anna")
                .filter(name -> name.startsWith("J"))
                .map(Person::new)
                .forEach(actualPeople::add);

        var expectedPeople = List.of(new Person("Jack"), new Person("John"));
        assertEquals(expectedPeople, actualPeople);
    }

    @Test
    void ioBasedStreams() throws Exception {
        Stream<String> lines = Files.lines(Path.of("src/test/resources/three_laws.txt"));

        assertEquals(3, lines.count());
    }

    @Test
    void nonInterference() {
        var bookList = Collections.nCopies(1_000_000, new Book());

        long count = bookList.stream()
                .parallel()
                .filter(b -> b.refersTo != null)
                .map(b -> {
                    b.refersTo.referred = true;
                    return b;
                })
                .filter(b -> b.referred)    //stateful - one whose result depends on any state that might change during the execution of the stream pipeline
                .count();

        assertEquals(1, count); // or 999 or 45 or ...
    }

    class Book {
        Book refersTo;
        boolean referred;
    }

    record Person(String name) {}

    @Test
    void longLambdaNoMeaning() {
        var allAccounts = List.of(
                new Account(new BigDecimal(120), "EUR"),
                new Account(new BigDecimal(0), "EUR"),
                new Account(new BigDecimal(200), "MDL"));

        var actualAccounts = allAccounts.stream().
                filter(account -> account.balance().compareTo(BigDecimal.ZERO) != 0 && account.currency().equals("EUR"))
                .collect(toList());

        var expectedAccounts = List.of(new Account(new BigDecimal(120), "EUR"));
        assertEquals(actualAccounts, expectedAccounts);
    }

    record Account(BigDecimal balance, String currency) {}

    @Test
    @DisplayName("how to trick final or effective final rule for lambda. An example of bad programming")
    void trickFinalOrEffectiveFinal() {
        boolean[] oddNumberWasGenerated = new boolean[1];

        var random = new Random();
        IntStream.generate(() -> random.nextInt(9))
                .limit(100)
                .forEach(i -> {
                    if(i % 2 != 0) {
                        oddNumberWasGenerated[0] = true;
                    }
                });

        assertTrue(oddNumberWasGenerated[0]);
    }

    @Test
    void excessiveBoxingAndUnboxing() {
        Optional<Integer> max = Stream.of(1, 2, 3, 4)
                .map(i -> i + 1) //involves executing the Integer methods intValue and valueOf, respectively, before and after
                .max(Integer::compareTo);

        max.ifPresentOrElse(i -> assertEquals(5, i), Assertions::fail);
    }
}
