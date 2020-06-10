/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import entity.don;
import java.io.IOException;
import services.ServiceDon;
//import gui.SendSMS;
/**
 *
 * @author Nesrine
 */
public class donAjout   {
       
    
    TextField tfNom   ;       
    TextField tfPrenom    ;   
    TextField tfEmail    ;  
    TextField tfDescription;
    TextField tfObjet;
//    ComboBox<String> tfObjet;
 
    Button btnajout ;
    Form f;
    Dialog dd  ;

    public donAjout() {
        f = new Form("home", BoxLayout.y());
        tfNom = new TextField("","nom");
        tfPrenom = new TextField("","prenom");
        tfEmail = new TextField("","email");
        tfObjet = new TextField("","objet");
        tfDescription = new TextField("","description");
        
        f.getToolbar().addCommandToRightBar("back", null, (et) -> {
            donAjout h = new donAjout();
            h.getF().show();
        });
        

        btnajout = new Button("Ajouter");
        


        f.add(tfNom);
        f.add(tfPrenom);
        f.add(tfEmail);
        f.add(tfDescription);
        f.add(tfObjet);
  
        f.add(btnajout);

        btnajout.addActionListener((ActionEvent e) -> {
            ServiceDon ser = new ServiceDon();
            don et = new don(tfNom.getText(),tfPrenom.getText(),tfEmail.getText(),
            tfObjet.getText(),tfDescription.getText());
            if(validation( et)){
            ser.addDon(et);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("don Ajouter avec succes", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button ok = new Button("OK");
           
            ok.addActionListener((ActionEvent ee) -> {
                donAjout a = null;
               
                    a = new donAjout();
                    System.out.println("erreuur");
                a.getF().show();
            });
            
            d.setLayout(new BorderLayout());
            d.addComponent(BorderLayout.SOUTH, ok);
            d.add(BorderLayout.CENTER, popupBody);
            d.show();
            }
            });
           
    }
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
public boolean validation(don e){
    boolean a = false ;
    ServiceDon es = new ServiceDon();
    if(!e.getNom().equals("")){
    for (don ev : es.getAllDons()) {
        if(e.getNom().equals(ev.getNom())){
         
             dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Le Nom existe deja !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
              dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
              return false ;
        }
    }
    
    }else {
              dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Nom ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
return false ;
            }
    if(e.getDescription().equals("") ){
         dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Description ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false ;
    }


return true ;
} 

  

    
}
