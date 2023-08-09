package ui;

import model.EventLog;
import model.Event;

import java.awt.*;

public class LogPrinter {

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}
