/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import leaveApp.monolith.entities.UserRole;
import leaveApp.monolith.repositories.UserRoleRepository;
import leaveApp.monolith.viewmodels.UserRoleVM;
import leaveApp.monolith.viewmodels.UserRoleVMMonolith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
@Service
public class UserRoleService {

    UserRoleRepository repository;

    @Autowired
    public UserRoleService(UserRoleRepository repository) {
        this.repository = repository;
    }
    
    public List<UserRole> getAlltoClient(){
        return repository.findAll();
    }
    
    public List<UserRole> findAllRole(String id){
        return repository.findByUserId(id);
    }
    
    public Optional<UserRole> getById(int id){
        Optional<UserRole> optional = repository.findById(id);
        return optional;
    }
    
    @Transactional
    public UserRole save(UserRole userRole) {
        return repository.save(userRole);
    }

    public String delete(int id) {
        try {
            repository.deleteById(id);
            return "delete succes id:" + id;
        } catch (Exception e) {
            return "detele failed";
        }
    }
    public List<UserRoleVMMonolith> getAll() {
        List<UserRoleVMMonolith> roleVMs = new ArrayList<>();
        List<UserRole> userRoles = repository.findAll();
        for (UserRole userRole : userRoles) {
            roleVMs.add(new UserRoleVMMonolith(userRole.getId().toString(), userRole.getUser().getId(), userRole.getRole().getId(), userRole.getUser().getUsername(),
                    userRole.getRole().getName(), userRole.getUser().getPassword(), userRole.getUser().getPassword()));

        }
        return roleVMs;
    }

    public List<UserRoleVMMonolith> getEmployee() {
        List<UserRoleVMMonolith> roleVMs = new ArrayList<>();
        List<UserRole> userRoles = repository.getByRoleEmployee();
        for (UserRole userRole : userRoles) {
            roleVMs.add(new UserRoleVMMonolith(userRole.getId().toString(), userRole.getUser().getId(), userRole.getRole().getId(), userRole.getUser().getUsername(),
                    userRole.getRole().getName(), userRole.getUser().getPassword(), userRole.getUser().getPassword()));

        }
        return roleVMs;
    }

    public List<UserRoleVMMonolith> getPic() {
        List<UserRoleVMMonolith> roleVMs = new ArrayList<>();
        List<UserRole> userRoles = repository.getByRolePic();
        for (UserRole userRole : userRoles) {
            roleVMs.add(new UserRoleVMMonolith(userRole.getId().toString(), userRole.getUser().getId(), userRole.getRole().getId(), userRole.getUser().getUsername(),
                    userRole.getRole().getName(), userRole.getUser().getPassword(), userRole.getUser().getPassword()));

        }
        return roleVMs;
    }

    public List<UserRoleVMMonolith> getManager() {
        List<UserRoleVMMonolith> roleVMs = new ArrayList<>();
        List<UserRole> userRoles = repository.getByRoleManager();
        for (UserRole userRole : userRoles) {
            roleVMs.add(new UserRoleVMMonolith(userRole.getId().toString(), userRole.getUser().getId(), userRole.getRole().getId(), userRole.getUser().getUsername(),
                    userRole.getRole().getName(), userRole.getUser().getPassword(), userRole.getUser().getPassword()));

        }
        return roleVMs;
    }

    public void upgradePic(String user, String role) {
        repository.upgradePic(user, role);

    }
    
    public void insertUserRole(String user, String role){
         repository.insertUserrole(user, role);

     }

}
