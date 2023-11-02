package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface MenuMapper {
    //xml 사용하면 menu-mapper.xml에 쿼리문을 넣고 사용하지만 자바 어노테이션으로 사용하면 여기서 쿼리문을 작성하는것
    @Results(id="menuResultMap", value = {
            @Result(id = true, property = "code", column = "MENU_CODE"),
            @Result(property = "name", column = "MENU_NAME"),
            @Result(property = "price", column = "MENU_PRICE"),
            @Result(property = "categoryCode", column = "CATEGORY_CODE"),
            @Result(property = "orderableStatus", column = "ORDERABLE_STATUS"),
    })
    //처음에 Results로 만들고 나중에 재사용할때 ResultMap 사용 SELECT일때만 사용
    @Select("SELECT\n" +
            "        menu_code, menu_name, menu_price, category_code, orderable_status\n" +
            "FROM\n" +
            "        tbl_menu\n" +
            "WHERE\n" +
            "        orderable_status = 'Y'\n" +
            "order by menu_code")
    List<MenuDTO> selectAllMenu();//List가 반환타입임
@Select("SELECT\n" +
        "        menu_code, menu_name, menu_price, category_code, orderable_status\n" +
        "FROM\n" +
        "        tbl_menu\n" +
        "WHERE\n" +
        "        orderable_status = 'Y'\n" +
        "AND menu_code = #{code}")
@ResultMap("menuResultMap")//위에 사용한 resultMap 재사용
    MenuDTO selectMenuByCOde(int code);
@Insert("INSERT\n" +
        "INTO tbl_menu(\n" +
        "        menu_name,\n" +
        "        menu_price,\n" +
        "        category_code,\n" +
        "        orderable_status\n" +
        "        )\n" +
        "VALUES(\n" +
        "                #{name},<!-->이걸 바인드변수라고 함 이건 DAO에서 전달한 DTO 값임<-->\n" +
        "                #{price},\n" +
        "                #{categoryCode},\n" +
        "                'Y'\n" +
        "        )")
    int insertMenu(MenuDTO menu);
@Update("UPDATE tbl_menu\n" +
        "SET\n" +
        "        menu_name = #{name},\n" +
        "        menu_price = #{price},\n" +
        "        category_code = #{categoryCode}\n" +
        "\n" +
        "WHERE\n" +
        "        menu_code = #{code}")
    int updateMenu(MenuDTO menu);
@Delete("DELETE\n" +
        "FROM tbl_menu\n" +
        "WHERE menu_code = #{code}")
    int deleteMenu(int code);
}
