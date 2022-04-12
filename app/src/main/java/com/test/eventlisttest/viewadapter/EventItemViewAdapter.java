package com.test.eventlisttest.viewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.test.eventlisttest.R;
import com.test.eventlisttest.data.EventItem;

import java.util.ArrayList;

public class EventItemViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<EventItem> dataSource;
    
    public EventItemViewAdapter( Context context, ArrayList<EventItem> data ) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from( context );
        this.dataSource = data;
    }
    
    @Override
    public int getItemCount() {
        return dataSource.size();
    }
    
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position ) {
        EventCellViewHolder eventHolder = (EventCellViewHolder)holder;
        EventItem event = dataSource.get( position );
        
        eventHolder.guideNameTextView.setText( event.name );
        eventHolder.startDateTextView.setText( event.startDate );
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View view = layoutInflater.inflate( R.layout.event_cell, parent, false );
        EventCellViewHolder viewHolder = new EventCellViewHolder( view );
        return viewHolder;
    }
}
