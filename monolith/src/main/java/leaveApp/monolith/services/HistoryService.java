/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.History;
import leaveApp.monolith.repositories.HistoryRepository;
import leaveApp.monolith.viewmodels.HistoryVMMonolith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
@Service
public class HistoryService {
    HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    //read
    public List<History> getAll() {
        return historyRepository.findAll();
    }
    
    public List<History> getAllByStatus(String status) {
        return historyRepository.findByStatus(status);
    }
    public List<History> getAllByStatusByManager(String status, String managerid) {
        return historyRepository.findByStatusAndManager(status, new Employee(managerid));
    }
    
    
    public List<History> getAllByEmployee(Employee manager) {
        return historyRepository.findByManager(manager);
    }
    
    public Optional<History> getById(String id) {
        return historyRepository.findById(id);
    }

    @Transactional
    public History save(History e) {
        return historyRepository.save(e);
    }

    public boolean delete(String id) {
        boolean requestCheck = getById(id).isPresent();
        if (requestCheck) {
            historyRepository.delete(getById(id).get());
            return true;
        }
        return false;
    }
    
    public List<HistoryVMMonolith> getAllMonolith() {
             List<HistoryVMMonolith> hMs= new ArrayList<>();
     List<History>historys = historyRepository.findAll();
     for (History history : historys) {
         hMs.add(new HistoryVMMonolith(
                 history.getId(), history.getStatus(), 
                 history.getNotes(), history.getManager().getId(), history.getRequest().getId(), history.getManager().getName()));
    }
        return hMs;
    }
}
