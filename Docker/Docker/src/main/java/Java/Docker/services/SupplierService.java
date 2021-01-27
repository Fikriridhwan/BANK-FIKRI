/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.services;

import Java.Docker.entities.Supplier;
import Java.Docker.repositories.SupplierRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class SupplierService {
    
    SupplierRepository repository;

    @Autowired
    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }
    
    public List<Supplier> getAll(){
        return repository.findAll();
    }
    
    public Supplier getById(int id){
        return repository.findById(id).get();
    }
    
    public Supplier save(Supplier supplier){
        Supplier s = null;
        try {
            return s = repository.save(supplier);
        } catch (Exception e) {
            return s;
        }
    }
    
    public boolean delete(int id){
        try {
            Supplier s = getById(id);
            repository.delete(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
    
}
