/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.codename1.l10n.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Rania
 */
public class affectation {
    int id;
    Besoins id_bs;
    don id_don;
   int quantite;
    Date date;

    public affectation(int id, Besoins id_bs, don id_don, int quantite) {
        this.id = id;
        this.id_bs = id_bs;
        this.id_don = id_don;
        this.quantite = quantite;
       
    }

    public affectation(Besoins id_bs, don id_don, int quantite) {
        this.id_bs = id_bs;
        this.id_don = id_don;
        this.quantite = quantite;
        
    }

    public affectation() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Besoins getId_bs() {
        return id_bs;
    }

    public void setId_bs(Besoins id_bs) {
        this.id_bs = id_bs;
    }

    public don getId_don() {
        return id_don;
    }

    public void setId_don(don id_don) {
        this.id_don = id_don;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDate() {
        return date;
    }
    public String getDateString(Date date) {
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateText = df2.format(date);
        return dateText;
    }

    public void setDate(long date) {
      this.date = new Date(date * 1000);
        System.out.println(this.date);
    }

    @Override
    public String toString() {
        return "affectation{" + "id=" + id + ", id_bs=" + id_bs + ", id_don=" + id_don + ", quantite=" + quantite + ", date=" + date + '}';
    }
    
}
