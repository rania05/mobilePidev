/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.MultiButton;
import com.codename1.db.Database;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.mycompany.myapp.MyApplication.theme;
import entities.Utilisateur;
import java.io.IOException;
import services.OpinionDAO;

/**
 *
 * @author actar
 */
public class BaseForm extends Form {
     public Form current;
   Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MIC, s);
FileSystemStorage fs = FileSystemStorage.getInstance();
String recordingsDir = fs.getAppHomePath() + "recordings/";
//fs.mkdir(recordingsDir);
    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

   
    
    
    
    //sidemenu
   protected void addSideMenu(Resources res) {
       Toolbar.setGlobalToolbar(true);
        Toolbar tb = getToolbar();
        
        /*Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
        hi.getToolbar().addCommandToLeftBar("Left", icon, (e) -> Log.p("Clicked"));
        hi.getToolbar().addCommandToRightBar("Right", icon, (e) -> Log.p("Clicked"));
        hi.getToolbar().addCommandToOverflowMenu("Overflow", icon, (e) -> Log.p("Clicked"));
        hi.getToolbar().addCommandToSideMenu("Sidemenu", icon, (e) -> Log.p("Clicked"));
        hi.show();*/
        //Image img = res.getImage("Novant.png");
       
        
         setUIID("Home");
        

      
      
        if(Utilisateur.current_user.getRoles().contains("ROLE_RESPCAMP")){
                 tb.addMaterialCommandToSideMenu("", FontImage.MATERIAL_EMOJI_EMOTIONS, null);
      tb.addMaterialCommandToSideMenu("Camps", FontImage.MATERIAL_HOME, e-> new GuiCamp(current,theme).show());
      
      tb.addMaterialCommandToSideMenu("Réfugiés", FontImage.MATERIAL_PEOPLE, e-> new refugie(current,res).show());
       
          tb.addMaterialCommandToSideMenu("Besoins", FontImage.MATERIAL_WARNING, e-> new GuiBesoins(current,res).show());
         tb.addMaterialCommandToSideMenu("Cas Sociaux", FontImage.MATERIAL_HELP, e-> new GuiCasSociale(current,res).show());
 tb.addMaterialCommandToSideMenu("Dons", FontImage.MATERIAL_MONEY_OFF, e-> new AddDonForm(current,res).show());
 
        }
        else
        {
                 tb.addMaterialCommandToSideMenu("", FontImage.MATERIAL_EMOJI_EMOTIONS, null);
             tb.addMaterialCommandToSideMenu("Cas Sociaux", FontImage.MATERIAL_HELP, e-> new GuiCasSociale(current,res).show());
 tb.addMaterialCommandToSideMenu("Dons", FontImage.MATERIAL_MONEY_OFF, e-> new AddDonForm(current,res).show());
    tb.addMaterialCommandToSideMenu("Projet", FontImage.MATERIAL_WORK, null);
       tb.addMaterialCommandToSideMenu("Partenaire", FontImage.MATERIAL_WORK, null);
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
            
        }
          
        
            
           //  Button btnValider = new Button("Contactez - nous !");
              tb.addMaterialCommandToSideMenu("Contactez - nous !",FontImage.MATERIAL_MAIL,e-> new OpinionDAO());
         // btnValider.addActionListener(new ActionListener() {
  

          //  @Override
          //  public void actionPerformed(ActionEvent evt) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             // new OpinionDAO();
            //}
  ///});  
       
        //  addAll(btnValider);

        tb.addMaterialCommandToSideMenu("se déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            try {
                Database db = Database.openOrCreate("Maddox.db");
                db.execute("delete from appstates");
                Utilisateur.current_user = null;
                new SignInForm(res).show();
            } catch (IOException ex) {
            }
        });
   }
    
}

