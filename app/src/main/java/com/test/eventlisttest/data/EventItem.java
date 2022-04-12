package com.test.eventlisttest.data;

/*
{
  "data": [
  {
    "startDate": "<date>",
    "endDate": "<date>",
    "name": "<name>",
    "url": "<url>",
    "venue": {"city": "<city>", "state": "<state>"},
    "icon": "<url to png image>"
    },
    … <more objects>
    ]
  }
 */

public class EventItem
{
    public String startDate;
    public String endDate;
    public String name;
    public String url;
    public Venue venue;
    public String icon;
    
    public class Venue {
        public String city;
        public String state;
        
        public Venue() {
            city = "";
            state = "";
        }
    }
    
    public EventItem() {
        startDate = "";
        endDate = "";
        name = "";
        url = "";
        icon = "";
        venue = new Venue();
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "Name: " + name );
        buffer.append( ", date:" + startDate );
        return buffer.toString();
    }
}
