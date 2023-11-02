package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SelectBuilderMapper {

    @Results(id="menuResultMap", value = {
            @Result(id = true, property = "code", column = "MENU_CODE"),
            @Result(property = "name", column = "MENU_NAME"),
            @Result(property = "price", column = "MENU_PRICE"),
            @Result(property = "categoryCode", column = "CATEGORY_CODE"),
            @Result(property = "orderableStatus", column = "ORDERABLE_STATUS")
    })

    @SelectProvider(type=SelectBuilderProvider.class, method="selectAllMenu")
    List<MenuDTO> selectAllMenu();

    @ResultMap("menuResultMap")
    @SelectProvider(type=SelectBuilderProvider.class, method="searchMenuByCodition")
    List<MenuDTO> searchMenuByCodition(SearchCriteria searchCriteria);
}
