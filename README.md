Compilers
=========

Compilers Repo

Dependencies: ANTLR 4.1

To build:
```
ant compile
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

To create and use the required jar for submission (Assignment 1):


```
ant jar
java -jar build/jar/Cubex.jar test/lexer/assignment_tests/simple_test2.in
```
