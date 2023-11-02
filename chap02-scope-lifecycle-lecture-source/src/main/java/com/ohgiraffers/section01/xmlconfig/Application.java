package com.ohgiraffers.section01.xmlconfig;

import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;

public class Application {
    public static void main(String[] args) {
        //커넥션 객체 하나만 생성해서 반복적으로 사용

        System.out.println(getSqlSession());
        System.out.println(getSqlSession());
        System.out.println(getSqlSession());
        System.out.println(getSqlSession());
        System.out.println(getSqlSession());
    }
}
