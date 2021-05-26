@Grapes(
    @Grab(group = 'com.nimbusds', module = 'nimbus-jose-jwt', version = '9.9.3')
)
/**
 * Decrypt a given JWE - dir/AES with a given key
 * @author violette - 2021.
 */

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*
import org.forgerock.util.encode.Base64

import javax.crypto.spec.SecretKeySpec;

// A given shared secret
def sharedSecret = "LS1TZWNyZXRLZXlTaXplMzJCeXRlc01pbmltdW0tLS0="
def key = new SecretKeySpec(Base64.decode(sharedSecret), "AES");

// A given JWE
def jweString = """eyJ0eXAiOiJKV1QiLCJraWQiOiJzeW1tZXRyaWMuc2lnbmluZy5rZXkuc2VjcmV0LmlkIiwiZW5jIjoiQTEyOENCQy1IUzI1NiIsImFsZyI6ImRpciJ9..
sEqR5YeyYe5XHHDqYu9LWw.mqfC7XcWHRcNsA8SeAAyxaDSAkebhBvls3UzLmF35_EUbFlK89zQnXGmJud0F1KcAwECwrdSE2ODofSWLPBvMw.
C7iH310pzaiSVxGDTiUCrw"""

def jweObject = JWEObject.parse(jweString);
def payload = jweObject.getPayload()
println("payload before decrypt >> " +  payload)
// Decrypt
jweObject.decrypt(new DirectDecrypter(key))
payload = jweObject.getPayload() // Have to call again otherwise is null
println("payload after decrypt >> " +  payload)
// println("JWT >>>> " + jweObject.serialize())
