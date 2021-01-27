/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.controllers;

import Java.Docker.entities.Transaction;
import Java.Docker.services.TransactionService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RequestMapping("api/transaction")
@RestController
public class TransactionController {

    TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }
    
    @GetMapping("")
    public List<Transaction> getAll(){
        return service.getAll();
    }
    
    @GetMapping("{id}")
    public Transaction getById(@PathVariable int id) {
        return service.getById(id);
    }
    
    @PostMapping("/save")
    public Transaction insert(@RequestBody Transaction transaction) {
        Transaction result = service.save(transaction);
        return result;
    }
    
    @DeleteMapping("{id}")
    public Map<String,String> delete(@PathVariable int id){
        boolean isSuccess = service.delete(id);
        Map<String, String> status = new HashMap<>();
        if (isSuccess) {
            status.put("status", "200");
            return status;
        }else{
            return status;
        }
    }
    
}
