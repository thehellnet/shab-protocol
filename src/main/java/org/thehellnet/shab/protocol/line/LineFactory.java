package org.thehellnet.shab.protocol.line;

import org.thehellnet.shab.protocol.exception.AbstractProtocolException;
import org.thehellnet.shab.protocol.exception.ChecksumNotValidException;
import org.thehellnet.shab.protocol.exception.CommandNotSupportedException;
import org.thehellnet.shab.protocol.exception.ParseLineException;

import static org.thehellnet.shab.protocol.utility.Checksum.checksum16;

/**
 * Created by sardylan on 03/08/16.
 */
public final class LineFactory {

    public static Line parse(String line) throws AbstractProtocolException {
        if (line == null) {
            throw new ParseLineException();
        }

        line = line.trim();
        String[] items = line.split("\\|");

        int providedChecksum;
        int strChecksum;
        try {
            providedChecksum = Integer.parseInt(items[0], 16);
            strChecksum = checksum16(line.substring(5));
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new ParseLineException();
        }

        if (providedChecksum != strChecksum) {
            throw new ChecksumNotValidException();
        }

        if (items.length < 2) {
            throw new ParseLineException();
        }

        switch (items[1]) {
            case ClientConnectLine.COMMAND_TAG:
                return new ClientConnectLine(line);
            case ClientUpdateLine.COMMAND_TAG:
                return new ClientUpdateLine(line);
            case ClientDisconnectLine.COMMAND_TAG:
                return new ClientDisconnectLine(line);
            case HabPositionLine.COMMAND_TAG:
                return new HabPositionLine(line);
            case HabImageLine.COMMAND_TAG:
                return new HabImageLine(line);
            case HabTelemetryLine.COMMAND_TAG:
                return new HabTelemetryLine(line);
        }

        throw new CommandNotSupportedException();
    }

    public static String addChecksum(String rawLine) {
        return String.format("%04X|%s", checksum16(rawLine), rawLine);
    }
}
