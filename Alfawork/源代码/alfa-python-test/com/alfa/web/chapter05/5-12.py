# -*- coding: UTF-8 -*-

str = "version"
num = 1.0
format = "%s" % str
print format

format = "%s %d" % (str, num)
print format
print "%f" % 1.25
print "%.1f" % 1.25
print "%.2f" % 1.254

word = "version3.0"
print word.center(20, "*");
print word.ljust(0)
print word.rjust(20)
print "% 30s" % word

path = "hello\twordl\n"
print path

print len(path)
path = r"hello\tworld\n"
print path
print len(path)
