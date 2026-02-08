package service;

//Represents a generic Safety Deposit Box
//Tracks allocation status and a unique identifier
public class SafetyDepositBox {
	
    //Flag indicating if the box is currently allotted
    private boolean isAllotted;
    
    //Unique identifier for the box
    private double id;
    
    //Returns whether the box is currently allotted
    public boolean isAllotted() {
        return isAllotted;
    }

    //Sets the allocation status of the box
    public void setAllotted(boolean isAllotted) {
        this.isAllotted = isAllotted;
    }

    //Returns the ID of the box
    public double getId() {
        return id;
    }

    //Sets the ID of the box
    public void setId(double id) {
        this.id = id;
    }
}
