/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Camp;
import entities.Cassociale;
import entities.Cord;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.joda.time.format.ISODateTimeFormat.date;

/**
 *
 * @author Rania
 */
public class CasSocialeService {
       public ConnectionRequest req;
         public static CasSocialeService  instance=null;
         public boolean resultOK;
         public ArrayList<Cassociale> tasks;
         
     
    public CasSocialeService() {
        req = new ConnectionRequest();    
    }
           
     public static CasSocialeService getInstance() {
        if (instance == null) {
            instance = new CasSocialeService();
        }
        return instance;
    }  
     
    public ArrayList<Cassociale> parseCas(String jsonText)throws Exception{
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser(); 
               Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
               List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
         
            Map<String, Object> date = (Map<String, Object>) obj.get("date");
         
            System.out.print("size :"+list.size());
           
                Cassociale t = new Cassociale();
                float id = Float.parseFloat(obj.get("id").toString());
        
               float dateF = Float.parseFloat(date.get("timestamp").toString());
                t.setDate((long)dateF);
               
                t.setId((int)id);
                t.setLieu(obj.get("lieu").toString());
               // t.setDate(Date.);
            
               
               
            
                tasks.add(t);
            }
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
     
     
     
   
         public boolean addCas(Cassociale t) {
                  DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/frm/ajouterCas?Lieu="+t.getLieu();
          String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/frm/ajouterCas?lieu="+t.getLieu()+"&date="+format.format(t.getDate());
             System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         
         
         
     public ArrayList<Cassociale> suppCas(int id){
      //  String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/supprimerCamp?id="+id;
         String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/frm/supprimerCas?id="+id;
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                    tasks = parseCas(new String(req.getResponseData()));
                } catch (Exception ex) {
                
                }
                    //System.out.println("chnia mochkol "+tasks);
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
   
  public boolean modifierCas(Cassociale t) {
         DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/modifierCamp?nomCamp="+t.getNomCamp()+"&lieu="+t.getLieu().getId()+ "&capacite="+t.getCapacite()+"&id="+t.getId();
      String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/frm/modifierCas?lieu="+t.getLieu()+"&date="+format.format(t.getDate())+"&id="+t.getId();
             System.out.println(url);
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
     
  
  
        public ArrayList<Cassociale> getAllCas(){
      //  String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/afficherCamp";
          String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/frm/afficherCas";
       //System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    tasks = parseCas(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
        

      
}
