/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Orderlines;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Imm
 */
@Stateless
public class OrderlinesFacade extends AbstractFacade<Orderlines> {
    @PersistenceContext(unitName = "A3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderlinesFacade() {
        super(Orderlines.class);
    }
    
}
