package com.example.Schedule.Service;

import com.example.Schedule.Model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
@Service
public class ScheduleService {
    static final String serverName = "10.5.0.5";
    static final String dbName = "memento_mori";
    static final String url = "jdbc:sqlserver://" +serverName + ":1433;database="+ dbName + ";encrypt=true;trustServerCertificate=true;";
    public final String user = "SA";
    public final String password = "Abcdefg-12345";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public Iterable<Schedule> showAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user,password);
        String sql = """ 
                SELECT * FROM dbo.Schedule
                """;
        var prst = connection.prepareStatement(sql);
        var result = prst.executeQuery();
        var results = new ArrayList<Schedule>();

        while(result.next()){
            results.add(new Schedule(result.getInt("id"),result.getInt("patientId"),result.getInt("postId"),result.getString("date"),result.getDouble("price")));
        }
        connection.close();
        return  results;
    }

    public void insertData(Schedule schedule) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, user,password);
        String sql = """ 
                INSERT INTO dbo.Schedule VALUES (?,?,?,?)
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1,schedule.getPatientId());
        prst.setInt(2,schedule.getPostId());
        prst.setString(3,schedule.getDate());
        prst.setDouble(4,schedule.getPrice());


        prst.execute();
        connection.close();
    }

    public void deleteById(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, user,password);
        String sql = """ 
                DELETE FROM dbo.Schedule WHERE id=?;
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1,id);

        prst.execute();
        connection.close();
    }


    public Schedule selectById(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, user,password);

        String sql = """ 
                SELECT * FROM dbo.Schedule WHERE id=?
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1, id);
        var result = prst.executeQuery();
        if(!result.next())
            return null;

        var value = new Schedule(result.getInt(1),result.getInt(2),result.getInt(3),result.getString(4),result.getDouble(5));
        connection.close();
        return value;
    }

    public void updateById(Schedule schedule) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url, user,password);

        String sql = """ 
                UPDATE dbo.Schedule SET name=? , surname=?, phoneNumber=?, address=?, email=?
                """;

        var prst = connection.prepareStatement(sql);
        prst.setInt(1,schedule.getPatientId());
        prst.setInt(2,schedule.getPostId());
        prst.setString(3,schedule.getDate());
        prst.setDouble(4,schedule.getPrice());


        prst.execute();
        connection.close();
    }

}
