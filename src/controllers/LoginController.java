package controllers;

public class LoginController {
    private String loginInput;

    public LoginController(String loginInput) {
        this.loginInput = loginInput;
    }

    public void loginCheck() {
        String adminUserName = "张三";
        String adminPassword = "123";

        String userName = loginInput.split(":")[0];
        String password = loginInput.split(":")[1];
        if (userName.equals(adminUserName) && password.equals(adminPassword)) {
            System.out.println("您好，超级管理员，请选择你需要进行的操作：\n" +
                    "1. 查询\n" +
                    "\t1.1 查询学生信息以及成绩\n" +
                    "\t\t1.1.1 所有学生信息\n" +
                    "\t\t1.1.2 指定学生姓名的信息以及所有课程的成绩\n" +
                    "\t\t1.1.3 指定老师的所有学生及其成绩\n" +
                    "\t\t1.1.4 指定课程的所有学生及其成绩\n" +
                    "\t1.2 查询课程信息\n" +
                    "\t    1.2.1 所有课程信息\n" +
                    "\t    1.2.2 指定课程名称的信息\n" +
                    "\t    1.2.3 指定老师的所有课程信息\n" +
                    "\t1.3 查询老师信息\n" +
                    "\t    1. 所有老师信息\n" +
                    "\t    2. 指定老师信息\n" +
                    "2. 新增\n" +
                    "\t2.1 新增学生信息\n" +
                    "\t2.2 新增课程信息\n" +
                    "\t2.3 新增老师信息\n" +
                    "\t2.4 给指定学生新增成绩\n" +
                    "3. 修改\n" +
                    "    3.1 修改指定学号的学生\n" +
                    "    3.2 修改指定课程的信息\n" +
                    "    3.3 修改指定老师的信息\n" +
                    "    3.4 修改指定学生的成绩\n" +
                    "4. 删除\n" +
                    "\t4.1 删除指定学生\n" +
                    "\t4.3 删除指定课程\n" +
                    "\t4.3 删除指定老师");
        } else {
            System.out.println("对不起，您输入的用户名/密码有误");
        }
    }
}
