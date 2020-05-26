/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import com.codename1.messaging.Message;
//package com.codename1.uikit.cleanmodern;

/**
 *
 * @author Nesrine
 */

//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class SendSMS {
    
     public static final String ACCOUNT_SID = "Jnk3LyeuuuZFrYVt0l6V2UVM7ffifTSk";
    public static final String AUTH_TOKEN = "943f29518aed4b1f2157c146748c9160";

    public static void sendSMSreservation(String nomEvenement)  {
    /*   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new com.twilio.type.PhoneNumber("+21658062600"),//to
                new com.twilio.type.PhoneNumber("+18594076554"),//from 
                "un nouveau don a  fait l inscription ").create();*/
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+21658062600"),
        new PhoneNumber("+21658062600"), 
        "This is the ship that made the Kessel Run in fourteen parsecs?").create();

    System.out.println(message.getSid());
    
    }
 
   
}