# -*- coding: UTF-8 -*-

def func():

    x = 1
    y = 2
    m = 3
    n = 4

    def sum():
        return x+y

    def sub():
        return m-n

    return sum() * sub()


print func()