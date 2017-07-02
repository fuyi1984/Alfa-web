# -*- coding: UTF-8 -*-
import urllib
import urllib2
import json


def http_post():
    url = 'http://localhost:8080/alfa-mobile-ws/rest/user/loginforweixin'
    #values = {"mobile": "13062317530", "captcha": "751047"}
    values = {"mobile": "13220356963", "captcha": "710079","openid":"123"}
    headers = {'content-type': 'application/json'}

    jdata = json.dumps(values)

    req = urllib2.Request(url, jdata, headers)
    response = urllib2.urlopen(req)
    return response.read()


if __name__ == '__main__':
    resp = http_post()
    print resp