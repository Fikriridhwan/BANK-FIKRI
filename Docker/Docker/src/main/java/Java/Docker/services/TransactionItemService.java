/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.services;

import Java.Docker.entities.TransactionItem;
import Java.Docker.repositories.TransactionItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class TransactionItemService {
    
    TransactionItemRepository repository;

    @Autowired
    public TransactionItemService(TransactionItemRepository itemRepository) {
        this.repository = itemRepository;
    }
    
    public List<TransactionItem> getAll(){
        return repository.findAll();
    }
    
    public TransactionItem getById(int id){
        return repository.findById(id).get();
    }
    
    public TransactionItem save(TransactionItem transaction){
        boolean transactionItemCheck = getById(transaction.getId()) != null ? true : false;
        TransactionItem t = null;
        try {
            return t = repository.save(transaction);
        } catch (Exception e) {
            return t;
        }
    }
    
    public boolean delete(int id){
        try {
            TransactionItem t = getById(id);
            repository.delete(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
