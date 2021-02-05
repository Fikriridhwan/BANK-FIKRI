/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "nasabah")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nasabah.findAll", query = "SELECT n FROM Nasabah n")
    , @NamedQuery(name = "Nasabah.findById", query = "SELECT n FROM Nasabah n WHERE n.id = :id")
    , @NamedQuery(name = "Nasabah.findByNama", query = "SELECT n FROM Nasabah n WHERE n.nama = :nama")
    , @NamedQuery(name = "Nasabah.findByAlamat", query = "SELECT n FROM Nasabah n WHERE n.alamat = :alamat")
    , @NamedQuery(name = "Nasabah.findByTempatLahir", query = "SELECT n FROM Nasabah n WHERE n.tempatLahir = :tempatLahir")
    , @NamedQuery(name = "Nasabah.findByNomorHandphone", query = "SELECT n FROM Nasabah n WHERE n.nomorHandphone = :nomorHandphone")
    , @NamedQuery(name = "Nasabah.findByKtp", query = "SELECT n FROM Nasabah n WHERE n.ktp = :ktp")})
public class Nasabah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "tempat_lahir")
    private String tempatLahir;
    @Basic(optional = false)
    @Column(name = "nomor_handphone")
    private String nomorHandphone;
    @Basic(optional = false)
    @Column(name = "ktp")
    private String ktp;
    @Basic(optional = true)
    @Column(name = "npwp")
    private String npwp;
    

    public Nasabah() {
    }

    public Nasabah(Integer id) {
        this.id = id;
    }

    public Nasabah(Integer id, String nama, String alamat, String tempatLahir, String nomorHandphone, String ktp) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.tempatLahir = tempatLahir;
        this.nomorHandphone = nomorHandphone;
        this.ktp = ktp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getNomorHandphone() {
        return nomorHandphone;
    }

    public void setNomorHandphone(String nomorHandphone) {
        this.nomorHandphone = nomorHandphone;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nasabah)) {
            return false;
        }
        Nasabah other = (Nasabah) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Java.Docker.entities.Nasabah[ id=" + id + " ]";
    }
    
}
