/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doti.gca.exception;

import javax.swing.JOptionPane;

/**
 *
 * @author vinicius
 */
public class GCAException extends Exception{
    public GCAException(String msg, String titulo, int msgType){
        JOptionPane.showMessageDialog(null, msg, titulo, msgType);
    }
}
