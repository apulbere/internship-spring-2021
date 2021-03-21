# Endava Spring 2021 Internship. Java Stream API, Optional and FP

#### Prerequisites
* Java 15
* Set correct version of Java in IntelliJ IDEA: 15 (Preview) - Sealed types, records, patterns, local enums and interfaces
  * Open Module Settings
    * Project 
      * Project SDK
      * Project Language Level
    * Modules
      * Language Level

### Use Optional properly
* `com.apulbere.is2021.PoorOptionalUsageTest`
* `List<Optional<String>>` - WRONG
* `Optional` method/constructor parameter - WRONG
* `Optional` attribute/field in a class - WRONG
* return `Optional` - CORRECT

### Use Stream properly
* `com.apulbere.is2021.PoorStreamUsageTest`
* `com.apulbere.is2021.SortedStreamTest`

### Study collectors
* `com.apulbere.is2021.ComposingCollectorsTest`
* `groupingBy` vs `toMap`

### Functional Interfaces
* `com.apulbere.is2021.FunInterfaceTest`

### Parallel Stream
* sequential stream should by default unless
  * you have a performance problem
  * (or) a huge chunk of data 
  * consider that using parallel in a highly multi-thread environment (like web container) might have more negative than positive effects

### Useful
* [Introduction to Stream by Brian Goetz](https://developer.ibm.com/articles/j-java-streams-1-brian-goetz/)
* Mastering Lambdas: Java Programming in a Multicore World (Oracle Press)