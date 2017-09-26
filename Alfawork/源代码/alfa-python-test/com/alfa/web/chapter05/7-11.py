# -*- coding: UTF-8 -*-
import os

files = os.listdir(".")
for filename in files:
    pos = filename.find(".")
    if filename[pos + 1:] == "txt":
        newname = filename[:pos + 1] + "tx"
        os.rename(filename, newname)
