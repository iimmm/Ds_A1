/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Dvds;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
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
public class ShopManagerBean {

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;
    @EJB
    private ShopServiceBeanLocal shopBean;
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

    public void completeOrder() {
        shopBean.completePurchase(loginBean.getUser());
        clearCart();
    }

    public void clearCart() {
        cart.clearCart();
    }

    public Set<Entry<Dvds, Integer>> getCartContents() {
        return cart.getCartContents().entrySet();
    }
}
