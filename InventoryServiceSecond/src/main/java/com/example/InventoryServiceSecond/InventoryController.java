package com.example.InventoryServiceSecond;

import com.example.InventoryServiceSecond.model.Inventory;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.InventoryServiceSecond.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class InventoryController {
    @Autowired
    InventoryService service;

    @GetMapping("/show")
    public ResponseEntity<Inventory> showHistoryOne(@RequestParam int id) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/showAll")
    public Iterable<Inventory> showHistory() throws SQLException {
        return service.showAll();
    }

    @GetMapping("/buy")
    public void buy(@RequestParam String name, int amount, double price, int BranchOfficeId) throws SQLException {
        service.buy(new Inventory(-1, name, amount, BranchOfficeId), price);
    }

    @GetMapping("/decrease")
    public ResponseEntity buy(@RequestParam int id, int amount) throws SQLException, ClassNotFoundException {
        if(!service.Decrease(id, amount))
        {
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok().build();
    }
}
