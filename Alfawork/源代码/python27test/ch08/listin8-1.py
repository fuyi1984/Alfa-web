#!/usr/bin/env python
# -*- coding: UTF-8 -*-
class Fruit:
    price = 0

    def __init__(self):
        self.__color = "blue"
        self.color = "red"
        zone = "China"


if __name__ == "__main__":
    print Fruit.price
    apple = Fruit()
    print apple.color
    Fruit.price = Fruit.price + 10
    print "apple's price:" + str(apple.price)


banana = Fruit()
print "banana's price:" + str(banana.price)
