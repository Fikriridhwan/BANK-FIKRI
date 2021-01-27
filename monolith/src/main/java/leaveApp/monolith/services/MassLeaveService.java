/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.LeaveData;
import leaveApp.monolith.entities.MassLeave;
import leaveApp.monolith.repositories.MassLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laila
 */

    @Service
public class MassLeaveService {
  
    
    MassLeaveRepository requestRepository;
@Autowired
    public MassLeaveService(MassLeaveRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

  //read
 public List<MassLeave> getAll(){
     return requestRepository.findAll();
 }
 //create/update
 public MassLeave save(MassLeave request){
     boolean requestCheck = getById(request.getId()).isPresent();
//     if(requestCheck){
//         
//         return update(request);
//     }
     try {
         return requestRepository.save(request);
//         notificationService.sendNotification(request.getEmail());
//         return  "success";
         
     } catch (Exception e) {
         System.out.println(e);
//         return  "error";
return null;
     }
//     MassLeave save = requestRepository.save(request);
//    return requestRepository.save(request);
//     
 }
// public List <MassLeave> saveAll() throws ParseException{
//     List<MassLeave> requests = new ArrayList<>();
//     String start1="03-03-2020 00:00:00";
//     String end1="08-03-2020 00:00:00";
//     Employee e= new Employee("02");
//      LeaveData le= new LeaveData("L01");
//Date startdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start1);
//Date enddate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end1);
//
//     requests.add(new MassLeave("R2", e, le, startdate, enddate, "level1"));
//     System.out.println(requests);
//    return requestRepository.saveAll(requests);
// }
 //delete
 public boolean delete (String id){
     boolean requestCheck = getById(id).isPresent();
     if(requestCheck){
         requestRepository.delete(getById(id).get());
         return true;
     }
     else{
     return false;
     }
 }
 public Optional<MassLeave> getById(String id){
     return requestRepository.findById(id);
 }
//  public List<MassLeave> getByEmployeeId(String employeeId){
////     List<MassLeave> requests= new ArrayList<>();
////     requestRepository.findByEmployeeId(employeeId).forEach(requests::add);
////      return requests;
//return requestRepository.findByEmployeeId(employeeId);
// }

    
//    public Optional<Region> findById(int id) {
//    return regionRepository.findById(id);
//    }
//public List<MassLeave> listAll(String keyword){
//    if(keyword !=null){
//        return requestRepository.search(keyword);
//    }
//    return requestRepository.findAll();
//}
//
//    public List<MassLeave> search(String keyword) {
//        List<MassLeave> requests = (List<MassLeave>) requestRepository.search(keyword);
//        return requests;
//  
//    }
    public boolean update (MassLeave massLeave){
        Optional<MassLeave> massOptional= getById(massLeave.getId());
        if(massOptional.isPresent()){
            massOptional.get().setName(massLeave.getName());
            massOptional.get().setStartDate(massLeave.getStartDate());
            massOptional.get().setEndDate(massLeave.getEndDate());
            requestRepository.save(massOptional.get());
            return true;
        }
        return false;
    }
   
    }
