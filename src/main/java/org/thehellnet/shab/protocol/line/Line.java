package org.thehellnet.shab.protocol.line;

import org.thehellnet.shab.protocol.Command;
import org.thehellnet.shab.protocol.LineFactory;
import org.thehellnet.shab.protocol.exception.AbstractProtocolException;

import java.io.Serializable;

/**
 * Created by sardylan on 03/08/16.
 */
public abstract class Line implements Serializable {

    private Command command;

    Line(Command command) {
        this.command = command;
    }

    Line(Command command, String rawLine) throws AbstractProtocolException {
        this.command = command;
        parse(rawLine.split("\\|"));
    }

    public Command getCommand() {
        return command;
    }

    public String serialize() {
        return LineFactory.addChecksum(serializeLine());
    }

    protected abstract String serializeLine();

    protected abstract void parse(String[] items) throws AbstractProtocolException;

    @Override
    public String toString() {
        return serialize();
    }
}
