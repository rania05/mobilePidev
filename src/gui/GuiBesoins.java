/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui ;



import com.codename1.capture.Capture;
import com.codename1.components.MultiButton;
import com.codename1.db.Database;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import entities.Besoins;
import entities.Categorie;
import entities.Cord;
import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import services.BesoinsService;
import services.CampService;
import services.OpinionDAO;
/**
 *
 * @author Rania
 */
public class GuiBesoins  extends Form{
     Form current;
    
      ArrayList<Categorie>listCat;
    ArrayList<Camp>listCamp;
  Camp idLieu ;
   Categorie idCat ;
     Toolbar tb = getToolbar();
    
    public GuiBesoins(Form previous,Resources theme) {
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
    
       
      
      
          ComboBox<String> tfcamp=new ComboBox("Camps");
                 listCamp=CampService.getInstance().getAllCamp();

for(int i=0;i<listCamp.size();i++)
{ tfcamp.addItem(listCamp.get(i).getLieu().getLieu());
System.out.println(listCamp.get(i).getLieu());
}
   ComboBox<String> tfcat=new ComboBox("Catégories");
                 listCat=BesoinsService.getInstance().getAllCategorie();

for(int i=0;i<listCat.size();i++)
{ tfcat.addItem(listCat.get(i).getNom());
System.out.println(listCat.get(i).getNom());

}
  TextField tfQuantite=new TextField("","Saisir la quantitée");
 tfQuantite.setConstraint(TextField.NUMERIC);//contrainte 3al type int
        
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
        
                   getToolbar().addCommandToRightBar("Afficher Besoins", null, ev->{
         new  gererBesoins(current,theme).show();
        });
                  
                   getToolbar().addCommandToRightBar("",null, ev->{
                      

FileSystemStorage fs = FileSystemStorage.getInstance();
String recordingsDir = fs.getAppHomePath() + "recordings/";
fs.mkdir(recordingsDir);
           try {
            String file = Capture.captureAudio();
            if(file != null) {
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd-kk-mm");
                String fileName =sd.format(new Date());
                String filePath = recordingsDir + fileName;
                Util.copy(fs.openInputStream(file), fs.openOutputStream(filePath));
                MultiButton mb = new MultiButton(fileName);
                mb.addActionListener((e) -> {
                    try {
                        Media m = MediaManager.createMedia(filePath, false);
                        m.play();
                    } catch(IOException err) {
                        Log.e(err);
                    }
                });
                this.add(mb);
                this.revalidate();
            }
        } catch(IOException err) {
            Log.e(err);
        }
        });
        

/*affichage de record
try { for(String file : fs.listFiles(recordingsDir)) {
        MultiButton mb = new MultiButton(file.substring(file.lastIndexOf("/") + 1));
        mb.addActionListener((e) -> {
            try {
                Media m = MediaManager.createMedia(recordingsDir + file, false);
                m.play();
            } catch(IOException err) {
                Log.e(err);
            }
        });
        this.add(mb);
    }
}
catch(IOException err) {
    Log.e(err);
}*/
ajouter.addActionListener(new ActionListener() {

            @Override
             public void actionPerformed(ActionEvent evt) {
                if (tfQuantite.getText().length()==0)
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
     
      
                else
                {
                    
                    try {
                         
                       idLieu =  rechercherCamp(tfcamp.getSelectedItem());
                            idCat =  rechercherCat(tfcat.getSelectedItem());
                   
                        Besoins t = new Besoins(idLieu,idCat,Integer.parseInt(tfQuantite.getText()));
                        System.out.println(t.toString());
                        if( BesoinsService.getInstance().addBesoins(t))
                        { Dialog.show("Success","vous avez ajouté un besoins",new Command("OK"));
             
                            
                        }
                           
                        else
                            Dialog.show("ERROR", "erreur du serveur", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR","", new Command("OK"));
                    }
                    
                }  
            
            }
        });
       
        
        //add to show
        current.addAll(tfcamp,tfcat,tfQuantite,ajouter);
        current.show();

        
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
    
  
    
      
    
}
