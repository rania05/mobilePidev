/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import entities.Utilisateur;
import java.io.IOException;
import services.ServiceUser;
 
/**
 *
 * @author actar
 */
public class SignInForm extends BaseForm {

	public SignInForm(Resources res) {
            
		super(new BorderLayout());

		if (!Display.getInstance().isTablet()) {
			BorderLayout bl = (BorderLayout) getLayout();
			bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
			bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
		}
		//getTitleArea().setUIID("welcome");
	
       
         setUIID("SignIn");

		add(BorderLayout.NORTH, new Label(res.getImage("logoeco.png"), "LogoLabel"));

		TextField username = new TextField("", "Username", 20, TextField.ANY);
		TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
		username.setSingleLineTextArea(true);
		password.setSingleLineTextArea(true);
		Button signIn = new Button("Sign In");
		
		//signUp.addActionListener(e -> new SignUpForm(res).show());
		//signUp.setUIID("Link");
		//Label doneHaveAnAccount = new Label("Don't have an account?");

		Container content = BoxLayout.encloseY(
				new FloatingHint(username),
				createLineSeparator(),
				new FloatingHint(password),
				createLineSeparator(),
				signIn,
				FlowLayout.encloseCenter()
		);
		content.setScrollableY(true);
	
		//content.getAllStyles().setMarginTop(500);
		add(BorderLayout.CENTER,content);
                  
		signIn.requestFocus();
		//signIn.addActionListener(e -> new NewsfeedForm(res).show());
		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            System.out.println("cococococo :"+password.getText());
				if (username.getText() != "" || password.getText() != "") {
					ServiceUser ser = new ServiceUser();
                                    System.out.println("cococococo :"+password.getText());
                                    
				 	Utilisateur u = ser.CheckLoginData(username.getText(), password.getText());
					if (u == null) {
						Dialog.show("Wrong Credentials", "Please verify your username and password.", "Ok", "");
						username.setText("");
						password.setText("");
					} else {
						Utilisateur.current_user = u;
						Database db;
						try {
							db = Database.openOrCreate("Heart.db");
							db.execute("delete from appstates");
							db.execute("insert into appstates(loggedin,loggeduserid) values (1," + u.getId() + ");");
							  System.out.println("Passage vers l'autre écran");
                                                          AccueilForm h = new AccueilForm(res);
                                                            h.show();
                                                          
                                                          
                                                          
                                                          
						} catch (IOException ex) {
						}
					}
				}
			}
		});
	}

}

