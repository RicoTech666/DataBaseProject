package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlQueryUtil {
    private String sqlQuery;

    public SqlQueryUtil(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public ResultSet executeQueryAndGetResult() {
        ConnectionTools connectionTools = new ConnectionTools();
        Connection connection = connectionTools.getConnect();
        Statement statement = connectionTools.getStatement(connection);
        return connectionTools.executeSQL(statement, sqlQuery);
    }


}
