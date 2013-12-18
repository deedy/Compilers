import os, sys, time
lib_path = os.path.abspath('../')
sys.path.append(lib_path)

curr_dir = os.getcwd()

from test_lib import *

# collect all the files to run

names_to_run = valid_pairs(curr_dir)
num_tests = len(names_to_run)

jar = "../../build/jar/Parser.jar"
prog = lambda x: call("java -jar %s %s.in" % (jar, x))

tests_failed = 0
for name in names_to_run:
	output = prog(name)
	ex = open(name + ".out")
	expected = ex.read().strip()
	if output != expected:
		tests_failed += 1
		print("====================================================")
		print("test failed: %s" % name)
		print("expected:" + expected)
		print("received:" + output)
		print("====================================================")

print("passed %d/%d tests" % (num_tests - tests_failed, num_tests))

