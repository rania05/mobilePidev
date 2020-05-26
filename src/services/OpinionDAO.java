/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;

/**
 *
 * @author Nesrine
 */
public class OpinionDAO {
         public OpinionDAO(){
    
    Message m = new Message("Body of message");
    
 //   Message m = new Message("Body of message");
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"nesrine.abdelkarim@esprit.tn"}, "Opinion", m);
        
        
    }
}
