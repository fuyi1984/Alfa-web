# -*- coding: UTF-8 -*-
import urllib
import urllib2
import json


def http_post():
    url = 'http://localhost:8080/alfa-ws/rest/roles/addrole'
    values = '{"role_name": "test2", "types": "test2", "typesname": "22222", "status": "2222", "statusname": "2222", "roleDesc": "2222","menuitem":"123"}'
    headers = {'content-type': 'application/json'}

    jdata = json.dumps(values)

    req = urllib2.Request(url, values, headers)
    response = urllib2.urlopen(req)

    return response.read()


if __name__ == '__main__':
    resp = http_post()
    print resp