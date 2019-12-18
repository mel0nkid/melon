PROGRAM_NAME="melon"

jar(){
  scp melon-start/target/$PROGRAM_NAME.jar admin@39.106.115.98:~/workspace
}

shell(){
  scp /Users/melonkid/work/workspace/github/melon/bootstrap.sh admin@39.106.115.98:~/workspace
}

db(){
    scp /Users/melonkid/datas/melon.db admin@39.106.115.98:~/datas
}

user_exists(){
  if id -u $1 >/dev/null 2>&1; then
    echo "1"
  else
    echo "0"
  fi
}

case $1 in
        jar)
          jar
        ;;

        shell)
          shell
        ;;

        db)
          db
        ;;
        *)
      echo -e $USAGE
        ;;
esac
exit 0