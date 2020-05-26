/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Rania
 */
public interface ScanResult {
    /**
     * Called upon a successful scan operation
     * 
     * @param contents the contents of the data
     * @param formatName the format of the scan
     * @param rawBytes the bytes of data
     */
    public void scanCompleted(String contents, String formatName, byte[] rawBytes);
    
    /**
     * Invoked if the user canceled the scan
     */
    public void scanCanceled();
    
    /**
     * Invoked if an error occurred during the scanning process
     * 
     * @param errorCode code
     * @param message descriptive message
     */
    public void scanError(int errorCode, String message);
}