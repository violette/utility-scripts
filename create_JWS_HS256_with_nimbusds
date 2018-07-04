@Grapes(
    @Grab(group = 'com.nimbusds', module = 'nimbus-jose-jwt', version = '5.12')
)
/**
 * Generates a SIGNED JWT with symmetric key.
 * @author violette - 2018.
 */

import java.security.SecureRandom

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

// Generate random 256-bits (32-bytes) shared secret
def sharedSecret = new byte[32];
new SecureRandom().nextBytes(sharedSecret);

def signer = new MACSigner(sharedSecret);

// Prepare JWT with claims set
def claimsSet = new JWTClaimsSet.Builder()
    .issueTime(new Date(1502382118))
    .claim("custom1", "value")
    .build();

def header = new JWSHeader.Builder(JWSAlgorithm.HS256)
    .customParam("customHeader", "value").
    build();

def jws = new SignedJWT(header, claimsSet);
jws.sign(signer);
println("JWT >>>> " + jws.serialize());
