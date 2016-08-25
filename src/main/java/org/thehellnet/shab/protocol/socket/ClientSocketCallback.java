package org.thehellnet.shab.protocol.socket;

/**
 * Created by sardylan on 25/08/16.
 */
public interface ClientSocketCallback {

    void connected();

    void newLine(String line);

    void disconnected();
}
