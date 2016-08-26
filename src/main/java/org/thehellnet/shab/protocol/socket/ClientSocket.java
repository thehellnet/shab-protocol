package org.thehellnet.shab.protocol.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * Created by sardylan on 25/08/16.
 */
public class ClientSocket {

    private static final Logger logger = LoggerFactory.getLogger(ClientSocket.class);
    private static final int RECONNECT_DELAY = 2000;

    private ClientSocketCallback callback;
    private Thread thread;
    private boolean keepRunning;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private String lastLine = "";

    public ClientSocket(ClientSocketCallback callback) {
        this.callback = callback;
    }

    public void start(final String address, final int port) {
        keepRunning = true;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (keepRunning && !thread.isInterrupted()) {
                    try {
                        socket = new Socket(address, port);
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                        logger.info("Connected");
                        callback.connected();

                        while (keepRunning && socket.isConnected()) {
                            lastLine = reader.readLine();
                            if (lastLine == null) {
                                socket.close();
                                break;
                            }
                            if (lastLine.length() > 0) {
                                callback.newSocketRawLine(lastLine);
                            }
                        }
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                        e.printStackTrace();
                    }

                    logger.info("Disconnected");
                    callback.disconnected();

                    try {
                        Thread.sleep(RECONNECT_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    logger.info("Reconnecting");
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void stop() {
        keepRunning = false;
        writer.close();
        try {
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void send(String line) {
        if (lastLine.equals(line) || lastLine.length() == 0) {
            return;
        }
        lastLine = line;
        writer.println(line);
        writer.flush();
    }

    public void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
