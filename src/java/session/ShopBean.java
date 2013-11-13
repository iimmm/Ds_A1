/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
}
