#!/bin/sh

runtest() {
    echo ==============================================    
    echo "test comment: $1"
    echo "test string: $2"    
    echo "output:\n"    
    java -jar target/parsecron-1.0-SNAPSHOT-jar-with-dependencies.jar "$2"
    echo
}

runtest "syntax error in sequence description for minutes" "3a 1 2 4 * /bin/command arg1 arg2"

runtest "missing command" "*/15 0 1,15 * 1-5"

runtest "to many minutes in hour" "60 1 2 3 4 /bin/command arg1 arg2"

runtest "to many hours in a day" "0 24 2 3 4 /bin/command arg1 arg2"

