package com.ohgiraffers.section02.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Application {


    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //Resources 파일을 불러오기위해 마이바티스에서 제공하는 클래스
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(false);
                    //sqlsession은 connection 객체랑 같은거임


            Date date = sqlSession.selectOne("mapper.selectSysdate");

            System.out.println("date = " + date);
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
