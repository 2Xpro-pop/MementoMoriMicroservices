package com.example.BranchOffices.Services;

import com.example.BranchOffices.Models.BranchOffices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
@Service
public class BranchOfficesService {
    static final int budgetPort = 8000;
    static final String serverName = "DESKTOP-1G0TK5F";
    static final String dbName = "memento_mori";
    static final String url = "jdbc:sqlserver://" +serverName + ";database="+ dbName + ";encrypt=true;trustServerCertificate=true;";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public void updateById(BranchOffices branchOffices) throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection connection = DriverManager.getConnection(url, "nazima","abcdefg");

        String sql = """ 
                UPDATE dbo.BranchOffices SET name=? , address=?, budget=? WHERE id=?
                """;

        var prst = connection.prepareStatement(sql);
        prst.setString(1,branchOffices.getName());
        prst.setString(2,branchOffices.getAddress());
        prst.setDouble(3,branchOffices.getBudget());
        prst.setInt(4,branchOffices.getId());

        prst.execute();
        connection.close();
    }

    public Iterable<BranchOffices> showAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, "nazima","abcdefg");

        String sql = """ 
                SELECT * FROM dbo.BranchOffices
                """;
        var prst = connection.prepareStatement(sql);

        var result = prst.executeQuery();
        var results = new ArrayList<BranchOffices>();

        while(result.next()){
            results.add(new BranchOffices(result.getInt("id"),result.getString("name"),result.getString("address"),result.getDouble("budget")));
        }
        connection.close();

        return  results;
    }

    public void deleteById(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "nazima","abcdefg");
        String sql = """ 
                DELETE FROM dbo.BranchOffices WHERE id=?;
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1,id);

        prst.execute();
        connection.close();
    }

    public void insertData(BranchOffices branchOffices) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "nazima","abcdefg");

        String sql = """ 
                INSERT INTO dbo.BranchOffices VALUES (?,?,?)
                """;

        var prst = connection.prepareStatement(sql);
        prst.setString(1,branchOffices.getName());
        prst.setString(2,branchOffices.getAddress());
        prst.setDouble(3,branchOffices.getBudget());

        prst.execute();
        connection.close();
    }

    public void changeTo(int id,double budget,String description) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "nazima","abcdefg");

        String sql = """ 
                UPDATE dbo.BranchOffices SET budget=budget+? WHERE id=?
                """;
        var prst = connection.prepareStatement(sql);

        prst.setDouble(1,budget);
        prst.setInt(2,id);
        prst.execute();
        connection.close();

        var rest = restTemplateBuilder.build();
        var urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:"+budgetPort+"/budget/income")
                .queryParam("moneyAmount", budget)
                .queryParam("description", description)
                .queryParam("BranchOfficeId",id);
        var query = urlBuilder.build().toUri().toString();

        rest.getForObject(query, String.class);
    }




    public BranchOffices selectById(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "nazima","abcdefg");

        String sql = """ 
                SELECT * FROM dbo.BranchOffices WHERE id=?
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1, id);
        var result = prst.executeQuery();
        if(!result.next())
            return null;

        var value = new BranchOffices(result.getInt(1),result.getString(2),result.getString(3),result.getDouble(4));

        connection.close();
        return value;
    }
}
