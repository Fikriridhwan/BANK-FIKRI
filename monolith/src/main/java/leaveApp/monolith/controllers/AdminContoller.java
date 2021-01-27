/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import leaveApp.monolith.entities.Contact;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.Employee2;
import leaveApp.monolith.entities.User;
import leaveApp.monolith.services.ContactService;
import leaveApp.monolith.services.Employee2Service;
import leaveApp.monolith.services.EmployeeService;
import leaveApp.monolith.services.RoleService;
import leaveApp.monolith.services.UserRoleService;
import leaveApp.monolith.services.UserService;
import leaveApp.monolith.viewmodels.EmployeeVM;
import leaveApp.monolith.viewmodels.UserRoleVMMonolith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
public class AdminContoller {

    UserService service;
    EmployeeService employeeService;
    UserRoleService urService;
    RoleService roleService;
    Employee2Service employee2Service;
    ContactService contactService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AdminContoller(UserService service, EmployeeService employeeService, UserRoleService urService, RoleService roleService, Employee2Service employee2Service, ContactService contactService, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.employeeService = employeeService;
        this.urService = urService;
        this.roleService = roleService;
        this.employee2Service = employee2Service;
        this.contactService = contactService;
        this.passwordEncoder = passwordEncoder;
    }

//============================ ADMIN =========================================
    @GetMapping("/admin")
    public String adminView(Model model) {
        List<UserRoleVMMonolith> listPic = urService.getPic();
        List<UserRoleVMMonolith> listEmp = urService.getEmployee();
        List<UserRoleVMMonolith> listMan = urService.getManager();
        System.out.println("list PIC : "+listPic);
        System.out.println("list Emp : "+listEmp);
        System.out.println("list Man : "+listMan);
        model.addAttribute("picEmployees", urService.getPic());
        model.addAttribute("employees", urService.getEmployee());
        model.addAttribute("managerEmployees", urService.getManager());
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeVM", new EmployeeVM());
        return "adminView";
    }
    
    @GetMapping("/adminManager")
    public String adminManagerView(Model model) {
        model.addAttribute("picEmployees", urService.getPic());
        model.addAttribute("employees", urService.getEmployee());
        model.addAttribute("managerEmployees", urService.getManager());
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeVM", new EmployeeVM());
        return "adminViewManager";
    }

    @GetMapping("/reAdminManager")
    public String reAdminManagerView(Model model) {
        return "redirect:/adminManager";
    }

    @GetMapping("/adminPic")
    public String adminPicView(Model model) {
        model.addAttribute("picEmployees", urService.getPic());
        model.addAttribute("employees", urService.getEmployee());
        model.addAttribute("managerEmployees", urService.getManager());
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeVM", new EmployeeVM());
        return "adminViewPic";
    }

    @GetMapping("/reAdminPic")
    public String reAdminPicView(Model model) {
        return "redirect:/adminPic";
    }

    @GetMapping("/adminEmployee")
    public String adminEmployeeView(Model model) {
        model.addAttribute("picEmployees", urService.getPic());
        model.addAttribute("employees", urService.getEmployee());
        model.addAttribute("managerEmployees", urService.getManager());
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeVM", new EmployeeVM());
        return "adminViewEmployee";
    }

    @GetMapping("/reAdminEmployee")
    public String reAdminEmployeeView(Model model) {
        return "redirect:/adminEmployee";
    }

    @ResponseBody
    @GetMapping("admin/getById")
    public EmployeeVM adminEmployeeGetById(String id) {
        System.out.println(id);
        Employee2 employee2 = new Employee2(id);
        Contact contact = new Contact(id);
        Contact c = contactService.getById(id).get();
        Employee2 e = employee2Service.getbyId(id).get();
        employee2.setBirthDate(e.getBirthDate());
        employee2.setContact(contact);
        employee2.setEndDate(e.getEndDate());
        employee2.setGender(e.getGender());
        employee2.setManager(e.getManager());
        employee2.setMaritalStatus(e.getMaritalStatus());
        employee2.setName(e.getName());
        employee2.setReligion(e.getReligion());
        employee2.setSalary(e.getSalary());
        employee2.setUniversity(e.getUniversity());
        contact.setEmail(c.getEmail());
        System.out.println(employee2.getContact().getEmail());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat StringFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date hire = e.getHireDate();
        String hiredate1 = dateFormat.format(hire);
        Date end = e.getEndDate();
        String enddate1 = dateFormat.format(end);
        EmployeeVM employeeVM = new EmployeeVM(employee2.getId(), employee2.getName(), contact.getEmail(), employee2.getGender(),
                employee2.getMaritalStatus(), hiredate1,
                enddate1, employee2.getReligion(), employee2.getManager(), 12);

        return employeeVM;
    }

    @ResponseBody
    @GetMapping("admin/getContactById")
    public Contact adminEmployeeGetContactById(String id) {

//        Employee2 employee2= new Employee2("2003");
        Contact contact = new Contact(id);
        Contact c = contactService.getById(id).get();
        contact.setEmail(c.getEmail());
        contact.setPhoneNumber(c.getPhoneNumber());
//        contact.setEmployee2(employee2);
        System.out.println(contact.getEmail());
        return contact;
    }

    @ResponseBody
    @PostMapping("/admin/consumeEmployee")
    public boolean consumeEmployee(Model model, String id, String name, String email, String gender, String marital_status, String hire_date, String end_date, String religion, String manager) {
//        EmployeeVM employeeVM= new EmployeeVM();
        System.out.println(email);
        employeeService.consumeEmployee(id, name, email, gender, marital_status, hire_date, end_date, religion, manager);
        employeeService.userInsert(id, name, passwordEncoder.encode(id));
        urService.insertUserRole(id, "EMP01");
        System.out.println(id);
        System.out.println(name);
        return true;
    }

    @ResponseBody
    @PostMapping("/admin/upgradePic")
    public boolean upgradePic(Model model, String user) {
//        EmployeeVM employeeVM= new EmployeeVM();
        urService.upgradePic(user, "EMP02");
        return true;
    }

    @ResponseBody
    @PostMapping("/admin/upgradeManager")
    public boolean upgradeManager(Model model, String user) {
//        EmployeeVM employeeVM= new EmployeeVM();
        urService.upgradePic(user, "EMP03");
        return true;
    }

    @GetMapping("/admin/deleterole")
    public String deleteRole(String id) {
//    System.out.println(requestService.getAll().toString());
        urService.delete(Integer.parseInt(id));

        return "redirect:/admin";

    }

}
