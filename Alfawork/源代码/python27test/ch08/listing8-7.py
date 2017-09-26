#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import gc


class Fruit:
    def __init__(self, color):
        self.__color = color
        print self.__color

    def __int__(self, name, color):
        self.__name = name
        self.__color = color

    def getColor(self):
        print self.__color

    def setColor(self, color):
        self.__color = color
        print self.__color

    def __del__(self):
        self.__color = ""
        print "free..."

    def grow(self):
        print "grow..."


if __name__ == "__main__":
    color = "red"
    fruit = Fruit(color)
    fruit.grow()
    fruit.getColor()
    fruit.setColor("blue")
