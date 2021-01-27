/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.repositories;

import java.util.Optional;
import leaveApp.monolith.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Laila
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    @Transactional
    @Query(value="SELECT * FROM db_hr.contact WHERE id=?1 ", nativeQuery = true)
   public Optional<Contact> getContactById(String id);

}