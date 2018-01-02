
public class Pilot {

	String name;
	int startTime;
	int endTime;
	boolean willTrain;
	
	public Pilot(String name, int startTime, int endTime){
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.willTrain = false;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getStartTime(){
		return this.startTime;
	}
	
	public int getEndTime(){
		return this.endTime;
	}
	
	public int getTimeNeeded(){
		return this.endTime-this.startTime;
	}
	
	public boolean getTrainingStatus(){
		return this.willTrain;
	}
	
	public boolean setTrainingStatus(boolean newStatus){
		return this.willTrain = newStatus;
	}
	
	public String toString(){
		String returnStr = "";
		if(this.willTrain == false){
			returnStr = "Pilot:\t" + this.name + "\nStart Time:\t" + this.getStartTime()
			+ "\nEnd Time:\t" + this.endTime + "\nTime Needed\t" + getTimeNeeded() + "\n";
		}else{
			returnStr = "Pilot:\t" + this.name + "\nStart Time:\t" + this.getStartTime()
			+ "\nEnd Time:\t" + this.endTime + "\nTime Needed\t" + getTimeNeeded() + 
			"\nTraining:\t Yes" +"\n";
		}
		return returnStr;
	}
}
