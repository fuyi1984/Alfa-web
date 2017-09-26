# -*- coding: UTF-8 -*-
import urllib
import urllib2
import json


def http_post():
    url = 'http://118.178.18.0:8082/alfa-mobile-ws/rest/user/getCaptcha/15320295813'
    #url = 'http://localhost:8080/alfa-mobile-ws/rest/user/getCaptcha/18580043708'
    req = urllib2.Request(url)
    response = urllib2.urlopen(req)
    return response.read()


if __name__ == '__main__':
    resp = http_post()
    print resp
