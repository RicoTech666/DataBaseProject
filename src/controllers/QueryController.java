package controllers;

import java.util.Scanner;

public class QueryController {
    private String inputQueryType;

    public QueryController(String inputQueryType) {
        this.inputQueryType = inputQueryType;
    }

    public String translateInputToQuery() {
        Scanner sc = new Scanner(System.in);
        switch (inputQueryType) {
            case "1.1.1":
                return "SELECT * FROM student";
            case "1.1.2":
                System.out.println("请输入具体要查询的学生姓名：");
                return "SELECT t1.name,t2.subject,t3.score FROM student as t1" +
                        "INNER JOIN subject as t2" +
                        "INNER JOIN score as t3" +
                        "on t1.id = t3.student_id AND t2.id = t3.subject_id AND t1.name =" + sc.next();
            case "1.1.3":
                System.out.println("请输入具体要查询的老师姓名：");
                return "SELECT t2.teacher,t1.name,t2.subject,t3.score FROM student as t1" +
                        "INNER JOIN subject as t2\n" +
                        "INNER JOIN score as t3 \n" +
                        "on t1.id = t3.student_id AND t2.id = t3.subject_id AND t2.teacher =" + sc.next();
            default:
                System.out.println("对不起，您的输入有误，无此对应查找项目");
                return "";
        }
    }

}
