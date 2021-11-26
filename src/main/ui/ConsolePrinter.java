package ui;

import model.Event;
import model.EventLog;

//code modified from Alarm System
//class that essentially prints out the events
public class ConsolePrinter implements LogPrinter {

    @Override
    //effects: loops through the eventlog and prints out the events
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }
}
