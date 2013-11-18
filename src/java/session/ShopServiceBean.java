/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Dvds;
import entities.Orderlines;
import entities.Orders;
import entities.Stock;
import entities.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Imm
 */
@Stateless
//@ManagedBean(name = "shopServiceBean")
//@SessionScoped
public class ShopServiceBean implements ShopServiceBeanLocal, Serializable {

    @EJB
    private OrdersFacade ordersBean;
    @EJB
    private OrderlinesFacade orderLinesBean;
    @EJB
    private StockFacade stockBean;
    @EJB
    private ShoppingCartLocal cartBean;

    // @TransactionAttribute(TransactionAttributeType.REQUIRED)
    //  @Override
    public void completePurchase(Users user) {

        updateStock(user);
        cartBean.clearCart();

    }

    private String updateStock(Users user) {

        StringBuilder errors = new StringBuilder();
        Orders order = new Orders();
        order.setDate(new Date(System.currentTimeMillis()));
        order.setUserId(user);


        // order = ordersBean.getLatest(user.getUserId());

        HashMap<Dvds, Integer> cartContents = cartBean.getCartContents();
        for (Map.Entry<Dvds, Integer> entry : cartContents.entrySet()) {

            Stock stock = stockBean.findByDvdId(entry.getKey().getDvdId());

            if (stock != null && stock.getQuantity() >= entry.getValue()) {

                stock.setQuantity(stock.getQuantity() - entry.getValue());
                createOrderLine(order, entry);

            } else {
                errors.append("Not enough " + entry.getKey().getTitle() + "; sold in the meantime.");
            }
        }

        ordersBean.create(order);


        return errors.toString();
    }

    private void createOrderLine(Orders order, Map.Entry<Dvds, Integer> entry) {

        Orderlines orderLine = new Orderlines();
        orderLine.setDvdId(entry.getKey());
        orderLine.setQuantity(entry.getValue());
        orderLine.setOrderId(order);

        if (order.getOrderlinesCollection() == null) {
            order.setOrderlinesCollection(new ArrayList<Orderlines>());
        }
        order.getOrderlinesCollection().add(orderLine);
    }
}
