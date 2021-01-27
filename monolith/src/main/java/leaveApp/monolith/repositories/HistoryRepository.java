/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.repositories;

import java.util.List;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Laila
 */
@Repository
public interface HistoryRepository extends JpaRepository<History, String>{
    List<History> findByManager(@Param("manager") Employee manager);
    List<History> findByStatus(@Param("status") String status);
    List<History> findByStatusAndManager(@Param("status") String status, @Param("manager") Employee manager);
}
