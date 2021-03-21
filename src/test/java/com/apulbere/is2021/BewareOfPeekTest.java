package com.apulbere.is2021;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BewareOfPeekTest {

    @Test
    void bewareOfPeek() {
        var isPeekInvoked = new AtomicBoolean(false);

        long actualCount = Stream.of("a", "b")
                .peek(item -> isPeekInvoked.set(true))
                .count();

        assertEquals(2, actualCount);
        assertTrue(isPeekInvoked.get());
    }
}
