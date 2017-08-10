#!/usr/bin/env python
# -*- coding: UTF-8 -*-
fname = raw_input('Enter file name:')

try:
    fobj = open(fname, 'r')
except IOError, e:
    print"*** file open error:", e
else:
    for eachLine in fobj:
        print eachLine,
    fobj.close()
