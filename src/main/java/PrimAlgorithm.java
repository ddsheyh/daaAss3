import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class PrimAlgorithm {
    public static MstResult prim(List<String> vertices, List<List<int[]>> adjList) {
        int n = vertices.size();
        int operations = 0;
        long startTime = System.nanoTime();
        boolean[] visited = new boolean[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        List<int[]> mstEdges = new ArrayList<>();
        int totalCost = 0;

        pq.offer(new int[]{-1, 0, 0});
        while (!pq.isEmpty() && mstEdges.size() < n) {
            operations++;
            int[] edge = pq.poll();
            int u = edge[1];

            if (visited[u]) {continue;}
            visited[u] = true;
            if (edge[0] != -1) {
                mstEdges.add(new int[]{edge[0], u, edge[2]});
                totalCost += edge[2];
            }

            for (int[] neighbor : adjList.get(u)) {
                operations++;
                int v = neighbor[0];
                int w = neighbor[1];
                if (!visited[v]) {
                    pq.offer(new int[]{u, v,w});
                }
            }
        }
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1000000.0;
        return new MstResult(mstEdges, totalCost, operations, executionTime);
    }
}
