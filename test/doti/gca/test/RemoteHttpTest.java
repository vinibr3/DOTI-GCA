/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doti.gca.test;

import doti.gca.dao.RemoteHttp;
import doti.gca.util.GCABase64;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vinicius
 */
public class RemoteHttpTest {
    
    public RemoteHttpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void inputStreamToString(){
        JSONObject carteirinhas;
        try {
            carteirinhas = RemoteHttp.getCarteirinhas("12345678");
            System.out.println(carteirinhas);
        } catch (IOException ex) {
            Logger.getLogger(RemoteHttpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(true);
    }
    
    @Test
    public void sendCertificados(){
        try {
            JSONObject obj = new JSONObject();
            obj.put("carteirinha_id", 5);
            obj.put("certificado", GCABase64.encode("codificação do certificado"));
            JSONArray array= new JSONArray();
            array.put(obj);
            JSONObject certificados = new JSONObject();
            certificados.put("certificados", array);
            JSONObject result = RemoteHttp.sendCertificados(certificados, "12345678");
            
            if(result.has("type") && result.getString("type").equals("success")){
                JOptionPane.showMessageDialog(null, result.getString("message"), "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(RemoteHttpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
