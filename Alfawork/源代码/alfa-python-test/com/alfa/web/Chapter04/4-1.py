# -*- coding: UTF-8 -*-
import os
# 创建文件夹
#os.makedirs('c:\\deliciout')

# 获取当前工作目录
print os.getcwd()

#改变当前工作目录
os.chdir('c:\\')

print os.getcwd()

print os.path.abspath('.')
print os.path.abspath('.\\Scripts')
print os.path.isabs('.')
print os.path.isabs(os.path.abspath('.'))
