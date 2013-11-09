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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Imm
 */
@Entity
@Table(name = "dvds")
@NamedQueries({
    @NamedQuery(name = "Dvds.findAll", query = "SELECT d FROM Dvds d"),
    @NamedQuery(name = "Dvds.findByDvdId", query = "SELECT d FROM Dvds d WHERE d.dvdId = :dvdId"),
    @NamedQuery(name = "Dvds.findByTitle", query = "SELECT d FROM Dvds d WHERE d.title = :title"),
    @NamedQuery(name = "Dvds.findByGenre", query = "SELECT d FROM Dvds d WHERE d.genre = :genre"),
    @NamedQuery(name = "Dvds.findByPrice", query = "SELECT d FROM Dvds d WHERE d.price = :price"),
    @NamedQuery(name = "Dvds.findByReleaseYear", query = "SELECT d FROM Dvds d WHERE d.releaseYear = :releaseYear"),
    @NamedQuery(name = "Dvds.findByDescription", query = "SELECT d FROM Dvds d WHERE d.description = :description"),
    @NamedQuery(name = "Dvds.findByStudio", query = "SELECT d FROM Dvds d WHERE d.studio = :studio")})
public class Dvds implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DvdId")
    private Integer dvdId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Genre")
    private String genre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ReleaseYear")
    private int releaseYear;
    @Size(max = 200)
    @Column(name = "Description")
    private String description;
    @Size(max = 45)
    @Column(name = "Studio")
    private String studio;

    public Dvds() {
    }

    public Dvds(Integer dvdId) {
        this.dvdId = dvdId;
    }

    public Dvds(Integer dvdId, String title, String genre, int price, int releaseYear) {
        this.dvdId = dvdId;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.releaseYear = releaseYear;
    }

    public Integer getDvdId() {
        return dvdId;
    }

    public void setDvdId(Integer dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dvdId != null ? dvdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dvds)) {
            return false;
        }
        Dvds other = (Dvds) object;
        if ((this.dvdId == null && other.dvdId != null) || (this.dvdId != null && !this.dvdId.equals(other.dvdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dvds[ dvdId=" + dvdId + " ]";
    }
    
}
