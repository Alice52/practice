#!/bin/bash

cd ../
echo $PWD
read -p "please enter now project name: " PROJECT_OLD_NAME
read -p "please enter new project name: " PROJECT_NEW_NAME

function readFile() {
    for dirlist in $(ls); do
        if test -d ${dirlist}; then
            newfile=$(echo $dirlist | sed -r "s/${PROJECT_OLD_NAME}/${PROJECT_NEW_NAME}/g")
            if [ $dirlist != $newfile ]; then
                echo "change $dirlist to $newfile"
                mv $dirlist $newfile
            fi
            cd ${newfile}
            readFile ${newfile}
            cd ..
        else
            newfile=$(echo $dirlist | sed -r "s/${PROJECT_OLD_NAME}/${PROJECT_NEW_NAME}/g")
            if [ $dirlist != $newfile ]; then
                echo "change $dirlist to $newfile"
                mv $dirlist $newfile
            fi
        fi
    done
}

function setDir() {
    if test -d $1; then
        cd $1
        readFile
        cd ..
    else
        cd $(dirname $1)
        local name=$(basename $1)
        newfile=$(echo $name | sed -r "s/${PROJECT_OLD_NAME}/${PROJECT_NEW_NAME}/g")
        if [ $name != $newfile ]; then
            echo "change $name to $newfile"
            mv $name $newfile
        fi
    fi
}

local param=$1
if [ -z "$1" ]; then
    param="./"
    echo "empty string: $param"
else
    param=$1
fi

if test -d $param; then
    setDir $param
elif test -f $param; then
    setDir $param
    exit 1
else
    echo "Neither folder nor file!!!"
    exit 1
fi
