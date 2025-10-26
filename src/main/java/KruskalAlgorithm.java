import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class KruskalAlgorithm {
    public static MstResult kruskal(List<String> vertices, List<int[]> edges) {
        int n = vertices.size();
        int operations = 0;
        long startTime = System.nanoTime();

        edges.sort(Comparator.comparingInt(edge -> edge[2]));
        operations += edges.size() * (int) Math.log(edges.size());
        DSU dsu = new DSU(n);
        List<int[]> mstEdges = new ArrayList<>();
        int totalCost = 0;
        for (int[] edge : edges) {
            operations += 3;
            if (dsu.union(edge[0], edge[1])) {
                mstEdges.add(edge);
                totalCost += edge[2];
                if (mstEdges.size() == n - 1) {break;}
            }
        }
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1000000.0;

        return new MstResult(mstEdges, totalCost, operations, executionTime);
    }
}
