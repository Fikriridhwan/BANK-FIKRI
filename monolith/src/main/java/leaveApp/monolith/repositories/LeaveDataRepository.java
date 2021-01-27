/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.repositories;

import java.util.List;
import leaveApp.monolith.entities.LeaveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Laila
 */
@Repository
public interface LeaveDataRepository extends JpaRepository<LeaveData, String> {
    List<LeaveData> findByIdNot(@Param("id") String id);
}
