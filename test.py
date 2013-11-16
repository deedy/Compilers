import os
import sys
from subprocess import call, Popen, PIPE
import re

x3s = set()
ins = set()
outs = set()
test_set = set()

# Loop through files in test folder
indir = os.path.dirname('./test/generator/')
cdir = os.path.dirname('./cbuild/')

for root, dirs, filenames in os.walk(indir):
    for f in filenames:

        match = re.match(r"(.*)\.(.*)", f)
        if match:
            (name, ext) = match.groups()
        else:
            # bad file name, skip
            continue
        if ext == "x3":
            x3s.add(name)
        elif ext == "in":
            ins.add(name)
        elif ext == "out":
            outs.add(name)

# find intersection to test on
for x3 in x3s:
    if x3 in ins and x3 in outs:
        test_set.add(x3)

test_diag = open('TEST_DIAG.txt','w')

for test in test_set:
    # get the relevant names
    common = indir + "/"+test
    prog = common + ".x3"
    infile = common + ".in"
    outfile = common + ".out"

    tmpfile = "_tempfile"

    devnull = open('/dev/null', 'w')

    # compile the progam (capture output if possible)
    # > output to temp file?
    comp = Popen(["java","-jar","build/jar/Cubex.jar",prog], stdout=PIPE)
    compiler_output, compiler_error = comp.communicate()
    compiler_output = compiler_output.decode('utf-8')

    # call make from the build directory (capture output if possible)
    mk_out = Popen(["make","-C",cdir], stdout=PIPE)
    make_output, make_error = mk_out.communicate()
    make_output = make_output.decode('utf-8')

    # move out file to the build directory
    call(["mv","./out.c",cdir], stdout = devnull, stderr = devnull)

    f = open(infile)
    args = f.read().split("\n")
    f.close()

    prog = Popen([cdir + "/a.out"] + args, stdout = PIPE)
    program_output, program_err = prog.communicate()
    program_output = program_output.decode('utf-8')

    correct_file = open(outfile,'r')
    correct_output = correct_file.read()

    print(program_output,correct_output)

    if (program_output == correct_output):
        print("TEST PASSED: "+ common +"\n")
        test_diag.write("===== TEST PASSED: "+ common +"\n")

    else:
        print("TEST FAILED: "+ common)
        test_diag.write("_________________________________\n\n")
        test_diag.write("TEST FAILED     : " + common[17:] +"\n\n")
        test_diag.write("compiler output :  \n=================\n" + compiler_output+"\n")
        test_diag.write("make output     :  \n=================\n" + make_output+"\n")
        test_diag.write("expected output : " + correct_output+"\n")
        test_diag.write("actual output   : " + program_output)
        test_diag.write("_________________________________\n\n\n")

    correct_file.close()
