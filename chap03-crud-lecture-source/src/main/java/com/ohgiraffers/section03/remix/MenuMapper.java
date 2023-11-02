package com.ohgiraffers.section03.remix;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MenuMapper {

    List<MenuDTO> selectAllMenu();

    MenuDTO selectMenuByCOde(int code);

    int insertMenu(MenuDTO menu);
    int updateMenu(MenuDTO menu);

    int deleteMenu(int code);
}
