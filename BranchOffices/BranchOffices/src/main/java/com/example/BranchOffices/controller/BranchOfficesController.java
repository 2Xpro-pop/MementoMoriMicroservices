package com.example.BranchOffices.controller;
import com.example.BranchOffices.Models.BranchOffices;
import com.example.BranchOffices.Services.BranchOfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/office")
public class BranchOfficesController {
    @Autowired BranchOfficesService service;

    @GetMapping("/showAll")
    public Iterable<BranchOffices> showAllData() throws SQLException {
        return service.showAll();
    }

    @GetMapping("/update")
    public ResponseEntity updateData(@RequestParam int id,@RequestParam String name,@RequestParam String address,@RequestParam double budget) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result == null) {
            return ResponseEntity.badRequest().build();
        }
        service.updateById(new BranchOffices(id,name,address,budget));
        return ResponseEntity.ok().build();
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

    @GetMapping("/add")
    public void insertData(@RequestParam String name,@RequestParam String address,@RequestParam double budget) throws SQLException, ClassNotFoundException {
        service.insertData(new BranchOffices(-1,name,address,budget));
    }

    @GetMapping("/change-to")
    public ResponseEntity changeTo(@RequestParam int id,@RequestParam double budget,@RequestParam String description) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result == null) {
            return ResponseEntity.badRequest().build();
        }
        service.changeTo(id,budget,description);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/show")
    public ResponseEntity<BranchOffices> showOne(@RequestParam int id) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result == null)
        {
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok(result);
    }
}
