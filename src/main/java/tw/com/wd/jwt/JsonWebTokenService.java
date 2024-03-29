package tw.com.wd.jwt;

import java.io.UnsupportedEncodingException;


public class JsonWebTokenService implements IJsonWebTokenService {
    private static final byte[] SECURET_KEY = Jwk.getJwk();
    private static final String REGEX_DOT = "\\.";
    private static final String DOT = ".";
    private static final int HEADER_PART = 0;
    private static final int PAYLOAD_PART = 1;
    private static final int SIGNATURE_PART = 2;

    public String buildToken(JwtHeader jwtHeader, JwtPayload jwtPayload) {
        String base64JwtHeader = Base64Util.toBase64(jwtHeader.toString().getBytes());
        String base64JwtPayload = Base64Util.toBase64(jwtPayload.toString().getBytes());



        String jwtData = base64JwtHeader + DOT + base64JwtPayload;
        byte[] jwtDataBytes = null;
        try {
            jwtDataBytes = jwtData.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            jwtDataBytes = jwtData.getBytes();
        }

        byte[] hmacBytes = HMacUtil.calHMac(jwtDataBytes, SECURET_KEY);
        String base64HMac = Base64Util.toBase64(hmacBytes);

        return jwtData + DOT + base64HMac;
    }

    public boolean isValidToken(String jwtString) {
        // Check format
        if (jwtString == null || jwtString.length() == 0) {
            System.err.printf("JWT is null or empty\n");
            return false;
        }

        String[] jwtParts = jwtString.split(REGEX_DOT);
        if (jwtParts.length != 3) {
            System.err.printf("JWT with wrong format\n");
            return false;
        }

        return verify(jwtParts);
    }

    private boolean verify(String[] jwtParts) {
        String jwtHeader = jwtParts[HEADER_PART];
        String jwtPayload = jwtParts[PAYLOAD_PART];

        String jwtData = jwtHeader + DOT + jwtPayload;
        byte[] jwtDataBytes = null;
        try {
            jwtDataBytes = jwtData.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            jwtDataBytes = jwtData.getBytes();
        }

        byte[] hmacBytes = HMacUtil.calHMac(jwtDataBytes, SECURET_KEY);
        String base64HMac = Base64Util.toBase64(hmacBytes);

        return jwtParts[SIGNATURE_PART].equals(base64HMac);
    }
}
