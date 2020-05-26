/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Random;
//import Utils.UserUniTech;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.BOTTOM;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import entities.Camp;
import entities.Refugie;
import entities.affectation;
import entities.don;
import java.io.IOException;

import static java.lang.Math.abs;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import services.BesoinsService;
import services.RefugieService;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import services.ServiceDon;

/**
 *
 * @author Nesrine
 */
public class gererAff  extends Form  {

  Form f;
    SpanLabel lb;
    Label nom;
    Container f1;
    private Resources theme;
    
    EncodedImage enc;
 

  /*  Database bd ;
    public ListDonForm(Form previous) {
        setTitle("List Don");

        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceDon.getInstance().getAllDons().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
     
    }*/
    
     public gererAff(Form previous)  {
         f=this;
         setTitle("List des affectations");
        setLayout(BoxLayout.y());
         for (affectation w : new BesoinsService().getAllAff())
        {
           
            this.add(affichage(w));
        }
        this.show();
            this.getToolbar().addCommandToLeftBar("back", MyApplication.theme.getImage("back-command.png"), ev->{
      new AddDonForm(this,theme).show();
        });
            
          
    }
     public Container affichage(affectation c){
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Button camp=new Button("camp : "+c.getId_bs().getId_c().getLieu().getLieu());
     //   Label nom=new Label("Nom "+c.getNom());
     
         Label don = new Label("don "+c.getId_don().getObjet());
            Label quantite = new Label("quantité "+c.getQuantite());
             Label labDate=new Label(c.getDateString(c.getDate()));
            
    
        cn2.add(camp).addAll(don,quantite,labDate);
        cn1.add(BorderLayout.WEST, cn2);
        
        
       cn1.setLeadComponent(camp);
        return cn1;
   
}

         
                
    
    
        
     }
         
     
      
    