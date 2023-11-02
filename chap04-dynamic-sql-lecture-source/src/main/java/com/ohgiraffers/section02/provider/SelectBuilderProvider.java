package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.jdbc.SQL;

public class SelectBuilderProvider {

    public String selectAllMenu() {
        //인스턴스 생성해서 메소드로 작업

        return new SQL()
                .SELECT("A.MENU_CODE")
                .SELECT("A.MENU_NAME")
                .SELECT("A.MENU_PRICE")
                .SELECT("A.CATEGORY_CODE")
                .SELECT("A.ORDERABLE_STATUS")
                .FROM("TBL_MENU A")
                .WHERE("A.ORDERABLE_STATUS = 'Y'").toString();
    }
    //뒤에 toString 안쓰면 리턴 타입이 SQL임 toString 붙이면 타입이 String이 됨


    public String searchMenuByCodition(SearchCriteria searchCriteria){

        SQL sql = new SQL();
        //아까는 위에서 한번만 쓰니까 리턴으로 바로 적었으나 이번에는 조건에 따라서 다르게 나가기 때문에 개체 생성한거임

        sql.SELECT("A.MENU_CODE")
                .SELECT("A.MENU_NAME")
                .SELECT("A.MENU_PRICE")
                .SELECT("A.CATEGORY_CODE")
                .SELECT("A.ORDERABLE_STATUS")
                .FROM("TBL_MENU A");

        if("category".equals(searchCriteria.getCondition())){
            sql.JOIN("TBL_CATEGORY B ON (A.CATEGORY_CODE = B.CATEGORY_CODE)")
                    .WHERE("A.ORDERABLE_STATUS = 'Y'")
                    .AND()
                    .WHERE("B.CATEGORY_NAME = #{ value }");
        } else if("name".equals(searchCriteria.getCondition())){
            /* 가변인자를 이용하면 자동 AND로 처리하기 때문에 OR를 사용해야 하는 경우 .OR를 이용해야 한다.*/
            sql.WHERE("A.ORDERABLE_STATUS = 'Y'"
                    , "A.MENU_NAME LIKE CONCAT('%', #{ value }, '%')");
            //배열 형식으로 몇개 들어올지 모를때 사용하는거 가변인자(...) 여기서 가변인자를 사용하면 자동으로 AND조건을 붙여서 , 쓰면 됨
            //OR를 사용하려면 직접 써야함
        }
        System.out.println("sql = " + sql);
        return sql.toString();
    }
}
