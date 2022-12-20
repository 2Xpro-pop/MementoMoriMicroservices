package com.example.Patients.Controller;

import com.example.Patients.Model.Patients;
import com.example.Patients.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequestMapping("/patients")
public class PatientsController {
    @Autowired PatientsService service;

    @GetMapping("/showAll")
    public Iterable<Patients> showAllData() throws SQLException {
        return service.showAll();
    }

    @GetMapping("/add")
    public void insertData(@RequestParam String name,@RequestParam String surname,@RequestParam String phoneNumber,@RequestParam String address,@RequestParam String email) throws SQLException, ClassNotFoundException {
        service.insertData(new Patients(-1,name,surname,phoneNumber,address,email));
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
    public ResponseEntity<Patients> showOne(@RequestParam int id) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result == null)
        {
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/update")
    public ResponseEntity updateData(@RequestParam int id,@RequestParam String name,@RequestParam String surname,@RequestParam String phoneNumber,@RequestParam String address,@RequestParam String email) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result == null) {
            return ResponseEntity.badRequest().build();
        }
        service.updateById(new Patients(id,name,surname,phoneNumber,address,email));
        return ResponseEntity.ok().build();
    }


}
