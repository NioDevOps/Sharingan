#!/bin/bash
BASE_DIR=$(cd "$(dirname "$0")/..";pwd)
cd $BASE_DIR

# zip log cron
if [[ -f $BASE_DIR/bin/zip_log_daily.sh ]]; then
  echo "6 2 * * * root sh $BASE_DIR/bin/zip_log_daily.sh 1>>/tmp/zip_log_daily.log" > /etc/cron.d/sharingan
fi


# start service
if [[ -f sharingan.pid ]]; then
    kill -9 `cat sharingan.pid`
    rm sharingan.pid
fi

nohup java -jar -Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector  -Dspring.config.location=$BASE_DIR/conf/  $BASE_DIR/bin/Sharingan.jar >/dev/null 2>&1 &

echo $! > sharingan.pid

