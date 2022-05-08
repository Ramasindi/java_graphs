package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Graph<T extends Comparable<T>> {

    private List<Vertex<T>> allVertices = new ArrayList<Vertex<T>>();
    private List<Edge<T>> allEdges = new ArrayList<Edge<T>>();

    public enum TYPE {
        DIRECTED, UNDIRECTED
    }

    /** Defaulted to undirected */
    private TYPE type = TYPE.UNDIRECTED;

    public Graph() {
    }

    public Graph(TYPE type) {
        this.type = type;
    }

    /**
     * Creates a Graph from the vertices and edges. This defaults to an undirected
     * Graph
     * 
     * NOTE: Duplicate vertices and edges ARE allowed.
     * NOTE: Copies the vertex and edge objects but does NOT store the Collection
     * parameters itself.
     * 
     * @param vertices Collection of vertices
     * @param edges    Collection of edges
     */
    public Graph(Collection<Vertex<T>> vertices, Collection<Edge<T>> edges) {
        this(TYPE.UNDIRECTED, vertices, edges);
    }

    /**
     * Creates a Graph from the vertices and edges.
     * 
     * NOTE: Duplicate vertices and edges ARE allowed.
     * NOTE: Copies the vertex and edge objects but does NOT store the Collection
     * parameters itself.
     * 
     * @param vertices Collection of vertices
     * @param edges    Collection of edges
     */
    public Graph(TYPE type, Collection<Vertex<T>> vertices, Collection<Edge<T>> edges) {
        this(type);

        this.allVertices.addAll(vertices);
        this.allEdges.addAll(edges);

        for (Edge<T> e : edges) {
            final Vertex<T> from = e.from;
            final Vertex<T> to = e.to;

            if (!this.allVertices.contains(from) || !this.allVertices.contains(to))
                continue;

            from.addEdge(e);
            if (this.type == TYPE.UNDIRECTED) {
                Edge<T> reciprical = new Edge<T>(e.getCost(), to, from);
                to.addEdge(reciprical);
                this.allEdges.add(reciprical);
            }
        }
    }

    /** Deep copies **/
    public Graph(Graph<T> g) {
        type = g.getType();

        // Copy the vertices which also copies the edges
        for (Vertex<T> v : g.getVertices())
            this.allVertices.add(new Vertex<T>(v));

        for (Vertex<T> v : this.getVertices()) {
            for (Edge<T> e : v.getEdges()) {
                this.allEdges.add(e);
            }
        }
    }

    public List<Vertex<T>> getVertices() {
        return allVertices;
    }

    private TYPE getType() {
        return type;
    }

    public List<Edge<T>> getEdges() {
        return allEdges;
    }

    @Override
    public int hashCode() {
        int code = this.type.hashCode() + this.allVertices.size() + this.allEdges.size();
        for (Vertex<T> v : allVertices)
            code *= v.hashCode();
        for (Edge<T> e : allEdges)
            code *= e.hashCode();
        return 31 * code;
    }

    @Override
    public boolean equals(Object g1) {
        if (!(g1 instanceof Graph))
            return false;

        final Graph<T> g = (Graph<T>) g1;

        final boolean typeEquals = this.type == g.type;
        if (!typeEquals)
            return false;

        final boolean verticesSizeEquals = this.allVertices.size() == g.allVertices.size();
        if (!verticesSizeEquals)
            return false;

        final boolean edgesSizeEquals = this.allEdges.size() == g.allEdges.size();
        if (!edgesSizeEquals)
            return false;

        // Vertices can contain duplicates and appear in different order but both arrays
        // should contain the same elements
        final Object[] ov1 = this.allVertices.toArray();
        Arrays.sort(ov1);
        final Object[] ov2 = g.allVertices.toArray();
        Arrays.sort(ov2);
        for (int i = 0; i < ov1.length; i++) {
            final Vertex<T> v1 = (Vertex<T>) ov1[i];
            final Vertex<T> v2 = (Vertex<T>) ov2[i];
            if (!v1.equals(v2))
                return false;
        }

        // Edges can contain duplicates and appear in different order but both arrays
        // should contain the same elements
        final Object[] oe1 = this.allEdges.toArray();
        Arrays.sort(oe1);
        final Object[] oe2 = g.allEdges.toArray();
        Arrays.sort(oe2);
        for (int i = 0; i < oe1.length; i++) {
            final Edge<T> e1 = (Edge<T>) oe1[i];
            final Edge<T> e2 = (Edge<T>) oe2[i];
            if (!e1.equals(e2))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Vertex<T> v : allVertices)
            builder.append(v.toString());
        return builder.toString();
    }

}
