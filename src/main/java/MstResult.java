import java.util.List;

class MstResult {
    List<int[]> mstEdges;
    int totalCost;
    int operationsCount;
    double executionTime;

    public MstResult(List<int[]> edges, int totalCost, int operationsCount, double executionTime) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTime = executionTime;
    }
}
