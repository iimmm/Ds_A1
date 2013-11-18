/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Dvds;
import entities.Users;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.ShopServiceBean;
import session.ShopServiceBeanLocal;
import session.ShoppingCartLocal;

/**
 *
 * @author Imm
 */
@ManagedBean
@SessionScoped
public class ShopManagerBean implements Serializable {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    @ManagedProperty(value = "#{shopServiceBean}")
    private ShopServiceBean shopServiceBean;

    public void setShopServiceBean(ShopServiceBean shopServiceBean) {
        this.shopServiceBean = shopServiceBean;
    }
    @EJB
    private ShoppingCartLocal cart;

    /**
     * Creates a new instance of ShopManager
     */
    public ShopManagerBean() {
    }

    public void addToCart(Dvds dvd) {
        cart.addDvd(dvd);
    }
    private Dvds current;

    public Dvds getCurrent() {
        return current;
    }

    public void setCurrent(Dvds current) {
        this.current = current;
    }

    public String setStock(Dvds dvd) {

        current = dvd;

        return "CreateStock";
    }

    public void completeOrder() {

        Users user = loginBean != null ? loginBean.getUser() : null;
        if (user != null) {
            shopServiceBean.completePurchase(user);
            clearCart();
        }
    }

    public void clearCart() {
        cart.clearCart();
    }

    public Set<Entry<Dvds, Integer>> getCartContents() {
        return cart.getCartContents().entrySet();
    }
}
