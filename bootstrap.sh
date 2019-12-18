#kconfig: - 95 15
# description: 抓取程序  start/stop/status script

#JDk 所在的路径
export JAVA_HOME=/usr/local/jdk1.8.0_151/

#Add Java binary files to PATH
export PATH=$JAVA_HOME/bin:$PATH

#需要启动的Java主程序（main方法类）
PROGRAM_NAME="melon"

#USAGE is the message if this script is called without any options
USAGE="Usage: $0 {start,stop,status,restart}"

#SHUTDOWN_WAIT is wait time in seconds for java proccess to stop
SHUTDOWN_WAIT=20
#获取执行id
tomcat_pid() {
        echo `ps -ef | grep $PROGRAM_NAME | grep -v grep | tr -s " "|cut -d" " -f2  `
}

##启动
start() {
  pid=$(tomcat_pid)
  if [ -n "$pid" ];then
    echo -e "Melon is already running (pid: $pid)"
  else
    echo -e "Starting Melon"
    nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar melon.jar  > /home/admin/logs/logger.log 2>&1 &
    status
  fi
  return 0
}
##############
#状态
#######
status(){
  pid=$(tomcat_pid)
  if [ -n "$pid" ];then
    echo -e "Melon is running with pid: $pid"
  else
    echo -e "Melon  is not running"
  fi
}
##停止
stop() {
  pid=$(tomcat_pid)
  if [ -n "$pid" ];then
    echo -e "Stoping melon"
    #  shutdown.sh

    #let kwait=$SHUTDOWN_WAIT  count=0;
    #until [ `ps -p $pid | grep -c $pid` = '0' ] || [ $count -gt $kwait ]
    #do
    #  echo -n -e "waiting for processes to exit";
    #  sleep 1
    #  let count=$count+1;
    #done

    if [ $count -gt $kwait ];then
      echo -n -e "killing processes which didn't stop after $SHUTDOWN_WAIT seconds"
      kill -9 $pid
    fi
  else
    echo -e "melon  is not running"
  fi

  return 0
}

user_exists(){
  if id -u $1 >/dev/null 2>&1; then
    echo "1"
  else
    echo "0"
  fi
}

case $1 in
        start)
          start
        ;;

        stop)
          stop
        ;;

        restart)
          stop
          start
        ;;

        status)
      status
        ;;

        *)
      echo -e $USAGE
        ;;
esac
exit 0
