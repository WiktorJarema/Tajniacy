package org.tajniacy.model;

public class Nickname {

    private Long id;
    private String name;
    private boolean isFree;

    public Nickname(Long id, String name) {
        this.id = id;
        this.name = name;
        this.isFree = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(boolean value) {
        isFree = value;
    }


    @Override
    public String toString() {
        return "Nickname{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isFree=" + isFree +
                '}';
    }

}
