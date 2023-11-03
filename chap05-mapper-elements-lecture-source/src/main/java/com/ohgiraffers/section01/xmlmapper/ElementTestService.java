package com.ohgiraffers.section01.xmlmapper;

import com.ohgiraffers.common.CategoryAndMenuDTO;
import com.ohgiraffers.common.MenuAndCategoryDTO;
import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getSqlSession;

public class ElementTestService {

    private ElementTestMapper mapper;
    public void selectCacheTest() {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);
        //시간에 대한 내용을 정해야함

        for(int i = 0; i < 10; i++){

            Long startTime = System.currentTimeMillis();
            //딱 실행됐을때 밀리세컨드 단위로 알려주는 메소드

            List<String> nameList = mapper.selectCacheTest();
            System.out.println(nameList);

            Long lastTime = System.currentTimeMillis();
            //여기 왔을때의 시점을 다시 구함

            Long interval = lastTime - startTime;
            //마지막 시점 시간 - 처음 시점 시간

            System.out.println("수행 시간 : " + interval + "(ms)");
        }
    }

    public void selectResultMapTest() {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectResultMapTest();

        for(MenuDTO menu : menuList) {
            System.out.println(menu);
        }

        sqlSession.close();
    }

    public void selectResultMapConstructorTest() {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectResultMapConstructorTest();
        for(MenuDTO menu : menuList) {
            System.out.println(menu);
        }

        sqlSession.close();
    }

    public void selectResultMapAssociationTest() {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuAndCategoryDTO> menuList = mapper.selectResultMapAssociationTest();

        for(MenuAndCategoryDTO menu : menuList) {
            System.out.println(menu);
        }

        sqlSession.close();
    }

    public void selectResultMapCollectionTest() {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<CategoryAndMenuDTO> categoryList = mapper.selectResultMapCollectionTest();

        for(CategoryAndMenuDTO category : categoryList){
            System.out.println(category);
        }

        sqlSession.close();
    }

    public void selectSqlTest() {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectSqlTest();
        for(MenuDTO menu : menuList){
            System.out.println(menu);
        }

        sqlSession.close();
    }

    public void insertMenuTest(MenuDTO menu) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        int result = mapper.insertMenuTest(menu);

        if(result > 0){
            System.out.println("메뉴 등록 성공!");
            sqlSession.commit();
        } else {
            System.out.println("메뉴 등록 실패!");
            sqlSession.rollback();
        }

        sqlSession.close();
    }

    public void insertCategoryAndMenuTest(MenuAndCategoryDTO menuAndCategory) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        //카테고리 먼저 등록하고, 메뉴 추가할거임 그래서 result 두번 해야함, 인자값은 같은걸 넘길거임
        int result1 = mapper.insertNewCategory(menuAndCategory);    //신규 카테고리 번호 등록
        int result2 = mapper.insertNewMenu(menuAndCategory);        //위에서 등록하면 신규 카테고리 번호를 모르는 상태
                                                                    //그래서 새로운 메뉴 등록하기 전에 등록된 신규 카테고리 번호를 조회해야함 이 과정을 제외하게 해주는게 selectKey


        //위 두개가 하나의 트랜젝션으로 묶여야함, 둘 다 성공이 되어야 commit 해야함

        if(result1 > 0 && result2 > 0){
            System.out.println("신규 카테고리와 메뉴 등록 성공!");
            sqlSession.commit();
        }else{
            System.out.println("신규 카테고리와 메뉴 등록 실패!");
            sqlSession.rollback();
        }
        sqlSession.close();
    }
}
