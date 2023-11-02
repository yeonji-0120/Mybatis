package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SqlBuilderMapper {

    @InsertProvider(type=SqlBuilderProvider.class, method = "registMenu")
    int registMenu(MenuDTO menu);

    @UpdateProvider(type=SqlBuilderProvider.class, method = "modifyMenu")
    int modifyMenu(MenuDTO menu);

    /*Map이나 getter가 있는 DTO가 아닌 기본 자료형 값을 전달해야 하는 경우 param 어노테이션을 이용한다.
    *전달해야 하는 값이 2개 이상인 경우도 Param어노테이션을 key값으로 값을 찾을 수 있다.
    * 기본 자료형은 키값이 없으니까 Param("code")를 키값 같이 만들어서 전달하는거 같은거임
    * 단, Provider메소드의 매개변수 선언부는 없어야한다. 그래서 SqlBuilderProvider 보면
    * public String deleteMenu() 이렇게만 선언함
     */
    @DeleteProvider(type = SqlBuilderProvider.class, method = "deleteMenu")
    int deleteMenu(@Param("code") int code);
}
