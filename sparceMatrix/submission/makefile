C=gcc
CFLAGS=-Wall -pedantic -std=c99
OFLAG=-O2 -flto
VPATH=src
OBJDIR=build
BINDIR=bin

all: directories build cmdapp

build: $(OBJDIR)/main.o $(OBJDIR)/getRealTime.o \

cmdapp: $(BINDIR)/text2bin

$(BINDIR)/text2bin: $(OBJDIR)/main.o $(OBJDIR)/getRealTime.o 
	$(C) -o $@ $(OFLAG) $(CFLAGS) $^

directories:
	mkdir -p $(OBJDIR)
	mkdir -p $(BINDIR)

clean:
	rm -rf $(OBJDIR)/*
	rm -f $(BINDIR)/*

$(OBJDIR)/getRealTime.o: getRealTime.c getRealTime.h
	$(C) -c $(CFLAGS) $(OFLAG) $< -o $@

$(OBJDIR)/main.o: main.c main.h
	$(C) -c $(CFLAGS) $(OFLAG) $< -o $@

