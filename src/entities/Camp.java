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
public class Camp {
    private int id;
    private Cord lieu;
     private String nomCamp;
    private int capacite;

    public Camp() {
    }

    public Camp(int id, Cord lieu,String nomCamp, int capacite) {
        this.id = id;
        this.lieu = lieu;
          this.nomCamp = nomCamp;
        this.capacite = capacite;
    }
    
    public Camp( Cord lieu, int capacite) {
        
        this.lieu = lieu;
        this.capacite = capacite;
    }

    public Camp(Cord  lieu, String nomCamp, int capacite) {
        this.lieu = lieu;
        this.nomCamp = nomCamp;
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Camp{" + "id=" + id + ", lieu=" + lieu.toString() + ", capacite=" + capacite + '}';
    }
    

    public int getId() {
        return id;
    }

    public Cord  getLieu() {
        return lieu;
    }
    
   

    public int getCapacite() {
        return capacite;
    }
    
     public String getCapaciteS() {
        return Integer.toString(capacite);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLieu(Cord  lieu) {
        this.lieu = lieu;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getNomCamp() {
        return nomCamp;
    }

    public void setNomCamp(String nomCamp) {
        this.nomCamp = nomCamp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.lieu);
        hash = 89 * hash + this.capacite;
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
        final Camp other = (Camp) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.capacite != other.capacite) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        return true;
    }
    
    
}
