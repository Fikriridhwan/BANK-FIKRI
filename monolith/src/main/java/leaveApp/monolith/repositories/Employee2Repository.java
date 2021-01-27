/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.repositories;

import java.util.Optional;
import leaveApp.monolith.entities.Contact;
import leaveApp.monolith.entities.Employee2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
@Repository
public interface Employee2Repository extends JpaRepository<Employee2, String> {

    @Transactional
    @Query(value = "SELECT * FROM db_hr.employee2 "
            + "WHERE db_hr.employee2.id=?1 ", nativeQuery = true)
    public Optional<Employee2> getEmployee2ById(String id);

    @Query(value = "SELECT * FROM db_hr.contact "
            + "WHERE db_hr.contact.id=?1 ", nativeQuery = true)
    public Optional<Contact> getContactById(String id);
}
