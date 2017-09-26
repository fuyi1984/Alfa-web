#!/usr/bin/env python
# -*- coding: UTF-8 -*-
def func(x):
    if x>0:
        return x

print filter(func,range(-9,10))