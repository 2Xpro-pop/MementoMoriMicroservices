package com.example.demo;

import com.example.demo.model.BudgetHistory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionHelper {
    static final String serverName = "WINDOWS-CQ7O1HH";
    static final String dbName = "momento_mori";
    static final String url = "jdbc:sqlserver://" +serverName + ";database="+ dbName + ";encrypt=true;trustServerCertificate=true;";
    static final String user = "erki";
    static final String password = "abcdefg";

    public static void Open(String sql, SqlStatementReader action) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);

            String query = """ 
                    SELECT * FROM dbo.BudgetHistory
                    """;

            var prst = connection.prepareStatement(query);
            action.run(prst);
        }
        finally {
            try{
                connection.close();
            } catch (Exception exception) {

            }

        }

    }

    public interface SqlStatementReader
    {
        void run(PreparedStatement preparedStatement);
    }

}
