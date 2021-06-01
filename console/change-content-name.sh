#!/bin/bash

cd ..
echo $PWD

read -p "please enter now project name: " PROJECT_OLD_NAME
read -p "please enter new project name: " PROJECT_NEW_NAME

sed -i "s/${PROJECT_OLD_NAME}/${PROJECT_NEW_NAME}/g" $(grep ${PROJECT_OLD_NAME} -rl .)

# 例如: 替换 `/home` 下所有文件中的 `www.bcak.com.cn` 为 `bcak.com.cn`
# sed -i "s/www.bcak.com.cn/bcak.com.cn/g" `grep www.bcak.com.cn -rl /home`
