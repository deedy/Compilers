CC=gcc
CFLAGS=-ansi -Wall
DEPS = cubex_external_functions.h cubex_private.h cubex_main.h cubex_lib.h

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

all: cubex_external_functions.o master.o out.o
	$(CC) -o a.out cubex_external_functions.o master.o out.o $(CFLAGS)

clean: FORCE
	rm *.o a.out

FORCE:
