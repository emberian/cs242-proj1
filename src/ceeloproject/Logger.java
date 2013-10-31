/**
 * Logger.java is the class that handles text output to a file
 * 
 * @author The Brickettes (Corey Richardson and Adam Kimball)
 * CS242
 * Project 1
 * 10/30/13
 */
package ceeloproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A helper class for logging.
 * 
 * @author Corey Richardson
 */
public class Logger {

    private static FileWriter logfile;

    /**
     * Create a logger using the default log file.
     */
    public Logger() {
        this("ceelo.log");
    }
    
    /**
     * Create a logger with a given filename.
     * @param s filename
     */
    public Logger(String s) {
        if (logfile == null) {
            try {
                logfile = new FileWriter(new File(s));
            } catch (IOException e) {
                System.err.println("Exception while opening log file:");
                System.err.println(e.getMessage());
            }
        }
    }
    /**
     * Log a string.
     * @param s string to log
     */
    public void log(String s) {
        try {
            logfile.write(s);
            logfile.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Log a string using the default log file.
     * @param s string to log
     */
    public static void dlog(String s) {
        Logger l = new Logger();
        l.log(s);
    }
}
