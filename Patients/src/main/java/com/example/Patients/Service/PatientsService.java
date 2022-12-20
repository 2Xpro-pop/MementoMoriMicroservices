package com.example.Patients.Service;

import com.example.Patients.Model.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
@Service
public class PatientsService {
    static final String serverName = "WINDOWS-CQ7O1HH";
    static final String dbName = "memento_mori";
    static final String url = "jdbc:sqlserver://" +serverName + ";database="+ dbName + ";encrypt=true;trustServerCertificate=true;";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public Iterable<Patients> showAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, "erki","abcdefg");
        String sql = """ 
                SELECT * FROM dbo.Patients
                """;
        var prst = connection.prepareStatement(sql);
        var result = prst.executeQuery();
        var results = new ArrayList<Patients>();

        while(result.next()){
            results.add(new Patients(result.getInt("id"),result.getString("name"),result.getString("surname"),result.getString("phoneNumber"),result.getString("address"),result.getString("email")));
        }
        connection.close();
        return  results;
    }

    public void insertData(Patients patients) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "erki","abcdefg");
        String sql = """ 
                INSERT INTO dbo.Patients VALUES (?,?,?,?,?)
                """;

        var prst = connection.prepareStatement(sql);
        prst.setString(1,patients.getName());
        prst.setString(2,patients.getSurname());
        prst.setString(3,patients.getPhoneNumber());
        prst.setString(4,patients.getAddress());
        prst.setString(5,patients.getEmail());

        prst.execute();
        connection.close();
    }

    public void deleteById(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "erki","abcdefg");
        String sql = """ 
                DELETE FROM dbo.Patients WHERE id=?;
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1,id);

        prst.execute();
        connection.close();
    }


    public Patients selectById(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "erki","abcdefg");

        String sql = """ 
                SELECT * FROM dbo.Patients WHERE id=?
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1, id);
        var result = prst.executeQuery();
        if(!result.next())
            return null;

        var value = new Patients(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6));
        connection.close();
        return value;
    }

    public void updateById(Patients patients) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, "erki","abcdefg");

        String sql = """ 
                UPDATE dbo.Patients SET name=? , surname=?, phoneNumber=?, address=?, email=?
                """;

        var prst = connection.prepareStatement(sql);
        prst.setString(1,patients.getName());
        prst.setString(2,patients.getSurname());
        prst.setString(3,patients.getPhoneNumber());
        prst.setString(4,patients.getAddress());
        prst.setString(5,patients.getEmail());


        prst.execute();
        connection.close();
    }


}
