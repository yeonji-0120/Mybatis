package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {
    /*SqlSessionFactory를 싱글톤으로 생성
    *SqlSessionFactory는 애플리케이션을 실행하는 동안 존재해야 하며,
    * 애플리케이션이 실행되는 동안 여러 차례 SqlSessionFactory를 다시 빌드하지 않는 것이 가장 좋은 형태이다
    * 애플리케이션 스코프로 관리하게 위한 가장 간당한 방법은 싱클톤 패턴을 이용하는것
    *
    */

    private static SqlSessionFactory sqlSessionFactory;
    //이 값은 처음 생생했을때 null인 상태일거임
    public static SqlSession getSqlSession(){
        /*SqlSessionFactoryBuilder를 SqlSession을 생성한 후에도 유지할 필요는 없다. 따라서 메소드 스코프로 만든다.
        * 여러 개의 SqlSessionFactory를 빌드하기 위해 재사용할 수도 있지만 유지하지 않는 것이 가장 좋다.
         */
        if(sqlSessionFactory == null){//처음에 null값이 들어있을테니 null값이면 sqlSessionFactory 값 넣고 아니면 더 안들어짐
            String resource = "mybatis-config.xml";
            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
            //여기까지가 sqlSessionFactory 만든거임
        }
        /*SqlSession은 Thread에 안전하지 않고, 공유되지 않아야 하므로 요청시 마다 생성
        *요청 시 생성하고 요청이 완료되면 close하는 HTTP요청과 유사한 스코프에 두는 것이 가장 올바른 방법
         */
        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        System.out.println("sqlSessionFactory의 hashCode() : " + sqlSessionFactory.hashCode());
        //Factory는 싱글톤이기때문에 hashCode 조회하면 같은 값이 나옴
        System.out.println("sqlSession의 hashCode() : " + sqlSession.hashCode());
        //그냥 세션은 싱글톤이 아니라서 다른값이 나옴
        return sqlSession;
    }
}
