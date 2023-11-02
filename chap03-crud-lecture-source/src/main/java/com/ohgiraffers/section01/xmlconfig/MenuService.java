package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;

public class MenuService {
    //service 클래스가 하는거 connection 객체 생성, 필요한 DAO 요청, commit, rollback, 연결 닫기

    private final MenuDAO menuDAO;
    public MenuService(){
        menuDAO = new MenuDAO();
    }
    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getSqlSession(); //커넥션 객체 생성
        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);//DAO에 연결객체 넘기기
        sqlSession.close();
        return menuList;

    }

    public MenuDTO selectMenuByCode(int code) {
        //커넥션 객체 생성
        SqlSession sqlSession = getSqlSession();
        //DAO에 연결객체 넘기기
        MenuDTO menu = menuDAO.selectMenuByCOde(sqlSession, code);
        //뭐 할거 있으면 하는건데 못들음 9시30분 수업에서 ㄷ찾아보기
        //클로즈
        sqlSession.close();
        //리턴
        return menu;
    }

    public boolean insertMenu(MenuDTO menu){
        //int 대신 boolean 쓰고 return에 return result > 0? true:false; 넣으면 조건식에 값만 넣으면 됨 이렇게 축약도 가능
        SqlSession sqlSession = getSqlSession();
        int result = menuDAO.insertMenu(sqlSession, menu);
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
        int result = menuDAO.updateMenu(sqlSession, menu);
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
        int result = menuDAO.deleteMenu(sqlSession, code);
        if(result > 0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result > 0 ? true : false;
    }
}
