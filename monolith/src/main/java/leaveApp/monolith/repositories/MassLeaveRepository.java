/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.repositories;

import java.util.List;
import leaveApp.monolith.entities.MassLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Laila
 */
@Repository
public interface MassLeaveRepository extends JpaRepository<MassLeave, String> {
//    @Query("SELECT r FROM MassLeave r WHERE r.employee like %?1% "
//            + "or r.id like %?1% ")
//    public List<MassLeave> search(String keyword);
//    public List<MassLeave> findByEmployeeId(String employee);
}
