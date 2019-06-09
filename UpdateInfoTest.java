//UpdateInfoTest.java
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

public class UpdateInfoTest{
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
    
    @Test
    public void testCodeNormal1() throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/updateInfo?user_name=111&money=20&game_level=10&quest_count=2&HP=100&health=4&is_quest_complete=false&attack=10&enemy_killed=15&num_come_to_store=0&in_quest=false").openConnection();
        http.setRequestMethod("POST");
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.OK_200);
    }
    @Test
    public void testCodeNormal2() throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/updateInfo?money=20&game_level=10&quest_count=2&HP=100&health=4&is_quest_complete=false&attack=10&enemy_killed=15&num_come_to_store=0&in_quest=false").openConnection();
        http.setRequestMethod("POST");
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.BAD_REQUEST_400);
    }
    @Test
    public void testCodeEdge() throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/updateInfo?user_name=1&money=20&game_level=10&quest_count=2&HP=100&health=4&is_quest_complete=false&attack=10&enemy_killed=15&num_come_to_store=0&in_quest=false").openConnection();
        http.setRequestMethod("POST");
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.NOT_FOUND_404);
    }
}