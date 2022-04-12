package com.test.eventlisttest.data;


import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import android.util.Log;

public class EventViewModel extends ViewModel
{
    private ArrayList<EventItem> eventList;
    
    public EventViewModel() {
        eventList = null;
    }
    
    public boolean hasList() {
        return eventList != null;
    }
    
    public int getCount() {
        int rValue = 0;
        if ( eventList != null ) {
            rValue = eventList.size();
        }
        
        return rValue;
    }
    
    public void setEventList( ArrayList<EventItem> list ) {
        eventList = list;
    }
    
    public ArrayList<EventItem> getEventList() {
        return eventList;
    }
}
