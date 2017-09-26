# -*- coding: UTF-8 -*-
import os
li=os.listdir(".")
print li

if "hello.txt" in li:
    os.rename("hello.txt","hi.txt")
elif "hi.txt" in li:
    os.rename("hi.txt","hello.txt")