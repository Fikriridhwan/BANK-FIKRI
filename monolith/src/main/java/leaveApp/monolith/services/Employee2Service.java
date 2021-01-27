/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.services;

import java.util.Optional;
import leaveApp.monolith.entities.Contact;
import leaveApp.monolith.entities.Employee2;
import leaveApp.monolith.repositories.ContactRepository;
import leaveApp.monolith.repositories.Employee2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laila
 */
@Service
public class Employee2Service {
    Employee2Repository employee2Repository;
    ContactRepository contactRepository;

    @Autowired
   public Employee2Service(Employee2Repository employee2Repository, ContactRepository contactRepository) {
        this.employee2Repository = employee2Repository;
        this.contactRepository = contactRepository;
    }
 
     public Optional<Employee2> getbyId(String id) {
         
        return employee2Repository.getEmployee2ById(id);
        
    }

    
public Optional<Contact> getContactbyId(String id) {
    employee2Repository.getEmployee2ById(id);
         System.out.println(employee2Repository.getContactById(id));
        return contactRepository.getContactById(id);
        
    }
   

}
