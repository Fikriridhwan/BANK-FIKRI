/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import leaveApp.monolith.entities.LeaveData;
import leaveApp.monolith.repositories.LeaveDataRepository;
import leaveApp.monolith.services.LeaveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Laila
 */
@Controller
public class LeaveDataController {
   @Autowired
    private LeaveDataService leaveDataService;
    
@Autowired
    LeaveDataRepository leaveDataRepository;
    
        @GetMapping("/leavedata")
    public String awal() {
            System.out.println(leaveDataService.getAll());
        return "index";
    }
    
//    @GetMapping("/ad")
//    public String admin() {
//            System.out.println(leaveDataService.getAll());
//        return "admin";
//    }
//  @GetMapping("")
//public String index( Model model){
//   model.addAttribute("leaveData", new LeaveData());
////    model.addAttribute("leaveDatas", leaveDataService.getAll());
//   
//    return "coba";
//}
//    @ResponseBody
//@GetMapping("/insertall")
//public List<LeaveData> saveAll() throws ParseException{
//    LeaveData leaveData = new LeaveData();
////    leaveDataService.save(leaveData);
////    System.out.println(leaveDataService.getAll().toString());
//leaveDataService.saveAll();
//        System.out.println(leaveDataService.getAll().toString());
//    return leaveDataService.saveAll();
//    
//    
//}

@ResponseBody
@GetMapping("/leavedata/insert")
public LeaveData save() throws ParseException{
    LeaveData leaveData = new LeaveData();
    leaveData.setId("LD02");
    leaveData.setName("Cuti Nikah Lagi");
    leaveData.setDuration(4);  
    
//    leaveDataService.save(leaveData);
//    System.out.println(leaveDataService.getAll().toString());
//leaveDataService.saveAll();
        System.out.println(leaveDataService.getAll().toString());
    return leaveDataService.save(leaveData);
    
    
}


    @ResponseBody
@GetMapping("/leavedata/update")
public boolean update() throws ParseException{
    LeaveData leaveData = new LeaveData();
    leaveData.setId("LD01");
    leaveData.setName("Cuti Nikaaaaah");
    leaveData.setDuration(4);  
    
//        leaveData.setAge(23);
//    leaveDataService.save(leaveData);
//    System.out.println(leaveDataService.getAll().toString());
//leaveDataService.saveAll();
        System.out.println(leaveDataService.getAll().toString());
    return leaveDataService.update(leaveData);
    
    
}
@ResponseBody
@RequestMapping("/leavedata/delete/{id}")
public boolean delete(@PathVariable String id){
    System.out.println(leaveDataService.getAll().toString());
//    leaveDataService.delete(id);
    
    return leaveDataService.delete(id);
    
}
@ResponseBody
@RequestMapping("/leavedata/getById/{id}")
public Optional<LeaveData> getById(@PathVariable @Valid String id){
//    Optional<LeaveData> leaveData= leaveDataRepository.findAllById(id);
//leaveData.addAttribute("leaveData", new LeaveData()); 
return leaveDataService.getById(id);

  }
//@ResponseBody
//@RequestMapping("/leavedata/getByEmployeeId/{employeeId}")
//public List<LeaveData> getByEmployeeId(@PathVariable String employeeId){
////    Optional<LeaveData> leaveData= leaveDataRepository.findAllById(id);
////leaveData.addAttribute("leaveData", new LeaveData()); 
//return leaveDataService.getByEmployeeId(employeeId);
////     return "index";
////    if (leaveData.isPresent()) {
////      return new ResponseEntity<>(leaveData.get(), HttpStatus.OK);
////    } else {
////      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////    }
//  }
//@ResponseBody
//@RequestMapping("/search/{keyword}")
//public List<LeaveData> search (@PathVariable @Valid String keyword){
//    return leaveDataService.search(keyword);
//}
//@RequestMapping("/search")
//public String viewHomePage(Model model){
//String keyword="a";
//    List<LeaveData> listLeaveDatas= leaveDataService.listAll(keyword);
//    model.addAttribute("listLeaveDatas", listLeaveDatas);
////    model.addAttribute("keyword", keyword);
//    return "index";
//}

//@GetMapping("/search")
//public String search (Model model, @LeaveDataParam String keyword){
//        List<LeaveData> leaveDatas = (List<LeaveData>) leaveDataRepository.search(keyword);
//    model.addAttribute("leaveDatas", leaveDatas);
//    return "showSearch";
//}

}

