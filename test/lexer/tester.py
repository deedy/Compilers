import os, sys
lib_path = os.path.abspath('../')
sys.path.append(lib_path)

curr_dir = os.getcwd()

from test_lib import *

# collect all the files to run

names_to_run = valid_pairs(curr_dir)
num_tests = len(names_to_run)

jar = "../../build/jar/Lexer.jar"
prog = lambda x: call("java -jar %s %s.in" % (jar, x))

tests_failed = 0
fails = []
for name in names_to_run:
	output = prog(name)
	ex = open(name + ".out")
	expected = ex.read().strip()
	if output != expected:
		tests_failed += 1
		fails.append((name, expected, output))

for (name, ex, out) in fails:
	print("test failed: %s" % name)
	print("expected:")
	print(ex)
	print("received:")
	print(out)

print("passed %d/%d tests" % (num_tests - tests_failed, num_tests))

