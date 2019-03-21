#!/bin/bash

ZIPDATE=$(date +%F -d "-2 day");
DELDATE=$(date +%F -d "-15 day");

find -L /data/app/sqe_sharingan/logs -maxdepth 1 -type f -name "*${ZIPDATE}*" -a ! -name "*.gz" -exec gzip {} \;
find -L /data/app/sqe_sharingan/logs -maxdepth 1 -type f -name "*${DELDATE}*" -a -name "*.gz" -exec rm -f {} \;
