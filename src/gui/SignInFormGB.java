package gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;


public class SignInFormGB extends Form  {
    public SignInFormGB(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Label gui_Label = new com.codename1.ui.Label();
    protected com.codename1.ui.TextField gui_Login_field = new com.codename1.ui.TextField();
    protected com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    protected com.codename1.ui.TextField gui_pw_field = new com.codename1.ui.TextField();
    protected com.codename1.ui.Button gui_Login_btn = new com.codename1.ui.Button();
    protected com.codename1.ui.Button gui_Clear_btn = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("SignInFormGB");
        setName("SignInFormGB");
        gui_Label.setPreferredSizeStr("14.285714mm inherit");
        gui_Label.setText("Login");
                gui_Label.setInlineStylesTheme(resourceObjectInstance);
        gui_Label.setName("Label");
        gui_Login_field.setText("Login");
                gui_Login_field.setInlineStylesTheme(resourceObjectInstance);
        gui_Login_field.setName("Login_field");
        gui_Label_1.setText("Password");
                gui_Label_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_1.setName("Label_1");
        gui_pw_field.setText("Password");
                gui_pw_field.setInlineStylesTheme(resourceObjectInstance);
        gui_pw_field.setName("pw_field");
        gui_Login_btn.setText("Login");
                gui_Login_btn.setInlineStylesTheme(resourceObjectInstance);
        gui_Login_btn.setName("Login_btn");
        gui_Clear_btn.setText("Annuler");
                gui_Clear_btn.setInlineStylesTheme(resourceObjectInstance);
        gui_Clear_btn.setName("Clear_btn");
        addComponent(gui_Label);
        addComponent(gui_Login_field);
        addComponent(gui_Label_1);
        addComponent(gui_pw_field);
        addComponent(gui_Login_btn);
        addComponent(gui_Clear_btn);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label.getParent().getLayout()).setInsets(gui_Label, "0.7936508mm 3.9682522mm auto 55.13083%").setReferenceComponents(gui_Label, "1 1 1 -1").setReferencePositions(gui_Label, "0.0 1.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Login_field.getParent().getLayout()).setInsets(gui_Login_field, "37.8327% auto auto 22.61236%").setReferenceComponents(gui_Login_field, "-1 -1 -1 -1").setReferencePositions(gui_Login_field, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label_1.getParent().getLayout()).setInsets(gui_Label_1, "auto auto 45.627377% 0.0mm").setReferenceComponents(gui_Label_1, "-1 -1 -1 0 ").setReferencePositions(gui_Label_1, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_pw_field.getParent().getLayout()).setInsets(gui_pw_field, "auto auto 45.627377% 24.7191%").setReferenceComponents(gui_pw_field, "-1 -1 -1 -1").setReferencePositions(gui_pw_field, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Login_btn.getParent().getLayout()).setInsets(gui_Login_btn, "auto auto 31.939163% 31.179777%").setReferenceComponents(gui_Login_btn, "-1 -1 -1 -1").setReferencePositions(gui_Login_btn, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Clear_btn.getParent().getLayout()).setInsets(gui_Clear_btn, "0.0mm auto auto 3.1746063mm").setReferenceComponents(gui_Clear_btn, "4 -1 -1 4 ").setReferencePositions(gui_Clear_btn, "0.0 0.0 0.0 1.0");
    }// </editor-fold>
//-- DON'T EDIT ABOVE THIS LINE!!!
}
