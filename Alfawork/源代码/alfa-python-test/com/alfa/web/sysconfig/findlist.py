# -*- coding: UTF-8 -*-
import urllib
import urllib2
import json


def http_post():
    url = 'http://localhost:8080/alfa-ws/rest/Sysconfig/findlist'
    values = 'filterscount=0&groupscount=0&pagenum=0&pagesize=10&recordstartindex=0&recordendindex=10&configName=&configKey='
    headers = {'content-type': 'application/json'}

    #jdata = json.dumps(values)

    req = urllib2.Request(url, values, headers)
    response = urllib2.urlopen(req)

    return response.read()


if __name__ == '__main__':
    resp = http_post()
    print resp
