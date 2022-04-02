/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

import java.io.*;
import java.util.*;

public class CompetitionFloydWarshall {

    final int INFINITY = Integer.MAX_VALUE;
    int sA, sB, sC; // walking speeds
    int E, V;       // no. of edges, vertices
    double[][] graph; // adjacency matrix representing graph

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {
        // if a valid file is given
        try {
            if (filename == null)
                return;
            this.sA = sA;
            this.sB = sB;
            this.sC = sC;
            // vertices included in edge
            int edgeFrom, edgeTo;
            // cost of edge
            double distance;
            // initialise scanner with input file
            Scanner sc = new Scanner(new File(filename));
            // V is first line of file
            V = sc.nextInt();
            // E is second line of file
            E = sc.nextInt();
            // size of graph is V x V
            graph = new double[V][V];
            // initialise graph weights as infinity
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    graph[i][j] = INFINITY;
                }
            }
            // if there is a graph
            if (V > 0) {
                // while file is not empty
                while (sc.hasNextInt()) {
                    // source vertex first value in line
                    edgeFrom = sc.nextInt();
                    // destination vertex second value in line
                    edgeTo = sc.nextInt();
                    // distance is 3rd value in line
                    distance = sc.nextDouble();
                    // insert distance into corresponding array entry
                    graph[edgeFrom][edgeTo] = distance;
                }
            }
            // if the file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    /**
     * @param graph: adjacency matrix representing graph
     * @return distance matrix
     */
    double[][] floydWarshall(double[][] graph) {
        // initialise adjacency matrix
        double[][] dist = new double[V][V];
        int i, j, k;

        // input weight values into adjacency matrix
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++) {
                if (i == j)
                    dist[i][j] = 0;
                else dist[i][j] = graph[i][j];
            }

        // find the shortest path  for all i,j
        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        // check if valid input
        if (V > 0 && sA <= 100 && sA >= 50 && sB <= 100 && sB >= 50 && sC <= 100 && sC >= 50) {
            int slowestSpeed;
            // check if the slowest speed is sA
            if (sA <= sB && sA <= sC)
                slowestSpeed = sA;
            // check if the slowest speed is sB
            else if (sB <= sA && sB <= sC)
                slowestSpeed = sB;
            // slowest speed is sC
            else slowestSpeed = sC;
            double[][] floydWarshall;
            ArrayList<Double> list = new ArrayList<>();
            // insert paths into matrix
            floydWarshall = floydWarshall(graph);
            // add the shortest paths to list
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // if path is infinity it is invalid
                    if (floydWarshall[i][j] == INFINITY)
                        return -1;
                    else list.add(floydWarshall[i][j]);
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