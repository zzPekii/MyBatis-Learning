#!/bin/bash

CONTAINER_NAME="my_mysql"
MYSQL_PASSWORD="1234"

if [ $# -eq 0 ]; then
    echo "❌ 请输入要检查的数据库名称或文件名！"
    exit 1
fi

for INPUT in "$@"
do
    # 【核心修复】去掉可能的 .sql 后缀，得到真正的库名
    DB_NAME="${INPUT%.*}"

    echo -e "\n🔍 正在检查数据库: $DB_NAME"
    echo "-------------------------------------------"

    # 检查库是否存在
    TABLES=$(docker exec -i $CONTAINER_NAME mysql -u root -p$MYSQL_PASSWORD -N -e "SHOW TABLES IN \`$DB_NAME\`;" 2>/dev/null)

    if [ -z "$TABLES" ]; then
        echo "⚠️  警告: 数据库 '$DB_NAME' 为空或不存在。"
        continue
    fi

    for TABLE in $TABLES
    do
        echo -e "\n📋 表名: $TABLE (前 3 条数据)"
        docker exec -i $CONTAINER_NAME mysql -u root -p$MYSQL_PASSWORD -t -e "SELECT * FROM \`$DB_NAME\`.\`$TABLE\` LIMIT 3;"
    done
done

echo -e "\n✅ 检查完毕！"