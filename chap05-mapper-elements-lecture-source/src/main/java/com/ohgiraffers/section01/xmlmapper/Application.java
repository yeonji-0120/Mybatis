package com.ohgiraffers.section01.xmlmapper;

import com.ohgiraffers.common.CategoryDTO;
import com.ohgiraffers.common.MenuAndCategoryDTO;
import com.ohgiraffers.common.MenuDTO;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ElementTestService elementTestService = new ElementTestService();

        do{
            System.out.println("================ 매퍼 element 테스트 메뉴 =================");
            System.out.println("1. <cache> 테스트");
            System.out.println("2. <resultMap> 서브 메뉴"); //중요!*********************
            System.out.println("3. <sql> 테스트");
            System.out.println("4. <insert> 서브 메뉴"); //중요!***********************
            System.out.print("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1: elementTestService.selectCacheTest(); break;
                case 2: resultMapSubMenu(); break;
                case 3: elementTestService.selectSqlTest(); break;
                case 4: insertSubMenu(); break;
            }
        }while(true);
    }

    private static void insertSubMenu() {

        Scanner sc = new Scanner(System.in);
        ElementTestService elementTestService = new ElementTestService();

        do {
            System.out.println("============ <insert> 서브 메뉴 ==================");
            System.out.println("1. <insert> 테스트 (메뉴 등록) ");
            System.out.println("2. <insert> 테스트 (신규 카테고리의 메뉴 등록)");
            System.out.print("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1: elementTestService.insertMenuTest(inputMenu()); break;
                case 2: elementTestService.insertCategoryAndMenuTest(inputMenuAndCategory());
                break;
            }
        } while(true);
    }

    private static MenuAndCategoryDTO inputMenuAndCategory() {

        Scanner sc = new Scanner(System.in);
        System.out.println("신규 카테고리명을 입력하세요 : ");
        String categoryName = sc.nextLine();
        System.out.println("등록할 메뉴 이름을 입력하세요 : ");
        String menuName = sc.nextLine();
        System.out.println("메뉴의 가격을 입력하세요 : ");
        int menuPrice = sc.nextInt();
        System.out.println("바로 판매 등록을 할까요?(Y/N) : ");
        sc.nextLine();
        String orderableStatus = sc.nextLine();
        //메뉴랑 카테고리에 대한 내용 두가지를 가지고 있는 상태

        MenuAndCategoryDTO menuAndCategory = new MenuAndCategoryDTO();
        CategoryDTO category = new CategoryDTO();

        menuAndCategory.setName(menuName);
        menuAndCategory.setPrice(menuPrice);
        menuAndCategory.setOrderableStatus(orderableStatus);
        category.setName(categoryName);

        menuAndCategory.setCategory(category);

        return menuAndCategory;
    }

    private static MenuDTO inputMenu() {

        Scanner sc = new Scanner(System.in);
        System.out.print("등록할 메뉴 이름을 입력하세요 : ");
        String name = sc.nextLine();
        System.out.print("메뉴의 가격을 입력하세요 : ");
        int price = sc.nextInt();
        System.out.print("등록할 카테고리를 입력하세요 : ");
        int categoryCode = sc.nextInt();
        System.out.print("바로 판매 등록을 할까요?(Y/N) : ");
        sc.nextLine();
        String orderableStatus = sc.nextLine();

        MenuDTO menu = new MenuDTO();
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);
        menu.setOrderableStatus(orderableStatus);

        return menu;
    }

    private static void resultMapSubMenu() {

        Scanner sc = new Scanner(System.in);
        ElementTestService elementTestService = new ElementTestService();

        do {
            System.out.println("================= <resultMap> 서브 메뉴 =====================");
            System.out.println("1. <resultMap> 테스트");
            System.out.println("2. <constructor> 테스트");
            System.out.println("3. <association> 테스트");
            System.out.println("4. <collection> 테스트");
            System.out.print("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1 : elementTestService.selectResultMapTest(); break;
                case 2 : elementTestService.selectResultMapConstructorTest(); break;
                case 3 : elementTestService.selectResultMapAssociationTest(); break;
                case 4 : elementTestService.selectResultMapCollectionTest(); break;
            }
        } while (true);
    }
}
