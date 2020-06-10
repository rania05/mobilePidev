/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import services.OpinionDAO;

/**
 *
 * @author Nesrine
 */
public class HomeForm extends Form{
   // Form current;
    
    Form f;
  // req.setUrl("http://localhost/piService/event/insert.php?titre=" +  p.getNom() + "&descritpion=" + p.getDescription() + "&prenom=" + p.getPrenom() + "&objet="+p.getObjet()+"&email="+p.getEmail());

    public HomeForm() {
        f = new Form("home");

        f=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Let's donate"));
        Button btnAddTask = new Button("Add Don");
     //   Button btnListTasks = new Button("List Dons");
      //  Button btnStat = new Button("Stat");
     
        btnAddTask.addActionListener(e-> new AddDonForm(f).show());
     //  btnListTasks.addActionListener(e-> new ListDonForm(f).show());
    //    btnStat.addActionListener(e-> new stat(f).show());
        
      
        Button   aff = new Button("List Dons");
           f.add(aff);
                  
       aff.addActionListener((e) -> {
            ListDonForm a = null;
            try {
                a = new ListDonForm();
            } catch (IOException ex) {
           
            }
            a.getF().show();
        });
                              // Form hi = new Form("ToastBarDemo", BoxLayout.y());
/*
Button basic = new Button("Basic");
Button progress = new Button("Progress");
Button expires = new Button("Expires");
Button delayed = new Button("Delayed");
f.add(basic).add(progress).add(expires).add(delayed);

basic.addActionListener(e -> {
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.show();
  //...  Some time later you must clear the status
  // status.clear();
});

progress.addActionListener(e -> {
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.setShowProgressIndicator(true);
  status.show();
  // ... Some time later you must clear it
});

expires.addActionListener(e -> {
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.setExpires(3000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
});

delayed.addActionListener(e -> {
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Hello world");
  status.showDelayed(300); // Wait 300 ms to show the status
  // ... Some time later, clear the status... this may be before it shows at all
});

f.show();*/
        Button btnValider = new Button("mail us!");
           
           
          btnValider.addActionListener(new ActionListener() {
  

            @Override
            public void actionPerformed(ActionEvent evt) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              new OpinionDAO();
            }
  });  
       
            addAll(btnAddTask,btnValider);
    }
    
    
    
    
      public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    /*
    LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      */
    
     public void localNotificationReceived(String notificationId) {
                System.out.println("Received local notification "+notificationId);
            }

    
}
