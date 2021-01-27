/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.repositories;

import Java.Docker.entities.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface NasabahRepository extends JpaRepository<Nasabah, Integer>{
    Nasabah findByKtp(@Param("ktp") String ktp);
}
