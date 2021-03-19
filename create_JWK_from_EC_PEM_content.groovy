@Grapes([
    @Grab(group = 'com.nimbusds', module = 'nimbus-jose-jwt', version = '9.7'),
    @Grab(group='org.bouncycastle', module='bcpkix-jdk15on', version='1.68')
])
/**
 * A groovy script that generates a JWK from a EC PEM.
 * @author violette - 2021.
 */
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import com.nimbusds.jose.jwk.*;

String pemContent =
        """-----BEGIN EC PRIVATE KEY-----
MHcCAQEEINwWJ9NRW3yVw0yI+6p39xsESs0OzijSFhAIiGV1oHoEoAoGCCqGSM49
AwEHoUQDQgAE8Y85rEHj0+XGxqapxLuf3sTX71YYVgtUi2AbWka4Ql70nc3f7+et
LuadzofIOIwZ7Q3FIiKIdKuTqQWQYRRNog==
-----END EC PRIVATE KEY-----""";

def jwk = JWK.parseFromPEMEncodedObjects(pemContent);
println("JWK >>>>> " + jwk);
