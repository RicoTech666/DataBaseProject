package controllers;

import java.sql.SQLException;
import java.util.Scanner;

public class MainController {

    public void run() throws SQLException {
        System.out.println("您好，欢迎登陆学生考试系统，请输入您的用户名和密码(用户名:密码)：");
        Scanner sc = new Scanner(System.in);
        String loginInput = sc.nextLine();
        LoginController loginController = new LoginController(loginInput);
        loginController.loginCheck();
        String inputQueryType = sc.next();
        QueryController queryController = new QueryController(inputQueryType);
        queryController.printResultToUser();
    }

}
