# compile.py
# takes in a list of x3 files or directories of x3 files
# compiles file.x3 to file.out in the same directory

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
                if arg.endswith(".x3"):
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

