package com.example;

public class User  implements Comparable<User>{
    private long id;
    private String name;
    private String handle;

    public User(String name, String handle) {
        this.name = name;
        this.handle = handle;
    }

    public String getName() {
        return name;
    }

    public String getHandle() {
        return handle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Override
    public int hashCode() {
        return (int) (id * name.hashCode() * handle.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(this  == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return this.id == user.id && this.name.equals(user.name) && this.handle.equals(user.handle);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", handle='" + handle + '\'' +
                '}';
    }

    @Override
    public int compareTo(User o) {
        return this.name.compareTo(o.name);
    }
}
