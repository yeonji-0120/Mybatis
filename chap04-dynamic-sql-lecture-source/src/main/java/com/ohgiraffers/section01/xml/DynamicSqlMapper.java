package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;

import java.util.List;
import java.util.Map;

public interface DynamicSqlMapper {
    List<MenuDTO> selectMenuByPrice(Map<String, Integer> map);

    List<MenuDTO> searchMenu(SearchCriteria searchCriteria);

    List<MenuDTO> searchMenuBySubCategory(SearchCriteria searchCriteria);
//전달 받을 때는
//List<MenuDTO> searchMenuByRandomMenuCode(Map<String, List<Integer>> criteria);

    //직접 접근하는 경우는 아래처럼
    List<MenuDTO> searchMenuByRandomMenuCode();
    List<MenuDTO> searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria);


    List<MenuDTO> searchMenuByNameOrCategory(Map<String, Object> criteria);

    int modifyMenu(Map<String, Object> criteria);
}
