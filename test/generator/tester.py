import os, sys

from subprocess import check_output, STDOUT, CalledProcessError
lib_path = os.path.abspath('../')
sys.path.append(lib_path)

curr_dir = os.getcwd()

from test_lib import *

def runner(args):
    try:
        return check_output(args, stderr=STDOUT,universal_newlines=True)
    except CalledProcessError as e:
        return e.output

# collect all the files to run

names_to_run = valid_triples(curr_dir)
num_tests = len(names_to_run)

jar = "../../build/jar/Cubex.jar"
def compiler(x):
	call("java -jar %s %s.x3" % (jar, x))
	call("cp -f out.c ../../cbuild")
	# print(call("make -C ../../cbuild clean"))
	print(call("make -C ../../cbuild"))

def runner(args):
    try:
        return check_output(args, stderr=STDOUT,universal_newlines=True)
    except CalledProcessError as e:
        return e.output

tests_failed = 0
fails = []
for name in names_to_run:
	# print(name)
	compiler(name)
	argf = open(name + ".in")
	args = argf.read().split("\n")
	argf.close()
	# print("../../cbuild/a.out %s" % (" ".join(args)))
	output = runner(["../../cbuild/a.out"] + args)
	ex = open(name + ".out")
	expected = ex.read()
	if output != expected:
		print("====================================================")
		print("test failed: %s" % name)
		print("expected:")
		print(expected)
		print("received:")
		print(output)
		print("====================================================")
		tests_failed += 1
	else:
		print("test passed: %s" % name)

print("passed %d/%d tests" % (num_tests - tests_failed, num_tests))

