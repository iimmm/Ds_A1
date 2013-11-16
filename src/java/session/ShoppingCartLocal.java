/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Dvds;
import java.util.HashMap;
import javax.ejb.Local;

/**
 *
 * @author Imm
 */
@Local
public interface ShoppingCartLocal {

    public String addDvd(Dvds dvd);

    public void clearCart();

    public HashMap<Dvds, Integer> getCartContents();
}
