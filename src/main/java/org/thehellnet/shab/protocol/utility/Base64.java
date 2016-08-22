package org.thehellnet.shab.protocol.utility;

/**
 * Created by sardylan on 21/08/16.
 */
public final class Base64 {

    public enum Method {
        STANDARD,
        URLSAFE
    }

    private static final Method DEFAULT_METHOD = Method.URLSAFE;

    private static final String CODES_STANDARD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    private static final String CODES_URLSAFE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=";

    public static byte[] decode(String input) {
        return decode(input, DEFAULT_METHOD);
    }

    public static byte[] decode(String input, Method method) {
        String codes = getCodes(method);

        if (input.length() % 4 != 0) {
            throw new IllegalArgumentException("Invalid base64 input");
        }

        char[] inChars = input.toCharArray();
        byte[] decoded = new byte[((input.length() * 3) / 4) -
                (input.indexOf('=') > 0
                        ? (input.length() - input.indexOf('='))
                        : 0)];

        int j = 0;
        int[] b = new int[4];
        for (int i = 0; i < inChars.length; i += 4) {
            b[0] = codes.indexOf(inChars[i]);
            b[1] = codes.indexOf(inChars[i + 1]);
            b[2] = codes.indexOf(inChars[i + 2]);
            b[3] = codes.indexOf(inChars[i + 3]);

            decoded[j++] = (byte) ((b[0] << 2) | (b[1] >> 4));
            if (b[2] < 64) {
                decoded[j++] = (byte) ((b[1] << 4) | (b[2] >> 2));
                if (b[3] < 64) {
                    decoded[j++] = (byte) ((b[2] << 6) | b[3]);
                }
            }
        }

        return decoded;
    }

    public static String encode(byte[] in) {
        return encode(in, DEFAULT_METHOD);
    }

    public static String encode(byte[] in, Method method) {
        String codes = getCodes(method);

        StringBuilder out = new StringBuilder((in.length * 4) / 3);

        int b;
        for (int i = 0; i < in.length; i += 3) {
            b = (in[i] & 0xFC) >> 2;
            out.append(codes.charAt(b));
            b = (in[i] & 0x03) << 4;
            if (i + 1 < in.length) {
                b |= (in[i + 1] & 0xF0) >> 4;
                out.append(codes.charAt(b));
                b = (in[i + 1] & 0x0F) << 2;
                if (i + 2 < in.length) {
                    b |= (in[i + 2] & 0xC0) >> 6;
                    out.append(codes.charAt(b));
                    b = in[i + 2] & 0x3F;
                    out.append(codes.charAt(b));
                } else {
                    out.append(codes.charAt(b));
                    out.append('=');
                }
            } else {
                out.append(codes.charAt(b));
                out.append("==");
            }
        }

        return out.toString();
    }

    private static String getCodes(Method method) {
        switch (method) {
            case URLSAFE:
                return CODES_URLSAFE;
            default:
                return CODES_STANDARD;
        }
    }
}
