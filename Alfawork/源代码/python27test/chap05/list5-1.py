#!/usr/bin/env python
# -*- coding: UTF-8 -*-
def sum(x=1, y=2):
    return x + y


def sums(x=1, y=2, z=3):
    return x + y + z


print apply(sum, (1, 3))
print apply(sums, (1, 3, 4))
print reduce(sum,range(0,10))
print reduce(sum,range(0,10),10)
print reduce(sum,range(0,0),10)

def power(x):
    return x**x

print map(power,range(1,5))
def power2(x,y):
    return x**y

print map(power2,range(1,5),range(5,1,-1))

str1="version"
num=1.0
format="%s" % str1
print format

format="%s %d" % (str1,num)
print format