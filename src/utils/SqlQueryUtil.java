package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlQueryUtil {
    private String sqlQuery;

    public SqlQueryUtil(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public void executeQueryAndPrintResult() throws SQLException {
        ConnectionTools connectionTools = new ConnectionTools();
        Connection connection = connectionTools.getConnect();
        Statement statement = connectionTools.getStatement(connection);
        ResultSet rs = connectionTools.executeDQL(statement, sqlQuery);
        while (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String sex = rs.getString("sex");
            System.out.println("姓名： " + name + ", 年龄： " + age + ", 性别： " + sex);
        }
    }

    public void executeDMLORDDLAndPrintResult() {
        ConnectionTools connectionTools = new ConnectionTools();
        Connection connection = connectionTools.getConnect();
        Statement statement = connectionTools.getStatement(connection);
        if (connectionTools.executeDMLORDDL(statement, sqlQuery) > 0) {
            System.out.println("操作成功！");
        }
    }

}
