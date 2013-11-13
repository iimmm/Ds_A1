/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Dvds;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Imm
 */   
   

@Stateful

public class ShoppingCart{
 @EJB
   private DvdsFacade dvdsBean;


  private HashMap<Dvds,Integer> cart;
  
  @PostConstruct
  private void init(){
  cart = new HashMap<Dvds,Integer>();
  }
  
  
  public void addDvd(Dvds dvd){
      
     Integer quantity = cart.get(dvd);
     
  }  
  
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void completePurchase(){
//    for(Product product : products){
//      em.persist(product);
//    }
//    products.clear();
  }

}