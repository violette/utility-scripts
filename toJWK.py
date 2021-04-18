#requires pip install jwcrypto
# pip install six
# pip install cryptography==2.3
#
# A groovy script that generates a JWK from a EC PEM.
# @author violette - 2021
#

import jwcrypto
from jwcrypto import jwk
from jwcrypto.jwk import JWK


EC_public_key = b"""-----BEGIN PUBLIC KEY-----
MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEhvGzt82WMJxqTuXCZxnvwrx4enQj
6xc+erlhbTq8gTMAJBzNRPbpuj4NOwTCwjohrtY0TAkthwTuixuojpGKmw==
-----END PUBLIC KEY-----"""
jwk_from_pem = JWK.from_pem(EC_public_key)
print(jwk_from_pem.export())
