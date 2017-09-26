#!/usr/bin/env python
# -*- coding: UTF-8 -*-
class Fruit:
    price = 0

    def __init__(self):
        self.__color = "red"

    def getColor(self):
        print self.__color

    @staticmethod
    def getPrice():
        print Fruit.price

    def __getPrice():
        Fruit.price = Fruit.price + 10
        print Fruit.price

    count = staticmethod(__getPrice)


if __name__ == "__main__":
    apple = Fruit()
    apple.getColor()
    Fruit.count()
    banana = Fruit()
    Fruit.count()
    Fruit.getPrice()
