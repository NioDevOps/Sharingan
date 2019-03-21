#!/bin/bash

BASE_DIR=$(cd "$(dirname "$0")/..";pwd)
cd $BASE_DIR


if [[ -f sharingan.pid ]]; then
    kill -9 `cat sharingan.pid`
    rm sharingan.pid
fi
