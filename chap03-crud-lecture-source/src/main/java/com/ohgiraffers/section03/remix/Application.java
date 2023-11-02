package com.ohgiraffers.section03.remix;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        //Application -> view -> controller -> service -> dao -> db 해당 순서로 요청, 역순으로 값을 가져옴
        //여기선 Application이랑 view 합침
        //자바 어노테이션 활용 - 인터페이스 생성해서 사용

        /*
        설정과 쿼리는 xml이 편함,요청하는건 자바가 편함 xml 사용하면 DAO가 필요하지만
        자바 어노테이션은 메소드만 만들면 알아서 찾아감 그래서 둘을 믹스해서 사용
         */

        Scanner sc = new Scanner(System.in);

        MenuController menuController = new MenuController();

        do{
            System.out.println("========메뉴 관리 ==========");
            System.out.println("1. 메뉴 전체 조회");
            System.out.println("2. 메뉴 코드로 메뉴 조회");
            System.out.println("3. 신규 메뉴 추가");
            System.out.println("4. 메뉴 수정");
            System.out.println("5. 메뉴 삭제");
            System.out.println("메뉴 관리 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no){

                case 1 : menuController.selectAllMenu(); break;
                case 2 : menuController.selectMenuByCode(inputMenuCode()); break;
                case 3 : menuController.registMenu(inputMenu());break;
                case 4 : menuController.modifyMenu(inputModifyMenu()); break;
                case 5 : menuController.deleteMenu(inputMenuCode()); break;//코드만 받으면 되니까 위에꺼 재사용
                default:
                    System.out.println("잘못된 메뉴를 선택하셨습니다."); break;
            }
        }
        while (true);

    }

    private static Map<String , String > inputModifyMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 메뉴 코드를 입력하세요 : ");
        String code = sc.nextLine();
        System.out.print("수정할 메뉴 이름을 입력하세요 : ");
        String  name = sc.nextLine();
        System.out.print("수정할 메뉴 가격을 입력하세요 : ");
        String price = sc.nextLine();
        System.out.print("수정할 카테고리 코드를 입력하세요 : ");
        String categoryCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("code", code);
        parameter.put("name", name);
        parameter.put("price", price);
        parameter.put("categoryCode", categoryCode);
        return parameter;
    }

    private static Map<String, String> inputMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 이름을 입력하세요 : ");
        String  name = sc.nextLine();
        System.out.print("메뉴 가격을 입력하세요 : ");
        String price = sc.nextLine();
        System.out.print("카테고리 코드를 입력하세요 : ");
        String categoryCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("name", name);
        parameter.put("price", price);
        parameter.put("categoryCode", categoryCode);
        return parameter;
    }

    private static Map<String,String> inputMenuCode() {
        //int 써도 되지만 나중에 웹에서 요청보내고 받는 경우 문자열로 주고 받기 때문에

        Scanner sc = new Scanner(System.in);
        System.out.println("메뉴 코드를 입력하세요 : ");
        String code = sc.nextLine();

        Map<String , String >parameter = new HashMap<>();
        parameter.put("code", code);

        return parameter;

    }
}
