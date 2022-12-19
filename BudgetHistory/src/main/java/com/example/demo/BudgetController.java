package com.example.demo;

import com.example.demo.model.BudgetHistory;
import com.example.demo.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    @Autowired BudgetService service;


    @GetMapping("/show")
    public BudgetHistory showHistoryOne(@RequestParam int id) throws SQLException, ClassNotFoundException {
        return service.selectById(id);
    }

    @GetMapping("/showAll")
    public Iterable<BudgetHistory> showHistory() throws SQLException {
        return service.showAll();
    }

    @GetMapping("/income")
    public Iterable<BudgetHistory> income(@RequestParam double moneyAmount,@RequestParam String description,@RequestParam int BranchOfficeId) throws SQLException {
        // http://localhost:8080/budget/income?action=14&description=ohmy
        service.Add(new BudgetHistory(-1,moneyAmount, description,BranchOfficeId));
        return service.showAll();
    }

    @GetMapping("/delete")
    BudgetHistory delete(@RequestParam int id) throws SQLException {
        return service.delete(id);
    }
}
