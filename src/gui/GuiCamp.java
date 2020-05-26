/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui ;



import com.codename1.db.Database;
import entities.Camp;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import static com.mycompany.myapp.MyApplication.theme;
import entities.Cord;
import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import services.CampService;
import services.OpinionDAO;
/**
 *
 * @author Rania
 */
public class GuiCamp  extends Form{
     Form current;
    
  
    ArrayList<Cord>listCord;
  Cord idLieu ;
   Toolbar tb = getToolbar();
    
    public GuiCamp(Form previous,Resources theme) {
        current=this;
          setLayout(BoxLayout.y());
                       tb.addMaterialCommandToSideMenu("", FontImage.MATERIAL_EMOJI_EMOTIONS, null);
      tb.addMaterialCommandToSideMenu("Camps", FontImage.MATERIAL_HOME, e-> new GuiCamp(current,theme).show());
      
      tb.addMaterialCommandToSideMenu("Réfugiés", FontImage.MATERIAL_PEOPLE, e-> new refugie(current,theme).show());
       
       tb.addMaterialCommandToSideMenu("Besoins", FontImage.MATERIAL_WARNING, e-> new GuiBesoins(current,theme).show());
         tb.addMaterialCommandToSideMenu("Cas Sociaux", FontImage.MATERIAL_HELP, e-> new GuiCasSociale(current,theme).show());
 tb.addMaterialCommandToSideMenu("Dons", FontImage.MATERIAL_MONEY_OFF, e-> new AddDonForm(current,theme).show());
 tb.addMaterialCommandToSideMenu("Contactez - nous !",FontImage.MATERIAL_MAIL,e-> new OpinionDAO());
 
  tb.addMaterialCommandToSideMenu("se déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            try {
                Database db = Database.openOrCreate("Maddox.db");
                db.execute("delete from appstates");
                Utilisateur.current_user = null;
                new SignInForm(theme).show();
            } catch (IOException ex) {
            }
        });
          
          
       Style catRecStyle= getAllStyles();
        //catRecStyle.setBgColor(0xAACDFC);
        
        catRecStyle.setBgColor(0xffffff);
        
              /*FORMULAIRE LIGNE DE TRANSPORT*/
      //  Form bus= new Form("LIGNE DE TRANSPORT",BoxLayout.y());
        TextField tfnomCamp= new TextField("","Saisir le nom du camp");
       
        TextField tfCapacite=new TextField("","Saisir la capacité");
        tfCapacite.setConstraint(TextField.NUMERIC);//contrainte 3al type int
      
          ComboBox<String> tfcamp=new ComboBox("lieu disponible");
                 listCord=CampService.getInstance().getAllCord();

for(int i=0;i<listCord.size();i++)
{ tfcamp.addItem(listCord.get(i).getLieu());
System.out.println(listCord.get(i).getLieu());
}
        
        Button ajouter=new Button("ajouter");
        //style button
         Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        Style butStyle5=ajouter.getAllStyles();
        butStyle5.setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
        butStyle5.setBgColor(0xff0000);
        butStyle5.setBgTransparency(255);
        butStyle5.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle5.setMargin(Component.BOTTOM, 0);       
        butStyle5.setMargin(Component.TOP,0);           
        butStyle5.setMargin(Component.LEFT,0);  
        butStyle5.setMargin(Component.RIGHT,0); 
        
                   getToolbar().addCommandToRightBar("affichage des camps", null, ev->{
         new  gererCamp(current,theme).show();
        });
        
        ajouter.addActionListener(new ActionListener() {

            @Override
             public void actionPerformed(ActionEvent evt) {
                if ((tfnomCamp.getText().length()==0)||(tfnomCamp.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
     
      
                else
                {
                    
                    try {
                         
                       idLieu =  rechercherCord(tfcamp.getSelectedItem());
                   
                        Camp t = new Camp(idLieu,tfnomCamp.getText(),Integer.parseInt(tfCapacite.getText()));
                        System.out.println(t.toString());
                        if( CampService.getInstance().addCamp(t))
                        { Dialog.show("Success","vous avez ajouté un camp",new Command("OK"));
             
                            
                        }
                           
                        else
                            Dialog.show("ERROR", "erreur du serveur", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "la capacité doit etre un numero", new Command("OK"));
                    }
                    
                }  
            
            }
        });
       
        
        //add to show
        current.addAll(tfnomCamp,tfcamp,tfCapacite,ajouter);
        current.show();

        
    }
   
  public Cord rechercherCord(String nom) {
      Cord id = null;
       for(int i = 0; i<listCord.size();i++){
          
           if(listCord.get(i).getLieu().equals(nom)){
                System.out.println(listCord.get(i).toString());
               id = listCord.get(i);
           }
       }
           return id;
       
    }
    
  
    
      
    
}
