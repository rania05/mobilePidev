/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.FloatingHint;
import com.codename1.components.MultiButton;
import com.codename1.db.Database;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
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
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import static com.mycompany.myapp.MyApplication.theme;
import entities.Utilisateur;
import entities.don;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import services.OpinionDAO;


//import java.util.logging.Level;
//import java.util.logging.Logger;
import services.ServiceDon;

/**
 *
 * @author Nesrine
 */
public class AddDonForm  extends Form{
      Form current;
   Database bd; 
   Form form;
    Dialog dd  ;
     Toolbar tb = getToolbar();
       Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MIC, s);
FileSystemStorage fs = FileSystemStorage.getInstance();
String recordingsDir = fs.getAppHomePath() + "recordings/";
//fs.mkdir(recordingsDir);
   public AddDonForm(Form previous,Resources res) {
       
        setTitle("Let's Help each other");
      
               setLayout(BoxLayout.y());
       if(Utilisateur.current_user.getRoles().contains("ROLE_RESPCAMP")){
                 tb.addMaterialCommandToSideMenu("", FontImage.MATERIAL_EMOJI_EMOTIONS, null);
      tb.addMaterialCommandToSideMenu("Camps", FontImage.MATERIAL_HOME, e-> new GuiCamp(current,theme).show());
      
      tb.addMaterialCommandToSideMenu("Réfugiés", FontImage.MATERIAL_PEOPLE, e-> new refugie(current,res).show());
       
        tb.addMaterialCommandToSideMenu("Besoins", FontImage.MATERIAL_WARNING, e-> new GuiBesoins(current,res).show());
         tb.addMaterialCommandToSideMenu("Cas Sociaux", FontImage.MATERIAL_HELP, e-> new GuiCasSociale(current,res).show());
 tb.addMaterialCommandToSideMenu("Dons", FontImage.MATERIAL_MONEY_OFF, e-> new AddDonForm(current,res).show());
  tb.addMaterialCommandToSideMenu("Contactez - nous !",FontImage.MATERIAL_MAIL,e-> new OpinionDAO());
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
        
        TextField tfNom         = new TextField("","nom" );
        TextField tfPrenom      = new TextField("", "prenom" );
        TextField tfEmail       = new TextField("","email", 20, TextField.EMAILADDR);
        TextField tfObjet       = new TextField("", "Objet");
        TextField tfDescription = new TextField("","description");
          TextField tfQnt = new TextField("","qantité");
          tfQnt.setConstraint(TextField.NUMERIC);
    
        Button btnValider = new Button("Add Don");
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        Style butStyle5=btnValider.getAllStyles();
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
             new  ListDonForm(form).show();
        });
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
        don p = new don(tfNom.getText(), tfPrenom.getText(),tfEmail.getText() ,tfObjet.getText(),tfDescription.getText(),Integer.parseInt(tfQnt.getText()));
                ConnectionRequest req = new ConnectionRequest();
          //  req.addArgument("image", fileName);
                  if(validation( p) ==true ){
            req.setUrl("http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/volentaire/new/"  + p.getNom() + "/" + p.getPrenom() + "/" + p.getEmail() + "/" + p.getObjet()+ "/" + p.getDescription()+ "/" + p.getQuantite());
           
                      System.out.println("http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/volentaire/new/"  + p.getNom() + "/" + p.getPrenom() + "/" + p.getEmail() + "/" + p.getObjet()+ "/" + p.getDescription()+ "/" + p.getQuantite());
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                       
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                      
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                        
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
   
                
                  //   Dialog d = new Dialog("Succes!");
          Dialog.show("Success","Merci pour votre don :) ",new Command("OK"));
            // SendSMS.sendSMSreservation("nouveau don");  
          //  popupBody.setUIID("PopupBody");
          //  popupBody.setEditable(false);
          //  Button ok = new Button("OK");
             // ok.addActionListener(e-> new ListDonForm(form,res).show());     
             /* ok.addActionListener((e) -> {
            ListDonForm a = null;
            try {
                a = new ListDonForm(form,res);
            } catch (IOException ex) {
           
            }
            a.getF().show();
        });*/
     /*  d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.add(BorderLayout.SOUTH,ok);
            d.show();
            d.setTimeout(2000);
            */
         
      
                

           //  Display.getInstance().setSMS("+999999999", "My SMS Message");
        
                  }
                
                
                }
        });
        
        addAll(tfNom,tfPrenom,tfEmail,tfObjet,tfDescription,tfQnt,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
        
           
       
       /*
          
   
    form = new Form("remplir le formulaire", BoxLayout.y());
    Validator v = new Validator();
    
    form.add(new Label("nom :"));
    TextField nom = new TextField();
    form.add(nom);
    
    form.add(new Label("prenom:"));
    TextField prenom = new TextField();
    form.add(prenom);
    
    form.add(new Label("Email:"));
    TextField email = new TextField();
    form.add(email);
    
  
        form.add(new Label("type:"));
    ComboBox<Object> objet = new ComboBox<>();
        objet.addItem("Argent");
        objet.addItem("vetement");
        objet.addItem("meal");
        objet.addItem("song");
        objet.addItem("autre chose");
        form.add(objet);
    

    
    form.add(new Label("description:"));
    TextField description = new TextField("", "1",1, TextArea.NUMERIC);
        v.addConstraint(description,new LengthConstraint(1));
        v.addConstraint(description,new NumericConstraint(true));
    form.add(description);
    
  
    

    
    Button valider = new Button("valider");
    form.add(valider);
    
    
    valider.addActionListener((ActionEvent e) -> {
            ServiceDon service = new ServiceDon();
          //   don(String nom, String prenom, String email, String objet, String descripton
            don transport = new don(nom.getText(),prenom.getText(),email.getText(),"Argent",description.getText());
            service.addDon(transport);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("votrce don est valider");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            
            Button ok = new Button("OK");
       /*   ok.addActionListener((ActionEvent ee) -> {
                ShowTransport a = null;
                try {
                    a = new ShowTransport();
                } catch (IOException ex) {
                    System.out.println("erreuur");
                }
                a.getForm().show();
            });*/
            
  /*     d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.add(BorderLayout.SOUTH,ok);
            d.show();
            d.setTimeout(2000);
            
        });
   
*/
       
    }
   

   
     
       
       
public boolean validation(don e){
    boolean a = false ;
    ServiceDon es = new ServiceDon();
    if(e.getNom().equals("")){
  
            dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Nom ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
return false ;
            }
    if(e.getDescription().equals("") ){
         dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Description ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false ;
    }
      if(e.getPrenom().equals("") ){
         dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("le prenom  ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false ;
    } 
      if(e.getEmail().equals(""))
      {
         dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Email ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false ;
    }

       if(e.getObjet().equals(""))
      {
         dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Objet ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false ;
    }
      
      
      

return true ;
} 
    
}
