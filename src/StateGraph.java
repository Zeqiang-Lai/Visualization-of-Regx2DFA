import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

public class StateGraph {

    static String epsilon = "&epsilon;";

    class Edge {
        int from, to;
        String label;

        Edge(int from, int to, String label) {
            this.from = from;
            this.to = to;
            this.label = label;
        }
    }

    //
    // Properties
    //

    public int getNum_of_state() {
        return num_of_state;
    }

    private int num_of_state = 0;

    public Vector<Edge> getEdges() {
        return edges;
    }

    private Vector<Edge> edges = new Vector<>();

    private int rank[];

    //
    // Methods
    //

    /**
     * create a new state.
     * @return an integer that represents a new state.
     */
    int getState() {
        int tmp = num_of_state;
        num_of_state += 1;
        return tmp;
    }

    /**
     * add a new edge into graph.
     * @param source integer, source state.
     * @param target integer, target state.
     * @param label String, edge label.
     */
    void addEdge(int source, int target, String label) {
        edges.add(new Edge(source, target, label));
    }

    /**
     * compute the rank for each node, using bfs.
     */
    private void computeRank() {
        // TODO: Optimize
        // Initialize rank array
        rank = new int[num_of_state];

        // Construct adjacent matrix.
        int A[][] = new int[num_of_state][num_of_state];
        for(Edge edge : edges) {
            A[edge.from][edge.to] = 1;
        }
        // Find the source.
        int source = -1;
        int tmp_count;
        for(int i=0; i<num_of_state; ++i) {
            tmp_count = 0;
            for (int j = 0; j < num_of_state; ++j)
                tmp_count += A[j][i];
            if(tmp_count == 0) {
                source = i;
                break;
            }
        }
        assert source == -1;

        // BFS
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean visit[] = new boolean[num_of_state];

        queue.add(source);
        rank[source] = 0;

        int head;
        while(!queue.isEmpty()) {
            head = queue.poll();
            visit[head] = true;
            for(int i=0; i<num_of_state; ++i){
                if(!visit[i] && A[head][i] == 1) {
                    queue.add(i);
                    rank[i] = rank[head] + 1;
                }
            }
        }
        for(int i=0; i<num_of_state; ++i){
            System.out.println(""+i+": "+rank[i]);
        }
    }

    /**
     * convert graph into dot file, which can be viewd with graphviz.
     * @return String, dot source file.
     */
    public String toDOT() {
        if(rank == null || rank.length != num_of_state)
            computeRank();

        StringBuilder buf = new StringBuilder();
        buf.append("digraph G {\n");
        buf.append("  ranksep=.25;\n");
        buf.append("  rankdir=\"LR\";\n");
        buf.append("  edge [arrowsize=.5]\n");
        buf.append("  node [shape=circle, fontname=\"ArialNarrow\",\n");
        buf.append("        fontsize=12, fixedsize=true, height=.45];\n");
        buf.append("  ");
        for(int i=0; i<num_of_state; ++i) { // print all nodes first
            buf.append(i);
            buf.append("; ");
        }
        buf.append("\n");

        //TODO: generate edge using breadth first search
        for (Edge edge : edges) {
                buf.append("  ");
                buf.append(edge.from);
                buf.append(" -> ");
                buf.append(edge.to);
                buf.append(" [ label = \"");
                buf.append(edge.label);
                buf.append("\";");
                if(rank[edge.from] > rank[edge.to])
                    buf.append("constraint=false;");
                buf.append("] ");
                buf.append(";\n");
        }

        buf.append("}\n");
        return buf.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

