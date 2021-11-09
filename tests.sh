#!/bin/sh

runtest() {
    echo ===    
    echo "test comment: $1"
    echo "test string: $2"    
    java ParseCron "$1"
}

runtest "aritchetical sequence + comma separated values + range + star" "*/15 0 1,15 * 1-5 /usr/bin/find"

runtest "multi parameter command" "*/15 0 1,15 * 1-5 /bin/command arg1 arg2"
