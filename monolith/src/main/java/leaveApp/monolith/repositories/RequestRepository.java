/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.repositories;

import java.util.List;
import java.util.Optional;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.LeaveData;
import leaveApp.monolith.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Laila
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, String> {

    List<Request> findByEmployee(@Param("employee") Employee employee);

    List<Request> findByApprovalNotAndEmployeeAndLeaveData(@Param("approval") String approval, @Param("employee") Employee employee, @Param("leavedata") LeaveData leaveData);

    List<Request> findByApprovalNotAndEmployeeAndLeaveDataNot(@Param("approval") String approval, @Param("employee") Employee employee, @Param("leavedata") LeaveData leaveData);

    Optional<Request> findByLeaveData(@Param("leavedata") LeaveData leaveData);

    @Modifying
    @Transactional
    @Query(value = "UPDATE request r SET r.approval ='level2' WHERE r.id  =?1", nativeQuery = true)
    void picApprove(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE request r SET r.approval ='concluded' WHERE r.id  =?1", nativeQuery = true)
    void picReject(@Param("id") String id);

    @Query(value = "SELECT * FROM request r WHERE r.approval = 'level1'", nativeQuery = true)
    List<Request> getAllLevel1();

    @Query(value = "SELECT * FROM request r WHERE r.approval = 'level2'", nativeQuery = true)
    List<Request> getAllLevel2();

    @Query(value = "SELECT * FROM request r WHERE r.approval = 'concluded'", nativeQuery = true)
    List<Request> getAllConcluded();
//---------------------Manager----------------------------

    @Modifying
    @Transactional
    @Query(value = "UPDATE request r SET r.approval ='concluded' WHERE r.id  =?1", nativeQuery = true)
    void managerAction(@Param("id") String id);
}
