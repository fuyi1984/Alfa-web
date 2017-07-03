# -*- coding: UTF-8 -*-
import urllib
import urllib2
import json


def http_post():
    url = 'http://localhost:8080/alfa-mobile-ws/rest/user/getCaptchaForWorker/13220356963'
    #url = 'http://localhost:8080/alfa-mobile-ws/rest/user/getCaptcha/18580043708'
    req = urllib2.Request(url)
    response = urllib2.urlopen(req)
    return response.read()


if __name__ == '__main__':
    resp = http_post()
    print resp