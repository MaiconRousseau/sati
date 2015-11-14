/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class Message {
    
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, 
                "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showWarning(String message, String title) {
        JOptionPane.showMessageDialog(null, message, 
                title, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, 
                "Erro!", JOptionPane.ERROR_MESSAGE);
    }
}
