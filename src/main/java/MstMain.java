import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class MstMain {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> data = mapper.readValue(new java.io.File("input.json"), Map.class);
            List<Map<String, Object>> graphs = (List<Map<String, Object>>) data.get("graphs");
            List<Map<String, Object>> results = new ArrayList<>();

            for (Map<String, Object> graph : graphs) {
                int id =(Integer)graph.get("id");
                List<String> nodes = (List<String>) graph.get("nodes");
                List<Map<String, Object>> edgesData = (List<Map<String, Object>>) graph.get("edges");

                Map<String, Integer> nodeIndex = new HashMap<>();
                for (int i = 0; i < nodes.size(); i++) {
                    nodeIndex.put(nodes.get(i), i);
                }

                //kruskal
                List<int[]> edges = new ArrayList<>();
                //prim
                List<List<int[]>> graphList = new ArrayList<>();

                for (int i = 0; i < nodes.size(); i++) {
                    graphList.add(new ArrayList<>());
                }

                for (Map<String, Object> edge : edgesData) {
                    String from = (String)edge.get("from");
                    String to = (String)edge.get("to");
                    int weight = (Integer)edge.get("weight");
                    int fromIndex = nodeIndex.get(from);
                    int toIndex = nodeIndex.get(to);
                    edges.add(new int[]{fromIndex, toIndex, weight});

                    graphList.get(fromIndex).add(new int[]{toIndex, weight});
                    graphList.get(toIndex).add(new int[]{fromIndex, weight});
                }

                MstResult prim = PrimAlgorithm.prim(nodes, graphList);
                MstResult kruskal = KruskalAlgorithm.kruskal(nodes, edges);

                Map<String, Object> result = new LinkedHashMap<>();
                result.put("graph_id", id);
                result.put("input_stats", Map.of("vertices", nodes.size(), "edges", edges.size()));
                result.put("prim", formatResult(prim,nodes));
                result.put("kruskal", formatResult(kruskal, nodes));
                results.add(result);
            }

            Map<String, Object> output = Map.of("results", results);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new java.io.File("output.json"), output);
            System.out.println("results in output.json");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Map<String, Object> formatResult(MstResult result, List<String> nodes) {
        List<Map<String, Object>> edges = new ArrayList<>();
        for (int[] edge : result.mstEdges) {
            edges.add(Map.of("from", nodes.get(edge[0]), "to", nodes.get(edge[1]), "weight", edge[2]));
        }
        return Map.of(
                "mst_edges", edges,
                "total_cost", result.totalCost,
                "operations_count", result.operationsCount,
                "execution_time_ms", Math.round(result.executionTime * 100.0) / 100.0
        );
    }
}
