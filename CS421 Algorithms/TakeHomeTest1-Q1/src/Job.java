
public class Job {

	char jobID;
	int deadline;
	int profit;
	
	Job(char jobID, int deadline, int profit){
		this.jobID = jobID;
		this.deadline = deadline;
		this.profit = profit;
	}
	
	public char getJobID(){
		return jobID;
	}
	
	public int getDeadline(){
		return deadline;
	}
	
	public int getProfit(){
		return profit;
	}
	
	public String toString(){
		String toString = "\n(" + jobID + ") Deadline: " + deadline + " Profit " + profit;
		return toString;
	}
	
}
