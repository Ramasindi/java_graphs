package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VertexText {
    @Test
    public void testVertex() {
        Vertex<User> user = new Vertex<User>(new User("Muano", "@muano"));
        assertEquals("Muano", user.getValue().getName());
    }
}
