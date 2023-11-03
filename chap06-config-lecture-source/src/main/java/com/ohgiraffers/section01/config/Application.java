package com.ohgiraffers.section01.config;

import static com.ohgiraffers.common.Template.getSqlSession;

public class Application {//실행만

    public static void main(String[] args) {
        System.out.println(getSqlSession());
    }
}
