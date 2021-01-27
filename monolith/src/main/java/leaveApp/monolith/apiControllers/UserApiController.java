/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.apiControllers;

import java.util.ArrayList;
import java.util.List;
import leaveApp.monolith.config.UserRefrence;
import leaveApp.monolith.config.UserRefrenceService;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.User;
import leaveApp.monolith.entities.UserRole;
import leaveApp.monolith.services.EmployeeService;
import leaveApp.monolith.services.RoleService;
import leaveApp.monolith.services.UserRoleService;
import leaveApp.monolith.services.UserService;
import leaveApp.monolith.viewmodels.EmployeeVM;
import leaveApp.monolith.viewmodels.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

/**
 *
 * @author User
 */
@RequestMapping("api")
@RestController
public class UserApiController {

    UserService userService;
    UserRoleService userRoleService;
    RoleService roleService;
    EmployeeService employeeService;
    UserRefrenceService refrenceService;
    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserApiController(UserRefrenceService refrenceService, AuthenticationManager authenticationManager, EmployeeService employeeService, UserService userService, UserRoleService userRoleService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.refrenceService = refrenceService;
        this.authenticationManager = authenticationManager;
        this.employeeService = employeeService;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth")
    public UserVM createAuthentication(@RequestBody UserVM userVM) throws Exception {
        System.out.println(userVM);
        try {
            authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(userVM.getUsername(), userVM.getPassword())));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        UserRefrence userDetails = refrenceService.loadUserByUsername(userVM.getUsername());
        System.out.println(userDetails.getUsername());
        return new UserVM("0", userDetails.getUsername(), userDetails.getPassword());
    }

    @GetMapping("/user/getById/{id}")
    public UserVM employeeGetById(@PathVariable String id) {
        User u = userService.getbyId(id).get();

        UserVM userVM = new UserVM(u.getId(), u.getUsername(), u.getPassword(), u.getCode());
        List<UserRole> ur = userRoleService.findAllRole(u.getId());
        List<String> role = new ArrayList<>();

        for (UserRole urData : ur) {
            role.add(urData.getRole().getName());
        }

        userVM.setlistRole(role);
        return userVM;
    }

    @GetMapping("/user/getByUsername/{username}")
    public UserVM userGetByUsername(@PathVariable String username) {
        User u = userService.getByUsername(username);

        UserVM userVM = new UserVM(u.getId(), u.getUsername(), u.getPassword());
        List<UserRole> ur = userRoleService.findAllRole(u.getId());
        List<String> role = new ArrayList<>();

        for (UserRole urData : ur) {
            role.add(urData.getRole().getName());
        }

        userVM.setlistRole(role);
        return userVM;
    }

    @PostMapping("/sendEmail")
    public EmployeeVM sendEmail(@RequestBody EmployeeVM employeeVM) throws Exception {
        System.out.println(employeeVM);
        String code = StringUtils.randomAlphanumeric(10).toUpperCase();
        Employee emp = employeeService.getbyId(employeeVM.getId()).get();
        System.out.println(emp.getEmail());
        User u = userService.getbyId(emp.getId()).get();
//        if (emp.getEmail().equals(employeeVM.getEmail())) {
        u.setCode(code);
        User user = userService.save(u);
        System.out.println(user + " " + user.getUsername() + " " + user.getCode());
        boolean resultEmail = employeeService.sendEmail(emp, u);
        if (resultEmail) {
            return new EmployeeVM(emp.getId());
        }
//        }
        return null;
    }

    @PostMapping("/sendNewPassword")
    public UserVM sendPass(@RequestBody UserVM userVM) throws Exception {
        String password = passwordEncoder.encode(userVM.getPassword());
//        System.out.println(userVM.getId()+", "+userVM.getUsername());
        System.out.println("password: " + password);
        User u = userService.save(new User(userVM.getId(), userVM.getUsername(), password, userVM.getCode()));
        System.out.println("user pass: " + u.getPassword());
        UserVM result = new UserVM();
        result.setId(u.getId());
        return result;
    }

}
