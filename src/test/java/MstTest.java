import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;

public class MstTest {

    @Test
    public void testSmallGraph() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");

        List<int[]> edges = Arrays.asList(
                new int[]{0, 1, 1},
                new int[]{0, 2, 4},
                new int[]{1, 2, 2},
                new int[]{1, 3, 5},
                new int[]{2, 3, 3}
        );
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        MstResult prim = PrimAlgorithm.prim(nodes, graph);
        MstResult kruskal = KruskalAlgorithm.kruskal(nodes, edges);

        assertEquals("price should be same", prim.totalCost, kruskal.totalCost);
        assertEquals("MST must have V-1 edges", nodes.size() - 1, prim.mstEdges.size());
        assertEquals("MST must have V-1 edges", nodes.size() - 1, kruskal.mstEdges.size());
        assertEquals("cost of MST should be 6", 6, prim.totalCost);
    }

    @Test
    public void testThreeNodes() {
        List<String> nodes = Arrays.asList("X", "Y", "Z");
        List<int[]> edges = Arrays.asList(
                new int[]{0, 1, 2},
                new int[]{1, 2, 3},
                new int[]{0, 2, 4}
        );

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        MstResult prim = PrimAlgorithm.prim(nodes, graph);
        MstResult kruskal = KruskalAlgorithm.kruskal(nodes, edges);

        assertEquals(5, prim.totalCost);
        assertEquals(5, kruskal.totalCost);
        assertEquals(2, prim.mstEdges.size());
    }
}