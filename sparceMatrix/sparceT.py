#!/usr/bin/python

import argparse

parser = argparse.ArgumentParser(description='Transposes a sparce matrix')
parser.add_argument('-i', dest='inputFile', help='Input file containing information about the original matrix')
parser.add_argument('-o', dest='outputFile', help='Output file with the original matrix')
parser.add_argument('-t', dest='transFile', help='Output file with the transposed matrix')
parser.add_argument('-s', dest='sparse', help='Flag that tells if the matrix is sparse  (optional-application should default to full matrix processing if not provided)')
parser.add_argument('-v', dest='value', help='The default value for the missing elements of the matrix (optional-should default to 0 if not provided)')
parser.add_argument('-n', dest='threads', help='The number of threads to use')

args = parser.parse_args()



