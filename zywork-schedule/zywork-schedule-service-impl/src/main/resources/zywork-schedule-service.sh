#!/bin/sh
# chkconfig: 2345 20 80
#

##created by Wang Zhenyu

## Notice: please set JAVA_HOME before you use this script!!!

APP_NAME=zywork-schedule-service

SERVICE_DIR=.
SERVICE_NAME=$APP_NAME
JAR_NAME=$SERVICE_NAME\.jar
PID_FILE=$SERVICE_NAME\.pid

cd $SERVICE_DIR

if [ "$JAVA_HOME" == "" ]; then
  echo "JAVA_HOME is not set! Please add it..."
else
  echo "use JAVA_HOME:$JAVA_HOME"
  case "$1" in

      start)
          nohup $JAVA_HOME/java -Xms256m -Xmx512m -Ddubbo.spring.config=classpath*:/config/*.xml -jar $JAR_NAME > /dev/null 2>&1 &
          echo "the pid is:$!"
          echo $! > $SERVICE_DIR/$PID_FILE
          echo "start $SERVICE_NAME"
          ;;

      stop)
          kill `cat $SERVICE_DIR/$PID_FILE`
          rm -rf $SERVICE_DIR/$PID_FILE
          echo "stop $SERVICE_NAME"

          sleep 5

          PID=`ps -ef | grep -w "$SERVICE_NAME" | grep 'java' | grep -v "grep" | awk '{print $2}'`
          if [ "$PID" == "" ]; then
              echo "$SERVICE_NAME process not exists or stop successfully"
          else
              echo "$SERVICE_NAME process pid is:$PID"
              echo "begin kill $SERVICE_NAME process forcelly, pid is:$PID"
              kill -9 $PID
          fi
          ;;

      restart)
          $0 stop
          sleep 2
          $0 start
          echo "restarted $SERVICE_NAME"
          ;;

      *)
          $0 stop
          sleep 2
          $0 start
          ;;

  esac
fi
exit 0
