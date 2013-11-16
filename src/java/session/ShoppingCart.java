/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Dvds;
import entities.Stock;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Named;

/**
 *
 * @author Imm
 */
@Stateful
@LocalBean
public class ShoppingCart implements ShoppingCartLocal {

    @EJB
    private StockFacade stockBean;
    private HashMap<Dvds, Integer> cart;

    public HashMap<Dvds, Integer> getCartContents() {
        return cart;
    }

    @PostConstruct
    private void init() {
        cart = new HashMap<Dvds, Integer>();
    }

    public String addDvd(Dvds dvd) {

        Integer quantity = cart.get(dvd);
        Stock stock = stockBean.findByDvdId(dvd.getDvdId());

        if (stock == null || stock.getQuantity() == 0) {
            return "Stock: 0";
        }

        if (quantity == null) {
            cart.put(dvd, 1);
        } else {
            cart.put(dvd, quantity + 1);
        }

        return "Added to cart";
    }

    public void clearCart() {
        cart = new HashMap<Dvds, Integer>();
    }
}
