//GetInfoTest.java
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdk.jfr.Timestamp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import static java.util.stream.Collectors.*;
import java.io.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class GetInfoTest{
    public static String getText(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        //add headers to the connection, or check the status if desired..
        
        // handle error response code it occurs
        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }
    
        BufferedReader in = new BufferedReader(
            new InputStreamReader(
                inputStream));
    
        StringBuilder response = new StringBuilder();
        String currentLine;
    
        while ((currentLine = in.readLine()) != null) 
            response.append(currentLine);
    
        in.close();
    
        return response.toString();
    }
    @Before
    public void beforeTest(){

    }
    @After
    public void afterTest(){

    }
    @Test
    public void testCodeNormal1() throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/getInfo?user_name=111&user_password=111").openConnection();
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.OK_200);
    }
    @Test
    public void testCodeNormal2() throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/getInfo?user_name=678&user_password=678").openConnection();
        http.connect();
        System.out.println(http.getContent());
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.OK_200);
    }
    @Test
    public void testCodeEdge() throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/getInfo?user_name=111&user_password=112").openConnection();
        http.connect();
        BufferedReader br;
        if (200 <= http.getResponseCode() && http.getResponseCode() <= 299) {
            br = new BufferedReader(new InputStreamReader(http.getInputStream()));
        }
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.NOT_FOUND_404);
    }
    @Test
    public void testBodyNormal1() throws IOException, ParseException{
        String url = "https://crystalis.herokuapp.com/forest/getInfo?user_name=111&user_password=111";
        String body = getText(url);
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        json = (JSONObject) parser.parse(body);
        long money = (Long)json.get("money");
        assertEquals(money, 100);
        long questCount = (Long)json.get("quest_count");
        assertEquals(questCount, 0);
    }
    @Test
    public void testBodyNormal2() throws IOException, ParseException{
        String url = "https://crystalis.herokuapp.com/forest/getInfo?user_name=678&user_password=678";
        String body = getText(url);
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        json = (JSONObject) parser.parse(body);
        long money = (Long)json.get("money");
        assertEquals(money, 0);
        long questCount = (Long)json.get("quest_count");
        assertEquals(questCount, 0);
    }

}