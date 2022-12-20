package com.example.Personal.Service;

import com.example.Personal.Model.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
@Service
public class PersonalService {
    static final int branchOfficePort = 3512;
    static final String serverName = "10.5.0.5";
    static final String dbName = "memento_mori";
    static final String url = "jdbc:sqlserver://" +serverName + ":1433;database="+ dbName + ";encrypt=true;trustServerCertificate=true;";
    public final String user = "SA";
    public final String password = "Abcdefg-12345";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public Iterable<Personal> showAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, "SA","Abcdefg-12345");
        String sql = """ 
                SELECT * FROM dbo.Personal
                """;
        var prst = connection.prepareStatement(sql);
        var result = prst.executeQuery();
        var results = new ArrayList<Personal>();

        while(result.next()){
            results.add(new Personal(result.getInt("id"),result.getString("name"),result.getString("surname"),result.getString("phoneNumber"),result.getString("address"),result.getString("email"),result.getInt("branchOfficeId"),result.getString("post"),result.getInt("salaryType"),result.getInt("salaryPercent"),result.getDouble("salaryAmount")));
        }
        connection.close();
        return  results;
    }

    public void insertData(Personal personal) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "SA","Abcdefg-12345");
        String sql = """ 
                INSERT INTO dbo.Personal VALUES (?,?,?,?,?,?,?,?,?,?)
                """;

        var prst = connection.prepareStatement(sql);
        prst.setString(1,personal.getName());
        prst.setString(2,personal.getSurname());
        prst.setString(3,personal.getPhoneNumber());
        prst.setString(4,personal.getAddress());
        prst.setString(5,personal.getEmail());
        prst.setInt(6,personal.getBranchOfficeId());
        prst.setString(7,personal.getPost());
        prst.setInt(8,personal.getSalaryType());
        prst.setInt(9,personal.getSalaryPercent());
        prst.setDouble(10,personal.getSalaryAmount());

        prst.execute();
        connection.close();
    }

    public void deleteById(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "SA","Abcdefg-12345");
        String sql = """ 
                DELETE FROM dbo.Personal WHERE id=?;
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1,id);

        prst.execute();
        connection.close();
    }


    public Personal selectById(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "SA","Abcdefg-12345");

        String sql = """ 
                SELECT * FROM dbo.Personal WHERE id=?
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1, id);
        var result = prst.executeQuery();
        if(!result.next())
            return null;

        var value = new Personal(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9),result.getInt(10),result.getDouble(11));
        connection.close();
        return value;
    }

    public void updateById(Personal personal) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "SA","Abcdefg-12345");

        String sql = """ 
                UPDATE dbo.Personal SET name=? , surname=?, phoneNumber=?, address=?, email=?, branchOfficeId=?,post=?,salaryType=?,salaryPercent=?,salaryAmount=? WHERE id=?
                """;

        var prst = connection.prepareStatement(sql);
        prst.setString(1,personal.getName());
        prst.setString(2,personal.getSurname());
        prst.setString(3,personal.getPhoneNumber());
        prst.setString(4,personal.getAddress());
        prst.setString(5,personal.getEmail());
        prst.setInt(6,personal.getBranchOfficeId());
        prst.setString(7,personal.getPost());
        prst.setInt(8,personal.getSalaryType());
        prst.setInt(9,personal.getSalaryPercent());
        prst.setDouble(10,personal.getSalaryAmount());
        prst.setInt(11,personal.getId());

        prst.execute();
        connection.close();
    }

    public void paySalaryTo(int id) throws SQLException, ClassNotFoundException {
        var personal = selectById(id);

        String description = "Salary given to "+personal.getName()+" "+personal.getSurname();

        var rest = restTemplateBuilder.build();
        var urlBuilder = UriComponentsBuilder.fromHttpUrl("http://10.5.0.7:"+branchOfficePort+"/office/change-to")
                .queryParam("id",personal.getBranchOfficeId())
                .queryParam("budget", -personal.getSalaryAmount())
                .queryParam("description",description);
        var query = urlBuilder.build().toUriString();
        rest.getForObject(query, String.class);

    }

}
