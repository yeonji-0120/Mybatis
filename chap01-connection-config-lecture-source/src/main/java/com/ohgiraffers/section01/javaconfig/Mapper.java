package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface Mapper {
    //쿼리문 넣는 파일을 대체하는 인터페이스
    //@넣으면 쿼리문에서 쓸수 있는 SELECT, INSERT, UPDATE, DELETE 나옴
    @Select("SELECT CURDATE() FROM DUAL")
    //DUAL은 임시테이블 같은거임 FROM이 필요하니까 임시테이블 넣어준거
    Date selectSysdate();//현재 날짜를 가져와주는 메소드 selectSysdate가 키값이 되고 @Select 괄호안에 값이 밸류 값이 됨
    //selectSysdate 호출하면 Select 실행됨 리턴타입이 Date가 됨
}
