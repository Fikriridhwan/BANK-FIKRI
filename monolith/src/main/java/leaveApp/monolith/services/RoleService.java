/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.services;

import java.util.List;
import java.util.Optional;
import leaveApp.monolith.entities.Role;
import leaveApp.monolith.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class RoleService {
    
    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    public List<Role> getAll(){
        return roleRepository.findAll();
    }
    
    public Optional<Role> getById(String id){
        Optional<Role> person = roleRepository.findById(id);
        return person;
    }
    
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public String delete(String id) {
        try {
            roleRepository.deleteById(id);
            return "delete succes id:" + id;
        } catch (Exception e) {
            return "detele failed";
        }
    }    
    
}
