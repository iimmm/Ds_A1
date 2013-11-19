/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Dvds;
import entities.Orders;
import entities.Stock;
import entities.Users;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Imm
 */
@Local
public interface ShopServiceBeanLocal {

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String completePurchase(Users user);

    public void prepareAddUpdate(Dvds dvd);
}
