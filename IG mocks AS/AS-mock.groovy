import com.google.common.base.Splitter;
import java.util.Arrays;
import java.util.Map;

def requestPath = request.uri.path
def response
def issuer
// -----------------------------------------------------------------------------
// Mock the responses given by an AS.
// See OPENIG-4046.
// -----------------------------------------------------------------------------
if (request.uri.path.startsWith("/authorize")) {
    println("AUTHORIZE >>>> -----------------------------------------------------------------------------")
    def query = request.uri.query;
    println("> REQUEST URI >> " + request.uri)
    issuer = "${request.uri.scheme}://${request.uri.host}:${request.uri.port}"
    final Map<String, String> map = Splitter.on('&').trimResults().withKeyValueSeparator('=').split(query);
    def state = map.get("state");
    def redirect = map.get("redirect_uri");
    response = new Response(Status.FOUND)
    def finalRedirect = redirect + "?state=" + state + "&code=SplxlOBeZQQYbYS6WxSbIA" + "&access_token=2YotnFZFEjr1zCsicMWpAA"
    response.headers['Location'] = finalRedirect
    println("<<<< AUTHORIZE -----------------------------------------------------------------------------")
} else if (request.uri.path.startsWith("/token")) {
    println("GET ACCESS_TOKEN >>>> -----------------------------------------------------------------------------")
    println("> REQUEST URI >> " + request.uri)
    // Note the id_token is required to be able to request the user_info endpoint. The content is unused.
    response = new Response(Status.OK)
    response.headers['Content-Type'] = 'application/json'
    response.entity = """{
        "access_token": "mockedAccessToken",
        "token_type":"example",
        "expires_in":3600,
        "refresh_token": "tGzv3JOkF0XG5Qx2TlKWIA",
        "id_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJuYW1lIjoidW51c2VkIGJ1dCByZXF1aXJlZCB0byBoYXZlIGFjY2VzcyB0byB1c2VyX2luZm8ifQ."
    }""" as String
    println("<<<< GET ACCESS_TOKEN -----------------------------------------------------------------------------")
    return response
} else if (request.uri.path.startsWith("/issuer")) {
    println("ISSUER >>>> -----------------------------------------------------------------------------")
    println("> REQUEST URI >> " + request.uri)
    issuer = "${request.uri.scheme}://${request.uri.host}:${request.uri.port}"
    response = new Response(Status.OK)
    response.headers['Content-Type'] = 'application/json'
    response.entity = """{
        "authorizeEndpoint": "${issuer}/authorize",
        "registrationEndpoint": "${issuer}/register",
        "tokenEndpoint": "${issuer}/token",
        "userInfoEndpoint": "${issuer}/userinfo"
    }""" as String
    println("<<<< ISSUER -----------------------------------------------------------------------------")
} else if (request.uri.path.startsWith("/register")) {
    println("REGISTER CLIENT >>>> -----------------------------------------------------------------------------")
    println("> REQUEST URI >> " + request.uri)
    response = new Response(Status.OK)
    response.headers['Content-Type'] = 'application/json'
    response.entity = """{
        "redirect_uris": ["http://openig.example.com:8082/account/callback"],
        "client_id": "myClient",
        "client_secret": "mysecretFromServer",
        "client_name": "My Example Client",
        "token_endpoint_auth_method": "client_secret_basic"
    }""" as String
    println("<<<< REGISTER CLIENT -----------------------------------------------------------------------------")
} else if (request.uri.path.startsWith("/.well-known/webfinger")) {
    println("WEBFINGER >>>> -----------------------------------------------------------------------------")
    println("> REQUEST URI >> " + request.uri)
    issuer = "${request.uri.scheme}://${request.uri.host}:${request.uri.port}"
    response = new Response(Status.OK)
    response.headers['Content-Type'] = 'application/json'
    response.entity = """{
        "subject": "${issuer}",
        "links":[{"rel": "http://openid.net/specs/connect/1.0/issuer",
                  "href": "${issuer}/issuer"}]
    }""" as String
    println("<<<< WEBFINGER -----------------------------------------------------------------------------")
} else if (request.uri.path.startsWith("/userinfo")) {
    println("USERINFO >>>> -----------------------------------------------------------------------------")
    println("> REQUEST URI >> " + request.uri)
    issuer = "${request.uri.scheme}://${request.uri.host}:${request.uri.port}"
    response = new Response(Status.OK)
    response.headers['Content-Type'] = 'application/json'
    if (issuer.contains("alpha")) {
        response.entity = """{
           "sub": "12345",
           "name": "Jane Alpha",
           "given_name": "Jane",
           "family_name": "Alpha",
           "preferred_username": "j.Alpha",
           "email": "janeAlpha@example.com"
        }""" as String
    } else {
        response.entity = """{
       "sub": "54321",
       "name": "JOHN Bravo",
       "given_name": "John",
       "family_name": "Bravo",
       "preferred_username": "j.brvo",
       "email": "johnBravo@example.com"
        }""" as String
    }
    println("<<<< USERINFO -----------------------------------------------------------------------------")
}
return response

