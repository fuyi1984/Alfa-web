#!/usr/bin/env python
# -*- coding: UTF-8 -*-
import urllib
import urllib2
import json


def http_post():
    url = 'http://118.178.18.0:8082/alfa-mobile-ws/rest/ordercomment/batchinsertordercomment?mobiletoken=2a9fe020abe94657802b3a524e1b75f2'
    values = [{"one": "7","two":"4","three":"1","four":"4","mobile":"18580043708"}]
    headers = {'content-type': 'application/json'}

    jdata = json.dumps(values)

    req = urllib2.Request(url, jdata, headers)
    response = urllib2.urlopen(req)
    return response.read()


if __name__ == '__main__':
    resp = http_post()
    print resp
