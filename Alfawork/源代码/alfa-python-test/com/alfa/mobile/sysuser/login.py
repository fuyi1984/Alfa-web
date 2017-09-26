# -*- coding: UTF-8 -*-
import urllib
import urllib2
import json


def http_post():
    url = 'http://118.178.18.0:8082/alfa-mobile-ws/rest/user/login'
    #values = {"mobile": "13062317530", "captcha": "751047"}
    values = {"mobile": "18580043708", "captcha": "202786"}
    headers = {'content-type': 'application/json'}

    jdata = json.dumps(values)

    req = urllib2.Request(url, jdata, headers)
    response = urllib2.urlopen(req)
    return response.read()


if __name__ == '__main__':
    resp = http_post()
    print resp