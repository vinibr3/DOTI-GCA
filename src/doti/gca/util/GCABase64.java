/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doti.gca.util;

import doti.gca.exception.GCAException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.swing.JOptionPane;

/**
 *
 * @author vinicius
 */
public final class GCABase64 {
    public static final String encode(String str){
        String auth64 = "";
        try {
            auth64 = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException ex) {
            new GCAException(ex.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }
        return auth64;
    } 
    public static final String encode(byte[] dados){
        return Base64.getEncoder().encodeToString(dados);
    } 
}
