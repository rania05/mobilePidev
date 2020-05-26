/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.mycompany.myapp.MyApplication.theme;
import entities.Camp;
import entities.Refugie;
import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import services.OpinionDAO;
import services.RefugieService;

/**
 *
 * @author ASUS
 */
public class refugie extends BaseForm  {
    Form current;
    int idCamp=0;
     public refugie() {
    }
    

    public refugie(Form previous,Resources res) {
        current=this;
         Toolbar tb = getToolbar();
        setLayout(BoxLayout.y());
   /*   getToolbar().addMaterialCommandToLeftSideMenu("back", 
                FontImage.MATERIAL_BACKUP, ev->{new gererRefugie(current).show();});
       getToolbar().addMaterialCommandToLeftSideMenu("home", 
                FontImage.MATERIAL_BACKUP, ev->{new AccueilForm(res).show();});
          getToolbar().addCommandToLeftSideMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
          this.getToolbar().addCommandToLeftSideMenu("Ajouter Camp", null, ev->{
         new  GuiCamp(current,theme).show();
    });
            this.getToolbar().addCommandToLeftSideMenu("Ajouter Refugié", null, ev->{
         new  refugie(current,res).show();
    });*/
     tb.addMaterialCommandToSideMenu("", FontImage.MATERIAL_EMOJI_EMOTIONS, null);
      tb.addMaterialCommandToSideMenu("Camps", FontImage.MATERIAL_HOME, e-> new GuiCamp(current,theme).show());
      
      tb.addMaterialCommandToSideMenu("Réfugiés", FontImage.MATERIAL_PEOPLE, e-> new refugie(current,res).show());
       
         tb.addMaterialCommandToSideMenu("Besoins", FontImage.MATERIAL_WARNING, e-> new GuiBesoins(current,res).show());
         tb.addMaterialCommandToSideMenu("Cas Sociaux", FontImage.MATERIAL_HELP, e-> new GuiCasSociale(current,res).show());
 tb.addMaterialCommandToSideMenu("Dons", FontImage.MATERIAL_MONEY_OFF, e-> new AddDonForm(current,res).show());
  tb.addMaterialCommandToSideMenu("Contactez - nous !",FontImage.MATERIAL_MAIL,e-> new OpinionDAO());

 
  tb.addMaterialCommandToSideMenu("se déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            try {
                Database db = Database.openOrCreate("Maddox.db");
                db.execute("delete from appstates");
                Utilisateur.current_user = null;
                new SignInForm(res).show();
            } catch (IOException ex) {
            }
        });
        Style catRecStyle= getAllStyles();
        //catRecStyle.setBgColor(0xAACDFC);
        
        catRecStyle.setBgColor(0xffffff);
              setTitle("Ajout des réfugiés ");
        TextField tfnom= new TextField("","Saisir le nom du refugie");
        TextField tfprenom=new TextField("","Saisir le prenom du refugie");
        TextField tfage=new TextField("","Saisir l'age du refugie");
        tfage.setConstraint(TextField.NUMERIC);
        TextField tforigine=new TextField("","Saisir l'origine");
        ComboBox<String> tfcamp=new ComboBox("Liste Camp");
        ArrayList<Camp>listCamp=RefugieService.getInstance().getAllCamp();
        
       // System.out.println("why"+listCamp.get(0).getNomCamp());

for(int i=0;i<listCamp.size();i++)
{ tfcamp.addItem(listCamp.get(i).getNomCamp());
System.out.println("why"+listCamp.get(i).getNomCamp());
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
        
                    this.getToolbar().addCommandToRightBar("List", null, ev->{
         new  gererRefugie(current).show();
        });
        
        ajouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tfnom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
      
                else
                {
                    try {
                        for(int i=0;i<listCamp.size();i++)
{ if(tfcamp.getSelectedItem().equals(listCamp.get(i).getNomCamp()))
{idCamp=listCamp.get(i).getId();
break;
}
    }
                        Refugie t = new Refugie(tfnom.getText(),tfprenom.getText(),Integer.parseInt(tfage.getText()),tforigine.getText(),idCamp);//,Integer.parseInt(String.valueOf(tfcamp))
                        if( RefugieService.getInstance().addRefugie(t))
                        { Dialog.show("Success","Connection accepted",new Command("OK"));
             
                            
                        }
                           
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "age must be a number", new Command("OK"));
                    }
                    
                }  
            
            }
        });
       
        
        
        current.addAll(tfnom,tfprenom,tfage,tforigine,tfcamp,ajouter);
     //   current.addScrollListener((ScrollListener) tfcamp);
                
        current.show();

        
    }
/*
   public Form StatEvent(Form previous) {
        EventPieChart a = new EventPieChart();
        Form stats_Form = a.execute();
        SpanLabel test_SpanLabel = new SpanLabel("Hiiii");
        Class cls = EventPieChart.class;
        stats_Form.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new refugie(previous).show();
        });
        return stats_Form;
    }
    */
}
