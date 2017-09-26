# -*- coding: UTF-8 -*-
import urllib
import urllib2
import json


def http_post():
    mobiletoken="08e90942aa90452d932cdc9ea4ee4934"
    url = 'http://localhost:8080/alfa-mobile-ws/rest/order/findlist?mobiletoken='+mobiletoken
    values = 'filterscount=0&groupscount=0&pagenum=0&pagesize=10&recordstartindex=0&recordendindex=10&roleId=10&phone=13062317530&iphone=13062317530'
    headers = {'content-type': 'application/json'}

    jdata = json.dumps(values)

    req = urllib2.Request(url, values, headers)
    response = urllib2.urlopen(req)

    return response.read()


if __name__ == '__main__':
    resp = http_post()
    print resp
