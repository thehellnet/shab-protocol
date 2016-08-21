package org.thehellnet.shab.protocol.entity;

import org.thehellnet.shab.protocol.helper.GpsFixStatus;
import org.thehellnet.shab.protocol.helper.Position;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by sardylan on 03/08/16.
 */
public class Hab implements Serializable {

    private GpsFixStatus fixStatus = GpsFixStatus.INVALID;
    private Position position;
    private int sliceTot;
    private int sliceNum;
    private ByteBuffer imageData = ByteBuffer.allocate(1024 * 1024);

    private int imageSize = 0;

    public GpsFixStatus getFixStatus() {
        return fixStatus;
    }

    public void setFixStatus(GpsFixStatus fixStatus) {
        this.fixStatus = fixStatus;
    }

    public void setFixStatus(int fixStatus) {
        this.fixStatus = GpsFixStatus.fromNumber(fixStatus);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public byte[] getImageData() {
        return Arrays.copyOfRange(imageData.array(), 0, imageSize);
    }

    public void clearImageData() {
        imageData.clear();
        imageSize = 0;
    }

    public void appendImageData(byte[] imageSlice) {
        imageData.put(imageSlice);
        imageSize += imageSlice.length;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s", position.toString());
    }
}
