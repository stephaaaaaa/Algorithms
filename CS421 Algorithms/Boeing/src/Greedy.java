import java.util.LinkedList;

public class Greedy {

	private static LinkedList<Pilot> initList(LinkedList<Pilot> P) {
		Pilot earheart = new Pilot("Earheart", 1, 4);
		P.add(earheart);
		Pilot lindbergh = new Pilot("Lindbergh", 3, 5);
		P.add(lindbergh);
		Pilot doolittle = new Pilot("Doolittle", 4, 6);
		P.add(doolittle);
		Pilot rickenbacker = new Pilot("Rickenbacker", 8, 11);
		P.add(rickenbacker);
		Pilot yeager = new Pilot("Yeager", 10, 12);
		P.add(yeager);
		Pilot richtoven = new Pilot("Richtoven", 2, 14);
		P.add(richtoven);
		Pilot fossett = new Pilot("Fosset", 3, 7);
		P.add(fossett);
		Pilot bleriot = new Pilot("Bleriot", 12, 15);
		P.add(bleriot);

		return P;
	}

	private static void printFinalListOfPilots(LinkedList<Pilot> P) {
		for (int i = 0; i < P.size(); i++) {
			Pilot currentPilot = P.get(i);
			if (currentPilot.willTrain == true)
				System.out.println(P.get(i).toString());
		}
	}

	private static double getAvgTimeUsed(LinkedList<Pilot> P) {
		double avgTimeUsed = 0;
		for (int i = 0; i < P.size(); i++) {
			avgTimeUsed += P.get(i).getTimeNeeded();
		}
		avgTimeUsed = Math.ceil(avgTimeUsed / P.size());
		return avgTimeUsed;
	}

	private static int getMaxTimeUnit(LinkedList<Pilot> P) {
		int max = 0;
		for (int i = 0; i < P.size(); i++) {
			if (P.get(i).getEndTime() > max)
				max = P.get(i).getEndTime();
		}
		return max;
	}

	private static int getEarliestTimeUnit(LinkedList<Pilot> P) {
		int min = getMaxTimeUnit(P);
		for (int i = 0; i < P.size(); i++) {
			if (P.get(i).getEndTime() < min)
				min = P.get(i).getStartTime();
		}
		return min;
	}

	public static void main(String[] args) {
		LinkedList<Pilot> P = new LinkedList<Pilot>();
		initList(P);
		// printListOfPilots(P);
		int avgTimeUsed = (int) getAvgTimeUsed(P);
		System.out.println("Average time each pilot takes is " + (int) avgTimeUsed + " unit(s)");
		int maxTime = getMaxTimeUnit(P);
		int startTime = getEarliestTimeUnit(P);
		System.out.println("Our max schedule is at " + maxTime + " unit(s)");
		System.out.println("Our schedule starts at " + startTime + " unit(s) \n");

		int i = 0;
		// keep track of the time you're at
		while (i < P.size()) {
			// keep a sequential count
			for (int j = 0; j < P.size(); j++) {
				// keep track of the current pilot,
				// the time they take, and when they
				// start
				Pilot currentPilot = P.get(j);
				int timeNeeded = currentPilot.getTimeNeeded();
				int pilotStart = currentPilot.getStartTime();
				// if the pilot's start time is at an available slot
				if (pilotStart >= i) {
					// if the pilot is below or at avg, proceed
					// this prevents occupying large amounts of time
					if (timeNeeded <= avgTimeUsed) {
						// train the pilot
						currentPilot.setTrainingStatus(true); 
						// set the next available time to their end time
						i = currentPilot.getEndTime(); 
					}
				}
			}
		}
		printFinalListOfPilots(P);
	}
}
