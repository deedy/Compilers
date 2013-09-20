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
java CubexParserProg ../../test/parserassignment_tests/parser_test1.in
```

To test a folder of tests:

```
java ParserAssignmentTest ../../test/parser/assignment_tests
```

To create and use the required jar for submission (Assignment 1):


```
ant jar
java -jar build/jar/Cubex.jar test/parser/assignment_tests/parser_test2.in
```

To quickly compile and run all tests in assignment tests:

```
ant compile test_lexer test_parser
```
