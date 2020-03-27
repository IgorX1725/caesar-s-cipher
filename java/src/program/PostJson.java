package program;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;


// envia uma requisição do tipo POST com o arquivo json como parametro MultipartEntity para a API
public class PostJson {
	
	public static String post(String url) throws IOException, ParseException {
	
		CloseableHttpClient httpClient = HttpClients.createDefault();
		File file = new File("./answer.json");
		HttpPost post = new HttpPost(url);
		FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.EXTENDED);
		builder.addPart("answer", fileBody);
		HttpEntity entity = builder.build();

		post.setEntity(entity);
		CloseableHttpResponse response = httpClient.execute(post);
		String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
	
			return responseBody;
	} 
}
