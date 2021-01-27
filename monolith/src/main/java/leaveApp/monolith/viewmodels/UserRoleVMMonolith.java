/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.viewmodels;

/**
 *
 * @author Laila
 */
public class UserRoleVMMonolith {
    String idUserRole, user, role, userName, roleName, password, code;

    public UserRoleVMMonolith(String idUserRole, String user, String role, String userName, String roleName, String password, String code) {
        this.idUserRole = idUserRole;
        this.user = user;
        this.role = role;
        this.userName = userName;
        this.roleName = roleName;
        this.password = password;
        this.code = code;
    }

    public String getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(String idUserRole) {
        this.idUserRole = idUserRole;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
}
