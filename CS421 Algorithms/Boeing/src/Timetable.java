
public class Timetable {

	int timeUnitCapacity;
	int index = 0;
	
	public Timetable(int timeUnitCapacity){
		this.timeUnitCapacity = timeUnitCapacity;
	}
	
	public int getEnd(){
		return this.timeUnitCapacity;
	}
}
