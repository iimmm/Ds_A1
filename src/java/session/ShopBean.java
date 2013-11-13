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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Imm
 */
@Stateless
public class ShopBean {

    @EJB
    private UsersFacade usersBean;
    @EJB
    private DvdsFacade dvdsBean;
    @EJB
    private OrdersFacade ordersBean;
    @EJB
    private OrderlinesFacade orderLinesBean;
    @EJB
    private StockFacade stockBean;
    @EJB
    private ShoppingCart cartBean;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void completePurchase(Users user) {

        updateStock(user);

//    for(Product product : products){
//      em.persist(product);
//    }
//    products.clear();
    }

    private String updateStock(Users user) {

        StringBuilder errors = new StringBuilder();
        Orders order = new Orders();
        order.setDate(new Date(System.currentTimeMillis()));
        order.setUserId(user);
        ordersBean.create(order);
        
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

        return errors.toString();
    }

    private void createOrderLine(Orders order, Map.Entry<Dvds, Integer> entry) {

        Orderlines orderLine = new Orderlines();
        orderLine.setDvdId(entry.getKey().getDvdId());
        orderLine.setQuantity(entry.getValue());
        orderLine.setOrderId(order.getOrderId());
        orderLinesBean.create(orderLine);
        

    }
}
