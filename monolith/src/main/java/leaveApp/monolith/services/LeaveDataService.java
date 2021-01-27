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
import leaveApp.monolith.entities.LeaveData;
import leaveApp.monolith.repositories.LeaveDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laila
 */
@Service
public class LeaveDataService {

    LeaveDataRepository leaveDataRepository;

    @Autowired
    public LeaveDataService(LeaveDataRepository leaveDataRepository) {
        this.leaveDataRepository = leaveDataRepository;
    }

    public List<LeaveData> getAll() {
        return leaveDataRepository.findAll();
    }
    
    public Optional<LeaveData> getById(String id) {
        return leaveDataRepository.findById(id);
    }

    public LeaveData save(LeaveData request) {
        boolean requestCheck = getById(request.getId()).isPresent();
//     if(requestCheck){
//         
//         return update(request);
//     }
        try {
            return leaveDataRepository.save(request);
//         notificationService.sendNotification(request.getEmail());
//         return  "success";

        } catch (Exception e) {
            System.out.println(e);
//         return  "error";
            return null;
        }
     
    }

    public boolean delete(String id) {
        boolean requestCheck = getById(id).isPresent();
        if (requestCheck) {
            leaveDataRepository.delete(getById(id).get());
            return true;
        } else {
            return false;
        }
    }

    public boolean update(LeaveData leaveData) {
        Optional<LeaveData> leaveOptional = getById(leaveData.getId());
        if (leaveOptional.isPresent()) {
            leaveOptional.get().setName(leaveData.getName());
            leaveOptional.get().setDuration(leaveData.getDuration());
            leaveDataRepository.save(leaveOptional.get());
            return true;
        }
        return false;
    }
    
    public List<LeaveData> spesialLeave(String id){
        return leaveDataRepository.findByIdNot(id);
    }

}
