package com.example.Personal.Controller;

import com.example.Personal.Model.Personal;
import com.example.Personal.Service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/personal")
public class PersonalController {
    @Autowired PersonalService service;

    @GetMapping("/showAll")
    public Iterable<Personal> showAllData() throws SQLException {
        return service.showAll();
    }

    @GetMapping("/add")
    public void insertData(@RequestParam String name,@RequestParam String surname,@RequestParam String phoneNumber,@RequestParam String address,@RequestParam String email,@RequestParam int officeId,@RequestParam String post,@RequestParam int salaryType,@RequestParam int salaryPercent,@RequestParam double salaryAmount) throws SQLException, ClassNotFoundException {
        service.insertData(new Personal(-1,name,surname,phoneNumber,address,email,officeId,post,salaryType,salaryPercent,salaryAmount));
    }

    @GetMapping("/delete")
    public ResponseEntity<Object> deleteData(@RequestParam int id) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result == null) {
            return ResponseEntity.badRequest().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/show-one")
    public ResponseEntity<Personal> showOne(@RequestParam int id) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result == null)
        {
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/update")
    public ResponseEntity updateData(@RequestParam int id,@RequestParam String name,@RequestParam String surname,@RequestParam String phoneNumber,@RequestParam String address,@RequestParam String email,@RequestParam int officeId,@RequestParam String post,@RequestParam int salaryType,@RequestParam int salaryPercent,@RequestParam double salaryAmount) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result == null) {
            return ResponseEntity.badRequest().build();
        }
        service.updateById(new Personal(id,name,surname,phoneNumber,address,email,officeId,post,salaryType,salaryPercent,salaryAmount));
        return ResponseEntity.ok().build();
    }


    @GetMapping("/pay-salary")
    public ResponseEntity paySalary(@RequestParam int id) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);

        if(result == null) {
            return ResponseEntity.badRequest().build();
        }
        service.paySalaryTo(id);
        return ResponseEntity.ok().build();
    }

}
