/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.repositories;

import java.util.List;
import leaveApp.monolith.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
    List<UserRole> findByUserId(String userId);
    
    @Query(value = "SELECT * FROM user_role WHERE role='EMP01' ", nativeQuery = true)
    public List<UserRole> getByRoleEmployee();

    @Query(value = "SELECT * FROM user_role WHERE role='EMP02' ", nativeQuery = true)
    public List<UserRole> getByRolePic();

    @Query(value = "SELECT * FROM user_role WHERE role='EMP03' ", nativeQuery = true)
    public List<UserRole> getByRoleManager();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_role(user, role) values (?1,?2)", nativeQuery = true)
    public void upgradePic(String user, String role);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_role(user, role) values (?1,?2)", nativeQuery = true)
    public void insertUserrole(String user, String role);
}
