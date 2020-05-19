import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import CITS2200.Graph;
import CITS2200.Search;

/**
 * @author Harry Carpenter 22723303
 * CITS2200 Data Structures and Algorithms
 */
public class SearchImp implements Search {
	

	private final static LinkedList<int[]> result = new LinkedList<int[]>();

	/**
	 * @param g Graph
	 * @param startVertex int
	 * Returns the parent vertex for each child.
	 * @return int[n] from LinkedList
	 * Assuming Breadth Fist Search (BFS)
	 * */

	@Override
	public int[] getConnectedTree(Graph g, int startVertex) {
		beforeFirst(g,startVertex);
		return result.get(0);
	}

	/**
	 *
	 * @param g Graph
	 * @param startVertex int
	 * @return result linkedList<int[]>
	 *
	 *     Get's distance each vertex is from the starting vertex
	 */
	@Override
	public int[] getDistances(Graph g, int startVertex) {
		beforeFirst(g,startVertex);
		return result.get(1);
		}

	/**
	 *
	 * @param g Graph
	 * @param startVertex int
	 *
	 * This function searches the Graph g from startVetex
	 * and aggregates the distance data in the first element in the results
	 * LinkedList.
	 */

	public void beforeFirst(Graph g, int startVertex) {

		int[][] adjacencyMatrix = g.getEdgeMatrix();
		int vertices = g.getNumberOfVertices();

		int[] colour = new int[vertices];

		colour[startVertex] = 1;

		int[] parent = new int[vertices];	
		Arrays.fill(parent, -1);

		int[] distance = new int[vertices];
		Arrays.fill(distance,-1);
		

		Queue<Integer> flow = new LinkedList<Integer>();
		

		flow.add(startVertex);

		distance[startVertex] = 0;
		
		while(!flow.isEmpty()){
			int focus = flow.poll();
			for(int i = 0; i < vertices; ++i){
				if (adjacencyMatrix[focus][i] > 0 && colour[i] < 1){
					distance[i] = distance[focus] + 1;
					parent[i] = focus;
					colour[i] = 1;
					flow.add(i);
				}
			}
		}
	result.add(0,parent);
	result.add(1,distance);
	}


	/**
	 * *Assuming a depth first search, output the start and
	 * finish times for each vertex.
	 * Given that the graph G has n vertices*
	 *
	 * @param g Graph
	 * @param startVertex int
	 * @return times int[][]
	 */

	@Override
	public int[][] getTimes(Graph g, int startVertex) {
		int vertices = g.getNumberOfVertices();

		int[][] times = new int[vertices][2];

		int[] colour = new int[vertices];

		int timer = 0;

		for(int i = 0; i<vertices; ++i){
			if(colour[i] < 1){

				depthFirstTimer(g, startVertex, colour, times, timer);
			}
		}
		return times;
	}


	/**
	 * A DepthFirstTimer, used in the getTimes function
	 *
	 * @param g Graph
	 * @param currentVertex int
	 * @param colour int[]
	 * @param times int[][]
	 * @param timer int
	 */
	private void depthFirstTimer(Graph g, int currentVertex, int[]colour, int[][]times, int timer){
		int vertices = g.getNumberOfVertices();
		colour[currentVertex] = 1;
		int[][] adjacencyMatrix = g.getEdgeMatrix();
		times[currentVertex][0] = ++timer;
		for(int i = 0; i < vertices; ++i){
			if(adjacencyMatrix[currentVertex][i] > 0 && colour[i] < 1){
				depthFirstTimer(g, i, colour, times, timer);
			}
		}
		colour[currentVertex] = 2;
		times[currentVertex][1] = ++timer;
	}
	
}