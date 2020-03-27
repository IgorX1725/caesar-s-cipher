package program;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

// Realiza a requisição HTTP para capturar o json da API
public class GetJson {

	private static String json;

	public static String get(String link) throws ParseException, IOException {

		HttpGet httpget = new HttpGet(link);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpget);

		try {
			System.out.println("--> pegando json");
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				long len = entity.getContentLength();
				if (len != -1 && len < 2048) {
					json = (String) EntityUtils.toString(entity);
				}
			}
			System.out.println("--> Json coletado com sucesso");
		} catch (Exception e) {
			System.out.println("não foi possível capturar o json: " + e.getMessage());
		}finally {
			
			response.close();
		}

		return json;
	}
}
