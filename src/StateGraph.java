import java.util.List;
import java.util.Vector;

public class StateGraph {

    static String epsilon = "ε";

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

    //
    // Methods
    //

    /**
     * create a new state.
     * @return an integer that represents a new state.
     */
    int getState() {
        num_of_state += 1;
        return num_of_state;
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
     * convert graph into dot file, which can be viewd with graphviz.
     * @return String, dot source file.
     */
    public String toDOT() {
        StringBuilder buf = new StringBuilder();
        buf.append("digraph G {\n");
        buf.append("  ranksep=.25;\n");
        buf.append("  edge [arrowsize=.5]\n");
        buf.append("  node [shape=circle, fontname=\"ArialNarrow\",\n");
        buf.append("        fontsize=12, fixedsize=true, height=.45];\n");
        buf.append("  ");
        for(int i=1; i<=num_of_state; ++i) { // print all nodes first
            buf.append(i);
            buf.append("; ");
        }
        buf.append("\n");
        for (Edge edge : edges) {
                buf.append("  ");
                buf.append(edge.from);
                buf.append(" -> ");
                buf.append(edge.to);
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

