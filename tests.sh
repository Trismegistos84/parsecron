#!/bin/sh

runtest() {
    echo ===    
    echo "test comment: $1"
    echo "test string: $2"    
    echo "output:\n"    
    java -jar target/parsecron-1.0-SNAPSHOT-jar-with-dependencies.jar "$2"
    echo
}

runtest "arithmetical sequence + comma separated values + range + star" "*/15 0 1,15 * 1-5 /usr/bin/find"

runtest "multiple numbers in sequence" "1 2 13,14,16 3 4 /usr/bin/find"

runtest "multi parameter command" "3 1 2 4 * /bin/command arg1 arg2"
