/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.repositories;

import leaveApp.monolith.entities.Employee;
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
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("SELECT e FROM Employee e WHERE e.id=?1 ")
    public Employee getEmployeeById(String id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO db_leave.employee(id, name, email, gender, marital_status, hire_date, end_date, quota, religion, manager) values (?1,?2,?3,?4,?5,?6, ?7,?8,?9,?10)", nativeQuery = true)
    public void consumeemployee(String id, String name, String email, String gender, String marital_status, String hire_date, String end_date, int quota, String religion, String manager);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO db_leave.user(id, username, password, code) values (?1,?2,?3,?4)", nativeQuery = true)
    public void insertUser(String id, String username, String password, String code);
}
