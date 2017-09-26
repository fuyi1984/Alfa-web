#!/usr/bin/env python
# -*- coding: UTF-8 -*-
class Fruit:
    def __init__(self):
        self.__color = "red"


class Apple(Fruit):
    pass


if __name__ == "__main__":
    fruit = Fruit()
    apple = Apple()
    print Apple.__bases__
    print apple.__dict__
    print apple.__module__

print apple.__doc__
