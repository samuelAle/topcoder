#!/usr/bin/python

import argparse
import time
import numpy as np

###################################################
# Setup the arguments and ranges and validity checks
###################################################

parser = argparse.ArgumentParser(description='Transposes a sparce matrix')
parser.add_argument('-i', type=open, required=True, dest='inputFile', help='Input file containing the initial matrix data')
parser.add_argument('-o', type=argparse.FileType('w+'), required=True, dest='outputFile', help='Output file containing binary version of the original matrix')
parser.add_argument('-t', type=argparse.FileType('w+'), required=True, dest='transposedFile', help='Output file containing binary data of the transposed matrix')
parser.add_argument('-n', type=int, choices=[1,2,4,8], required=True, dest='numThreads', help='The number of threads to use')
parser.add_argument('-s', action='store_true', dest='sparse', help='Flag that tells if the matrix is sparse  (optional-application should default to full matrix processing if not provided)')
parser.add_argument('-v', type=int, default=0, required=False, dest='value', help='The default value for the missing elements of the matrix (optional-should default to 0 if not provided)')

args = parser.parse_args()

if args.value > 127 or args.value < -127:
    exit('[FAILURE] -v (value) parameter must be between (-127, 127)')
    
print 'Input source file:', args.inputFile.name
print 'Output file destination:', args.outputFile.name
print 'Transposed matrix file destination:', args.transposedFile.name
print 'Number of threads:', args.numThreads
print 'Sparse matrix?', args.sparse
print 'Sparse value:', args.value, '\n'

###################################################
# Begin building data structures from input
###################################################

"""
Your input file is structured like so:
    row col value
    0   0    2
    1   0   -1
    0   1    0

2 Possible Solutions:
    - Build a transposed matrix of the correct size and 
        populate it entirely with the sparse value
    - Divide the array into equally sized quadrants
    - Assign each quadrant to a thread
    - When a thread is assigned a quadrant it does the following:
        - Checks to see if the quadrant matrix has any non-sparse values (by adding all vals)
        - If there are non-sparse values, the matrix is transposed and put into the dest matrix

    ORRRRRRRR
    
    - Build a transposed matrix of the correct size and 
        populate it entirely with the sparse value
    - threads some how are at an index of the matrix and they
        keep on expanding their range (always maintaining a rectangular quadrant) 
        until they can't if the range is large enough, the thread marks the area as no-transpose

"""

print 'Loading Matrix...'
startTime = time.time()
with args.inputFile as inFile:
    # read the last line of the input file to determine the size of the input matrix
    inFile.seek(-64, 2)
    last = inFile.readlines()[-1].decode()
    last = str(last).strip().split('\t')
    size = [int(last[0])+1, int(last[1])+1]
    
    # allocate memory for the matrix
    #inMat = []
    #for x in range(size[0]):
    #    l = list()
    #    for y in range(size[1]):
    #        l.append(int())
    #    inMat.append(l)
    inMat = np.empty(size, np.dtype('i1'))
    print 'matrix size is', len(inMat), 'x', len(inMat[0])
    
    # initialize the matrix according to the retrieved size
    inFile.seek(0)
    for line in inFile.readlines():
        line = line.strip().split('\t')
        #print 'assigning', line[2], 'to', line[0],'x',line[1]
        inMat[int(line[0])][int(line[1])] = int(line[2])
       
endTime = time.time()
print 'Took', (endTime-startTime), 'seconds to load matrix'




























