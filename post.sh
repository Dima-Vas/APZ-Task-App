#!/bin/bash

for ((i=1; i<=10; i++))
do
    curl -d "log=This is test log #$i" http://localhost:8081/log/
done

for ((i=1; i<=10; i++))
do
    curl -d "message=This is message #$i" http://localhost:8081/message/
done
