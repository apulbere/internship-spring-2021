package com.apulbere.is2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunInterfaceTest {

    @Test
    @SuppressWarnings("compilationError")
    void isThisAFunctionalInterface() {

        class Account {
            public String toString() {
                return "Account{}";
            }
        }

        interface AccountToStringFunction {

            String transform(Account account);

            String toString();

            private void print() {}
        }

        AccountToStringFunction fun = Account::toString;

        assertEquals("Account{}", fun.transform(new Account()));
    }


}
