import java.util.LinkedList;

public class JobSort {
	static int avgProfit;
	static int avgDeadline;

	static LinkedList<Job> jobsList = new LinkedList<Job>();

	public static void main(String[] args) {
		// initialize our list of jobs
		jobsList.add(new Job('A', 5, 20));
		jobsList.add(new Job('B', 2, 30));
		jobsList.add(new Job('C', 1, 25));
		jobsList.add(new Job('D', 1, 15));
		jobsList.add(new Job('E', 3, 35));

		int jobsListLength = jobsList.size();
		// gather the average priority and deadline
		int totalProfit = 0;
		int totalDeadline = 0;
		for (int i = 0; i < jobsList.size(); i++) {
			totalProfit += jobsList.get(i).getProfit();
			totalDeadline += jobsList.get(i).getDeadline();
		}
		avgProfit = (int) Math.ceil(totalProfit / jobsListLength);
		avgDeadline = (int) Math.ceil(totalDeadline / jobsListLength);

		// start with sorting by profit
		LinkedList<Job> totalSortedList = sortByProfit(jobsList, avgProfit);
		LinkedList<Job> deadlineSortedList = sortByDeadline(jobsList, totalSortedList, avgDeadline);
		totalSortedList.addAll(deadlineSortedList);
		// add the remainder
		totalSortedList.addAll(cleanList(jobsList, totalSortedList));
		System.out.println(totalSortedList.toString());
	}

	static private LinkedList<Job> sortByProfit(LinkedList<Job> unsorted, int avgProfit) {
		LinkedList<Job> sorted = new LinkedList<>();
		int maxProfit = avgProfit;
		for (int i = 0; i < unsorted.size(); i++) {
			Job currentJob = unsorted.get(i);
			int currentProfit = currentJob.getProfit();
			if (currentProfit > maxProfit) { // if you see a max, add to the
												// front
				sorted.addFirst(currentJob);
				maxProfit = currentJob.getProfit();
			} else if (currentProfit == maxProfit) {
				if (currentJob.getDeadline() >= avgDeadline) {
					sorted.addFirst(currentJob);
				} else {
					sorted.add(currentJob);
				}
			}
		}
		return sorted;
	}

	static private LinkedList<Job> sortByDeadline(LinkedList<Job> unsorted, LinkedList<Job> partialSorted,
			int avgDeadline) {
		LinkedList<Job> sorted = new LinkedList<>();
		int maxDeadline = avgDeadline;
		// remove the nodes that are already sorted by priority
		unsorted = cleanList(unsorted, partialSorted);
		// iterate and put lower deadlines first
		for (int i = 0; i < unsorted.size(); i++) {
			Job currentJob = unsorted.get(i);
			int currentDeadline = currentJob.getDeadline();
			if (currentDeadline < maxDeadline) {
				sorted.addFirst(currentJob);
				maxDeadline = currentJob.getDeadline();
			} else if (currentDeadline == maxDeadline) {
				if (currentJob.getProfit() >= avgProfit) {
					sorted.addFirst(currentJob);
			
				} else {
					jobsList.remove(currentJob);
				}
			}
		}
		return sorted;
	}

	static private LinkedList<Job> cleanList(LinkedList<Job> toClean, LinkedList<Job> partialSorted) {
		for (int i = 0; i < partialSorted.size(); i++) {
			Job currentJob = partialSorted.get(i);
			if (toClean.contains(currentJob)) {
				toClean.remove(currentJob);
			}
		}
		return toClean;
	}
}