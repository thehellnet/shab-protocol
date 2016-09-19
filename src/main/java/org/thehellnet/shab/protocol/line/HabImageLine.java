package org.thehellnet.shab.protocol.line;

import org.thehellnet.shab.protocol.entity.Hab;
import org.thehellnet.shab.protocol.exception.AbstractProtocolException;
import org.thehellnet.shab.protocol.exception.ParseLineException;
import org.thehellnet.shab.protocol.utility.Base64;

/**
 * Created by sardylan on 03/08/16.
 */
public class HabImageLine extends Line {

    private static final Command COMMAND = Command.HAB_IMAGE;
    public static final String COMMAND_TAG = "HI";

    private long timestamp;
    private int sliceTot;
    private int sliceNum;
    private byte[] data;

    public HabImageLine() {
        super(COMMAND);
    }

    public HabImageLine(String rawLine) throws AbstractProtocolException {
        super(COMMAND, rawLine);
    }

    public HabImageLine(Hab hab) {
        super(COMMAND);

        if (hab == null) {
            return;
        }

        if (hab.getImageData().length > 0) {
            sliceTot = hab.getSliceTot();
            sliceNum = hab.getSliceNum();
            data = hab.getImageData();
        }
    }

    @Override
    public String serializeLine() {
        return String.format("%s|%d|%d|%d|%s",
                COMMAND_TAG,
                timestamp,
                sliceTot,
                sliceNum,
                Base64.encode(data));
    }

    @Override
    protected void parse(String[] items) throws AbstractProtocolException {
        if (!items[1].equals(COMMAND_TAG) || items.length != 6) {
            throw new ParseLineException();
        }

        timestamp = Long.parseLong(items[2]);
        sliceTot = Integer.parseInt(items[3]);
        sliceNum = Integer.parseInt(items[4]);
        data = Base64.decode(items[5]);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getSliceTot() {
        return sliceTot;
    }

    public void setSliceTot(int sliceTot) {
        this.sliceTot = sliceTot;
    }

    public int getSliceNum() {
        return sliceNum;
    }

    public void setSliceNum(int sliceNum) {
        this.sliceNum = sliceNum;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
