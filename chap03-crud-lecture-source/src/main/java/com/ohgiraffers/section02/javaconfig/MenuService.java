package com.ohgiraffers.section02.javaconfig;



import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section02.javaconfig.Template.getSqlSession;


public class MenuService {
    //service 클래스가 하는거 connection 객체 생성, 필요한 DAO 요청, commit, rollback, 연결 닫기

    private MenuMapper menuMapper;
    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getSqlSession(); //커넥션 객체 생성
        //sqlSession은 요청 단위로 생성해야 하기 떄문에 getMapper 메소드 스코프로 매번 불러와야 한다.
        menuMapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menuList = menuMapper.selectAllMenu();//
        sqlSession.close();
        return menuList;

    }

    public MenuDTO selectMenuByCode(int code) {
        //커넥션 객체 생성
        SqlSession sqlSession = getSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        //DAO에 연결객체 넘기기
        MenuDTO menu = menuMapper.selectMenuByCOde(code);//왜 sqlsession이랑 코드지?
        //뭐 할거 있으면 하는건데 못들음 9시30분 수업에서 ㄷ찾아보기
        //클로즈
        sqlSession.close();
        //리턴
        return menu;
    }

    public boolean insertMenu(MenuDTO menu){
        //int 대신 boolean 쓰고 return에 return result > 0? true:false; 넣으면 조건식에 값만 넣으면 됨 이렇게 축약도 가능
        SqlSession sqlSession = getSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result = menuMapper.insertMenu(menu);
        //insert, update, delete의 경우 성공과 실패 여부에 따라 트랜잭션 처리
        if(result > 0 ){ //insert, update, delete는 결과값을 정수 형태로 반환, 성공하면 0보다 큰 값일거임 그래서 성공하면 커밋
            sqlSession.commit();
        }else {//실패하면 롤백
            sqlSession.rollback();

        }
        sqlSession.close();
        return result > 0? true:false;
    }

    public boolean modifyMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result = menuMapper.updateMenu(menu);
        if(result > 0 ){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();
        return result > 0? true:false;


    }

    public boolean deleteMenu(int code) {
        SqlSession sqlSession = getSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result = menuMapper.deleteMenu(code);
        if(result > 0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result > 0 ? true : false;
    }
}
