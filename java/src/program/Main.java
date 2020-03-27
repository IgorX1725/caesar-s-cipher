package program;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.hc.core5.http.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException, IOException {
		//carrega o token do usuário através do arquivo .properties
		Properties prop = new Properties();
		FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"/bin/"+"token.properties");
		prop.load(ip);
		
		//salva o json retornado pelo metodo get da classe GetJson
		String json = GetJson.get(
				"https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token="+prop.getProperty("token"));
		
		// instancia da classe JsonManipulator para manipular o json passado como parametro
		JsonManipulator jsonManipulator = new JsonManipulator(json);
		
		// salva  nas variaveis  o salto e o texto criptografado presentes no json 
		Integer salts =  jsonManipulator.getNumSaltos();
		String cipher =  jsonManipulator.getCifrado();
		
		//Salva o texto decifrado pela classe Cipher
		String deciphered = Cipher.decipher(salts, cipher);
		
		//atualiza a chave "decifrado" do json que estava vazia até então
		jsonManipulator.setDecifrado(deciphered);
		
		// salva o hash gerado pela classe makeSha1 a partir do texto descriptografado
		String resume = MakeSha1.GenerateSHA1(deciphered);
		
		// salva o hash gerado pela classe makeSha1 no json
		jsonManipulator.setResumo(resume);
		
		//Atualiza o arquivo em disco onde está salvo o json
		jsonManipulator.updateJson();
	
		//Exibe o json no console
		System.out.println();
		System.out.println(jsonManipulator.toString());

		//Envia o arquivo Json para a API valida-lo
		String score = PostJson.post("https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token="+prop.getProperty("token"));
		
		//Exibe o score da avaliação no terminal
		System.out.println();
		System.out.println(score);
	}
}
