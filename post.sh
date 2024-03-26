#!/bin/bash

for ((i=1; i<=10; i++))
do
    curl -d "log=This is test log #$i" http://localhost:8081/
done
