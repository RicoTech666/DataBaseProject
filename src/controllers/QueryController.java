package controllers;

import utils.ConnectionTools;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class QueryController {
    private String inputQueryType;

    QueryController(String inputQueryType) {
        this.inputQueryType = inputQueryType;
    }

    private String translateInputToQuery() {
        Scanner sc = new Scanner(System.in);
        switch (inputQueryType) {
            case "1.1.1":
                return "SELECT * FROM student";
            case "1.1.2":
                System.out.println("请输入具体要查询的学生姓名：");
                return "SELECT t1.name,t2.subject,t3.score FROM student as t1\n" +
                        "INNER JOIN subject as t2\n" +
                        "INNER JOIN score as t3\n" +
                        "on t1.id = t3.student_id AND t2.id = t3.subject_id AND t1.name =\"" + sc.next() + "\"";
            case "1.1.3":
                System.out.println("请输入具体要查询的老师姓名：");
                return "SELECT t2.teacher,t1.name,t2.subject,t3.score FROM student as t1\n" +
                        "INNER JOIN subject as t2\n" +
                        "INNER JOIN score as t3 \n" +
                        "on t1.id = t3.student_id AND t2.id = t3.subject_id AND t2.teacher =\"" + sc.next() + "\"";
            case "1.1.4":
                System.out.println("请输入具体要查询的课程名称：");
                return "SELECT t2.subject,t1.name,t3.score FROM student as t1\n" +
                        "INNER JOIN subject as t2\n" +
                        "INNER JOIN score as t3 \n" +
                        "on t1.id = t3.student_id AND t2.id = t3.subject_id AND t2.subject =\"" + sc.next() + "\"";
            case "1.2.1":
                return "SELECT * FROM subject";
            case "1.2.2":
                System.out.println("请输入具体要查询的课程名称：");
                return "SELECT * FROM subject WHERE subject =\"" + sc.next() + "\"";
            case "1.2.3":
                System.out.println("请输入具体要查询的老师姓名：");
                return "SELECT * FROM subject WHERE teacher =\"" + sc.next() + "\"";
            case "1.3.1":
                return "SELECT * FROM teacher";
            case "1.3.2":
                System.out.println("请输入要查询的老师姓名：");
                return "SELECT * FROM teacher WHERE name =\"" + sc.next() + "\"";
            case "2.1":
                System.out.println("请输入学生信息(例如：学号：1001，姓名： 小明, 年龄： 18, 性别： 男)：");

                String stuInfo = sc.next();
                String studentId = stuInfo.split("，")[0].split("：")[1].trim();
                String studentName = stuInfo.split("，")[1].split("：")[1].trim();
                String studentAge = stuInfo.split("，")[2].split("：")[1].trim();
                String studentGender = stuInfo.split("，")[3].split("：")[1].trim();

                return "INSERT INTO student (id,name,age,sex)\n" +
                        "VALUES(" + studentId + ",\"" + studentName + "\"," + studentAge + ",\"" + studentGender +
                        "\")";
            case "2.2":
                System.out.println("请输入课程信息(例如：科目编号：1001，科目： 语文, 代课老师： 李老师)：");
                String subjectInfo = sc.next();
                String subjectId = subjectInfo.split("，")[0].split("：")[1].trim();
                String subjectName = subjectInfo.split("，")[1].split("：")[1].trim();
                String subjectTeacher = subjectInfo.split("，")[2].split("：")[1].trim();

                return "INSERT INTO subject (id,subject,teacher)\n" +
                        "VALUES(" + subjectId + ",\"" + subjectName + "\",\"" + subjectTeacher + "\")";
            case "2.3":
                System.out.println("请输入老师信息(例如：老师编号：1，老师姓名：张三，工资：2000)");
                String teacherInfo = sc.next();
                String teacherId = teacherInfo.split("，")[0].split("：")[1].trim();
                String teacherName = teacherInfo.split("，")[1].split("：")[1].trim();
                String teacherSalary = teacherInfo.split("，")[2].split("：")[1].trim();

                return "INSERT INTO teacher (id,name,salary)\n" +
                        "VALUES(" + teacherId + ",\"" + teacherName + "\"," + teacherSalary + ")";
            case "2.4":
                System.out.println("请输入学生成绩信息(例如：学生姓名：张三，科目：语文，成绩：100)");
                String scoreInfo = sc.next();
                String scoreStu = scoreInfo.split("，")[0].split("：")[1].trim();
                String scoreSubj = scoreInfo.split("，")[1].split("：")[1].trim();
                String scoreVal = scoreInfo.split("，")[2].split("：")[1].trim();

                return "INSERT INTO score (student_id,subject_id,score)\n" +
                        "SELECT t1.id,t2.id,\'" + scoreVal + "\'FROM student AS t1\n" +
                        "INNER JOIN subject AS t2\n" +
                        "ON t1.name = \"" + scoreStu + "\"AND t2.subject = \"" + scoreSubj + "\"";
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
                String scoreValAlter = scoreInfoAlter.split("，")[2].split("：")[1].trim();

                return "UPDATE score,\n" +
                        "(SELECT t1.id AS stu_id, t2.id AS subj_id FROM student as t1\n" +
                        "INNER JOIN subject AS t2\n" +
                        "INNER JOIN score AS t3\n" +
                        "ON t1.id = t3.student_id AND t2.id = t3.subject_id AND t1.name =\"" + scoreStuAlter + "\"\n" +
                        "AND t2.subject=\"" + scoreSubjAlter + "\")AS score_info\n" +
                        "SET score.score = " + scoreValAlter + " WHERE score.student_id = score_info.stu_id\n" +
                        "AND score.subject_id = score_info.subj_id";
            case "4.1":
                System.out.println("删除学生之后，该学生信息将不能恢复，是否要继续删除？\n" +
                        "1. 是\n" +
                        "2. 否");
                if ("1".equals(sc.next())) {
                    System.out.println("请输入要删除的学生信息：(例：学号：1001)");
                    String stuIdDelete = sc.next().split("：")[1].trim();

                    return "DELETE FROM student where id=" + stuIdDelete;
                } else {
                    System.out.println("您选择了否，将退出程序！");
                    return "";
                }
            case "4.2":
                System.out.println("删除课程之后，该课程信息将不能恢复，是否要继续删除？\n" +
                        "1. 是\n" +
                        "2. 否");
                if ("1".equals(sc.next())) {
                    System.out.println("请输入要删除的课程信息：(例：课程号：1001)");
                    String subjIdDelete = sc.next().split("：")[1].trim();

                    return "DELETE FROM subject where id=" + subjIdDelete;
                } else {
                    System.out.println("您选择了否，将退出程序！");
                    return "";
                }
            case "4.3":
                System.out.println("删除老师之后，该老师信息将不能恢复，是否要继续删除？\n" +
                        "1. 是\n" +
                        "2. 否");
                if ("1".equals(sc.next())) {
                    System.out.println("请输入要删除的老师信息：(例：老师编号： 1001)");
                    String teacherIdDelete = sc.next().split("：")[1].trim();

                    return "DELETE FROM teacher where id=" + teacherIdDelete;
                } else {
                    System.out.println("您选择了否，将退出程序！");
                    return "";
                }

            default:
                return "";
        }
    }

     void printResultToUser() throws SQLException {
        String sqlTranslated = translateInputToQuery();
        ConnectionTools connectionTools = new ConnectionTools();
        Connection connection = connectionTools.getConnect();
        Statement statement = connectionTools.getStatement(connection);
        switch (inputQueryType) {
            case "1.1.1":
                ResultSet rs = connectionTools.executeDQL(statement, sqlTranslated);
                while (rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String sex = rs.getString("sex");
                    System.out.println("姓名： " + name + ", 年龄： " + age + ", 性别： " + sex);
                }
                ConnectionTools.closeConnect(rs, statement, connection);
                break;
            case "1.1.2":
                rs = connectionTools.executeDQL(statement, sqlTranslated);
                while (rs.next()) {
                    String name = rs.getString("name");
                    String subject = rs.getString("subject");
                    double score = rs.getDouble("score");
                    System.out.println("姓名： " + name + ", 科目： " + subject + ", 成绩： " + score);
                }
                ConnectionTools.closeConnect(rs, statement, connection);
                break;
            case "1.1.3":
                rs = connectionTools.executeDQL(statement, sqlTranslated);
                while (rs.next()) {
                    String name = rs.getString("name");
                    String teacher = rs.getString("teacher");
                    String subject = rs.getString("subject");
                    double score = rs.getDouble("score");
                    System.out.println("老师： " + teacher + ", 学生： " + name + ", 科目： " + subject + ", 成绩： " + score);
                }
                ConnectionTools.closeConnect(rs, statement, connection);
                break;
            case "1.1.4":
                rs = connectionTools.executeDQL(statement, sqlTranslated);
                while (rs.next()) {
                    String name = rs.getString("name");
                    String subject = rs.getString("subject");
                    double score = rs.getDouble("score");
                    System.out.println(", 科目： " + subject + ", 学生： " + name + ", 成绩： " + score);
                }
                ConnectionTools.closeConnect(rs, statement, connection);
                break;
            case "1.2.1":
            case "1.2.2":
            case "1.2.3":
                rs = connectionTools.executeDQL(statement, sqlTranslated);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String subject = rs.getString("subject");
                    String teacher = rs.getString("teacher");
                    System.out.println("科目编号： " + id + ", 科目： " + subject + ", 任课教师： " + teacher);
                }
                ConnectionTools.closeConnect(rs, statement, connection);
                break;
            case "1.3.1":
            case "1.3.2":
                rs = connectionTools.executeDQL(statement, sqlTranslated);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double salary = rs.getDouble("salary");
                    System.out.println("教师编号： " + id + ", 教师： " + name + ", 工资： " + salary);
                }
                ConnectionTools.closeConnect(rs, statement, connection);
                break;
            case "2.1":
            case "2.2":
            case "2.3":
            case "2.4":
            case "3.1":
            case "3.2":
            case "3.3":
            case "3.4":
            case "4.1":
            case "4.2":
            case "4.3":
                int result = connectionTools.executeDMLORDDL(statement, sqlTranslated);
                if (result > 0) {
                    System.out.println("操作成功！");
                } else {
                    System.out.println("操作失败！");
                }
                ConnectionTools.closeConnect(statement,connection);
                break;
            default:
                ConnectionTools.closeConnect(statement,connection);
                System.out.println("对不起，您的输入有误，无此对应查找项目");
        }

    }
}
