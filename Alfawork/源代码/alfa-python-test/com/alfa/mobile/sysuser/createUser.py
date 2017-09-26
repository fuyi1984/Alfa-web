# -*- coding: UTF-8 -*-
import urllib
import urllib2
import json


def http_post():
    url = 'http://118.178.18.0:8082/alfa-mobile-ws/rest/user/createUser'
    values = {"mobile": "18580043708", "captcha": "997460","orgname":"阿尔法石油化工"}
    headers = {'content-type': 'application/json'}

    jdata = json.dumps(values)

    req = urllib2.Request(url, jdata, headers)
    response = urllib2.urlopen(req)
    return response.read()


if __name__ == '__main__':
    resp = http_post()
    print resp