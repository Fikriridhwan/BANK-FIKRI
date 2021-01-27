/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.viewmodels;

import java.util.List;
import leaveApp.monolith.entities.Role;

/**
 *
 * @author User
 */
public class UserVM {

    private String id;
    private String username, password, code;
    private List<String> listRole;

    public UserVM() {
    }

    public UserVM(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserVM(String id, String username, String password, String code) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.code = code;
    }

    public UserVM(String id, String username, String password, List<String> listRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.listRole = listRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getListRole() {
        return listRole;
    }

    public void setlistRole(List<String> listRole) {
        this.listRole = listRole;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
