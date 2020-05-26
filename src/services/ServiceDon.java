/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import entities.don;

/**
 *
 * @author Nesrine
 */
public class ServiceDon {
   public ArrayList<don> dons;
    
    public static ServiceDon instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
 String a ="";
    public ServiceDon() {
         req = new ConnectionRequest();
    }

    public static ServiceDon getInstance() {
        if (instance == null) {
            instance = new ServiceDon();
        }
        return instance;
    }

    public boolean addDon(don t) {
     //   ConnectionRequest con = new ConnectionRequest();
        String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/volentaire/new" + t.getNom() + "/" + t.getPrenom() + "/" + t.getEmail() + "/" + t.getObjet()+ "/" + t.getDescription()+ "/" + t.getQuantite();
        req.setUrl(url);
        
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
    
    
     public void addDonn(don t) {
      // ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/volentaire/new"
                  + t.getNom() + "/" + t.getPrenom() + "/" + t.getEmail() + "/" + t.getObjet()+ "/" + t.getDescription()+ "/" + t.getQuantite();
        req.setUrl(Url);


        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    public ArrayList<don> parseDons(String jsonText) throws IOException{
         try {
        dons=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list)
            {
                don t = new don();
                
       /*      float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));*/
       
               t.setId((int)Float.parseFloat(obj.get("id").toString()));
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setObjet(obj.get("objet").toString());
                t.setEmail(obj.get("email").toString());
                t.setDescription(obj.get("description").toString());
                 float quantite= Float.parseFloat(obj.get("quantite").toString());
                t.setQuantite((int)quantite);
             

                dons.add(t);
             }
       
        } catch (IOException ex) {
            
        }
        return dons;
    }
       
    

     public ArrayList<don> getAllDons(){
       /* ConnectionRequest con = new ConnectionRequest();/*
                Form hi = new Form("ToastBarDemo", BoxLayout.y());

Button basic = new Button("Basic");
Button progress = new Button("Progress");
Button expires = new Button("Expires");
Button delayed = new Button("Delayed");
hi.add(basic).add(progress).add(expires).add(delayed);

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

hi.show();*/
       String url = "http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/volentaire/all";
      

  req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
          try {
                   // System.out.println(url);
                    dons = parseDons(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return dons;
    }
     
     
     
        public String getMail(int id){
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Allforkids/web/app_dev.php/Etab/getMail/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 ServiceDon ser = new ServiceDon();
                 a = ser.getMail2(new String(con.getResponseData()));
             
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
     return a ;
    }
     public String getMail2(String json) {
                
         String  e = "" ;
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
          e = obj.get("mail").toString();
             
            
        } catch (IOException ex) {
        }
        return e;
        
    }
     
     
     
    
}
