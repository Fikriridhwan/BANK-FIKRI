/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.services;

import Java.Docker.entities.Item;
import Java.Docker.repositories.ItemRepositoy;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class ItemService {
    
    ItemRepositoy repository;
    
    @Autowired
    public ItemService(ItemRepositoy itemRepositoy) {
        this.repository = itemRepositoy;
    }
    
    public List<Item> getAll(){
        return repository.findAll();
    }
    
    public Item getById(int id){
        return repository.findById(id).get();
    }
    
    public Item save(Item item){
        Item i = null;
        try {
            return i = repository.save(item);
        } catch (Exception e) {
            return i;
        }
    }
    
    public boolean delete(int id){
        try {
            Item i = getById(id);
            repository.delete(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
