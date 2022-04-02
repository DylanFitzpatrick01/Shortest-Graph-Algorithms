/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants'
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CompetitionDijkstra {

    final int INFINITY = Integer.MAX_VALUE;
    int sA, sB, sC;   // walking speeds
    int E, V;         // no. of edges, vertices
    double[][] graph; // adjacency matrix representing graph

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionDijkstra(String filename, int sA, int sB, int sC) {
        // if a valid file is given
        try {
            if (filename == null)
                return;
            this.sA = sA;
            this.sB = sB;
            this.sC = sC;
            // vertices included in edge
            int edgeFrom, edgeTo;
            // cost of edge variable
            double distance;
            // initialise scanner from file
            Scanner sc = new Scanner(new File(filename));
            // V is first line of file
            V = sc.nextInt();
            // E is second line of file
            E = sc.nextInt();
            // size of graph is vertices x vertices
            graph = new double[V][V];

            // initialise all values in graph to negative value (to account for if weight is 0)
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    graph[i][j] = -7;
                }
            }

            // if there is at least one vertex
            if (V > 0) {
                // while file is not empty
                while (sc.hasNextInt()) {
                    // source vertex first value in line
                    edgeFrom = sc.nextInt();
                    // destination vertex second value in line
                    edgeTo = sc.nextInt();
                    // distance is 3rd value in line
                    distance = sc.nextDouble();
                    graph[edgeFrom][edgeTo] = distance; // insert distance into corresponding array entry
                }
            }
            // if the file cannot be found
        } catch (FileNotFoundException e) {
            System.err.print("No file found. ");
            e.printStackTrace();
        }
    }
        /*
        BufferedReader br = new BufferedReader(new FileReader(filename)); // initialise buffered reader
        V = Integer.parseInt(br.readLine()); // read number of vertices from text file
        E = Integer.parseInt(br.readLine()); // read number of edges from text file
        graph = new double[V][V];            // size of graph is no. of vertices x no. of vertices
        String line;                         // string to be used with br
        String[] splitString;                // string array split by spaces
        int[] intersections = new int[2];    // the 2 connected intersections
        double weight;                       // length between intersections
        while ((line = br.readLine()) != null){
            splitString = line.split("\\s+");              // split line by spaces
            intersections[0] = Integer.parseInt(splitString[0]); // first value is first intersection
            intersections[1] = Integer.parseInt(splitString[1]); // second value is second intersection
            weight = Double.parseDouble(splitString[2]);         // third value is weight
            graph[intersections[0]][intersections[1]] = weight;  // insert value into adjacency matrix
        }
        //System.out.print(Arrays.deepToString(graph).replace("], ", "]\n"));
         */

    /**
     * @param visited:  check if the vertex has been visited
     * @param distance: minimum distance values
     * @return vertex with minimum distance
     */
    int getMinimumVertex(boolean[] visited, double[] distance) {
        // initialise minimum distance as infinity
        double minDistance = INFINITY;
        // initialise vertex as -1
        int vertex = -1;
        // check if there is a shorter distance and update the value
        for (int i = 0; i < V; i++) {
            if (!visited[i] && minDistance > distance[i]) {
                minDistance = distance[i];
                vertex = i;
            }
        }
        return vertex;
    }

    /**
     * @param source: source vertex
     * @return distance array
     */
    double[] dijkstra(int source) {
        boolean[] spt = new boolean[V];
        double[] distance = new double[V];

        //Initialize all the distance to infinity
        for (int i = 0; i < V; i++) {
            distance[i] = INFINITY;
        }

        //start from the vertex 0
        distance[source] = 0;

        //create the shortest path array
        for (int i = 0; i < V; i++) {

            //get the vertex with the minimum distance
            int minimumVertex = getMinimumVertex(spt, distance);

            if (minimumVertex != -1) {
                //include this vertex in the shortest path array
                spt[minimumVertex] = true;

                //iterate through all the adjacent vertices of the shortest path array and update the keys
                for (int adjacentVertex = 0; adjacentVertex < V; adjacentVertex++) {
                    //check the edge between minimumVertex and adjacentVertex
                    if (graph[minimumVertex][adjacentVertex] != -7) {
                        //check if this vertex 'adjacentVertex' already in the shortest paths array and

                        // if adjacent vertex is marked as false and the graph value is not infinity
                        if (!spt[adjacentVertex] && graph[minimumVertex][adjacentVertex] != Integer.MAX_VALUE) {

                            //check if distance is shorter and update
                            double shortestDistance = graph[minimumVertex][adjacentVertex] + distance[minimumVertex];
                            if (shortestDistance < distance[adjacentVertex])
                                distance[adjacentVertex] = shortestDistance;
                        }
                    }
                }
            }
        }
        return distance;
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        // if valid input
        if (V > 0 && sA <= 100 && sA >= 50 && sB <= 100 && sB >= 50 && sC <= 100 && sC >= 50) {
            int slowestSpeed;
            // check if sA is the slowest speed
            if (sA <= sB && sA <= sC)
                slowestSpeed = sA;
            // check if sB is the slowest speed
            else if (sB <= sA && sB <= sC)
                slowestSpeed = sB;
            // sC is the slowest speed
            else slowestSpeed = sC;
            // initialise dijkstra array
            double[] dijkstra;

            // create array list to store values in
            ArrayList<Double> list = new ArrayList<>();

            // add values to dijkstra array and put into list
            for (int i = 0; i < V; i++) {
                dijkstra = dijkstra(i);
                for (int j = 0; j < V; j++) {
                    // if dijkstra value is infinity we return -1
                    if (dijkstra[j] == INFINITY)
                        return -1;
                    else list.add(dijkstra[j]);
                }
            }
            // find the highest value in list
            double topDistance = Collections.max(list);
            // convert speed from metres to kilometres
            double slowestSpeedInKpm = (double) slowestSpeed / 1000;
            // find the time in minutes
            double timeInMinutes = topDistance / slowestSpeedInKpm;
            // round the time up
            return (int) Math.ceil(timeInMinutes);
        } else return -1;
    }
}