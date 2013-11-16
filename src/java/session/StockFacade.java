/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Stock;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Imm
 */
@Stateless
public class StockFacade extends AbstractFacade<Stock> {

    @PersistenceContext(unitName = "A3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockFacade() {
        super(Stock.class);
    }

    public Stock findByDvdId(int dvdId) {
        Query query = getEntityManager().createNamedQuery("Stock.findByDvdId");
        query.setParameter("id", dvdId);
        return (Stock) query.getSingleResult();
    }
}
