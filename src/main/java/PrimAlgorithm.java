import java.util.*;

class PrimAlgorithm {
    public static MstResult prim(List<String> vertices, List<List<int[]>> adjList) {
        int n = vertices.size();
        if (n == 0) {return new MstResult(new ArrayList<>(), 0, 0, 0);}
        int operations = 0;
        long startTime = System.nanoTime();
        boolean[] visited = new boolean[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        List<int[]> mstEdges = new ArrayList<>();
        int totalCost = 0;
        pq.offer(new int[]{-1, 0, 0});

        while (!pq.isEmpty() && mstEdges.size() < n - 1) {
            operations++;
            int[] edge = pq.poll();
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            if (visited[to]) {continue;}
            visited[to] = true;
            if (from != -1) {
                mstEdges.add(new int[]{from, to, weight});
                totalCost += weight;
            }

            for (int[] neighbor : adjList.get(to)) {
                operations++;
                int nextNode = neighbor[0];
                int nextWeight = neighbor[1];
                if (!visited[nextNode]) {
                    pq.offer(new int[]{to, nextNode, nextWeight});
                }
            }
        }
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1000000.0;
        if (mstEdges == null) {
            mstEdges = new ArrayList<>();
        }
        return new MstResult(mstEdges, totalCost, operations, executionTime);
    }
}