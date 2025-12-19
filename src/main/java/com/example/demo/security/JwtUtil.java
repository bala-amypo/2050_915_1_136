import java.util.HashMap;

public Map<String, Object> getAllClaims(String token) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("email", "test@example.com");
    return claims;
}
