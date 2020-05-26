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
import com.codename1.ui.events.ActionListener;
import entities.Camp;
import entities.Cord;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rania
 */
public class CampService {
       public ConnectionRequest req;
         public static CampService  instance=null;
         public boolean resultOK;
         public ArrayList<Camp> tasks;
         public Camp ti=new Camp();
          public ArrayList<Cord> tasks1;
     
    public CampService() {
        req = new ConnectionRequest();    
    }
           
     public static CampService getInstance() {
        if (instance == null) {
            instance = new CampService();
        }
        return instance;
    }  
     
     public ArrayList<Camp> parseRefugie(String jsonText)throws Exception{
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                Camp t = new Camp();
                 System.out.println(obj.get("lieu").toString());
                float id = Float.parseFloat(obj.get("id").toString());
                Map<String, Object> lieu = (Map<String, Object>) obj.get("lieu");
                float capacite= Float.parseFloat(obj.get("capacite").toString());
               
                t.setId((int)id);
                t.setNomCamp(obj.get("nomCamp").toString());
                Cord c = new Cord((int)Float.parseFloat(lieu.get("id").toString()),lieu.get("lieu").toString(),(int)Float.parseFloat(lieu.get("latitude").toString()),(int)Float.parseFloat(lieu.get("longitude").toString()));
                t.setLieu(c);
                t.setCapacite((int)capacite);
                
                System.out.println("Camp = "+t.toString());
                tasks.add(t);
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return tasks;
    }
     
     
     
    /*  public Camp parseCamp1(String jsonText){
       
              Camp t = new Camp();
              try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          //  System.out.println(tasksListJson.get(0));
                float id = Float.parseFloat(tasksListJson.get("id").toString());
                float capacite= Float.parseFloat(tasksListJson.get("capacite").toString());
                float lieu= Float.parseFloat(tasksListJson.get("ieu").toString());
                t.setId((int)id);
                t.setNomCamp(tasksListJson.get("nomCamp").toString());
               
                t.setLieu((int)lieu);
                  t.setCapacite((int)capacite);
                
                tasks.add(t);
            
        } catch (IOException ex) {
            
        }
        return t;
    }*/
    
      
       
        /*public Camp getCamp(int id){
        String url ="http://127.0.0.1/Maddox_heart2hold (1)/Maddox_heart2hold/web/app_dev.php/gcamp/CampMobile/"+id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
               
                ti = parseCamp1(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ti;
    }*/
        
        
         public boolean addCamp(Camp t) {
        //String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/ajoutCamp?nomCamp="+t.getNomCamp()+"&lieu="+t.getLieu().getId()+ "&capacite="+t.getCapacite(); //création de l'URL
          String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/ajoutCamp?nomCamp="+t.getNomCamp()+"&lieu="+t.getLieu().getId()+
                "&capacite="+t.getCapacite(); //création de l'URL
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
         
         
         
     public ArrayList<Camp> suppCamp(int id){
      //  String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/supprimerCamp?id="+id;
         String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/supprimerCamp?id="+id;
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                    tasks = parseRefugie(new String(req.getResponseData()));
                } catch (Exception ex) {
                
                }
                    //System.out.println("chnia mochkol "+tasks);
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
   
  public boolean modifierCamp(Camp t) {
            // String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/modifierCamp?nomCamp="+t.getNomCamp()+"&lieu="+t.getLieu().getId()+ "&capacite="+t.getCapacite()+"&id="+t.getId();
             String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/modifierCamp?nomCamp="+t.getNomCamp()+"&lieu="+t.getLieu().getId()+ "&capacite="+t.getCapacite()+"&id="+t.getId(); 
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
     
  
  
        public ArrayList<Camp> getAllCamp(){
      //  String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/afficherCamp";
          String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/afficherCamp";
       //System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    tasks = parseRefugie(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
        
   
      public ArrayList<Cord> parseCord(String jsonText)throws Exception{
        try {
            tasks1=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                Cord t = new Cord();
               float id = Float.parseFloat(obj.get("id").toString());
               
                double longitude= Double.parseDouble(obj.get("longitude").toString());
                   double latitude= Double.parseDouble(obj.get("latitude").toString());
                t.setId((int)id);
                t.setLieu(obj.get("lieu").toString());
                System.out.println(t.toString());
               // t.setLieu((int)lieu);
                  t.setLongitude((double)longitude);
                                  t.setLatitude((double)latitude);
                
                tasks1.add(t);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return tasks1;}
       public ArrayList<Cord> getAllCord(){
         //   String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/lieu";
               String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/lieu";
       //System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    tasks1 = parseCord(new String(req.getResponseData()));
                   
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks1;
    }
}
