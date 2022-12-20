package com.example.InventoryServiceSecond.service;

import com.example.InventoryServiceSecond.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class InventoryService {
    static final int BranchPort = 3512;
    static final String serverName = "10.5.0.5";
    static final String dbName = "memento_mori";
    static final String url = "jdbc:sqlserver://" +serverName + ":1433;database="+ dbName + ";encrypt=true;trustServerCertificate=true;";
    public final String user = "SA";
    public final String password = "Abcdefg-12345";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public Inventory selectById(int id) throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection connection = DriverManager.getConnection(url, user,password);

        String sql = """ 
            SELECT * FROM dbo.Inventory WHERE id=?""";

        var prst = connection.prepareStatement(sql);
        prst.setInt(1, id);

        var result = prst.executeQuery();

        if (!result.next()) {
            return null;
        }

        var value = new Inventory(result.getInt(1), result.getString(2),result.getInt(3),result.getInt(4));

        connection.close();

        return value;
    }

    public Iterable<Inventory> showAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user,password);

        String sql = """ 
            SELECT * FROM dbo.Inventory
            """;

        var prst = connection.prepareStatement(sql);

        var result = prst.executeQuery();

        var results = new ArrayList<Inventory>();

        while (result.next()){
            results.add(new Inventory(result.getInt(1), result.getString("Name"),result.getInt("Amount"), result.getInt("BranchOfficeId")));
        }
        connection.close();

        return results;
    }

    public void buy(Inventory inventory, double price) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = """ 
                INSERT INTO dbo.Inventory VALUES (?,?,?)""";

        var prst = connection.prepareStatement(sql);

        prst.setString(1, inventory.getName());
        prst.setInt(2, inventory.getAmount());
        prst.setInt(3, inventory.getBranchOfficeId());


        prst.execute();

        connection.close();


        var rest = restTemplateBuilder.build();
        var urlBuilder = UriComponentsBuilder.fromHttpUrl("http://10.5.0.7:"+BranchPort+"/office/change-to")
                .queryParam("id", inventory.getBranchOfficeId())
                .queryParam("budget", -price*inventory.getAmount())
                .queryParam("description", "Bought "+ inventory.getName());
        var query = urlBuilder.build().toUriString();

        rest.getForObject(query, String.class);

    }

    public boolean Decrease(int id, int amount) throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = """ 
                UPDATE Inventory 
                SET Amount=?
                WHERE id=?""";

        var prst = connection.prepareStatement(sql);

        prst.setInt(2, id);

        int AmountInStock = (selectById(id)).getAmount();
        if (AmountInStock-amount >= 0) {
            prst.setInt(1, AmountInStock-amount);
        } else {
            connection.close();
            return false;
        }

        prst.execute();

        connection.close();

        return true;
    }
}


