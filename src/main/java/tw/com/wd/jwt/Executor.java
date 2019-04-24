package tw.com.wd.jwt;

/**
 * Hello world!
 *
 */
public class Executor {
    public static void main( String[] args ) {
        JsonWebTokenService jsonWebTokenService = new JsonWebTokenService();

        String jwt = jsonWebTokenService.buildJsonWebToken(
                new JwtHeader("jwt", "HS256"),
                new JwtPayload("abc-123", "Noname"));

        System.out.println(jwt);
    }
}
