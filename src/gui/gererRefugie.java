/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Statistique.EventPieChart;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import entities.Camp;
import entities.Refugie;
import java.util.ArrayList;
import services.RefugieService;

/**
 *
 * @author ASUS
 */
public class gererRefugie extends Form{
    Form current;
    Resources res;
    int idCamp=0;
    
        public gererRefugie(Form previous){
        
        current = this;
       
              setTitle("List des réfugiés");
        setLayout(BoxLayout.y());
       
        for (Refugie w : new RefugieService().getAllRefugie())
        {
           
            this.add(affichage(w));
        }
        this.show();
            this.getToolbar().addCommandToLeftBar("back", MyApplication.theme.getImage("back-command.png"), ev->{
      new refugie(this,res).show();
        });
            this.getToolbar().addCommandToRightBar("Stat", MyApplication.theme.getImage("back-command.png"), ev->{
     StatEvent(previous).show();  
        });
          
    }
        
        public Form StatEvent(Form previous) {
        EventPieChart a = new EventPieChart();
        Form stats_Form = a.execute();
        SpanLabel test_SpanLabel = new SpanLabel("Hiiii");
        Class cls = EventPieChart.class;
        stats_Form.getToolbar().addCommandToRightBar("baack", MyApplication.theme.getImage("back-command.png"), evt -> {
            new gererRefugie(previous).show();
        });
        return stats_Form;
        }
        
        
        
        
    public Container affichage(Refugie c){
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Button btn=new Button("Prenom : "+c.getPrenom());
        Label lab=new Label("Nom "+c.getNom());
         Label labnbr=new Label("Age :"+String.valueOf(c.getAge()));
         Label org = new Label("Origine :"+c.getOrigine());
   Label nomCamp=new Label("Nom camp :"+c.getNomCamp());
    
        cn2.add(btn).add(lab).addAll(labnbr,org,nomCamp);
        cn1.add(BorderLayout.WEST, cn2);
        //cn1.add(BorderLayout.CENTER,labnbr);
     //supprimer   
        btn.addActionListener(e->{
            Form f2=new Form("Details",BoxLayout.y());
          TextField  tfNom=new TextField(c.getNom());
          TextField  tfPrenom=new TextField(c.getPrenom());
          TextField tfage=new TextField(String.valueOf(c.getAge()));
         TextField  tforigine=new TextField(c.getOrigine());
         ComboBox<String> tfcamp=new ComboBox("Liste Camp");
                 ArrayList<Camp>listCamp=RefugieService.getInstance().getAllCamp();

for(int i=0;i<listCamp.size();i++)
{ tfcamp.addItem(listCamp.get(i).getNomCamp());
System.out.println(listCamp.get(i).getNomCamp());
}
 tfcamp.setSelectedItem(c.getNomCamp());
          Button btn_modif=new Button("modifier");
           Button btn_sup=new Button("supprimer");
         f2.add("Refugie").add("Nom : ").add(tfNom).add("Prenom : ").add(tfPrenom).add("age : ").add(tfage).add("Origine : ").add(tforigine).add(tfcamp)./*add("Camp : ").add(tfcamp)*/add(btn_sup).add(btn_modif);
     btn_sup.addActionListener(ww ->
     
     {
         new RefugieService().suppRefugie(c.getId());
         new gererRefugie(this).showBack();
     }
     
     );
          btn_modif.addActionListener(aa ->
     
     {                     for(int i=0;i<listCamp.size();i++)
{ if(tfcamp.getSelectedItem().equals(listCamp.get(i).getNomCamp()))
{idCamp=listCamp.get(i).getId();
System.out.println(idCamp);

break;
}
    }
         c.setIdcamp(idCamp);
         c.setNom(tfNom.getText());
         c.setPrenom(tfPrenom.getText());
         c.setAge(Integer.valueOf(tfage.getText()));
                 c.setOrigine(tforigine.getText());
//         c.setIdcamp(Integer.valueOf(tfcamp.getText()));
         new RefugieService().modifierRefugie(c);
         new gererRefugie(this).showBack();
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
