package org.thehellnet.shab.protocol.entity;

import org.thehellnet.shab.protocol.helper.GpsFixQuality;
import org.thehellnet.shab.protocol.helper.Position;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by sardylan on 03/08/16.
 */
public class Hab implements Serializable {

    private long lastTimestamp = 0;

    private GpsFixQuality fixStatus = GpsFixQuality.INVALID;
    private Position position;
    private int sliceTot = 0;
    private int sliceNum = 0;
    private ByteBuffer imageData = ByteBuffer.allocate(1024 * 1024);
    private byte[] lastImageData;

    private float intTemp = 0;
    private float extTemp = 0;
    private float extAlt = 0;

    public long getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    public GpsFixQuality getFixStatus() {
        return fixStatus;
    }

    public void setFixStatus(GpsFixQuality fixStatus) {
        this.fixStatus = fixStatus;
    }

    public void setFixStatus(int fixStatus) {
        this.fixStatus = GpsFixQuality.fromNumber(fixStatus);
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
        lastImageData = Arrays.copyOfRange(imageData.array(), 0, imageData.position());
        return lastImageData;
    }

    public void clearImageData() {
        imageData.clear();
    }

    public void appendImageData(byte[] imageSlice) {
        imageData.put(imageSlice);
    }

    public byte[] getLastImageData() {
        return lastImageData;
    }

    public float getIntTemp() {
        return intTemp;
    }

    public void setIntTemp(float intTemp) {
        this.intTemp = intTemp;
    }

    public float getExtTemp() {
        return extTemp;
    }

    public void setExtTemp(float extTemp) {
        this.extTemp = extTemp;
    }

    public float getExtAlt() {
        return extAlt;
    }

    public void setExtAlt(float extAlt) {
        this.extAlt = extAlt;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s", position.toString());
    }
}
