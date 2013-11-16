/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Dvds;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.ShopServiceBeanLocal;
import session.ShoppingCartLocal;

/**
 *
 * @author Imm
 */
@ManagedBean
@SessionScoped
public class ShopManager {

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;
    @EJB
    private ShopServiceBeanLocal shopBean;
    @EJB
    private ShoppingCartLocal cart;

    /**
     * Creates a new instance of ShopManager
     */
    public ShopManager() {
    }

    public void addToCart(Dvds dvd) {
        cart.addDvd(dvd);
    }

    public void clearCart() {
        cart.clearCart();
    }
}
