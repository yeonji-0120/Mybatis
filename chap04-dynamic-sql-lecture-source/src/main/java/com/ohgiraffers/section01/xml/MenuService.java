package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSqlSession;

public class MenuService {
    private DynamicSqlMapper mapper;

    public void selectMenuByPrice(int price) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        //DynamicSqlMapper.clss 를 호출하면 이 클래스 가 갖고 있는 내부 메타 정보가 있는데 어느 클래스의 어느 종류를 가지고 있다라고 알려주면
        // getMapper라는 메소드가 DynamicSqlMapper 인터페이스와 DynamicSqlMapper.xml에 있는 쿼리문에 대한 내용을 조합해서 결과값은 반환시켜줄 수 있게끔 객체를 만들어 놓음
        // 내부적으로 해당 내용들을 쓸 수 있게끔 만들어주느거임!

        /*조건값으로 값을 비교할 경우 기본자료형으로는 안됨
        * 1. dto를 이용하는 방법
        * 2. hashmap의 key 를 통한 방법
        */
        Map<String, Integer> map = new HashMap<>();
        map.put("price", price);
        List<MenuDTO> menuList = mapper.selectMenuByPrice(map);
                //불러오는 값이 하나일까 여러개일까 생각해서 여기서는 여러개일테니 list 생성

        if(menuList != null && menuList.size() > 0){
            //menulist가 null이 아니고 다른 값이 있을수 있으니까 더블체크로 size 까지 넣음
            for(MenuDTO menu : menuList){
                System.out.println(menu);
            }

        }else{
            System.out.println("검색 결과가 존재하지 않습니다.");


        }
        sqlSession.close();


    }

    public void searchMenu(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenu(searchCriteria);
        if(menuList != null && menuList.size() > 0){
            //menulist가 null이 아니고 다른 값이 있을수 있으니까 더블체크로 size 까지 넣음
            for(MenuDTO menu : menuList){
                System.out.println(menu);
            }

        }else{
            System.out.println("검색 결과가 존재하지 않습니다.");


        }
        sqlSession.close();
    }

    public void searchMenuBySubCategory(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuBySubCategory(searchCriteria);
        if(menuList != null && menuList.size() > 0){
            //menulist가 null이 아니고 다른 값이 있을수 있으니까 더블체크로 size 까지 넣음
            for(MenuDTO menu : menuList){
                System.out.println(menu);
            }

        }else{
            System.out.println("검색 결과가 존재하지 않습니다.");


        }
        sqlSession.close();
    }

    public void searchMenuByRandomMenuCode(List<Integer> randomMenuCodeList) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        //맵객체로 넘기면 자동으로 형태 변환해서 사용하기 때문에 맵객체로 넘기는게 편함
        Map<String, List<Integer>> criteria = new HashMap<>();
        criteria.put("randomMenuCodeList", randomMenuCodeList);

        //mapper interface에 값을 전달할 경우
//        List<MenuDTO> menuList = mapper.searchMenuByRandomMenuCode(criteria);

        //createRandomMenuCodeList에 직접 접근해서 사용하기
        List<MenuDTO> menuList = mapper.searchMenuByRandomMenuCode();

        if(menuList != null && menuList.size() > 0){
            //menulist가 null이 아니고 다른 값이 있을수 있으니까 더블체크로 size 까지 넣음
            for(MenuDTO menu : menuList){
                System.out.println(menu);
            }

        }else{
            System.out.println("검색 결과가 존재하지 않습니다.");


        }
        sqlSession.close();
    }

    public void searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);
        List<MenuDTO> menuList = mapper.searchMenuByCodeOrSearchAll(searchCriteria);
        if(menuList != null && menuList.size() > 0){
            //menulist가 null이 아니고 다른 값이 있을수 있으니까 더블체크로 size 까지 넣음
            for(MenuDTO menu : menuList){
                System.out.println(menu);
            }

        }else{
            System.out.println("검색 결과가 존재하지 않습니다.");


        }
        sqlSession.close();
    }

    public void searchMenuByNameOrCategory(Map<String, Object> criteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByNameOrCategory(criteria);
        if(menuList != null && menuList.size() > 0){
            //menulist가 null이 아니고 다른 값이 있을수 있으니까 더블체크로 size 까지 넣음
            for(MenuDTO menu : menuList){
                System.out.println(menu);
            }

        }else{
            System.out.println("검색 결과가 존재하지 않습니다.");


        }
        sqlSession.close();
    }

    public void modifyMenu(Map<String, Object> criteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        int result = mapper.modifyMenu(criteria);
        if (result > 0) {
            System.out.println("메뉴 정보 변경에 성공하였습니다.");
            sqlSession.commit();
        }else {
            System.out.println("메뉴 정보 변경에 실패하였습니다.");
            sqlSession.rollback();
        }
        sqlSession.close();
    }
}
