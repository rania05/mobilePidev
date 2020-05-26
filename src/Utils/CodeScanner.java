/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.codename1.io.Log;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Display;
import java.util.Vector;


/**
 *
 * @author Rania
 */
public class CodeScanner {
     private ScanResult callback;
    private static NativeCodeScanner nativeInstance;
    private static CodeScanner instance;
    
    private CodeScanner() {
        try {
            nativeInstance = (NativeCodeScanner)NativeLookup.create(NativeCodeScanner.class);
        } catch (Throwable ex) {
            Log.p("Failed to load code scanner on this platform.");
        }
    }
    
    public static boolean isSupported() {
        getInstance();
        return nativeInstance != null && nativeInstance.isSupported();
    }
    
    /**
     * Returns the instance of the code scanner, notice that this method is equivalent 
     * to Display.getInstance().getCodeScanner().
     * 
     * @return instance of the code scanner
     */
    public static CodeScanner getInstance() {
        if (instance == null) {
            instance = new CodeScanner();
        }
        return instance;
    }
        
    /**
     * Scans based on the settings in this class and returns the results
     * 
     * @param callback scan results
     */
    public void scanQRCode(ScanResult callback) {
        this.callback = callback;
        nativeInstance.scanQRCode();
    }
        
    /**
     * Scans based on the settings in this class and returns the results
     * 
     * @param callback scan results
     */
    public void scanBarCode(ScanResult callback) {
        this.callback = callback;
        nativeInstance.scanBarCode();
    }
    
    
    /**
     * Called upon a successful scan operation
     * 
     * @param contents the contents of the data
     * @param formatName the format of the scan
     * @param rawBytes the bytes of data
     */
    static void scanCompletedCallback(final String contents, final String formatName, final byte[] rawBytes) {
        if (getInstance().callback != null) {
            Display.getInstance().callSerially(new Runnable() {
                public void run() {
                    getInstance().callback.scanCompleted(contents, formatName, rawBytes);
                    getInstance().callback = null;
                    Display.getInstance().getCurrent().revalidate();
                    Display.getInstance().getCurrent().repaint();
                }
            }); 
        }
    }
    
    /**
     * Invoked if the user canceled the scan
     */
    static void scanCanceledCallback() {
        if (getInstance().callback != null) {
            Display.getInstance().callSerially(new Runnable() {
                public void run() {
                    getInstance().callback.scanCanceled();
                    getInstance().callback = null;
                    Display.getInstance().getCurrent().revalidate();
                    Display.getInstance().getCurrent().repaint();
                }
            });
        }
    }
    
    /**
     * Invoked if an error occurred during the scanning process
     * 
     * @param errorCode code
     * @param message descriptive message
     */
    static void scanErrorCallback(final int errorCode, final String message) {
        if (getInstance().callback != null) {
            Display.getInstance().callSerially(new Runnable() {
                public void run() {
                    getInstance().callback.scanError(errorCode, message);
                    getInstance().callback = null;
                    Display.getInstance().getCurrent().revalidate();
                    Display.getInstance().getCurrent().repaint();
                }
            });
            
        }
    }
}
