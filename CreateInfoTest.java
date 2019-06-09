//CreateInfoTest.java
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
public class CreateInfoTest{
    @Test
    public void testNormal1()throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/createInfo?user_name=ppg&user_password=99920&user_email=907@qq.com").openConnection();
        http.setRequestMethod("POST");
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.OK_200);

    }
    @Test
    public void testNormal2()throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/createInfo?user_name=jpc&user_password=99920&user_email=907@qq.com").openConnection();
        http.setRequestMethod("POST");
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.OK_200);
    }
    @Test
    public void testEdge1()throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/createInfo?user_name=111&user_password=99920&user_email=907@qq.com").openConnection();
        http.setRequestMethod("POST");
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.CONFLICT_409);
    }
    @Test
    public void testEdge2()throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/createInfo?user_name=111&user_password=99920&user_email=907").openConnection();
        http.setRequestMethod("POST");
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.METHOD_NOT_ALLOWED_405);
    }
    @Test
    public void testEdge3() throws IOException{
        HttpURLConnection http = (HttpURLConnection)new URL("https://crystalis.herokuapp.com/forest/createInfo?user_name=NVC&user_password=99920").openConnection();
        http.setRequestMethod("POST");
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.BAD_REQUEST_400);
    }
}