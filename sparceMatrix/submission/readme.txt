Alex Kantchelian
alex.kantchelian@gmail.com
3/3/2016


# Building & Usage

Tutorial video is available at: https://youtu.be/jEHEkL4JkjM

Use make to build the command line executable:

$ make

This will create text2bin in the bin/ subdirectory. 
text2bin takes exactly one argument: the location of the input file.
The run on the given test input file looks like this:

$ bin/text2bin test.in
Max Auxillary Buffers:	1.0 MB
Matrix Size Upper Bound:	56.3 MB
TOTAL MEMORY UPPER BOUND:	57.3 MB

Parsing matrix...OK	Elapsed Wall Time 0.362s
Writing Transpose..OK	Elapsed Wall Time 0.457s
Writing Original..OK	Elapsed Wall Time 1.000s

This will produce the required two files out.bin and out-transpose.bin
in the current directory.


# Tuning notes

This is a single threaded implementation which uses constant size
read and write buffers to accelerate disk reads/writes. The current
reading and writing/transposition buffers are set to 1MB. A higher
value might be desirable for high-latency storage (e.g. magnetic drives).
Those values can be changed in the main.h header:

#define MAX_RBUF_SIZE 1*1024*1024 // this is the size of a read from the text file
#define MAX_TBUF_SIZE 1*1024*1024 // this is both the size of a write and a transposition window


# Implementation & design notes

* single threaded
* uses O(|final matrix|) RAM
* custom byte parsing function
* use pread for random reads in file
* uses a row-major representation of the matrix internally
* reads the input file only once (upper bounds the final matrix size based on the file size)
* code documentation available in the header files
