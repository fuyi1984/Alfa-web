# -*- coding: UTF-8 -*-
src=file("hello.txt","w")
li=["hello world\n","hello China\n"]
src.writelines(li)
src.close()

src=file("hello.txt","r")
dst=file("hello2.txt","w")
dst.write(src.read())
src.close()
dst.close()