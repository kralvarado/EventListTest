package com.test.eventlisttest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.test.eventlisttest.data.EventItem;
import com.test.eventlisttest.data.EventItemReader;
import com.test.eventlisttest.data.EventViewModel;
import com.test.eventlisttest.viewadapter.EventItemViewAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements EventItemReader.EventReaderReadyListener
{
    private static final String TAG = "Event List Activity";
    
    private TextView buildTypeTextView;
    private RecyclerView eventRecyclerView;
    private EventItemViewAdapter eventAdapter;
    
    private EventItemReader eventReader;
    private EventViewModel eventViewModel;
    
    
    
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        
        buildTypeTextView = findViewById( R.id.buildTypeTextView );
        buildTypeTextView.setText( getString( R.string.app_build_type ));
        eventRecyclerView = findViewById( R.id.eventRecyclerView );
    
        ViewModelProvider vmp = new ViewModelProvider( this );
        eventViewModel = vmp.get( EventViewModel.class );
        
        if ( eventViewModel.hasList() ) {
            if ( eventViewModel.getEventList() != null && eventViewModel.getCount() > 0 ) {
                initListView();
            }
        }
        else {
            eventReader = new EventItemReader();
            eventReader.setEventReadyListener( this );
            eventReader.fetchEventList( EventItemReader.DATA_URL );
        }
    }
    
    private void initListView() {
        eventAdapter = new EventItemViewAdapter( this, eventViewModel.getEventList() );
        eventRecyclerView.setLayoutManager( new LinearLayoutManager( this ));
        eventRecyclerView.setAdapter( eventAdapter );
    }
    
    @Override
    public void onListReady( final ArrayList<EventItem> eventList ) {
        Log.i( TAG, "Recieved List" );
        eventViewModel.setEventList( eventList );
        buildTypeTextView.post( new Runnable() {
            @Override
            public void run() {
                if ( eventList != null && eventList.size() > 0 ) {
                    initListView();
                }
            }
        });
    }
}
