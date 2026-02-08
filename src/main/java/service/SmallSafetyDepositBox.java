package service;

//Represents a smaller variant of SafetyDepositBox
//Adds a capacity field specific to small boxes
public class SmallSafetyDepositBox extends SafetyDepositBox{
	
	//Capacity of the small box
    private double capacity;

    //Returns the capacity of the box
    public double getCapacity() {
        return capacity;
    }

    //Sets the capacity of the box
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
