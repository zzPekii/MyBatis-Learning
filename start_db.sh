#!/bin/bash

# 1. 定义变量，方便以后修改
CONTAINER_NAME="my_mysql"
MYSQL_PASSWORD="Zzyisgenius"
SQL_FILE="db1.sql"

echo "🚀 正在清理旧的容器..."
docker rm -f $CONTAINER_NAME 2>/dev/null

echo "🐳 正在启动 MySQL 容器 (M1 Optimized)..."
docker run --name $CONTAINER_NAME -e MYSQL_ROOT_PASSWORD=$MYSQL_PASSWORD -p 3306:3306 -d mysql:latest

echo "⏳ 等待数据库初始化 (15秒)..."
sleep 15

echo "数据 正在导入 $SQL_FILE..."
docker exec -i $CONTAINER_NAME mysql -u root -p$MYSQL_PASSWORD < $SQL_FILE

echo "✅ 全部搞定！你的数据库已就绪。"
echo "-----------------------------------"
echo "连接信息："
echo "Host: localhost"
echo "Port: 3306"
echo "User: root"
echo "Password: $MYSQL_PASSWORD"