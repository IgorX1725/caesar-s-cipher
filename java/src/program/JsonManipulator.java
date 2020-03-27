package program;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

// salva o json em disco e cria o JSONObject para manipulação do mesmo na memória
public class JsonManipulator {

	private String source = "./answer.json";
	private JSONObject json;


	public JsonManipulator(String json){
		save(json);
		setJson(new JSONObject(json));
	}

	private void save(String json) {
		System.out.println("--> Salvando json em "+ source);
		FileWriter jsonFile;

		try {
			jsonFile = new FileWriter(source);
			jsonFile.write(json);
			jsonFile.close();
			System.out.println("--> Json salvo com sucesso!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	//Atualiza o arquivo em disco com o jsonObject atualizado
	public void updateJson() {
		save(getJson().toString());
		System.out.println("--> Json Atualizado com sucesso em: "+source);
	}
	
	//getters e setters
	private JSONObject getJson() {
		return json;
	}

	private void setJson(JSONObject json) {
		this.json = json;
	}
	
	public void setDecifrado(String decifrado) {
		getJson().put("decifrado",decifrado);
	}
	
	public void setResumo(String resume) {
		getJson().put("resumo_criptografico",resume);
	}
	public Integer getNumSaltos() {
		return  getJson().getInt("numero_casas");
	}
	
	public String getCifrado() {
		return getJson().getString("cifrado");
	}
	
	@Override
	public String toString() {
		return getJson().toString();
	}
}
