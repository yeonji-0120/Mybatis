package com.ohgiraffers.section01.xml;


import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.type.ClobReaderTypeHandler;

import javax.print.attribute.standard.RequestingUserName;
import java.util.*;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("===========마이바티스 동적 SQL(조건문)=========-");
            System.out.println("1. if 확인하기");//조건문에 부합하면 보여주고 아니면 안보여주고 기존에 사용하던거랑 같은 방식
            System.out.println("2. choose(when, otherwise) 확인하기");//java에 swich문 역할
            System.out.println("3. foreach 확인하기");//반복, list에 담긴 여러 내용을 기준으로 테이블에서 내용 가져오기
            System.out.println("4. trim(where, set) 확인하기");//if조건이 부합하면 where절에 있는 내용을 가져오고 아니면 where절 제외, set은 update할때 사용
            System.out.println("9. 종료하기");
            System.out.println("메뉴를 선택하세요 : ");
            int no = sc.nextInt();

            switch (no){
                case 1 : ifSubMenu(); break;
                case 2 : chooseSubMenu(); break;
                case 3 : foreachSubMenu(); break;
                case 4 : trimSubMenu(); break;
                case 9 :
                    System.out.println("프로그램을 종료합니다."); break;
            }
        } while (true);
    }

    private static void trimSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do {
            System.out.println("========trim 서브 메뉴 ========");
            System.out.println("1. 검색조건이 있는 경우 메뉴코드로 조회, 단 없으면 전체 조회");
            System.out.println("2. 메뉴 혹은 카테고리코드로 검색, 단 메뉴와 카테고리 둘다 일치하는 경우도 검색하며, 검색조건이 없는 경우 전체 검색");
            System.out.println("3. 원하는 메뉴 정보만 수정하기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no){
                case 1 : menuService.searchMenuByCodeOrSearchAll(inputAllOrOne()); break;
                case 2 : menuService.searchMenuByNameOrCategory(inputSearchCriteriaMap()); break;
                case 3 : menuService.modifyMenu(inputChangeInfo()); break;
                case 9 : break;
            }
        } while (true);

    }

    private static Map<String, Object> inputChangeInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 메뉴 코드를 입력하세요 : ");
        int code = sc.nextInt();
        System.out.println("변경할 메뉴 이름을 입력하세요 : ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("변경항 카테고리 코드를 입력하세요 : ");
        int categoryCode = sc.nextInt();
        System.out.println("판매 여부를 결정해주세요 (Y/N) : ");
        sc.nextLine();
        String orderableStatus = sc.nextLine();

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("code", code);
        criteria.put("name", name);
        criteria.put("categoryCode", categoryCode);
        criteria.put("orderableStatus", orderableStatus);

        return criteria;
    }

    private static Map<String, Object> inputSearchCriteriaMap() {
        //문자열이나 serchcriteria를 넘겨서 사용하는 방법
        Scanner sc = new Scanner(System.in);
        System.out.println("검색할 조건을 입력하세요(category or name or both or null) : ");
        String condition = sc.nextLine();

        Map<String, Object> criteria = new HashMap<>();

        if("category".equals(condition)){
            System.out.println("검색할 카테고리 코드를 입력하세요 : ");
            int categoryValue = sc.nextInt();

            criteria.put("categoryValue", categoryValue);

        }else if("name".equals(condition)){
            System.out.println("검색할 이름을 입력하세요 : ");
            String nameValue = sc.nextLine();

            criteria.put("nameValue", nameValue);

        }else if("both".equals(condition)){
            System.out.println("검색할 이름을 입력하세요 : ");
            String nameValue = sc.nextLine();
            System.out.println("검색할 카테고리 코드를 입력하세요 : ");
            int categoryValue = sc.nextInt();

            criteria.put("nameValue", nameValue);
            criteria.put("categoryValue", categoryValue);

        }
        return criteria;
    }

    private static SearchCriteria inputAllOrOne() {
        Scanner sc = new Scanner(System.in);
        System.out.println("검색 조건을 입력하시겠습니까? (예 or 아니오) : ");
        boolean isSearchValue = "예".equals(sc.nextLine())? true : false;

        SearchCriteria searchCriteria = new SearchCriteria();
        if(isSearchValue){
            System.out.println("검색할 메뉴 코드를 입력하세요 : ");
            String code = sc.nextLine();
            searchCriteria.setCondition("menuCode");
            searchCriteria.setValue(code);
        }
        return searchCriteria;
    }

    private static void foreachSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do{
            System.out.println("==========foreach 서브 메뉴 ==========");
            System.out.println("1. 랜덤한 메뉴 5개 추출해서 조회하기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no){
                case 1 : menuService.searchMenuByRandomMenuCode(createRandomMenuCodeList()); break;
                case 9 : break;
            }
        }while (true);
    }

    public static List<Integer> createRandomMenuCodeList() {


        Set<Integer> set = new HashSet<>();
        while (set.size() < 5){
            int tmep = ((int) (Math.random() * 21)) +1;
            set.add(tmep);
        }
        List<Integer> list = new ArrayList<>(set);
        //깊은 복사가 됨
        Collections.sort(list);

        return list;
    }

    private static void chooseSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do {
            System.out.println("===========choose 서브 메뉴 ===========");
            System.out.println("1. 카테고리 상위 분류별 메뉴 보여주기(식사, 음료, 디저트");
            System.out.println("2. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no){
                case 1 : menuService.searchMenuBySubCategory(inputSubCategory()); break;
                case 9 : return;
            }

        } while (true);
    }

    private static SearchCriteria inputSubCategory() {

        Scanner sc = new Scanner(System.in);
        System.out.println("상위 분류를 입력해주세요(식사, 음료, 디저트) : ");
        String value = sc.nextLine();

        return new SearchCriteria("category", value);
    }
    //메소드나 클래스에 정보를 남길 수 있음
    /**
     * if문 서브메뉴 출력용 메소드
     * 메소드 이름 위에 마우스 올리면 이 내용이 나옴
     * **/
    private static void ifSubMenu() {
        MenuService menuService = new MenuService();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("===============if 서브 메뉴 ===========");
            System.out.println("1. 원하는 금액대에 적합한 추천 메뉴 목록 보여주기");
            System.out.println("2. 메뉴 이름 혹은 카테고리 명으로 검색하여 메뉴 목록 보여주기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    menuService.selectMenuByPrice(inputPrice());
                    break;
                case 2:
                    menuService.searchMenu(inputSearchCriteria()); break;//Criteria는 두개를 받을때 사용 카테고리랑 객체 받을떄!
                case 3:
                    break;
            }
        } while (true);
    }

    private static SearchCriteria inputSearchCriteria() {
        Scanner sc = new Scanner(System.in);
        System.out.println("검색기준을 입력하세요(name or category) : ");
        String condition = sc.nextLine();
        System.out.println("검색어를 입력해주세요 : ");
        String value = sc.nextLine();

        return new SearchCriteria(condition, value);
    }

    private static int inputPrice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색하실 가격의 최대 금액을 입력해주세요 : ");
        int price = sc.nextInt();

        return price;
    }
}
