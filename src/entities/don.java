/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

//import java.util.Objects;

/**
 *
 * @author Nesrine
 */
public class don {
    int id ;
    String nom ;
    String prenom ;
    String email ;
    String objet ;
    String description ; 
      int quantite ; 
    
     public don() {
      
    }

    
     public don(int id ,String nom, String prenom, String email, String objet, String description) {
         this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.objet = objet;
        this.description = description;
    }

    public don(String nom, String prenom, String email, String objet, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.objet = objet;
        this.description = description;
    }

   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getObjet() {
        return objet;
    }

    public String getDescription() {
        return description;
    }

     public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setDescription(String descripton) {
        this.description = descripton;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public don(int id, String nom, String prenom, String email, String objet, String description, int quantite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.objet = objet;
        this.description = description;
        this.quantite = quantite;
    }

    public don(String nom, String prenom, String email, String objet, String description, int quantite) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.objet = objet;
        this.description = description;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "don{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", objet=" + objet + ", description=" + description + ", quantite=" + quantite + '}';
    }

   
  
   
    
    
    
}