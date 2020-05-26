/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Nesrine
 */
public class event {
    int id ;
    String adresee;
    String titre ;
    String lieu;
    Date date ;
    Float prix;

    public event(String adresee, String titre, String lieu, Date date, Float prix) {
        this.adresee = adresee;
        this.titre = titre;
        this.lieu = lieu;
        this.date = date;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public String getAdresee() {
        return adresee;
    }

    public String getTitre() {
        return titre;
    }

    public String getLieu() {
        return lieu;
    }

    public Date getDate() {
        return date;
    }

    public Float getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdresee(String adresee) {
        this.adresee = adresee;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "event{" + "id=" + id + ", adresee=" + adresee + ", titre=" + titre + ", lieu=" + lieu + ", date=" + date + ", prix=" + prix + '}';
    }
    
    
}
