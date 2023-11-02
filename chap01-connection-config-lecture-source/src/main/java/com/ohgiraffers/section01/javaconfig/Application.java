package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Date;

public class Application {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/menu";
    private static String USER = "ohgiraffers";
    private static String PASSWORD = "ohgiraffes";

    public static void main(String[] args) {
        /*DB 접속에 관한 환경 설정
         *
         * JdbcTransactionFactory : 수동 커밋
         * ManagerTransactionFactroy : 자동 커밋
         *
         * PooledDataSource : ConnectionPool 사용
         * UnpooledDataSource : ConnectionPool 미사용
         *Pool은 최초 커넥션 객체를 생성할 때 Pool이라는 영역에 저장해두고 객체 생성을 사용할때
         * 재사용할거라고 하는 내용 Unpooled는 커넥션 개체를 별도로 저장하지 않고 사용할때마다 호출할거라는 뜻*/

        Environment environment = new Environment
                ("dev",
                /* 환경 정보 이름, 실제 DB와 테스트용 DB(개발DB)가 따로 있음 각각의 DB는 설정값이 다름, 대표적으로 url, 아이디, 비밀번호
                여기에 dev는 DB의 명칭같은거임
                *  */
                        new JdbcTransactionFactory() //트랜젝션 매니저 종류 결정(JDBC or MANAGER)
                        , new PooledDataSource(DRIVER, URL, USER, PASSWORD) //ConnectionPool 사용 유무(Pooled or Unpooled)
                        //위 내용은 JDBCTemplate의 Connection메소드와 같은 내용임

                );

        //생성한 환경 설정 정보로 Mybatis 설정 객체 생성
        Configuration configuration = new Configuration(environment); //설정정보의 기본값에 대한 내용 추가함
        //import org.apache.ibatis.session.Configuration; 이거로 넣어야함!

        //뭘 사용할지 명시해줘야함, 설정 객체에 매퍼 등록
        configuration.addMapper(Mapper.class);//class.forname랑 비슷한거임 Mapper.class 타입으로 되어 있는 객체를 자동으로 주입해서 객체로 생성
        //여기까지가 설정에 대한 정보를 담고 있는 정보를 만든거임

        /*SqlSessionFactory : SqlSession 객체를 생성하기 위한 팩토리 역할의 인터페이스, 사용할 수 있는 연결객체를 만들어주는 용도의 공장
         * SqlSessionFactoryBuilder : SqlSessionFactory 인터페이스 타입의 하위 구현 객체를 생성하기 위한 빌드 역할
         * build() : 설정에 대한 정보를 담고 있는 Configuration 타입의 객체 혹은 외부 설정 파일가 연결된 Stream 을 매겨변수로
         * 전달하면 SqlSessionFactory 인터페이스 타입의 객체를 반환하는 메소드
         */

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);//내부에 build라는 메소드를 통해서 객체 생성할 수 있게 만들어짐
        //configuration은 주문서와 같은거임, SqlSessionFactory는 연결 객체를 사용할 수 있는 연결 객체를 만들어주는 용도의 공장
        //SqlSessionFactory에 대한 내용들을 구현하려면 SqlSessionFactoryBuilder이 필요하고
        //build로 필요한 메소드 하나씩 꺼내서 사용할 수 있게 만들어주는 메소드, 설정 값을 가지고  객체를 생성하거나 외부 파일의 내용들을 넣어서   SqlSession에 대한 내용들의 타입을 반환


        /*openSession() : sqlSession 인터페이스 타입의 객체를 반환하는 메소드로 boolean 타입을 인자로 전달
         * false : Connection 인터페이스 타입 객체로 DML 수행 후 auto commit에 대한 옵션을 false로 지정(권장)
         * true : Connection 인터페이스 타입 객체로 DML 수행 후 auto commit에 대한 옵션을 true로 지정*/
        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        //여기까지가 사용하기 위한 설정 정보 세팅 끝난 상태
        //----------------------------------------------------------------------------

        //getmapper() : Configuration 에 등록된 매퍼를 동일 타입에 대해 반환하는 메소드
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        //내가 사용하려는 class 타입 기입

        Date date = mapper.selectSysdate();
        System.out.println(date);

        sqlSession.close();
        //연결 닫기



    }
}