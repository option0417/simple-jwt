package tw.com.wd.jwt;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;


public class JwtPayload {
    @SerializedName("userid")
    private String issuer;

    @SerializedName("username")
    private String expireTimestamp;

    //@SerializedName("\"http://example.com/is_root\":true")
    private transient boolean isRoot;


    public JwtPayload(String issuer, String expireTimestamp) {
        super();
        this.issuer = issuer;
        this.expireTimestamp = expireTimestamp;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getExpireTimestamp() {
        return expireTimestamp;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }


    @Override
    public String toString() {
        return new Gson().toJson(this, JwtPayload.class);
    }
}
