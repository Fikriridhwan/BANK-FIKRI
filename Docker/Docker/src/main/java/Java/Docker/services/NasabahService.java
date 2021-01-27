/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.services;

import Java.Docker.entities.Nasabah;
import Java.Docker.repositories.NasabahRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class NasabahService {

    NasabahRepository repository;

    @Autowired
    public NasabahService(NasabahRepository repository) {
        this.repository = repository;
    }

    public List<Nasabah> getAll() {
        return repository.findAll();
    }

    public Nasabah getById(int id) {
        return repository.findById(id).get();
    }

    public Nasabah findByKtp(String ktp) {
        return repository.findByKtp(ktp);
    }

    public Nasabah save(Nasabah nasabah) {
        Nasabah n = null;
        try {
            return n = repository.save(nasabah);
        } catch (Exception e) {
            return n;
        }
    }

    public boolean delete(int id) {
        try {
            Nasabah n = getById(id);
            repository.delete(n);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
