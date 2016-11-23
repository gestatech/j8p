# Java 8 Practice

Quick and dirty snippets in preparation for the Java 8 certification exam.

Trivial classes are just added as-is, more complicated with a simple unit test to try.

## Upgrade to Java SE 8 OCP ( Java SE 6 and all prior versions) 1Z0-813 - topics

### Language Enhancements

- [Basics](src/main/java/io/r2/j8p/t1_language/Basics.java) Develop code that uses String objects in the switch statement, binary literals, and numeric literals, including underscores in literals
- [TryWithResources](src/main/java/io/r2/j8p/t1_language/TryWithResources.java) Develop code that uses try-with-resources statements, including using classes that implement the AutoCloseable interface
- [MultiCatch](src/main/java/io/r2/j8p/t1_language/MultiCatch.java) Develop code that handles multiple Exception types in a single catch block
- [Interfaces](src/main/java/io/r2/j8p/t1_language/Interfaces.java) Use static and default methods of an interface including inheritance rules for a default method

### Concurrency

- [Synchronizers](src/main/java/io/r2/j8p/t2_concurrency/Synchronizers.java), [Note:ConcurrentCollections](notes/ConcurrentCollections.md)  Use classes from the java.util.concurrent package including CyclicBarrier and CopyOnWriteArrayList with a focus on the advantages over and differences from the traditional java.util collections 
- [LockAtomic](src/main/java/io/r2/j8p/t2_concurrency/LockAtomic.java)  Use Lock, ReadWriteLock, and ReentrantLock classes in the java.util.concurrent.locks and java.util.concurrent.atomic packages to support lock-free thread-safe programming on single variables
- [ExecutorEx](src/main/java/io/r2/j8p/t2_concurrency/ExecutorEx.java) Use Executor, ExecutorService, Executors, Callable, and Future to execute tasks using thread pools
- [ForkJoin](src/main/java/io/r2/j8p/t2_concurrency/ForkJoin.java) Use the parallel Fork/Join Framework

### Localization

- [LocaleEx](src/main/java/io/r2/j8p/t2_concurrency/LocaleEx.java) Describe the advantages of localizing an application and developing code that defines, reads, and sets the locale with a Locale object
- [Bundles](src/main/java/io/r2/j8p/t2_concurrency/Bundles.java) Build a resource bundle for a locale and call a resource bundle from an application
- [LocalDateAndTime](src/main/java/io/r2/j8p/t2_concurrency/LocalDateAndTime.java) Create and manage date- and time-based events by using LocalDate, LocalTime, LocalDateTime, Instant, Period, and Duration, including a combination of date and time in a single object
- [NumberDateFormat](src/main/java/io/r2/j8p/t2_concurrency/NumberDateFormat.java) Format dates, numbers, and currency values for localization with the NumberFormat and DateFormat classes, including number and date format patterns
- [Note:ZonedTimes](notes/ZonedTimes.md) Work with dates and times across time zones and manage changes resulting from daylight savings

### Java File I/O (NIO.2)

- [FilePath](src/main/java/io/r2/j8p/t4_nio2/FilePath.java) Operate on file and directory paths by using the Paths class
- [FilesBasicOps](src/main/java/io/r2/j8p/t4_nio2/FilesBasicOps.java) Check, delete, copy, or move a file or directory by using the Files class 
- [DirWalking](src/main/java/io/r2/j8p/t4_nio2/DirWalking.java) Recursively access a directory tree by using the DirectoryStream and FileVisitor interfaces
- [Note:PathMatcher](notes/PathMatcher.md) Find a file by using the PathMatcher interface
- [DirWalking](src/main/java/io/r2/j8p/t4_nio2/DirWalking.java) [FilesAdvancedOps](src/main/java/io/r2/j8p/t4_nio2/FilesAdvancedOps.java) Use Java SE 8 I/O improvements, including Files.find(), Files.walk(), and lines() methods
- [DirWatch](src/main/java/io/r2/j8p/t4_nio2/DirWatch.java) Observe the changes in a directory by using the WatchService interface

### Lambda

- [FunctionalIface](src/main/java/io/r2/j8p/t5_lambda/FunctionalIface.java) Define and write functional interfaces and describe the interfaces of the java.util.function package
- [LambdaInfo](src/main/java/io/r2/j8p/t5_lambda/LambdaInfo.java) Describe a lambda expression; refactor the code that uses an anonymous inner class to use a lambda expression; describe type inference and target typing
- [OptionalAPI](src/main/java/io/r2/j8p/t5_lambda/OptionalAPI.java) [FunctionalIface](src/main/java/io/r2/j8p/t5_lambda/FunctionalIface.java) Develop code that uses the built-in interfaces included in the java.util.function package, such as Function, Consumer, Supplier, UnaryOperator, Predicate, and Optional APIs, including the primitive and binary variations of the interfaces
- [MethodRef](src/main/java/io/r2/j8p/t5_lambda/MethodRef.java) Develop code that uses a method reference, including refactoring a lambda expression to a method reference

### Java Collections

- [Diamond](src/main/java/io/r2/j8p/t6_collections/Diamond.java) Develop code that uses diamond with generic declarations
- [CollectionBasics](src/main/java/io/r2/j8p/t6_collections/CollectionBasics.java) Develop code that iterates a collection, filters a collection, and sorts a collection by using lambda expressions
- [CollectionSearch](src/main/java/io/r2/j8p/t6_collections/CollectionSearch.java) Search for data by using methods, such as findFirst(), findAny(), anyMatch(), allMatch(), and noneMatch()
- (not available) Perform calculations on Java Streams by using count, max, min, average, and sum methods and save results to a collection by using the collect method and Collector class, including the averagingDouble, groupingBy, joining, partitioningBy methods
- [CollectionBasics](src/main/java/io/r2/j8p/t6_collections/CollectionBasics.java) Develop code that uses Java SE 8 collection improvements, including the Collection.removeIf(), List.replaceAll(), Map.computeIfAbsent(), and Map.computeIfPresent() methods
- (not available) Develop  code that uses the merge(), flatMap(), and map() methods on Java Streams

### Java Streams

- (not available) Describe the Stream interface and pipelines; create a stream by using the Arrays.stream() and  IntStream.range() methods; identify the lambda operations that are lazy
- (not available) Develop code that uses parallel streams, including decomposition operation and reduction operation in streams
 
 ### Extras not covered explicitly by official topic list
- [NioCore](src/main/java/io/r2/j8p/t4_nio2/NioCore.java) Quick review of basic java.nio operations
