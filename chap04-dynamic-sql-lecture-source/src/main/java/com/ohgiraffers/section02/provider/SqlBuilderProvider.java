package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.jdbc.SQL;

public class SqlBuilderProvider {

    public String registMenu(MenuDTO menu) {
        //인설트할때 DTO 타입으로 넣음 (객체), 그냥 객체로 넘길때는 사용 방법이 다름

        SQL sql = new SQL();

        sql.INSERT_INTO("TBL_MENU")
                .VALUES("MENU_NAME", "#{ name }")
                .VALUES("MENU_PRICE", "#{ price }")
                .VALUES("CATEGORY_CODE", "#{ categoryCode }")
                .VALUES("ORDERABLE_STATUS", "#{ orderableStatus }");

        System.out.println("sql = " + sql);
        return sql.toString();
    }

    public String modifyMenu(MenuDTO menu) {

        SQL sql = new SQL();
        //전체 내용 수정할땐
//        sql.UPDATE("TBL_MENU")
//                .SET("MENU_NAME = #{name}")
//                .SET("MENU_PRICE = #{price}")
//                .SET("CATEGORY_CODE = #{categoryCode}")
//                .SET("ORDERABLE_STATUS = #{orderableStatus}")
//                .WHERE("MENU_CODE = #{code}");

        sql.UPDATE("TBL_MENU");

        if(menu.getName() != null && !"".equals(menu.getName())){
            sql.SET("MENU_NAME = #{ name }");
        }

        if(menu.getPrice() > 0){
            sql.SET("MENU_PRICE = #{ price }");
        }

        if(menu.getCategoryCode() > 0) {
            sql.SET("CATEGORY_CODE = #{ categoryCode }");
        }

        if(menu.getOrderableStatus() != null && !"".equals(menu.getOrderableStatus())){
            sql.SET("ORDERABLE_STATUS = #{ orderableStatus }");
        }
        //여기까지가 입력 받은게 null 또는 공백 또는 0보다 크면 해당 내용에 넣겠다는 뜻임 입력받은 내용만 수정하는 메소드

        sql.WHERE("MENU_CODE = #{ code }");
        return sql.toString();
    }

    public String deleteMenu(){
        SQL sql = new SQL();
        sql.DELETE_FROM("TBL_MENU")
                .WHERE("MENU_CODE = #{code}");

        return sql.toString();
    }
}
