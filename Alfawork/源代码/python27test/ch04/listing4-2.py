#!/usr/bin/env python
# -*- coding: UTF-8 -*-

labels = {
    'phone': 'phone number',
    'addr': 'address'
}

name = raw_input('Name:')

request = raw_input('Phone number (p) or address (a)?')

key = request

if request == 'p': key = 'phone'
if request == 'a': key = 'addr'


