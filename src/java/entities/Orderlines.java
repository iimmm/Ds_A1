/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Imm
 */
@Entity
@Table(name = "orderlines")
@NamedQueries({
    @NamedQuery(name = "Orderlines.findAll", query = "SELECT o FROM Orderlines o"),
    @NamedQuery(name = "Orderlines.findByOrderLineId", query = "SELECT o FROM Orderlines o WHERE o.orderLineId = :orderLineId"),
    @NamedQuery(name = "Orderlines.findByDvdId", query = "SELECT o FROM Orderlines o WHERE o.dvdId = :dvdId"),
    @NamedQuery(name = "Orderlines.findByOrderId", query = "SELECT o FROM Orderlines o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "Orderlines.findByQuantity", query = "SELECT o FROM Orderlines o WHERE o.quantity = :quantity")})
public class Orderlines implements Serializable {

    @JoinColumn(name = "OrderId", referencedColumnName = "OrderId")
    @ManyToOne(optional = false)
    private Orders orderId;
    @JoinColumn(name = "DvdId", referencedColumnName = "DvdId")
    @ManyToOne(optional = false)
    private Dvds dvdId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderLineId")
    private Integer orderLineId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private int quantity;

    public Orderlines() {
    }

    public Orderlines(Integer orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Orderlines(Integer orderLineId, int quantity) {
        this.orderLineId = orderLineId;

        this.quantity = quantity;
    }

    public Integer getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(Integer orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Dvds getDvdId() {
        return dvdId;
    }

    public void setDvdId(Dvds dvd) {
        this.dvdId = dvd;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders order) {
        this.orderId = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderLineId != null ? orderLineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderlines)) {
            return false;
        }
        Orderlines other = (Orderlines) object;
        if ((this.orderLineId == null && other.orderLineId != null) || (this.orderLineId != null && !this.orderLineId.equals(other.orderLineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Orderlines[ orderLineId=" + orderLineId + " ]";
    }
}
