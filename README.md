Compilers
=========

Compilers Repo

Dependencies: ANTLR 4.1

To build:
```
ant compile jar
```

To run (example):

```
cd build/classes
java CubexLexerProg ../../test/lexer/assignment_tests/simple_test1.in
```

To test a folder of tests:

```
java LexerAssignmentTest ../../test/lexer/assignment_tests
```

To create the required jar for submission (Assignment 1):


```
cd build/classes
jar cvfm CubexLexerProg.jar ../../MANIFEST.MF *class ../../src/lexer/*g4 ../../src/lexer/*java
java -jar CubexLexerProg.jar ../../test/lexer/assignment_tests/simple_test.in
```
