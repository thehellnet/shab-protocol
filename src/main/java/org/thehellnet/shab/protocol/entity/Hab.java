package org.thehellnet.shab.protocol.entity;

import org.thehellnet.shab.protocol.Position;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by sardylan on 03/08/16.
 */
public class Hab implements Serializable {

    private Position position = new Position();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s", position.toString());
    }
}
