package esp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;

public class ESPTestClient {

	private static final String URL = "http://localhost:8100";

	private static CloseableHttpClient client;
	private static ObjectMapper objectMapper = new ObjectMapper();

//    public static void main(String[] args) throws IOException {
//        CredentialsProvider provider = new BasicCredentialsProvider();
//        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("user", "dupa"));
//        client = HttpClientBuilder.create()
//                .setDefaultCredentialsProvider(provider)
//                .build();
//
//       // insertUser(new EquipmentDto(null, "Admin", null, null));
//       // registerUser("Admin", "dupa2");
//        authenticateUser("Admin", "dupa2");
//        getUser(1L);
//        getUsers();
//
//    }
//
//    private static void insertUser(EquipmentDto equipmentDto) throws IOException {
//        System.out.println("user: " + equipmentDto);
//
//        String userJson = objectMapper.writeValueAsString(equipmentDto);
//        System.out.println("userJson: " + userJson);
//
//        post(URL+"/user", userJson);
//    }
//
//    private static void getUser(long id) throws IOException {
//        String responseBody = get(String.format(URL + "/user?id=%d", id));
//        System.out.println("User from get: "+responseBody);
//    }
//
//    private static void getUsers() throws IOException {
//        String responseBody = get(URL + "/user");
//        System.out.println("Users from getAll: "+responseBody);
//    }
//
//    private static void registerUser(String user, String pass) throws IOException {
//        AuthenticationData authenticationData = new AuthenticationData(user, pass);
//        System.out.println("user: " + authenticationData);
//
//        String authJson = objectMapper.writeValueAsString(authenticationData);
//        System.out.println("authJson: " + authJson);
//
//        String res = post(URL+"/authenticate", authJson);
//
//        System.out.println("User registered: "+res);
//    }
//
//    private static void authenticateUser(String user, String pass) throws IOException {
//        AuthenticationData authenticationData = new AuthenticationData(user, pass);
//        System.out.println("user: " + authenticationData);
//
//        String res = get(String.format(URL+"/authenticate?username=%s&password=%s", user, pass));
//
//        System.out.println("User registered: "+res);
//    }
//
//    private static String post(String url, String body) throws IOException {
//        var httpPost = new HttpPost(url);
//        StringEntity entity = new StringEntity(body);
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-type", "application/json");
//
//        try(CloseableHttpResponse response = client.execute(httpPost)){
//            String responseBody = IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));
//            System.out.println("Response: "+response.getStatusLine());
//            return responseBody;
//        }
//    }
//
//    private static String get(String url) throws IOException {
//        try(CloseableHttpResponse response = client.execute(new HttpGet(url))){
//            String responseBody = IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));
//            System.out.println("User from get: "+responseBody);
//            return responseBody;
//        }
//    }
}