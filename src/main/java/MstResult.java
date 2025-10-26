import java.util.*;

class MstResult {
    List<int[]> mstEdges;
    int totalCost;
    int operationsCount;
    double executionTime;

    public MstResult(List<int[]> edges, int totalCost, int operationsCount, double executionTime) {
        this.mstEdges = mstEdges != null ? mstEdges : new ArrayList<>();
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTime = executionTime;
    }
}
