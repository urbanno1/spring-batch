package cs544.cs.mum.edu.batchupload.model;

public class JwtResponse {
    private final String jwttoken;

    public JwtResponse(String token) {
        this.jwttoken = token;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
