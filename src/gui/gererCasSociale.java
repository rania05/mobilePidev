/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Statistique.EventPieChart;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import static com.mycompany.myapp.MyApplication.theme;
import entities.Camp;
import entities.Cassociale;
import entities.Refugie;
import java.util.ArrayList;
import services.CasSocialeService;
import services.RefugieService;

/**
 *
 * @author ASUS
 */
public class gererCasSociale extends Form{
    Form current;
    Resources res;
  
    
        public gererCasSociale(Form previous, Resources theme){
        
         current = this;
    
              setTitle("Liste des cas sociaux");
        setLayout(BoxLayout.y());
        
        for (Cassociale r : new CasSocialeService().getAllCas ())
        {
            this.add(addItem(r,theme));
        }
        this.show();
                this.getToolbar().addCommandToLeftBar("back", MyApplication.theme.getImage("back-command.png"), ev->{
 
      new GuiCasSociale(this,theme).show();
        });
    }
    
  
      public Container addItem(Cassociale c, Resources theme){
      
        
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Button btn=new Button("Lieu :"+c.getLieu());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         //Label labnbr=new Label("Date :"+format.format(c.getDate()));
          Label labnbr=new Label(c.getDateString(c.getDate()));
 
  
    
        cn2.add(btn).addAll(labnbr);
        cn1.add(BorderLayout.WEST, cn2);
//     supprimer   
        btn.addActionListener((ActionEvent e)->{
            Form f2=new Form("Details",BoxLayout.y());
          TextField  tfLieu=new TextField(c.getLieu());
         
               Picker Date = new Picker();
               

          
            
          Button btn_modif=new Button("modifier");
           Button btn_sup=new Button("supprimer");
         
         
            f2.add("Cassociale").add("lieu : ").add(tfLieu).add("date : ").add(Date).add(btn_modif).add(btn_sup);
     btn_sup.addActionListener(ww ->
     
     {
         new CasSocialeService().suppCas(c.getId());
         new gererCasSociale(this,theme).showBack();
     }
     
     );
          btn_modif.addActionListener(aa ->
     
     {
        
         c.setLieu(tfLieu.getText());
         c.setDate(Date.getDate());
     
   
         new CasSocialeService().modifierCas(c);
         new gererCasSociale(this,theme).showBack();
     }
     
     );
             
     
    
            f2.getToolbar().addCommandToLeftBar("back", null, ev->{
            this.show();
        });
                   f2.show();
        });
        
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
}