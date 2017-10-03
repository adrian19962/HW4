package edu.csumb.scd.cst438;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Hw4 {
	public static void main(String[] args){
		getAPI(args[0],args[1]);
	}


public static String getAPI(String crip,String type){
	String request = "https://api.coinmarketcap.com/v1/ticker/"+ crip +"/?convert=" + type;
    
    System.out.println("Current url: " + request);
    
    try 
    {
        URL url = new URL(request);
        
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        
        http.setRequestMethod("GET");
        http.setUseCaches(false);
        http.setAllowUserInteraction(false);
        http.setConnectTimeout(10000);
        http.setReadTimeout(10000);
        http.connect();
        
        System.out.println("Starting connection");
        
        
            System.out.println("Got a response");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            
            System.out.println("Reading data...");
            
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
                
                System.out.println("Current reading: " + line);
            }
            printInfo(line,crip, type);
            reader.close();
            
            return builder.toString();
        
    } 
    catch (MalformedURLException e) 
    {
        return "MalformedURLExeption";
    }
    catch (ProtocolException e) 
    {
        return "ProtocolException";
    } 
    catch (IOException e) 
    {
        return "IOException";
    }
  
}
public static void printInfo(String line, String crip, String type){
	int cripInfo = line.indexOf(crip);
	int cripend = line.indexOf("coin");
	int typeinfo = line.indexOf(type);
	String printline = line.substring(cripInfo,cripend + 4);
	String printline2 = line.substring(typeinfo, typeinfo + 3);
	System.out.println("Data Line: " + line );
	System.out.println("Clean Data: CrypCurrenty :" + printline + "Converted to: " + printline2 );
}



}

