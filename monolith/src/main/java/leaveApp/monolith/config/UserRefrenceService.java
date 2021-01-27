/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.config;

import java.util.ArrayList;
import java.util.List;
import leaveApp.monolith.viewmodels.UserVM;
import leaveApp.monolith.config.UserRefrence;
import leaveApp.monolith.entities.Role;
import leaveApp.monolith.entities.User;
import leaveApp.monolith.entities.UserRole;
import leaveApp.monolith.repositories.RoleRepository;
import leaveApp.monolith.repositories.UserRepository;
import leaveApp.monolith.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
//public class UserRefrenceService {
@Service
public class UserRefrenceService implements UserDetailsService {

    UserRoleRepository userRoleRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;

    public UserRefrenceService(UserRoleRepository userRoleRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRefrence loadUserByUsername(String string) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(string);        
        if (u != null) {
            UserVM userVM = new UserVM(u.getId(), u.getUsername(), u.getPassword());
            List<UserRole> ur = userRoleRepository.findByUserId(u.getId());
            List<String> role = new ArrayList<>();
            for (UserRole urData : ur) {
                Role r = roleRepository.findById(""+urData.getRole().getId()).get();
                role.add(r.getName());
            }
            userVM.setlistRole(role);
            UserRefrence refrence = new UserRefrence(userVM);

            return refrence;
        }
        throw new UsernameNotFoundException("User not found with the name " + string);
    }

}
