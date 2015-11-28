package Addons;

import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas
 */
public class Aviso {
    
    public static void showInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Advertência", JOptionPane.WARNING_MESSAGE);
    }
}
