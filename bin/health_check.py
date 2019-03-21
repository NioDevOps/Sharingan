# !/usr/bin/env python

import json
import os
import sys
import time
import urllib2

# hard code port for all env temporarily
url_check = "http://127.0.0.1:8080/actuator/health"

for i in xrange(15):
    try:
        health_check_result = urllib2.urlopen(url_check).read()
        health = json.loads(health_check_result)
        if health["status"]["code"] == "UP":
            print "status: " + health_check_result
            os._exit(0)
    except Exception as e:
        print "status: not yet ready", e
        time.sleep(5)

health_check_result = urllib2.urlopen(url_check).read()
print "status: " + health_check_result
sys.exit(1)
