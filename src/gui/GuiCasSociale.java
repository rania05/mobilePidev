/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui ;



import com.codename1.components.MultiButton;
import com.codename1.db.Database;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import entities.Cassociale;
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
import java.util.Random;
import entities.Utilisateur;
import services.TwilioSMS;
import com.mycompany.myapp.MyApplication;
import entities.Cord;
import java.util.ArrayList;
import services.CasSocialeService;
import com.codename1.ui.spinner.Picker;
import static com.mycompany.myapp.MyApplication.theme;
import java.io.IOException;
import services.OpinionDAO;
/**
 *
 * @author Rania
 */
public class GuiCasSociale  extends Form{
     Form current;
    
  Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MIC, s);
FileSystemStorage fs = FileSystemStorage.getInstance();
String recordingsDir = fs.getAppHomePath() + "recordings/";
//fs.mkdir(recordingsDir);

   
    
    public GuiCasSociale(Form previous,Resources theme) {
        current=this;
         Toolbar tb = getToolbar();
          setLayout(BoxLayout.y());
      if(Utilisateur.current_user.getRoles().contains("ROLE_RESPCAMP")){
                 tb.addMaterialCommandToSideMenu("", FontImage.MATERIAL_EMOJI_EMOTIONS, null);
      tb.addMaterialCommandToSideMenu("Camps", FontImage.MATERIAL_HOME, e-> new GuiCamp(current,theme).show());
      
      tb.addMaterialCommandToSideMenu("Réfugiés", FontImage.MATERIAL_PEOPLE, e-> new refugie(current,theme).show());
       
          tb.addMaterialCommandToSideMenu("Besoins", FontImage.MATERIAL_WARNING, e-> new GuiBesoins(current,theme).show());
         tb.addMaterialCommandToSideMenu("Cas Sociaux", FontImage.MATERIAL_HELP, e-> new GuiCasSociale(current,theme).show());
 tb.addMaterialCommandToSideMenu("Dons", FontImage.MATERIAL_MONEY_OFF, e-> new AddDonForm(current,theme).show());
  tb.addMaterialCommandToSideMenu("Contactez - nous !",FontImage.MATERIAL_MAIL,e-> new OpinionDAO());
        }
        else
        {
                 tb.addMaterialCommandToSideMenu("", FontImage.MATERIAL_EMOJI_EMOTIONS, null);
             tb.addMaterialCommandToSideMenu("Cas Sociaux", FontImage.MATERIAL_HELP, e-> new GuiCasSociale(current,theme).show());
              tb.addMaterialCommandToSideMenu("Projet", FontImage.MATERIAL_WORK, null);
       tb.addMaterialCommandToSideMenu("Partenaire", FontImage.MATERIAL_WORK, null);
 tb.addMaterialCommandToSideMenu("Dons", FontImage.MATERIAL_MONEY_OFF, e-> new AddDonForm(current,theme).show());
  tb.addMaterialCommandToSideMenu("Messages des refugiées", FontImage.MATERIAL_MIC, (ActionEvent e)->
            
  {
  try { for(String file : fs.listFiles(recordingsDir)) {
        MultiButton mb = new MultiButton(file.substring(file.lastIndexOf("/") + 1));
        mb.addActionListener((l) -> {
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
}
  });
  tb.addMaterialCommandToSideMenu("Contactez - nous !",FontImage.MATERIAL_MAIL,e-> new OpinionDAO());
            
        }
          
 
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
        TextField tfLieu= new TextField("","Saisir le lieu de cas sociale");
        Picker datePicker = new Picker();
             
         //datePicker.setType(Display.PICKER_TYPE_DATE);
            
       
       


        
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
        
                   getToolbar().addCommandToRightBar("affichage", null, ev->{
         new  gererCasSociale(current,theme).show();
        });
        
        ajouter.addActionListener(new ActionListener() {

            @Override
             public void actionPerformed(ActionEvent evt) {
                if (tfLieu.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
     
      
                else
                {
                    
                    try {
                         
                       Cassociale t = new Cassociale();
                   t.setLieu(tfLieu.getText());
                        t.setDate(datePicker.getDate());
                        CasSocialeService ta=new CasSocialeService();
                        ta.addCas(t);
                        
                        System.out.println(t.toString());
                        if( CasSocialeService.getInstance().addCas(t))
                        { Dialog.show("Success","Connection accepted",new Command("OK"));
             
                                          Random r = new Random();
                           String val = "" + r.nextInt(10000);

                      System.out.println("val  : " +val);
                          Utilisateur.current_user.toString();
                            TwilioSMS s=new TwilioSMS("AC6868827b2f536a5a1ea09289a85cde51","1e9ffd6e2ba97e21ac55148317a2c346","+14809234745");
                              s.sendSmsAsync("+21626051540","Madame,Monsieu le cas social a été bien confirmé :"+Utilisateur.current_user.getNom()+" "+Utilisateur.current_user.getPrenom()+"Votre code  de cas social est :"+val);
                            
                        }
                           
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "le lieu ne doit pas etre vide", new Command("OK"));
                    }
                    
                }  
            
            }
        });
       
        
        //add to show
        current.addAll(tfLieu,datePicker,ajouter);
        current.show();

        
    }
   
  
    
  
    
      
    
}
