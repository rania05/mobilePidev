/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;

import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Camp;
import entities.Cord;
import services.CampService;
import com.mycompany.myapp.MyApplication;
import entities.Besoins;
import entities.Categorie;
import entities.affectation;
import entities.don;
import java.util.ArrayList;
import services.BesoinsService;
import services.ServiceDon;

/**
 *
 * @author Rania
 */
public class gererBesoins  extends Form{
    Form current;
BesoinsService bs=new BesoinsService();
    
  ArrayList<Camp>listCamp;
  Camp idLieu ;
    ArrayList<Categorie>listCat;
  Categorie idCat ;
     don idDon ;
    Besoins idBs;
    ArrayList<don>listDon;
 private static final String HTML_API_KEY = "*********************************";
    
    public gererBesoins(Form previous, Resources theme){
        
        current = this;
       
       Display.getInstance().setCommandBehavior(Display.COMMAND_BEHAVIOR_SIDE_NAVIGATION);
// UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
UIManager.getInstance().getLookAndFeel().setMenuBarClass(SideMenuBar.class);
              setTitle("");
              getToolbar().addCommandToRightBar("Afficher affectations", null, ev->{
         new  gererAff(current).show();
        });
        setLayout(BoxLayout.y());
          Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");


TextField searchField = new TextField("", "Veuillez saisir la catégorie"); // <1>
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);
getToolbar().setTitleComponent(searchField);
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
searchField.addDataChangeListener((i1, i2) -> { // <2>
    String t = searchField.getText();
    if(t.length() < 1) {
        for(Component cmp : getContentPane()) {
              
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
    } else {
        t = t.toLowerCase();
        for(Component cmp : getContentPane()) {
            
            String val = null;
            if(cmp instanceof Container){
                Container c1 =(Container)cmp;
            
            if(c1.getComponentAt(0).getClientProperty("Etu") instanceof Label) {
                val = ((Label)c1.getComponentAt(0).getClientProperty("Etu")).getText();
            } 
            }
            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
            cmp.setHidden(!show); // <3>
            cmp.setVisible(show);
        }
    }
    getContentPane().animateLayout(250);
});
getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
    searchField.startEditingAsync(); // <4>
});
        
        for (Besoins  r : new BesoinsService().getAllBesoins())
        {
            this.add(addItem(r,theme));
        }
        this.show();
                this.getToolbar().addCommandToLeftBar("back", MyApplication.theme.getImage("back-command.png"), ev->{
 
      new GuiBesoins (this,theme).show();
        });
    }
    
  
      public Container addItem(Besoins c, Resources theme){
        
          Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Button btn=new Button(c.getId_c().getLieu().getLieu());
        
         Label labnbr=new Label(String.valueOf(c.getQuantite()));
 
   Label Besoins=new Label(c.getNom_bs().getNom());
    
        cn2.add(btn).addAll(Besoins,labnbr);
        cn1.add(BorderLayout.WEST, cn2);
        cn2.putClientProperty("Etu", Besoins);
     //supprimer   
        btn.addActionListener((ActionEvent e)->{
            Form f2=new Form("Details",BoxLayout.y());
          
          TextField  tfQuantite=new TextField(String.valueOf(c.getQuantite()));
          
            ComboBox<String> tfcamp=new ComboBox("lieu camp");
                 listCamp=CampService.getInstance().getAllCamp();
                  ComboBox<String> tfcat=new ComboBox("categorie");
                 listCat=BesoinsService.getInstance().getAllCategorie();
                
                

for(int i=0;i<listCamp.size();i++)
{ 
    tfcamp.addItem(listCamp.get(i).getLieu().getLieu());
}

for(int i=0;i<listCat.size();i++)
{ 
    tfcat.addItem(listCat.get(i).getNom());
}
ComboBox<String> tfDon=new ComboBox("dons");
                 listDon=BesoinsService.getInstance().getNomDon(c.getNom_bs().getNom());

for(int i=0;i<listDon.size();i++)
{ tfDon.addItem(listDon.get(i).getNom());
System.out.println(listDon.get(i).getNom());
}
 tfcamp.setSelectedItem(c.getId_c().getLieu().getLieu());
 tfcat.setSelectedItem(c.getNom_bs().getNom());

 
          Button btn_modif=new Button("modifier");
           Button btn_sup=new Button("supprimer");
                Button btn_aff=new Button("affecter un don");
          
         
            f2.add("besoins").add("Nom : ").add("lieu du camp: ").add(tfcamp).add("catégorie: ").add(tfcat).add("quantitée : ").add("Don").add(tfDon).add(tfQuantite).add(btn_sup).add(btn_modif).add(btn_aff);
     btn_sup.addActionListener(ww ->
     
     {
         new BesoinsService().suppBesoins(c.getId());
         new gererBesoins(this,theme).showBack();
     }
     
     );
          btn_modif.addActionListener(aa ->
     
     {
           idLieu =  rechercherCamp(tfcamp.getSelectedItem());
            idCat =  rechercherCat(tfcat.getSelectedItem());
            
        
         c.setId_c(idLieu);
         c.setNom_bs(idCat);
         c.setQuantite(Integer.valueOf(tfQuantite.getText()));
        
         new BesoinsService().modifierBesoins(c);
         new gererBesoins(this,theme).showBack();
     }
     
     );
             btn_aff.addActionListener((ActionEvent aa) ->
     
     {
        affectation aff=new affectation();
         System.out.println(c.getQuantite());
         int quantiteDon=BesoinsService.getInstance().getQntDon(tfDon.getSelectedItem(),tfcat.getSelectedItem());
        if ((c.getQuantite()-Integer.valueOf(tfQuantite.getText())>=0) ||(quantiteDon- Integer.valueOf(tfQuantite.getText())>=0) ){
             
         int nouvQuantDon = quantiteDon- Integer.valueOf(tfQuantite.getText());
         
                 int nouvQnt =  c.getQuantite()-Integer.valueOf(tfQuantite.getText());
                 
         c.setQuantite(nouvQnt);
           idDon =  rechercherDon(tfDon.getSelectedItem());
               BesoinsService.getInstance().modifierDon(nouvQuantDon,idDon.getId());
                 BesoinsService.getInstance().modifierBesoins(c);
                 TextField  tfBs=new TextField(String.valueOf(c.getId()));
            
          
            idBs=c;
          aff.setId_don(idDon);
          aff.setId_bs(idBs);
          aff.setQuantite(Integer.valueOf(tfQuantite.getText()));
        
        
        
         new BesoinsService().addAffectation(aff);
         new gererBesoins(this,theme).showBack();
     }
        
        else
        {
        Dialog.show("ERROR", "la quantité affectée est suppérieure à la quantité des besoins ou la quantitée des dons disponibles pour cette catégorie", new Command("OK"));
        }
         
       
   //TextField  tfQnt=new TextField(String.valueOf(aff.getQuantite()));
            
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
  public Camp rechercherCamp(String nom) {
      Camp id = null;
       for(int i = 0; i<listCamp.size();i++){
          
           if(listCamp.get(i).getLieu().getLieu().equals(nom)){
                System.out.println(listCamp.get(i).toString());
               id = listCamp.get(i);
           }
       }
           return id;
       
    }
    public Categorie rechercherCat(String nom) {
      Categorie id = null;
       for(int i = 0; i<listCat.size();i++){
          
           if(listCat.get(i).getNom().equals(nom)){
                System.out.println(listCat.get(i).toString());
               id = listCat.get(i);
           }
       }
           return id;
       
    }
    
   public don rechercherDon(String nom) {
      don id = null;
       for(int i = 0; i<listDon.size();i++){
          
           if(listDon.get(i).getNom().equals(nom)){
                System.out.println(listDon.get(i).toString());
               id = listDon.get(i);
           }
       }
           return id;
       
    }
      
    
}
