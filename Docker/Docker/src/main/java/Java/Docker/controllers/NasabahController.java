/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.controllers;

import Java.Docker.entities.Nasabah;
import Java.Docker.services.NasabahService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RequestMapping("api/nasabah")
@RestController
public class NasabahController {

    NasabahService service;

    @Autowired
    public NasabahController(NasabahService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Nasabah> getAll() {
        return service.getAll();
    }

    @GetMapping("/search/{ktp}")
    public Nasabah getByKtp(@PathVariable String ktp) {
        return service.findByKtp(ktp);
    }

    @PostMapping("/save")
    public Nasabah insert(@RequestBody Nasabah nasabah) {
        if (nasabah.getId() != 0) {
            Nasabah temp = service.getById(nasabah.getId());
            if (nasabah.getAlamat() != null) {
                temp.setAlamat(nasabah.getAlamat());
            }
            if (nasabah.getNomorHandphone() != null) {
                temp.setNomorHandphone(nasabah.getNomorHandphone());
            }
            Nasabah result = service.save(temp);
            return result;
        }
        Nasabah result = service.save(nasabah);
        return result;
    }

    @DeleteMapping("{id}")
    public Map<String, String> delete(@PathVariable int id) {
        boolean isSuccess = service.delete(id);
        Map<String, String> status = new HashMap<>();
        if (isSuccess) {
            status.put("status", "200");
            return status;
        } else {
            return status;
        }
    }

}
