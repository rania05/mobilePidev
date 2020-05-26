/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Rania
 */
public class Besoins {
    private int id;
     private Camp id_c;
    private  Categorie nom_bs;
  
   
    private int quantite;

    public Besoins() {
    }

    public Besoins(int id, Camp id_c, Categorie nom_bs, int quantite) {
        this.id = id;
        this.id_c = id_c;
        this.nom_bs = nom_bs;
        this.quantite = quantite;
    }

    public Besoins(Camp id_c, Categorie nom_bs, int quantite) {
        this.id_c = id_c;
        this.nom_bs = nom_bs;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Camp getId_c() {
        return id_c;
    }

    public void setId_c(Camp id_c) {
        this.id_c = id_c;
    }

    public Categorie getNom_bs() {
        return nom_bs;
    }

    public void setNom_bs(Categorie nom_bs) {
        this.nom_bs = nom_bs;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Besoins{" + "id=" + id + ", id_c=" + id_c + ", nom_bs=" + nom_bs + ", quantite=" + quantite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.id_c);
        hash = 41 * hash + Objects.hashCode(this.nom_bs);
        hash = 41 * hash + this.quantite;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Besoins other = (Besoins) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.id_c, other.id_c)) {
            return false;
        }
        if (!Objects.equals(this.nom_bs, other.nom_bs)) {
            return false;
        }
        return true;
    }

  
   
    
    
}
