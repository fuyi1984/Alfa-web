#!/usr/bin/env python
# -*- coding: UTF-8 -*-
class Fruit(object):
    def __init__(self, color="red", price=0):
        self.__color = color
        self.__price = price

    def __getattribute__(self, name):
        return object.__getattribute__(self, name)

    def __setattr__(self, name, value):
        self.__dict__[name] = value

if __name__=="__main__":
    fruit=Fruit("blue",10)
    
