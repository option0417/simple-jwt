package tw.com.wd.jwt;

public interface IJsonWebTokenService {
    /**
     * Build Token for authentication
     * @param jwtHeader
     * @param jwtPayload
     * @return
     */
    public String buildToken(JwtHeader jwtHeader, JwtPayload jwtPayload);

    /**
     * Check token is valid or not
     * @param jwtString
     * @return
     */
    public boolean isValidToken(String jwtString);
}
