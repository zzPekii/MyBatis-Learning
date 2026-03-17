CREATE DATABASE IF NOT EXISTS mybatis_test;
USE mybatis_test;

-- DROP TABLE if exist tb_brand;

CREATE TABLE tb_brand (
    id int PRIMARY KEY AUTO_INCREMENT,
    brand_name varchar(200),
    company_name varchar(200),
    ordered int,
    description varchar(1000),
    status int
);

INSERT INTO tb_brand (brand_name, company_name, ordered, description, status) VALUES
('华为', '华为技术有限公司', 100, '全球领先的ICT基础设施和智能终端提供商', 1),
('小米', '小米科技有限责任公司', 90, '专注于智能硬件和电子产品研发的移动互联网公司', 1),
('格力', '珠海格力电器股份有限公司', 80, '好空调，格力造', 1),
('顺丰', '顺丰控股股份有限公司', 70, '国内领先的快递物流综合服务商', 1),
('比亚迪', '比亚迪股份有限公司', 95, '全球领先的新能源汽车制造商', 1),
('美团', '北京三快在线科技有限公司', 60, '生活服务电子商务平台', 0);

-- SELECT * FROM tb_brand;