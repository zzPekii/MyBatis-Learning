#!/bin/bash

# 1. 配置信息
CONTAINER_NAME="my_mysql"
MYSQL_PASSWORD="1234"
MYSQL_PORT=3306

# 检查是否输入了 SQL 文件
if [ $# -eq 0 ]; then
    echo "❌ 错误: 请至少输入一个 SQL 文件名！"
    echo "用法: $0 db1.sql [db2.sql ...]"
    exit 1
fi

echo "🚀 正在强制清理旧容器..."
docker rm -f $CONTAINER_NAME 2>/dev/null

echo "🐳 正在启动 MySQL 容器 (UTF-8 强化版)..."
# 【关键改动】增加 --character-set-server 配置
docker run --name $CONTAINER_NAME \
    -e MYSQL_ROOT_PASSWORD=$MYSQL_PASSWORD \
    -p $MYSQL_PORT:3306 \
    -d mysql:latest \
    --character-set-server=utf8mb4 \
    --collation-server=utf8mb4_unicode_ci

echo "⏳ 等待数据库初始化 (20秒，给足启动时间)..."
sleep 20

# 2. 核心逻辑：循环处理传入的文件
for FILE_NAME in "$@"
do
    DB_NAME="${FILE_NAME%.*}"

    if [ -f "$FILE_NAME" ]; then
        echo "📂 正在处理: $FILE_NAME"
        
        # 创建数据库时也指定编码
        docker exec -i $CONTAINER_NAME mysql -u root -p$MYSQL_PASSWORD \
            -e "CREATE DATABASE IF NOT EXISTS \`$DB_NAME\` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
        
        # 【关键改动】导入时强制指定 --default-character-set=utf8mb4
        echo "📥 正在导入数据到库 [$DB_NAME]..."
        docker exec -i $CONTAINER_NAME mysql -u root -p$MYSQL_PASSWORD \
            --default-character-set=utf8mb4 "$DB_NAME" < "$FILE_NAME"
        
        if [ $? -eq 0 ]; then
            echo "✅ $DB_NAME 导入成功！"
        else
            echo "❌ $DB_NAME 导入失败。"
        fi
    else
        echo "⚠️  跳过: 未找到文件 $FILE_NAME"
    fi
done

echo "-----------------------------------"
echo "🏁 全部任务处理完毕！"
echo "连接端口: $MYSQL_PORT | 密码: $MYSQL_PASSWORD"