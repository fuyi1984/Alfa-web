# -*- coding: UTF-8 -*-
import shutil

#shutil.copyfile("hello.txt","hello3.txt")
#shutil.move("hello.txt","../")
#shutil.move("hello3.txt","../hello3.txt")

class Fruit:
    price=0

    def __init__(self):
        self.color="red"

    def getColor(self):
        print self.color

if __name__=="__main__":
    print Fruit.price
    apple=Fruit()
    print apple.color
    Fruit.price=Fruit.price+10
    print "apple's price:"+str(apple.price)

banana=Fruit()
print "banana's price:"+str(banana.price)