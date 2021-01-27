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
public class ContactService {

    ContactRepository contactRepository;
    Employee2Repository employee2Repository;

    @Autowired

    public ContactService(ContactRepository contactRepository, Employee2Repository employee2Repository) {
        this.contactRepository = contactRepository;
        this.employee2Repository = employee2Repository;
    }

    public Optional<Contact> getById(String id) {
        System.out.println(contactRepository.getContactById(id));
        return contactRepository.getContactById(id);

    }

}
