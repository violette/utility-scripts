@Grapes(
    @Grab(group = 'com.nimbusds', module = 'nimbus-jose-jwt', version = '5.12')
)

import com.nimbusds.jose.PlainHeader
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.PlainJWT

import static java.util.Collections.singletonMap

/**
 * Generates an UNSIGNED JWT.
 * @author violette - 2018
 */
def claimsSet = new JWTClaimsSet.Builder()
        .issueTime(new Date(1502382118))
        .claim("custom1", "value")
        .build();
def header = new PlainHeader.Builder()
        .customParams(singletonMap("customHeader", "value"))
        .build();
def jwsObject = new PlainJWT(header, claimsSet);
println("JWT >>>> " + jwsObject.serialize());
