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
                return "SELECT t1.name,t2.subject,t3.score FROM student as t1\n" +
                        "INNER JOIN subject as t2\n" +
                        "INNER JOIN score as t3\n" +
                        "on t1.id = t3.student_id AND t2.id = t3.subject_id AND t1.name =\"" + sc.next()+"\"";
            case "1.1.3":
                System.out.println("请输入具体要查询的老师姓名：");
                return "SELECT t2.teacher,t1.name,t2.subject,t3.score FROM student as t1\n" +
                        "INNER JOIN subject as t2\n" +
                        "INNER JOIN score as t3 \n" +
                        "on t1.id = t3.student_id AND t2.id = t3.subject_id AND t2.teacher =\"" + sc.next()+"\"";
            case "1.1.4":
                System.out.println("请输入具体要查询的课程名称：");
                return "SELECT t2.subject,t1.name,t3.score FROM student as t1\n" +
                        "INNER JOIN subject as t2\n" +
                        "INNER JOIN score as t3 \n" +
                        "on t1.id = t3.student_id AND t2.id = t3.subject_id AND t2.subject =\"" + sc.next()+"\"";
            case "1.2.1":
                return "SELECT * FROM subject";
            case "1.2.2":
                return "SELECT subject FROM subject";
            case "1.2.3":
                System.out.println("请输入具体要查询的老师姓名：");
                return "SELECT subject，teacher FROM subject WHERE teacher =\"" + sc.next()+"\"";
            case "1.3.1":
                return "SELECT teacher,subject FROM subject";
            case "1.3.2":
                System.out.println("请输入要查询的老师姓名：");
                return "SELECT teacher,subject FROM subject WHERE teacher =\"" + sc.next()+"\"";
            case "2.1":
                System.out.println("请输入学生信息(例如：学号：1001，姓名： 小明, 年龄： 18, 性别： 男)：");

                String stuInfo = sc.next();
                String studentId = stuInfo.split("，")[0].split("：")[1].trim();
                String studentName = stuInfo.split("，")[1].split("：")[1].trim();
                String studentAge = stuInfo.split("，")[2].split("：")[1].trim();
                String studentGender = stuInfo.split("，")[3].split("：")[1].trim();

                System.out.println("添加学生[ " + studentName + studentId + " ]成功！");
                return "INSERT INTO student (id,name,age,sex)\n" +
                        "VALUES(" + studentId + ",\"" + studentName + "\"," + studentAge + ",\"" + studentGender + "\")";
            case "2.2":
                System.out.println("请输入课程信息(例如：科目编号：1001，科目： 语文, 代课老师： 李老师)：");
                String subjectInfo = sc.next();
                String subjectId = subjectInfo.split("，")[0].split("：")[1].trim();
                String subjectName = subjectInfo.split("，")[1].split("：")[1].trim();
                String subjectTeacher = subjectInfo.split("，")[2].split("：")[1].trim();

                System.out.println("添加科目[ " + subjectName + subjectId + " ]成功！");
                return "INSERT INTO subject (id,subject,teacher)\n" +
                        "VALUES(" + subjectId + ",\"" + subjectName + "\",\"" + subjectTeacher + "\")";
            case "2.3":
                System.out.println("请输入老师信息(例如：老师编号：1，老师姓名：张三，工资：2000)");
                String teacherInfo = sc.next();
                String teacherId = teacherInfo.split("，")[0].split("：")[1].trim();
                String teacherName = teacherInfo.split("，")[1].split("：")[1].trim();
                String teacherSalary = teacherInfo.split("，")[2].split("：")[1].trim();

                System.out.println("添加老师[ " + teacherName + teacherId + " ]成功！");
                return "INSERT INTO teacher (id,name,salary)\n" +
                        "VALUES(" + teacherId + ",\"" + teacherName + "\"," + teacherSalary + ")";
            case "2.4":
                System.out.println("请输入学生成绩信息(例如：学生姓名：张三，科目：语文，成绩：100)");
                String scoreInfo = sc.next();
                String scoreStu = scoreInfo.split("，")[0].split("：")[1].trim();
                String scoreSubj = scoreInfo.split("，")[1].split("：")[1].trim();
                String scoreVal = scoreInfo.split("，")[2].split("，")[1].trim();

                System.out.println("添加成绩成功！");
                return "INSERT INTO score (student_id,subject_id,score)\n" +
                        "SELECT t1.id,t2.id," + scoreVal + "FROM student AS t1\n" +
                        "INNER JOIN subject AS t2\n" +
                        "ON t1.name = \"" + scoreStu + "\"AND t2.subject = \"" + scoreSubj+"\"";
            case "3.1":
                System.out.println("请输入学生信息(例如：学号：1001，姓名： 小明, 年龄： 18, 性别： 男)：");
                String stuInfoAlter = sc.next();
                String studentIdAlter = stuInfoAlter.split("，")[0].split("：")[1].trim();
                String studentNameAlter = stuInfoAlter.split("，")[1].split("：")[1].trim();
                String studentAgeAlter = stuInfoAlter.split("，")[2].split("：")[1].trim();
                String studentGenderAlter = stuInfoAlter.split("，")[3].split("：")[1].trim();

                return "UPDATE student SET name =\"" + studentNameAlter + "\",age ="
                        + studentAgeAlter + ",sex =\"" + studentGenderAlter + "\"WHERE id =" + studentIdAlter;
            case "3.2":
                System.out.println("请输入课程信息(例如：科目编号：1001，科目： 语文, 代课老师： 李老师)：");
                String subjectInfoAlter = sc.next();
                String subjectIdAlter = subjectInfoAlter.split("，")[0].split("：")[1].trim();
                String subjectNameAlter = subjectInfoAlter.split("，")[1].split("：")[1].trim();
                String subjectTeacherAlter = subjectInfoAlter.split("，")[2].split("：")[1].trim();

                return "UPDATE subject SET subject =\"" + subjectNameAlter + "\",teacher =\""
                        + subjectTeacherAlter + "\"WHERE id =" + subjectIdAlter;
            case "3.3":
                System.out.println("请输入老师信息(例如：老师编号：1，老师姓名：张三，工资：2000)");
                String teacherInfoAlter = sc.next();
                String teacherIdAlter = teacherInfoAlter.split("，")[0].split("：")[1].trim();
                String teacherNameAlter = teacherInfoAlter.split("，")[1].split("：")[1].trim();
                String teacherSalaryAlter = teacherInfoAlter.split("，")[2].split("：")[1].trim();

                return "UPDATE teacher SET name =\"" + teacherNameAlter + "\",salary ="
                        + teacherSalaryAlter + "WHERE id =" + teacherIdAlter;
            case "3.4":
                System.out.println("请输入学生成绩信息(例如：学生姓名：张三，科目：语文，成绩：100)");
                String scoreInfoAlter = sc.next();
                String scoreStuAlter = scoreInfoAlter.split("，")[0].split("：")[1].trim();
                String scoreSubjAlter = scoreInfoAlter.split("，")[1].split("：")[1].trim();
                String scoreValAlter = scoreInfoAlter.split("，")[2].split("，")[1].trim();

                return "UPDATE score,\n" +
                        "(SELECT t1.id AS stu_id, t2.id AS subj_id FROM student as t1\n" +
                        "INNER JOIN subject AS t2\n" +
                        "INNER JOIN score AS t3\n" +
                        "ON t1.id = t3.student_id AND t2.id = t3.subject_id AND t1.name =\"" + scoreSubjAlter +"\"\n"+
                        "AND t2.subject=\"" + scoreStuAlter + "\")AS score_info\n" +
                        "SET score.score = " + scoreValAlter + " WHERE score.student_id = score_info.stu_id\n" +
                        "AND score.subject_id = score_info.subj_id";
            default:
                System.out.println("对不起，您的输入有误，无此对应查找项目");
                return "";
        }
    }

    public static void main(String[] args) {
        String test = "学号：1001，姓名： 小明， 年龄： 18， 性别： 男";
        System.out.println(test.split("，")[2].split("：")[1].trim());
    }
}
