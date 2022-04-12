package com.test.eventlisttest.viewadapter;

import android.view.View;
import android.widget.TextView;

import com.test.eventlisttest.R;

import androidx.recyclerview.widget.RecyclerView;


public class EventCellViewHolder extends RecyclerView.ViewHolder
{
    public TextView guideNameTextView;
    public TextView startDateTextView;
    
    public EventCellViewHolder( View view ) {
        super( view );
        
        guideNameTextView = view.findViewById( R.id.guideNameTextView );
        startDateTextView = view.findViewById( R.id.startDateTextView );
    }
}
