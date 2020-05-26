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
import entities.Besoins;
import entities.Camp;
import entities.Categorie;
import entities.Cord;
import entities.affectation;
import entities.don;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rania
 */
public class BesoinsService {
       public ConnectionRequest req;
         public static BesoinsService  instance=null;
         public boolean resultOK;
         public ArrayList<Camp> tasks;
         public ArrayList<affectation> tasks3;
             public ArrayList<Besoins> task;
         public Camp ti=new Camp();
          public ArrayList<Cord> tasks1;
           public ArrayList<Categorie> tasks2;
               public ArrayList<don> tasks4;
     int qnt = -1;
    public BesoinsService() {
        req = new ConnectionRequest();    
    }
           
     public static BesoinsService getInstance() {
        if (instance == null) {
            instance = new BesoinsService();
        }
        return instance;
    }  
     
     public ArrayList<Besoins> parseBesoins(String jsonText)throws Exception{
        try {
            task=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                Besoins t = new Besoins();
                
                float id = Float.parseFloat(obj.get("id").toString());
                Map<String, Object> id_c = (Map<String, Object>) obj.get("id_c");
                   Map<String, Object> nom_bs = (Map<String, Object>) obj.get("nomBs");
                float quantite= Float.parseFloat(obj.get("quantite").toString());
                 Map<String, Object> lieu = (Map<String, Object>) id_c.get("lieu");
                System.out.println((int)Float.parseFloat(id_c.get("id").toString()) + " lieu = "+lieu.get("lieu").toString()+"long = "+(double)Float.parseFloat(lieu.get("longitude").toString()));
                t.setId((int)id);
                Cord co = new Cord((int)Float.parseFloat(id_c.get("id").toString()),lieu.get("lieu").toString(),(double)Float.parseFloat(lieu.get("longitude").toString()),(double)Float.parseFloat(lieu.get("latitude").toString()));
                System.out.println(co.toString()); 
                Camp c = new Camp((int)Float.parseFloat(id_c.get("id").toString()),co,id_c.get("nomCamp").toString(),(int)Float.parseFloat(id_c.get("capacite").toString()));
                System.out.println(c.toString()); 
               Categorie ca = new Categorie((int)Float.parseFloat(nom_bs.get("id").toString()),nom_bs.get("nom").toString());
                t.setId_c(c);
                 t.setNom_bs(ca);
                t.setQuantite((int)quantite);
                
                System.out.println("Camp = "+t.toString());
                task.add(t);
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return task;
    }
 
        
        
         public boolean addBesoins(Besoins t) {
        //String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/ajoutCamp?nomCamp="+t.getNomCamp()+"&lieu="+t.getLieu().getId()+ "&capacite="+t.getCapacite(); //création de l'URL
          String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/ajoutBesoins?id_c="+t.getId_c().getId()+"&nom="+t.getNom_bs().getId()+"&quantite="+t.getQuantite();
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
         
         
         
     public ArrayList<Besoins> suppBesoins(int id){
      //  String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/supprimerCamp?id="+id;
         String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/supprimerBesoins?id="+id;
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                    task = parseBesoins(new String(req.getResponseData()));
                } catch (Exception ex) {
                
                }
                    //System.out.println("chnia mochkol "+tasks);
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return task;
    }
   
  public boolean modifierBesoins(Besoins t) {
         String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/modifierBesoins?id_c="+t.getId_c().getId()+"&nom="+t.getNom_bs().getId()+"&quantite="+t.getQuantite()+"&id="+t.getId();
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
    public boolean modifierDon(int qnt, int id) {
         String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/volentaire/modif?quantite="+qnt+"&id="+id;
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
     
  
  
        public ArrayList<Besoins> getAllBesoins(){
      //  String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/afficherCamp";
          String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/afficherBesoins";
       //System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    task = parseBesoins(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return task;
    }
      
 
      public ArrayList<Categorie> parseCat(String jsonText)throws Exception{
        try {
            tasks2=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                Categorie t = new Categorie();
               float id = Float.parseFloat(obj.get("id").toString());
               
               
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                System.out.println(t.toString());
               // t.setLieu((int)lieu);
                 
                
                tasks2.add(t);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return tasks2;}
       public ArrayList<Categorie> getAllCategorie(){
           //String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/lieu";
               String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/cat";
       //System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    tasks2 = parseCat(new String(req.getResponseData()));
                   
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks2;
    }
                public boolean addAffectation(affectation t) {
                    System.out.println("qte= "+t.getQuantite());
                            System.out.println("besoin = "+t.getId_bs().getId());
                                    System.out.println("don = "+t.getId_don().getId());
 String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/ajoutAff?qnt="+t.getQuantite()+
                  "&id_bs="+t.getId_bs().getId()+"&id_don="+t.getId_don().getId();
         
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
                public ArrayList<affectation> getAllAff(){
           //String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/lieu";
               String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/afficherAff";
       //System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    tasks3 = parseAff(new String(req.getResponseData()));
                   
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks3;
    }
                   public ArrayList<don> parseDon(String jsonText)throws Exception{
        try {
            tasks4=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                don t = new don();
                System.out.println(obj);
                t.setNom(obj.get("nom").toString());
                t.setId((int)Float.parseFloat(obj.get("id").toString()));
                
                System.out.println(t.toString());
               // t.setLieu((int)lieu);
                 
                
                tasks4.add(t);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return tasks4;}
                      public ArrayList<don> getNomDon(String nom){
           //String url ="http://localhost/Maddox_heart2hold/web/app_dev.php/gcamp/lieu";
               String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/nomD/"+nom;
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    tasks4 = parseDon(new String(req.getResponseData()));
                   
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks4;
    }
                       public int parseDon2(String jsonText)throws Exception{
                           int qnt = -1;
        try {
           
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                don t = new don();
              
               qnt= (int)Float.parseFloat(obj.get("quantite").toString());
                System.out.println(t.toString());
               // t.setLieu((int)lieu);
                 
                
                
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return qnt;}
                      public int getQntDon(String nom,String objet){
           
               String url ="http://localhost/Maddox_heart2hold%20(1)/Maddox_heart2hold/web/app_dev.php/gcamp/qnt/"+nom+"/"+objet;
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                try {
                    
                    qnt = parseDon2(new String(req.getResponseData()));
                   
                    req.removeResponseListener(this);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return qnt;
    }
                   public ArrayList<affectation> parseAff(String jsonText)throws Exception{
        try {
            tasks3=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                 affectation t = new affectation();
                float id = Float.parseFloat(obj.get("id").toString());
                Map<String, Object> id_bc = (Map<String, Object>) obj.get("idBs");
                System.out.println("id_bc = "+id_bc);
                 Map<String, Object> cat = (Map<String, Object>) id_bc.get("nomBs");
                 System.out.println("cat = "+cat);
                 Map<String, Object> id_c = (Map<String, Object>) id_bc.get("idC");
                 Map<String, Object> lieu = (Map<String, Object>) id_c.get("lieu");
                 System.out.println("id_c = "+id_c);
                Map<String, Object> id_don = (Map<String, Object>) obj.get("idDon");
                Map<String, Object> date = (Map<String, Object>) obj.get("date");
                float quantite= Float.parseFloat(obj.get("qnt").toString());
                Cord co = new Cord((int)Float.parseFloat(id_bc.get("id").toString()),lieu.get("lieu").toString(),(double)Float.parseFloat(lieu.get("longitude").toString()),(double)Float.parseFloat(lieu.get("latitude").toString()));
                System.out.println("coAff = "+co.toString());
                Camp c = new Camp((int)Float.parseFloat(id_c.get("id").toString()),co,id_c.get("nomCamp").toString(),(int)Float.parseFloat(id_c.get("capacite").toString()));
                System.out.println("camp = "+c.toString());
                
                Categorie ca = new Categorie((int)Float.parseFloat(cat.get("id").toString()),cat.get("nom").toString());
                System.out.println("id = "+(int)Float.parseFloat(id_bc.get("id").toString()));
                System.out.println((int) Float.parseFloat(id_bc.get("quantite").toString()));
                Besoins b = new Besoins((int)Float.parseFloat(id_bc.get("id").toString()),c, ca, (int) Float.parseFloat(id_bc.get("quantite").toString()));
                don d = new don((int)Float.parseFloat(id_don.get("id").toString()),id_don.get("nom").toString(), id_don.get("prenom").toString(), id_don.get("email").toString(), id_don.get("objet").toString(), id_don.get("description").toString());
                t.setId((int)id);
                t.setQuantite((int)quantite);
                t.setId_bs(b);
                t.setId_don(d);
                 float dateF = Float.parseFloat(date.get("timestamp").toString());
                t.setDate((long)dateF);
                System.out.println(t.toString());
              //  System.out.println((int)Float.parseFloat(id_c.get("id").toString()) + " lieu = "+lieu.get("lieu").toString()+"long = "+(double)Float.parseFloat(lieu.get("longitude").toString()));
        /*        t.setId((int)id);
               
                System.out.println(co.toString()); 
                
                System.out.println(c.toString()); 
               
                t.setId_c(c);
                 t.setNom_bs(ca);
                t.setQuantite((int)quantite);
                */
                System.out.println("Camp = "+t.toString());
                tasks3.add(t);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return tasks3;}
}
