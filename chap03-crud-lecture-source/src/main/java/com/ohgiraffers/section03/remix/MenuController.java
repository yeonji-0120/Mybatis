package com.ohgiraffers.section03.remix;


import java.util.List;
import java.util.Map;

public class MenuController {

    /*1. 사용자가 선택한 기능을 실행시켜주는 기능
    * 2. 사용자가 전달한 값을 정재 또는 새롭게 생성한 후에 필요한 서비스를 요청
    * 3. 서비스에서 전달받은 결과값에 따라서 성공 또는 실패의 화면을 결정 짓는 역할*/

    private final PrintResult printResult;//결과에 대한 내용

    private final MenuService menuService;//2번에 대한 내용

    public MenuController(){//기본 생성자에 초기화
        printResult = new PrintResult();
        menuService = new MenuService();
    }
    public void selectAllMenu() {
        List<MenuDTO> menuList = menuService.selectAllMenu();

        if(menuList != null){
            printResult.printMenuList(menuList);
        }else{
            printResult.printErrorMessage("selectList");
        }


    }

    public void selectMenuByCode(Map<String, String> parameter) {

        //받은 값은 string이고 카테고리code는 정수 형식으로 가지고 있으니까 int 형식으로 바꿔줘야함
        int code = Integer.parseInt(parameter.get("code"));
        System.out.println("code = " + code);

        MenuDTO menu = menuService.selectMenuByCode(code);
                //보여줘야하는 값은 MenuDTO니까 이거 사용

        if(menu != null){
            printResult.printMenu(menu);
        }else{
            printResult.printErrorMessage("selectOne");
        }

    }

    public void registMenu(Map<String ,String> parameter){
        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);

        System.out.println("menu = " + menu);

        if(menuService.insertMenu(menu)){//참 또는 거짓인 값이 올거임 int로 했으면 대소비교로 작성
            printResult.printSuccessMessage("insert");

        }else {
            printResult.printErrorMessage("insert");
        }
    }

    public void modifyMenu(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("code"));
        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setCode(code);
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);

        System.out.println("menu = " + menu);

        if(menuService.modifyMenu(menu)){
            printResult.printSuccessMessage("update");

        }else {
            printResult.printErrorMessage("update");
        }

    }

    public void deleteMenu(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("code"));
        if(menuService.deleteMenu(code)){
            printResult.printSuccessMessage("delete");
        }else {
            printResult.printErrorMessage("delete");
        }
    }
}
