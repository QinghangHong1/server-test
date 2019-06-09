//Test
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
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
public class Test{
    public static void main(String[] args) throws IOException{
        String body = getText("https://crystalis.herokuapp.com/forest/getInfo?user_name=678&user_password=678");
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try{
            json = (JSONObject) parser.parse(body);
            long money = (Long)json.get("money");
            System.out.println("money: " + money);

        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }
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
    
}