import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CompetitionTests {

    @Test
    public void testDijkstraConstructor()  {
        new CompetitionDijkstra("tinyEWD.txt", 50, 50, 50);
    }

    @Test
    public void testTimeRequiredForCompetitionDijkstra() {
        CompetitionDijkstra dijkstra =  new CompetitionDijkstra(null, 50, 50, 50);
        assertEquals("Dijkstra with null file name: ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("input-A.txt", 50, 50, 50);
        assertEquals("Dijkstra with infinite distance value: ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("inputK.txt", 50, 50, 50);
        assertEquals("Dijkstra with non-existent file: ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("input-J.txt", 50, 50, 50);
        assertEquals("Dijkstra with empty graph: ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 60, 60);
        assertEquals("Testing time required for competition for tinyEWD.txt with valid speeds (sA lowest): ", 38, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 60, 50, 60);
        assertEquals("Testing time required for competition for tinyEWD.txt with valid speeds (sB lowest): ", 38, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 60, 60, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with valid speeds (sC lowest): ", 38, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 49, 50, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sA (< 50): ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 101, 50, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sA (> 100): ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 49, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sB (< 50): ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 101, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sB (> 100): ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 50, 49);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sC (< 50): ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 50, 101);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sC (> 100): ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 101, 101, 101);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid no valid times (> 100): ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("tinyEWD.txt", 49, 49, 49);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid no valid times (< 50): ", -1, dijkstra.timeRequiredforCompetition());
        dijkstra = new CompetitionDijkstra("1000EWD.txt", 50, 50, 50);
        assertEquals("Testing time required for competition for 1000EWD.txt with valid speeds: ", 28, dijkstra.timeRequiredforCompetition());
    }

    @Test
    public void testFWConstructor()  {
        new CompetitionFloydWarshall("tinyEWD.txt", 50, 50, 50);
    }

    @Test
    public void testTimeRequiredForCompetitionFW() {
        CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall(null, 50, 50, 50);
        assertEquals("Floyd Warshall with null file name: ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("input-A.txt", 50, 50, 50);
        assertEquals("Floyd Warshall with infinite distance value: ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("inputK.txt", 50, 50, 50);
        assertEquals("Floyd Warshall with non-existent file: ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("input-J.txt", 50, 50, 50);
        assertEquals("Floyd Warshall with empty graph: ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 60, 60);
        assertEquals("Testing time required for competition for tinyEWD.txt with valid speeds (sA lowest): ", 38, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 60, 50, 60);
        assertEquals("Testing time required for competition for tinyEWD.txt with valid speeds (sB lowest): ", 38, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 60, 60, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with valid speeds (sC lowest): ", 38, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 49, 50, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sA (< 50): ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 101, 50, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sA (> 100): ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 49, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sB (< 50): ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 101, 50);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sB (> 100): ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 50, 49);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sC (< 50): ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 50, 101);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid sC (> 100): ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 101, 101, 101);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid no valid times (> 100): ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 49, 49, 49);
        assertEquals("Testing time required for competition for tinyEWD.txt with invalid no valid times (< 50): ", -1, floydWarshall.timeRequiredforCompetition());
        floydWarshall = new CompetitionFloydWarshall("1000EWD.txt", 50, 50, 50);
        assertEquals("Testing time required for competition for 1000EWD.txt with valid speeds: ", 28, floydWarshall.timeRequiredforCompetition());
    }
}