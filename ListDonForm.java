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
import entity.don;
import java.io.IOException;

import static java.lang.Math.abs;
import static java.lang.Math.abs;
import java.util.Date;
import java.util.List;
import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import services.ServiceDon;

/**
 *
 * @author Nesrine
 */
public class ListDonForm  extends Form  {

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
    
     public ListDonForm() throws IOException {
      
       
        f = new Form("List don");
        f1 = new Container(BoxLayout.y());
        lb = new SpanLabel("");
        nom = new Label("");
        
        f.add(lb);
        f.add(f1);
        ServiceDon serviceevent = new ServiceDon();
        java.util.List<don> l = serviceevent.getAllDons();
        for (don e : serviceevent.getAllDons()) {
           
           
            nom.setText(e.getNom());
 
           MultiButton mb = new MultiButton(e.getNom()+" "+e.getPrenom());
            mb.setTextLine2(e.getEmail()+"   "+e.getObjet());
           
            theme = UIManager.initFirstTheme("/theme");
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            Date hash = new Date();
        //    Image i = URLImage.createToStorage(enc,System.currentTimeMillis()+hash.toString()+e.getNom(), "http://localhost/allforkids/web/uploads/images/" + e.getPhoto(), URLImage.RESIZE_SCALE);

       //     mb.setIcon(i);
          /*  mb.addActionListener((al)->{
            DetailEvenement a = new DetailEvenement(e);
           
            a.getF().show();
        });*/
           

  

            f1.add(mb);
             
          
            
            }
        

        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
    /*     f.getToolbar().addCommandToRightBar("Ajout", null, (ev) -> {
            AjoutEvenement h = new AjoutEvenement();
            h.getF().show();    
        });*/
    }

   
    
       public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

 }
    

    
