# Shortest-Graph-Algorithms
Assignment in which we implement Dijkstra and Floyd Warshall algorithms as well as unit testing

## Problem description:
The assignment addresses a reality TV contest that starts three contestants at three random city
intersections. In order to win, the three contestants need all to meet at any intersection of the city, as
fast as possible.
The contestants may arrive at the intersections at different times, in which case, the first to arrive
has to wait until the others arrive.
From an estimated walking speed for each one of the three contestants, your job is to determine the
minimum time that a live TV broadcastshould lastto covertheirjourney regardless ofthe contestants’
initial positions and the intersection at which they finally meet.
You may assume the following:
* Each contestant walks at a given estimated speed.
* The city is a collection of intersections in which some pairs are connected by one-way streets
that the contestants can use to traverse the city. Two intersections can be connected by two
one-way streets allowing travel in opposite directions of each other. 

## Input:
As input, you are given the following parameters:
* A filename (String) containing the details of the road network, as follows:
  * Line 1 = integer N representing the total number of intersections
  * Line 2 = integer S representing the total number of streets in the city. All streets are 
    one-way, they connect two intersections, and there is at most one street
connecting any pair of intersections in any one direction (at most two streets, one
in each direction)
  * Lines 3 onwards – each line represents a street and consists of 2 integers (2
intersections that the street is connecting) and a double (representing the street
length in kilometers), separated by a space.
* Three integers representing the average walking speed of each of the three contestants, in
meters per minute sA, sB, sC where (50 ≤ sA, sB, sC ≤ 100)
