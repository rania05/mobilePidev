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
import entities.Refugie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class RefugieService {
         public ConnectionRequest req;
         public static RefugieService  instance=null;
         public boolean resultOK;
         public ArrayList<Refugie> tasks;
         public Refugie ti=new Refugie();
              public ArrayList<Camp> tasks1;
              ArrayList<Refugie> listR=new ArrayList();

    public RefugieService() {
        req = new ConnectionRequest();    
    }
           
     public static RefugieService getInstance() {
        if (instance == null) {
            instance = new RefugieService();
        }
        return instance;
    }  
     
     public ArrayList<Refugie> parseRefugie(String jsonText)throws Exception{
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list)
            {
                Refugie t = new Refugie();
                float id = Float.parseFloat(obj.get("id").toString());
        
                float age= Float.parseFloat(obj.get("age").toString());
               
                t.setId((int)id);
                t.setNomCamp(obj.get("nomCamp").toString());
                t.setNom(obj.get("nom").toString());   
                t.setPrenom(obj.get("prenom").toString());
                t.setAge((int)age);
                t.setOrigine(obj.get("origine").toString());
            
                tasks.add(t);
            }
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
     
     
     
      public Refugie parseRefugie1(String jsonText){
       
               Refugie t = new Refugie();
              try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
     
                float id = Float.parseFloat(tasksListJson.get("id").toString());
                float age= Float.parseFloat(tasksListJson.get("age").toString());
         
                t.setId((int)id);
                t.setNom(tasksListJson.get("nom").toString());
                t.setPrenom(tasksListJson.get("prenom").toString());
                t.setAge((int)age);
                t.setOrigine(tasksListJson.get("origine").toString());
                //t.setOrigine(obj.get("ligne").toString());
             
                
                tasks.add(t);
            
        } catch (IOException ex) {
            
        }
        return t;
    }
    
      
       
        public Refugie getRefugie(int id){
     //   String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/refugie/mobileApi/"+id;
          String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/refugie/mobileApi/"+id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                ti = parseRefugie1(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ti;
    }
        
        
         public boolean addRefugie(Refugie t) {
       /* String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/refugie/ajouter_RefugieMobile/"+t.getIdcamp()+"?nom="+t.getNom()+"&prenom="+t.getPrenom()+
                "&age="+t.getAge()+"&origine="+t.getOrigine(); //création de l'URL*/
       String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/refugie/ajouter_RefugieMobile/"+t.getIdcamp()+"?nom="+t.getNom()+"&prenom="+t.getPrenom()+
                "&age="+t.getAge()+"&origine="+t.getOrigine(); //création de l'URL
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
         
         
         
     public ArrayList<Refugie> suppRefugie(int id){
       // String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/refugie/supp_refMob?id="+id;
             String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/refugie/supp_refMob?id="+id;
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
   
  public boolean modifierRefugie(Refugie t) {
       /* String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/refugie/modifMob/"+t.getIdcamp()+"?nom="+t.getNom()+"&prenom="+t.getPrenom()+
                "&age="+t.getAge()+"&origine="+t.getOrigine()+"&id="+t.getId();*/
       String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/refugie/modifMob/"+t.getIdcamp()+"?nom="+t.getNom()+"&prenom="+t.getPrenom()+
                "&age="+t.getAge()+"&origine="+t.getOrigine()+"&id="+t.getId();
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
     
  
  
        public ArrayList<Refugie> getAllRefugie(){
       // String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/refugie/"+"afficherCampMs";
        String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/refugie/"+"afficherCampMs";
       //System.out.println(url);
        req.setUrl(url);
        
        req.setPost(false);
         
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                   // System.out.println(url);
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
        
        
        
        /*****************/
             public ArrayList<Camp> parseCamp(String jsonText)throws Exception{
        try {
            tasks1=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                Camp t = new Camp();
               float id = Float.parseFloat(obj.get("id").toString());
               // float lieu= Float.parseFloat(obj.get("lieu").toString());
                float capacite= Float.parseFloat(obj.get("capacite").toString());
                t.setId((int)id);
                t.setNomCamp(obj.get("nomCamp").toString());
                System.out.println(t.getNomCamp());
               // t.setLieu((int)lieu);
                  t.setCapacite((int)capacite);
                
                
                tasks1.add(t);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return tasks1;}
        public ArrayList<Camp> getAllCamp(){
            //String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/refugie/afficherCampM";
             String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/refugie/afficherCampM";
       //System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    tasks1 = parseCamp(new String(req.getResponseData()));
                   
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks1;
    }
      

           public ArrayList<Refugie> parseStat (String jsonText)throws Exception{
               listR=new ArrayList<>();
              try {
               JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                Refugie t = new Refugie();
               float id = Float.parseFloat(obj.get("1").toString());
               // float lieu= Float.parseFloat(obj.get("lieu").toString());
                t.setNbrR(id);
                t.setNomCamp(obj.get("nomCamp").toString());
                System.out.println(t.getNomCamp());
               // t.setLieu((int)lieu);
               
                
                
                listR.add(t);
            }}
         catch (IOException ex) {
            System.out.println(ex);
        }
        return listR;}  

  public ArrayList<Refugie>  nombreRefugie() {

      /* String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/refugie/modifMob/"+t.getIdcamp()+"?nom="+t.getNom()+"&prenom="+t.getPrenom()+
                "&age="+t.getAge()+"&origine="+t.getOrigine()+"&id="+t.getId();*/
       String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/refugie/nbRef";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                      try {
                    listR = parseStat(new String(req.getResponseData()));
                   
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listR;
    }     
     
}
