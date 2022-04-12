package com.test.eventlisttest.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class EventItemReader
{
    public static final String TAG = "EventItemReader";
    public static final String DATA_URL = "https://guidebook.com/service/v2/upcomingGuides/";
    
    private EventReaderReadyListener listReadyListener;
    
    
    
    public interface EventReaderReadyListener {
        void onListReady( ArrayList<EventItem> eventList );
    }
    
    
    
    public EventItemReader() {
        listReadyListener = null;
    }
    
    public void setEventReadyListener( EventReaderReadyListener listener ) {
        listReadyListener = listener;
    }
    
    public void fetchEventList( String urlString ) {
        GetEventListThread fetchThread = new GetEventListThread( urlString );
        fetchThread.start();
    }
    
    
    public class GetEventListThread extends Thread {
        private String urlString;
        
        public GetEventListThread( String urlString ) {
            this.urlString = urlString;
        }
        
        @Override
        public void run() {
            ArrayList<EventItem> rList = null;
            EventItem[] eventList = null;
            
            try {
                URL url = new URL( urlString );
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod( "GET" );
                urlConnection.setReadTimeout( 10000 );
                urlConnection.setConnectTimeout( 15000 );
                urlConnection.setDoOutput( true );
                urlConnection.connect();
    
                InputStreamReader streamReader = new InputStreamReader( url.openStream() );
    
                JsonStreamParser jsonStreamParser = new JsonStreamParser( streamReader );
                JsonObject jObject = jsonStreamParser.next().getAsJsonObject();
                // print out to logcat
                Log.i(TAG, "Printing Data:");
                Log.i(TAG, jObject.toString());
                
                JsonArray jArray = jObject.get( "data" ).getAsJsonArray();
                eventList = new Gson().fromJson( jArray.toString(), EventItem[].class );
                
            }
            catch ( Exception e ) {
                Log.e( TAG, "ERROR: " + e.toString() );
            }
            
            if ( eventList != null ) {
                rList = new ArrayList<EventItem>( Arrays.asList( eventList ));
            }
            
            if ( listReadyListener != null ) {
                listReadyListener.onListReady( rList );
            }
        }
    }
}
