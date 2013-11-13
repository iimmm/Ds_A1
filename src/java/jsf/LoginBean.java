/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import entities.Users;


/**
 *
 * @author Imm
 */
@SessionScoped
@Named
public class LoginBean {

    public static final String AUTH_KEY = "app.user.name";
    public static final String IS_ADMIN = "app.user.isadmin";
    @EJB
    private session.UsersFacade ejbFacade;
    private String name;
    private String password;
    private String HomeAddress;
    private boolean isAdmin;  
    private Date birthdate;

    
    private Users loggedUser;

    public Users getUser() {
        return loggedUser;
    }
    
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
   

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String HomeAddress) {
        this.HomeAddress = HomeAddress;
    }

    public String login() {

        try {
            Users user = ejbFacade.findUserByName(name);
            this.loggedUser = user;
            if (user != null && password.equals(user.getPassword())) {

                if (!user.getIsAdmin()) {
                  
                    HomeAddress = user.getAddress();
                    isAdmin = user.getIsAdmin();              

                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                            .remove(IS_ADMIN);
                    return "ownpage";
                }
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, name);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(IS_ADMIN, isAdmin);
                return "/users/List";
            }


        } catch (Exception e) {
            logout();
            return "login";
        }
        logout();
        return "login";
    }

    public String logout() {
        name = password = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .remove(AUTH_KEY);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .remove(IS_ADMIN);
        return "login";
    }
}
