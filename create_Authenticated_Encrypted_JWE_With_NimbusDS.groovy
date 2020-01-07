@Grapes(
        @Grab(group = 'com.nimbusds', module = 'nimbus-jose-jwt', version = '5.12')
)
/**
 * Generates a ENCRYPTED JWT with symmetric key (Authenticated Encryption)
 * @author violette - 2020.
 */

import java.security.SecureRandom

import com.nimbusds.jose.*
import com.nimbusds.jose.Algorithm.*
import com.nimbusds.jose.crypto.*
import com.nimbusds.jwt.*

// Generate random 256-bits (32-bytes) shared secret
def sharedSecret = "--SecretKeySize32BytesMinimum---".getBytes();
new SecureRandom().nextBytes(sharedSecret);

def claimsSet = new JWTClaimsSet.Builder()
        .issueTime(new Date(1502382118))
        .claim("a-value", "ForgeRock IG")
        .build();

def payload = new Payload(claimsSet.toJSONObject());
def header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);
def encrypter = new DirectEncrypter(sharedSecret);

JWEObject jweObject = new JWEObject(header, payload);
jweObject.encrypt(encrypter);

println("JWT >>>> " + jweObject.serialize());
