# -*- coding: UTF-8 -*-
import csv

exampleFile=open('example.csv')

exampleReader=csv.reader(exampleFile)

# exampleData=list(exampleReader)
#
# print exampleData
#
# print exampleData[0][0]
# print exampleData[0][1]
# print exampleData[0][2]
#
# print exampleData[1][1]
# print exampleData[6][1]

for row in exampleReader:
    print 'Row #'+str(exampleReader.line_num)+' '+str(row)


