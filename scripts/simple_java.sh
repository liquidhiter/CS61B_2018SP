# What this bash script does:
# compile the java class and run the java code
# Usage:
# 1> source simple_java.sh
# 2> java_ HelloWorld

#########################################################################
# File Name:    simple_java.sh
# Author:       Maxin
# mail:         lyingflatlearner@gmail.com
# Created Time: Sat Nov 11 15:00:54 2023
#########################################################################
#!/bin/usr/env bash

function java_()
{
    local current_dir=$(pwd)

    echo "current  directory = $current_dir"
    java_class=$1
    
    java_class_path=$current_dir/$java_class.java
    echo "compile the java class = $java_class_path"
    
    javac $java_class_path && java $java_class
}