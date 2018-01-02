class AllPairShortestPath {
	final static int INF = 99999, numVertices = 11;

	static String[] outposts = {
			"Qo'nos",
			"Gall",
			"C.P.",
			"Ork",
			"Fhlost",
			"Al. IV",
			"Kobol",
			"OP8",
			"Thermia",
			"LV-426",
			"Lond."
		  };
	
	public static void main(String[] args) {
		double originalGraph[][] = {
								{0, 3.3, INF, 1.3, INF, INF, INF, INF, INF, INF, INF},
								{3.3, 0, INF, 5.3, INF, INF, INF, INF, INF, INF, INF},
								{INF, INF, 0, 7.5, INF, 3.4, INF, INF, INF, INF, INF},
								{1.3, 5.3, 7.5, 0, 2.4, INF, INF, INF, INF, INF, INF},
								{INF, INF, INF, 2.4, 0, 4.7, 1.4, INF, INF, INF, INF},
								{INF, INF, 3.4, INF, 4.7, 0, 5.2, INF, INF, INF, INF},
								{INF, INF, INF, INF, 1.4, 5.2, 0, 7.1, INF, 4.6, INF},
								{INF, INF, INF, INF, INF, INF, 7.1, 0, 5.0, INF, INF},
								{INF, INF, INF, INF, INF, INF, INF, 5.0, 0, INF, 2.3},
								{INF, INF, INF, INF, INF, INF, 4.6, INF, INF, 0, 1.2},
								{INF, INF, INF, INF, INF, INF, INF, 5.0, INF, 1.2, 0}
								}
								;
		
		shortestPath(originalGraph);
	}
	
	static void shortestPath(double graph[][]) {
		double dist[][] = new double[numVertices][numVertices];
		int row, column, k;

		// create an identical separate graph
		for (row = 0; row < numVertices; row++)
			for (column = 0; column < numVertices; column++)
				dist[row][column] = graph[row][column];

		for (k = 0; k < numVertices; k++) {
			// Pick all vertices as source one by one
			for (row = 0; row < numVertices; row++) {
				// Pick all vertices as destination for the
				// above picked source
				for (column = 0; column < numVertices; column++) {
					// If vertex k is on the shortest path from
					// i to j, then update the value of distance[i][j]
					if (dist[row][k] + dist[k][column] < dist[row][column])
						dist[row][column] = dist[row][k] + dist[k][column];
				}
			}
		}
		printSolution(dist);
	}

	static void printSolution(double dist[][]) {
		System.out.println("Following matrix shows the shortest distances between every pair of vertices\n\n");
		for (int i = 0; i < numVertices; ++i)
			System.out.print("\t\t" + outposts[i]);
		System.out.println("");
		for (int i = 0; i < numVertices; ++i) {
			System.out.print(outposts[i] + "\t\t");
			for (int j = 0; j < numVertices; ++j) {
				if (dist[i][j] == INF || dist[i][j] > 99990)
					System.out.print("INF");
				else
					System.out.printf("%.2f", dist[i][j]);
					System.out.print("\t\t");
			}
			System.out.println();
		}
	}
}