package service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Unit tests for SafetyDepositBoxService class
//Tests thread-safe allocation and release of safety deposit boxes
class SafetyDepositBoxServiceTest {
	
    //Reference to the singleton service
    private SafetyDepositBoxService service;

	@BeforeEach
	void setUp() {
        service = SafetyDepositBoxService.getInstance();
        SafetyDepositBoxService.setNumberOfSafetyDepositBox(2);
        service.getSafetyDepositBoxes().clear();
	}
	
	 @Test
	 void shouldSetAndGetId() {
		 SafetyDepositBox box = new SafetyDepositBox();
		 box.setId(101);
		 assertEquals(101, box.getId());
		 }
	 
	 @Test
	 void shouldReturnConfiguredMaximumNumberOfSafetyDepositBoxes() {
		 SafetyDepositBoxService.setNumberOfSafetyDepositBox(2);
		 int result = SafetyDepositBoxService.getNumberOfSafetyDepositBox();
		 assertEquals(2, result);
	 }
	 
	 @Test
	 void shouldReuseReleasedSafetyDepositBox() {
		 SafetyDepositBoxService.setNumberOfSafetyDepositBox(2);
	     SafetyDepositBoxService service = SafetyDepositBoxService.getInstance();
	     SafetyDepositBox box1 = service.allocateSafetyDepositBox();
	     box1.setAllotted(false); //Simulate released but still in pool
	     SafetyDepositBox reusedBox = service.allocateSafetyDepositBox();
	     assertSame(box1, reusedBox);
	 }

	 @Test
	 void shouldSetAndGetCapacity() {
		 SmallSafetyDepositBox box = new SmallSafetyDepositBox();
		 box.setCapacity(50.0);
		 assertEquals(50.0, box.getCapacity());
	    }
	 
    @Test
    void shouldNotWaitWhenTwoThreadsRequestSafetyDepositBoxes() throws InterruptedException {
        //Thread 1: allocates a box, sleeps 5 seconds, then releases it    
    		Thread t1 = new Thread(() -> {
            SafetyDepositBox box = service.allocateSafetyDepositBox();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            	Thread.currentThread().interrupt(); //Preserve interrupt status
            } finally {
                service.releaseSafetyDepositBox(box);
            }
        });

    		//Thread 2: same behavior as Thread 1
        Thread t2 = new Thread(() -> {
            SafetyDepositBox box = service.allocateSafetyDepositBox();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            	Thread.currentThread().interrupt(); //Preserve interrupt status
            } finally {
                service.releaseSafetyDepositBox(box);
            }
        });
        //Start both threads
        t1.start();
        t2.start();

        //Allow time for allocation
        Thread.sleep(1000);
        
        //Wait for threads to finish to avoid test interference
        t1.join();
        t2.join();
        
        //Assert that no thread is waiting, because 2 boxes satisfy 2 requests
        assertFalse(service.isThreadWaiting(),
                "No thread should be waiting when only two boxes are requested");
    }
    
    @Test
    void shouldWaitWhenThreeThreadsRequestSafetyDepositBoxes() throws InterruptedException {
        //Thread 1: allocates a box, sleeps, then releases
        Thread t1 = new Thread(() -> {
            SafetyDepositBox box = service.allocateSafetyDepositBox();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            	Thread.currentThread().interrupt(); //Preserve interrupt status
            } finally {
                service.releaseSafetyDepositBox(box);
            }
        });
        
        //Thread 2: same as Thread 1
        Thread t2 = new Thread(() -> {
            SafetyDepositBox box = service.allocateSafetyDepositBox();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            	Thread.currentThread().interrupt(); //Preserve interrupt status
            } finally {
                service.releaseSafetyDepositBox(box);
            }
        });
        
        //Thread 3: will attempt to allocate a box but must wait because only 2 boxes exist
        Thread t3 = new Thread(() -> {
            SafetyDepositBox box = service.allocateSafetyDepositBox();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            	Thread.currentThread().interrupt(); //Preserve interrupt status
            } finally {
                service.releaseSafetyDepositBox(box);
            }
        });

        //Start all threads
        t1.start();
        t2.start();
        t3.start();
        
        //Allow threads time to attempt allocation
        Thread.sleep(1500);
        
        //Assert that at least one thread is waiting for a box
        assertTrue(service.isThreadWaiting(),
                "A thread should be waiting when three boxes are requested");
        
        //Wait for threads to finish
        t1.join();
        t2.join();
        t3.join();
    } 
    
    @Test
    void shouldThrowExceptionWhenReleasingNullSafetyDepositBox() {
    	SafetyDepositBoxService service = SafetyDepositBoxService.getInstance();
    	IllegalArgumentException ex = assertThrows(
    			IllegalArgumentException.class,
    			() -> service.releaseSafetyDepositBox(null)
    			);
    	assertEquals("SafetyDepositBox cannot be null", ex.getMessage());
    }
    
    @Test
    void shouldCountAvailableSafetyDepositBoxesCorrectly() {
    	SafetyDepositBoxService.setNumberOfSafetyDepositBox(2);
    	SafetyDepositBoxService service = SafetyDepositBoxService.getInstance();
    	SafetyDepositBox box1 = service.allocateSafetyDepositBox();
    	SafetyDepositBox box2 = service.allocateSafetyDepositBox();

    	// Make one box available
    	box1.setAllotted(false);
    	int available = service.getNumberOfAvailableSafetyDepositBoxes();
    	assertEquals(1, available);
    	
    	// Cleanup
    	service.releaseSafetyDepositBox(box2);
    }
}
