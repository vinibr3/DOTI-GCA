/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doti.gca.dao;

import doti.gca.exception.GCAException;
import doti.gca.util.GCABase64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author vinicius
 */
public class RemoteHttp {
    private static final String SERVER = "http://localhost:3000/";
    private static final String CARTEIRINHAS_URL = 
            "api/carteirinhas?status_versao_impressa=Aprovada";
    private static final String CERTIFICADOS_URL = "api/certificados/create";
    private static final String USUARIO = "doti";
    
    private static String inputStreamToJSON(InputStream inputStream){
        if(inputStream != null){
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            try {
                while ((length = inputStream.read(buffer))!= -1){
                    result.write(buffer, 0, length);
                }
                return result.toString("UTF-8");
            } catch (IOException ex) {
                new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
    
    private static final String buildAuthPropertie(String token){
        return "Basic "+GCABase64.encode(USUARIO+":"+token);
    }
    
    public static JSONObject getCarteirinhas(String token) throws IOException{
        URL url;
        HttpURLConnection request = null;
        InputStream input=null;
        try {
            url = new URL(SERVER+CARTEIRINHAS_URL);
            request = (HttpURLConnection) url.openConnection();
            
            String auth = buildAuthPropertie(token);
            request.setRequestProperty("Authorization", auth);
            request.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            request.setConnectTimeout(5000);
            request.connect();
            
            input = request.getInputStream(); 
            JSONObject result = new JSONObject(inputStreamToJSON(input));
            
            if(result.has("type") && result.getString("type").equals("erro")){
                new GCAException(result.getString("errors"),"INFO", JOptionPane.INFORMATION_MESSAGE);
            }
            return result;  
        } catch (MalformedURLException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (JSONException ex){
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        }finally{
            input.close();
            request.disconnect();
        }
        return null;
    }
    
    public static JSONObject sendCertificados(JSONObject certificados, String token) throws IOException{
        URL url;
        HttpURLConnection request=null;
        InputStream input=null;
        OutputStream output=null;
        try{
            url = new URL(SERVER+CERTIFICADOS_URL);
            request = (HttpURLConnection) url.openConnection();
            
            String auth = buildAuthPropertie(token);
            request.setRequestProperty("Authorization", auth);
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            request.setConnectTimeout(5000);
            request.setDoOutput(true);
            request.setDoInput(true);
            request.connect();
            
            output = request.getOutputStream();
            output.write(certificados.toString().getBytes("UTF-8"));
            
            input = request.getInputStream(); 
            JSONObject result = new JSONObject(inputStreamToJSON(input));
            return result;
        }catch(MalformedURLException ex){
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        }catch(IOException ex){
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        }catch(JSONException ex){
            new GCAException(ex.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        }finally{
            output.close();
            input.close();
            request.disconnect();
        }
        return null;
    }
}