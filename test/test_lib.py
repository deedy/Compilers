# useful functions for testing
import os
import sys
from subprocess import getoutput
import re

# call a program and get its stdout and stderr
def call(cmd):
	return getoutput(cmd);

def get_files(directory):
	for root, dirs, filenames in os.walk(directory):
		for f in filenames:
			match = re.match(r"(.*)\.(.*)", f)
			if match:
				groups = match.groups()
				yield groups
			else:
				# bad file name, skip
				continue

def valid_pairs(directory):
	ins = set()
	outs = set()
	for (name, ext) in get_files(directory):
		if ext == "in":
			ins.add(name)
		elif ext == "out":
			outs.add(name)
	return ins & outs

def valid_triples(directory):
	ins = set()
	outs = set()
	x3s = set()
	for (name, ext) in get_files(directory):
		if ext == "in":
			ins.add(name)
		elif ext == "out":
			outs.add(name)
		elif ext == "x3":
			x3s.add(name)
	return ins & outs & x3s
