/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.services;

import Java.Docker.entities.Transaction;
import Java.Docker.repositories.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class TransactionService {

    TransactionRepository repository;
    
    @Autowired
    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }
    
    public List<Transaction> getAll(){
        return repository.findAll();
    }
    
    public Transaction getById(int id){
        return repository.findById(id).get();
    }
    
    public Transaction save(Transaction transaction){
        boolean transactionCheck = getById(transaction.getId()) != null ? true : false;
        Transaction t = null;
        try {
            return t = repository.save(transaction);
        } catch (Exception e) {
            return t;
        }
    }
    
    public boolean delete(int id){
        try {
            Transaction t = getById(id);
            repository.delete(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
