package gui;

import org.LiveGraph.dataFile.write.DataStreamWriter;
import org.LiveGraph.dataFile.write.DataStreamWriterFactory;


/**
 * This class is used for generating a demo data file for demonstration and
 * testing purposes.
 * It creates a data file and fills it with data. This happens in bursts
 * of 1 to 10 datasets at a time.
 * Between the bursts the execution is paused for 0 to 10 milliseconds.
 * This way a data generating simulation is emulated.
 * The program stops after 100000 datasets have been written.
 * 
 * <p><strong>LiveGraph</strong> (http://www.live-graph.org).</p>
 * <p>Copyright (c) 2007 by G. Paperin.</p>
 * <p>File: LiveGraphDemo.java</p> 
 * <p><u>Licence</u>: See http://www.live-graph.org/licence.html.</p>
 * 
 * @author Greg Paperin
 * @version 1.0.1
 */
public class LiveGraphDemo {

public static final String DEMO_DIR = System.getProperty("user.dir");

public static final int MIN_SLEEP = 0;
public static final int MAX_SLEEP = 1000;
public static final int MIN_BURST = 1;
public static final int MAX_BURST = 10;
public static final int MAX_DATASETS = 100000;

public void exec() {
  
  // Print a welcome message:
  System.out.println("Welcome to the LiveLog demo.");
  
  // Setup a data writer object:
  DataStreamWriter out = DataStreamWriterFactory.createDataWriter(DEMO_DIR,
                                "LiveGraphDemo");
  
  // Set a values separator:
  out.setSeparator(";");
  
  // Add a file description line:
  out.writeFileInfo("LiveGraph demo file.");
  
  // Set-up the data series:
  out.addDataSeries("Time");
  out.addDataSeries("Dataset number");
  out.addDataSeries("Burst number");
  out.addDataSeries("Random value");
  
  // Loop until enough datasets a written:
  int datasetNumber = 0;
  int burstNumber = 0;
  long startMillis = System.currentTimeMillis();
  while (MAX_DATASETS > datasetNumber) {
  
    // Status message:
    System.out.println("Datasets written so far: " + datasetNumber + ". "
             + "Now writing burst " + burstNumber + "...");
    
    // Write a few datasets to the file:
    int burstSize = (int) (MIN_BURST + (Math.random()
                        * (double) (MAX_BURST - MIN_BURST)));
    for (int b = 0; b < burstSize && MAX_DATASETS > datasetNumber; b++) {
      
      // Set-up the data values:
      out.setDataValue(System.currentTimeMillis() - startMillis);
      out.setDataValue(datasetNumber);
      out.setDataValue(burstNumber);
      //out.setDataValue(gui.PitchDetectorExample.pitch);
      
      // Write dataset to disk:
      out.writeDataSet();
      
      // Check for IOErrors:      
      if (out.hadIOException()) {
        out.getIOException().printStackTrace();
        out.resetIOException();
      }
      
      datasetNumber++;
    }
    burstNumber++;
    
    
    // Pause:
    Thread.yield();
    long sleep = (long) (MIN_SLEEP + (Math.random()
                          * (double) (MAX_SLEEP - MIN_SLEEP)));
    try { Thread.sleep(sleep); } catch (InterruptedException e) {}
    Thread.yield();
  }    
  
  // Finish:
  out.close();
  System.out.println("Demo finished. Cheers.");
}

public static void main(String[] unusedArgs) {
  (new LiveGraphDemo()).exec();  
}

} // public class LiveGraphDemo