import os
import sys
from subprocess import check_output, STDOUT, CalledProcessError
import re

def easy_output(args):
    try:
        return check_output(args, stderr=STDOUT,universal_newlines=True)
    except CalledProcessError as e:
        return e.output


args = sys.argv[1:]
if len(args) < 1:
    print("Usage: [<file.x3> <file2.x3> ... | <test/generator/fileDir>]")

# Loop through files in test folder
indir = os.path.dirname('./test/generator/')
cdir = os.path.dirname('./cbuild/')

progs = []
for arg in args:

    if arg.endswith(".x3"):
        progs = progs + [indir+"/"+arg]
    elif arg.endswith("/"):
        for root, dirs, filenames in os.walk(indir):
            for f in filenames:
                progs = progs + f
    else:
        print(arg + " not recognized as file or directory.")

for prog in progs:

    # compile the progam (capture output if possible)
    compiler_output = easy_output(["java","-jar","build/jar/Cubex.jar",prog])
    print(compiler_output)
    # move out file to the build directory
    easy_output(["mv","./out.c",cdir])

    # call make from the build directory (capture output if possible)
    make_output = easy_output(["make","-C",cdir])

    easy_output(["mv",cdir+"/a.out",prog[:-2]+"out"])






# x3s = set()
# ins = set()
# outs = set()
# test_set = set()


# for root, dirs, filenames in os.walk(indir):
#     for f in filenames:

#         match = re.match(r"(.*)\.(.*)", f)
#         if match:
#             (name, ext) = match.groups()
#         else:
#             # bad file name, skip
#             continue
#         if ext == "x3":
#             x3s.add(name)
#         elif ext == "in":
#             ins.add(name)
#         elif ext == "out":
#             outs.add(name)

# # find intersection to test on
# for x3 in x3s:
#     if x3 in ins and x3 in outs:
#         test_set.add(x3)

# test_diag = open('TEST_DIAG.txt','w')

# for test in sorted(list(test_set)):
#     # get the relevant names
#     common = indir + "/"+test
#     prog = common + ".x3"
#     infile = common + ".in"
#     outfile = common + ".out"

#     tmpfile = "_tempfile"

#     devnull = open('/dev/null', 'w')

#     # compile the progam (capture output if possible)

#     compiler_output = easy_output(["java","-jar","build/jar/Cubex.jar",prog])

#     # move out file to the build directory
#     easy_output(["mv","./out.c",cdir])

#     # call make from the build directory (capture output if possible)
#     make_output = easy_output(["make","-C",cdir])

#     f = open(infile)
#     args = f.read().split("\n")
#     f.close()

#     program_output = easy_output([cdir + "/a.out"] + args)

#     correct_file = open(outfile,'r')
#     correct_output = correct_file.read()

#     if (program_output == correct_output):
#         print("TEST PASSED: "+ common)
#         test_diag.write("===== TEST PASSED: "+ common +"\n")

#     else:
#         print("TEST FAILED: "+ common)
#         test_diag.write("_________________________________\n\n")
#         test_diag.write("TEST FAILED     : " + test +"\n\n")
#         test_diag.write("compiler output :  \n=================\n" + compiler_output+"\n")
#         test_diag.write("make output     :  \n=================\n" + make_output+"\n")
#         test_diag.write("expected output : " + correct_output+"\n")
#         test_diag.write("actual output   : " + program_output)
#         test_diag.write("_________________________________\n\n\n")


#     correct_file.close()


