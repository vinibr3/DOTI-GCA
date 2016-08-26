/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doti.gca.util;

import doti.gca.exception.GCAException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author vinicius
 */
public final class KeyStoreLoader {
    public static final List<Item> certificadosItems(){
        List<Item> itens = new ArrayList();
        KeyStore ks;
        try {
            ks = KeyStore.getInstance("Windows-MY");
            ks.load(null, null);
            java.util.Enumeration en = ks.aliases();
            while (en.hasMoreElements()) {
		String aliasKey = (String)en.nextElement();
		X509Certificate cert = (X509Certificate) ks.getCertificate(aliasKey);
                
                Item item = new Item(aliasKey, cert.getSubjectDN().getName(), 
                                cert.getNotBefore(), cert.getNotAfter(), 
                                cert.getIssuerDN().getName());
                itens.add(item);
            }
            return itens;
        } catch (KeyStoreException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (NoSuchAlgorithmException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (CertificateException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static final X509Certificate loadCertificado(String alias){
        KeyStore ks;
        try {
            ks = KeyStore.getInstance("Windows-MY");
            ks.load(null, null);
            return (X509Certificate) ks.getCertificate(alias);
        } catch (KeyStoreException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (NoSuchAlgorithmException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (CertificateException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } 
        return null;
    } 
    
    public static final PrivateKey loadPrivateKey(String alias){   
        KeyStore ks;
        try {
            ks = KeyStore.getInstance("Windows-MY");
            ks.load(null, null);                                          
            return (PrivateKey) ks.getKey(alias, null);
        } catch (KeyStoreException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (NoSuchAlgorithmException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (CertificateException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (UnrecoverableKeyException ex) { 
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
