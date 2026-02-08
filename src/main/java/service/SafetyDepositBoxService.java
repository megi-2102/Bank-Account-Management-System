package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Singleton service class that manages a pool of SafetyDepositBox objects
//Supports allocation and release of boxes with thread-safe access
public class SafetyDepositBoxService {
		
	//Singleton instance of the service
	private static SafetyDepositBoxService safetyDepositBoxService;
	
	//List to hold all the safety deposit boxes
	private List<SafetyDepositBox> safetyDepositBoxes = new ArrayList<>();
	
	//Maximum number of boxes allowed in the pool
	private static int numberOfSafetyDepositBox = 2;
	
	//Flag to track if any thread is currently waiting for a box
	private boolean threadWaiting = false;
	
	//Logger instance for logging operations
	private static final Logger logger = LogManager.getLogger(SafetyDepositBoxService.class);

    //Private constructor to prevent external instantiation
	private SafetyDepositBoxService() {
		this.safetyDepositBoxes = new ArrayList<>();
        logger.info("SafetyDepositBoxService initialised with max boxes = {}",numberOfSafetyDepositBox);
	}
	
    //Returns the singleton instance of SafetyDepositBoxService
    //Thread-safe using synchronized method
	public static synchronized SafetyDepositBoxService getInstance()
	{
		if(safetyDepositBoxService == null)
			safetyDepositBoxService = new SafetyDepositBoxService();
		return safetyDepositBoxService;
	}
	
    //Sets the maximum number of safety deposit boxes allowed
    //Synchronized to avoid race conditions
	public static synchronized void setNumberOfSafetyDepositBox(int numberOfSafetyDepositBox) {
		SafetyDepositBoxService.numberOfSafetyDepositBox = numberOfSafetyDepositBox;
		logger.info("Max number of SafetyDepositBoxes set to {}", numberOfSafetyDepositBox);
	}
	
	//Returns the configured maximum number of boxes
	public static synchronized int getNumberOfSafetyDepositBox() {
		return numberOfSafetyDepositBox;
	}
	
    //Allocates a safety deposit box to a requesting thread
    //Reuses a released box if available
    //Creates a new box if pool size allows
    //Waits if no boxes are available
    //Thread-safe
	public synchronized SafetyDepositBox allocateSafetyDepositBox() {
	    logger.info("Thread {} requesting SafetyDepositBox", Thread.currentThread().getName());

	    //Loop until a box is allocated
	    while (true) {
	        //Try to reuse released box
	        Optional<SafetyDepositBox> releasedBox = getReleasedSafetyDepositBox();
	        if (releasedBox.isPresent()) {
	            SafetyDepositBox box = releasedBox.get();
	            box.setAllotted(true);
	            logger.info("Thread {} acquired SafetyDepositBox with id {}", Thread.currentThread().getName(), box.getId());
	            return box;
	        }

            //Create new box if max number not reached
	        if (safetyDepositBoxes.size() < numberOfSafetyDepositBox) {
	            SafetyDepositBox box = new SafetyDepositBox();
	            box.setAllotted(true);
	            safetyDepositBoxes.add(box);
	            logger.info("Created new SafetyDepositBox. Pool size now {}", safetyDepositBoxes.size());
	            return box;
	        }

            //Wait if no box is available
	        try {
	            threadWaiting = true;
	            logger.warn("No SafetyDepositBox available. Thread {} waiting", Thread.currentThread().getName());
	            wait(); //releases lock and waits for notify
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            logger.error("Thread interrupted while waiting", e);
	        } finally {
	            threadWaiting = false;
	        }
	        //Loop will retry allocation after waking up
	    }
	}

    //Releases a previously allocated SafetyDepositBox
    //Notifies waiting threads that a box is available
    public synchronized void releaseSafetyDepositBox(SafetyDepositBox box) {
        if (box == null) {
            logger.error("Attempted to release null SafetyDepositBox");
            throw new IllegalArgumentException("SafetyDepositBox cannot be null");
        }
        safetyDepositBoxes.remove(box); //Remove from pool
        logger.info("SafetyDepositBox released and removed from pool. Pool size now {}",safetyDepositBoxes.size());
        notifyAll(); //notify waiting thread
    }

    //Returns the first unallocated (released) box, if any
    public synchronized Optional<SafetyDepositBox> getReleasedSafetyDepositBox() {
        for (SafetyDepositBox box : safetyDepositBoxes) {
            if (!box.isAllotted()) {
                return Optional.of(box);
            }
        }
        return Optional.empty();
    }
	
    //Counts the number of boxes currently available for allocation
	public synchronized int getNumberOfAvailableSafetyDepositBoxes()
	{
		int count = 0;
		for (SafetyDepositBox box : safetyDepositBoxes) {
			if (!box.isAllotted()) 
				count++;
		}
		return count;
	}
	
	//Returns whether a thread is currently waiting for a box
	public synchronized boolean isThreadWaiting() {
		return threadWaiting;
		}

	//Returns a copy of the current list of all boxes in the pool
	public synchronized List<SafetyDepositBox> getSafetyDepositBoxes() {
		return new ArrayList<>(safetyDepositBoxes);
	}
}
