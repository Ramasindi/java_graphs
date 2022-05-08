package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {
    
    private T value = null;
    private int weight = 0;
    private List<Edge<T>> edges = new ArrayList<Edge<T>>();

    public Vertex(T value) {
        this.value = value;
    }

    public Vertex(T value, int weight) {
        this(value);
        this.weight = weight;
    }

    /** Deep copies the edges along with the value and weight **/
    public Vertex(Vertex<T> vertex) {
        this(vertex.value, vertex.weight);

        this.edges.addAll(vertex.edges);
    }

    public T getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void addEdge(Edge<T> e) {
        edges.add(e);
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public Edge<T> getEdge(Vertex<T> v) {
        for (Edge<T> e : edges) {
            if (e.to.equals(v))
                return e;
        }
        return null;
    }

    public boolean pathTo(Vertex<T> v) {
        for (Edge<T> e : edges) {
            if (e.to.equals(v))
                return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int code = this.value.hashCode() + this.weight + this.edges.size();
        return 31 * code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object v1) {
        if (!(v1 instanceof Vertex))
            return false;

        final Vertex<T> v = (Vertex<T>) v1;

        final boolean weightEquals = this.weight == v.weight;
        if (!weightEquals)
            return false;

        final boolean edgesSizeEquals = this.edges.size() == v.edges.size();
        if (!edgesSizeEquals)
            return false;

        final boolean valuesEquals = this.value.equals(v.value);
        if (!valuesEquals)
            return false;

        final Iterator<Edge<T>> iter1 = this.edges.iterator();
        final Iterator<Edge<T>> iter2 = v.edges.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            // Only checking the cost
            final Edge<T> e1 = iter1.next();
            final Edge<T> e2 = iter2.next();
            if (e1.getCost() != e2.getCost())
                return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Vertex<T> v) {
        final int valueComp = this.value.compareTo(v.value);
        if (valueComp != 0)
            return valueComp;

        if (this.weight < v.weight)
            return -1;
        if (this.weight > v.weight)
            return 1;

        if (this.edges.size() < v.edges.size())
            return -1;
        if (this.edges.size() > v.edges.size())
            return 1;

        final Iterator<Edge<T>> iter1 = this.edges.iterator();
        final Iterator<Edge<T>> iter2 = v.edges.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            // Only checking the cost
            final Edge<T> e1 = iter1.next();
            final Edge<T> e2 = iter2.next();
            if (e1.getCost() < e2.getCost())
                return -1;
            if (e1.getCost() > e2.getCost())
                return 1;
        }

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Value=").append(value).append(" weight=").append(weight).append("\n");
        for (Edge<T> e : edges)
            builder.append("\t").append(e.toString());
        return builder.toString();
    }   
}
