package com.ning;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
/**
 *CREATE TABLE `user` (
 *   `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
 *   `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
 *   `age` int DEFAULT NULL COMMENT '年龄',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
 *
 * @author ning
 * @date   2022/12/15
 **/
public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        /******************************分割线******************************/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
        // 获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.selectById(1L).getName());
        sqlSession.commit();
        sqlSession.close();
        // 关闭一个sqlsession（查询过得数据会保存在二级缓存中），再开启新的sqlsession查询二级缓存的内容
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper mapper1 = sqlSession2.getMapper(UserMapper.class);
        System.out.println(mapper1.selectById(1L).getName());
    }
}
