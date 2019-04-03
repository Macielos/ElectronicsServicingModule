package esp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import esp.dto.CommentDto;
import esp.dto.EquipmentDto;
import esp.dto.EquipmentParameterDto;
import esp.dto.IssueDto;
import lombok.var;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.core.util.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;

public class ESPTestClient {

	private static final String URL = "http://localhost:8100";

	private static CloseableHttpClient client;
	private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("user", "pass"));
        client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

        insertEq(new EquipmentDto(null, "Pralka", "AGD",
		        Collections.singletonList(new EquipmentParameterDto("Max obroty", "1200")),
				Collections.singletonList(new IssueDto(null, "zepsuta", null, "PENDING", new Date())),
				Collections.singletonList(new CommentDto(null, "janusz123", "Nie dziala", new Date()))
				));
        getEq(1L);
        getEqs();
    }

    private static void insertEq(EquipmentDto equipmentDto) throws IOException {
        System.out.println("equipment: " + equipmentDto);

        String userJson = objectMapper.writeValueAsString(equipmentDto);
        System.out.println("equipmentJson: " + userJson);

        post(URL+"/equipment", userJson);
    }

    private static void getEq(long id) throws IOException {
        String responseBody = get(String.format(URL + "/equipment?id=%d", id));
        System.out.println("Eq from get: "+responseBody);
    }

    private static void getEqs() throws IOException {
        String responseBody = get(URL + "/equipment?offset=0&size=10");
        System.out.println("Eq from getPage: "+responseBody);
    }

    private static String post(String url, String body) throws IOException {
        var httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(body);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try(CloseableHttpResponse response = client.execute(httpPost)){
            return IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));
        }
    }

    private static String get(String url) throws IOException {
        try(CloseableHttpResponse response = client.execute(new HttpGet(url))){
            return IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));
        }
    }
}