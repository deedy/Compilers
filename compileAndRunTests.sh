#!/bin/bash
cd `dirname $0`
ant compile
cd build/classes
java LexerAssignmentTest ../../test/lexer/assignment_tests
cd ../../
