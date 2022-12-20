package com.example.demo.services;

import com.example.demo.model.BudgetHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
@Service
public class BudgetService {
    static final String serverName = "localhost";
    static final String dbName = "memento_mori";
    static final String url = "jdbc:sqlserver://" +serverName + ":178;database="+ dbName + ";encrypt=true;trustServerCertificate=true;";
    public final String user = "SA";
    public final String password = "Abcdefg-12345";


    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public BudgetHistory selectById(int id) throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection connection = DriverManager.getConnection(url, user,password);

        String sql = """ 
            SELECT * FROM dbo.BudgetHistory WHERE id=?""";

        var prst = connection.prepareStatement(sql);
        prst.setInt(1, id);

        var result = prst.executeQuery();

        result.next();

        var value = new BudgetHistory(result.getInt(1),result.getDouble(2),result.getString(3),result.getInt(4));

        connection.close();

        return value;
    }

    public Iterable<BudgetHistory> showAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user,password);

        String sql = """ 
            SELECT * FROM dbo.BudgetHistory
            """;

        var prst = connection.prepareStatement(sql);

        var result = prst.executeQuery();

        var results = new ArrayList<BudgetHistory>();

        while (result.next()){

            results.add(new BudgetHistory(result.getInt("id"),result.getDouble("action"),result.getString("description"),result.getInt("BranchOfficeId")));
        }
        connection.close();

        return results;
    }

    public void Add(BudgetHistory history) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user,password);

        String sql = """ 
            INSERT INTO dbo.BudgetHistory VALUES (?,?,?)""";

        var prst = connection.prepareStatement(sql);

        prst.setDouble(1, history.getAction());
        prst.setString(2, history.getDescription());
        prst.setInt(3,history.getBranchOfficeId());

        prst.execute();

        connection.close();
    }
    public BudgetHistory delete(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = """ 
                DELETE FROM dbo.BudgetHistory WHERE id=?""";

        var prst = connection.prepareStatement(sql);

        prst.setInt(1, id);

        prst.execute();

        connection.close();
        return null;
    }
}
