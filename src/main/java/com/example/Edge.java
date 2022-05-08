package com.example;

public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {

    Vertex<T> from = null;
    Vertex<T> to = null;
    private int cost = 0;

    public Edge(int cost, Vertex<T> from, Vertex<T> to) {
        if (from == null || to == null)
            throw (new NullPointerException("Both 'to' and 'from' vertices need to be non-NULL."));

        this.cost = cost;
        this.from = from;
        this.to = to;
    }

    public Edge(Edge<T> e) {
        this(e.cost, e.from, e.to);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Vertex<T> getFromVertex() {
        return from;
    }

    public Vertex<T> getToVertex() {
        return to;
    }

    @Override
    public int hashCode() {
        final int cost = (this.cost * (this.getFromVertex().hashCode() * this.getToVertex().hashCode())); 
        return 31 * cost;
    }

    @Override
    public boolean equals(Object e1) {
        if (!(e1 instanceof Edge))
            return false;

        final Edge<T> e = (Edge<T>) e1;

        final boolean costs = this.cost == e.cost;
        if (!costs)
            return false;

        final boolean from = this.from.equals(e.from);
        if (!from)
            return false;

        final boolean to = this.to.equals(e.to);
        if (!to)
            return false;

        return true;
    }

    @Override
    public int compareTo(Edge<T> e) {
        if (this.cost < e.cost)
            return -1;
        if (this.cost > e.cost)
            return 1;

        final int from = this.from.compareTo(e.from);
        if (from != 0)
            return from;

        final int to = this.to.compareTo(e.to);
        if (to != 0)
            return to;

        return 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ").append(from.getValue()).append("(").append(from.getWeight()).append(") ").append("]").append(" -> ")
               .append("[ ").append(to.getValue()).append("(").append(to.getWeight()).append(") ").append("]").append(" = ").append(cost).append("\n");
        return builder.toString();
    }

}
