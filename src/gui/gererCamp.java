/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
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
import java.util.ArrayList;

/**
 *
 * @author Rania
 */
public class gererCamp  extends Form{
    Form current;

    
  ArrayList<Cord>listCord;
  Cord idLieu ;
    
 private static final String HTML_API_KEY = "*********************************";
    
    public gererCamp(Form previous, Resources theme){
        
        current = this;
       Display.getInstance().setCommandBehavior(Display.COMMAND_BEHAVIOR_SIDE_NAVIGATION);
 UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
UIManager.getInstance().getLookAndFeel().setMenuBarClass(SideMenuBar.class);
              setTitle("affichage  un camp");
        setLayout(BoxLayout.y());
        
        for (Camp  r : new CampService().getAllCamp ())
        {
            this.add(addItem(r,theme));
        }
        this.show();
                this.getToolbar().addCommandToLeftBar("back", MyApplication.theme.getImage("back-command.png"), ev->{
 
      new GuiCamp (this,theme).show();
        });
    }
    
  
      public Container addItem(Camp c,Resources theme){
   
       Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Button btn=new Button("Nom du camp :"+c.getNomCamp());
        
         Label labnbr=new Label("Capacité :"+String.valueOf(c.getCapacite()));
 
   Label nomCamp=new Label("Lieu :"+c.getLieu().getLieu());
    
        cn2.add(btn).addAll(labnbr,nomCamp);
        cn1.add(BorderLayout.WEST, cn2);
     //supprimer   
        btn.addActionListener((ActionEvent e)->{
            Form f2=new Form("Details",BoxLayout.y());
          TextField  tfNomCamp=new TextField(c.getNomCamp());
         
          TextField tfLieu=new TextField(c.getLieu().getLieu());
          TextField  tfCapacite=new TextField(String.valueOf(c.getCapacite()));
          
            ComboBox<String> tfcamp=new ComboBox("Liste lieu");
                 listCord=CampService.getInstance().getAllCord();
                
                

for(int i=0;i<listCord.size();i++)
{ 
    tfcamp.addItem(listCord.get(i).getLieu());
}
 tfcamp.setSelectedItem(c.getLieu().getLieu());
 idLieu =  rechercherCord(tfcamp.getSelectedItem());
          Button btn_modif=new Button("modifier");
           Button btn_sup=new Button("supprimer");
           Button btn_map=new Button("localisation");
         
            f2.add("Camp").add("Nom : ").add(tfNomCamp).add("lieu : ").add(tfcamp).add("capacité: ").add(tfCapacite).add(btn_sup).add(btn_modif).add(btn_map);
     btn_sup.addActionListener(ww ->
     
     {
         new CampService().suppCamp(c.getId());
         new gererCamp(this,theme).showBack();
     }
     
     );
          btn_modif.addActionListener(aa ->
     
     {
          idLieu =  rechercherCord(tfcamp.getSelectedItem());
         c.setNomCamp(tfNomCamp.getText());
     
    c.setLieu(idLieu);
            c.setCapacite(Integer.valueOf(tfCapacite.getText()));
        
         new CampService().modifierCamp(c);
         new gererCamp(this,theme).showBack();
     }
     
     );
             btn_map.addActionListener(aa ->
     
     {
          idLieu =  rechercherCord(tfcamp.getSelectedItem());
          
     Form hi = new Form("Native Maps Test");
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
         Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
 FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));
       
           
                     cnt.setCameraPosition(new Coord(idLieu.getLatitude(), idLieu.getLongitude()));
                      cnt.zoom(new Coord(idLieu.getLatitude(), idLieu.getLongitude()), 13);
                        TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            /*InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
            enterName.setDoneListener(e2->{
                String txt = enterName.getText();
                cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        cnt.getCoordAtPosition(e.getX(), e.getY()),
                        enterName.getText(),
                        "",
                        e3->{
                                ToastBar.showMessage("You clicked "+txt, FontImage.MATERIAL_PLACE);
                        }
                );
                dlg.dispose();
            });
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();*/
       
       

       /* Button btnAddMarker = new Button("Add Marker");
        btnAddMarker.addActionListener(Mark->{*/

            //cnt.setCameraPosition(new Coord(idLieu.getLatitude(), idLieu.getLongitude()));
            cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCameraPosition(),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                     }
            );

        //});

        Button btnAddPath = new Button("");
        btnAddPath.addActionListener(Path->{

            cnt.addPath(
                    cnt.getCameraPosition(),
                    new Coord(idLieu.getLatitude(), idLieu.getLongitude()) // Sydney
                  //  new Coord(-18.142, 178.431),  // Fiji
                   // new Coord(21.291, -157.821),  // Hawaii
                   // new Coord(37.423, -122.091)  // Mountain View
            );
        });

       Button btnClearAll = new Button("");
        btnClearAll.addActionListener(Clear->{
            cnt.clearMapLayers();
        });

       /*cnt.addTapListener(e->{
            TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
            enterName.setDoneListener(e2->{
                String txt = enterName.getText();
                cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        cnt.getCoordAtPosition(e.getX(), e.getY()),
                        enterName.getText(),
                        "",
                        e3->{
                                ToastBar.showMessage("You clicked "+txt, FontImage.MATERIAL_PLACE);
                        }
                );
                dlg.dispose();
            });
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();
        });*/

       Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom(  btnAddPath, btnClearAll)
               )
        );
        hi.getToolbar().addCommandToLeftBar("back", null, ev->{
            this.show();
        });
        hi.add(BorderLayout.CENTER, root);
        hi.show();
        
         
        
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
      public Cord rechercherCord(String nom) {
      Cord id = null;
       for(int i = 0; i<listCord.size();i++){
          
           if(listCord.get(i).getLieu().equals(nom)){
                System.out.println("Cord Trouvé = "+listCord.get(i).toString());
               id = listCord.get(i);
           }
       }
           return id;
       
    }
   
      
    
}
