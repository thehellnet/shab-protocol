package org.thehellnet.shab.protocol.entity;

import org.thehellnet.shab.protocol.Position;

/**
 * Created by sardylan on 11/08/16.
 */
public class Client {

    private String id;
    private String name;
    private Position position;

    public Client() {
    }

    public Client(String id) {
        this.id = id;
    }

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(String id, String name, Position position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getId(), getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return getId().equals(client.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
