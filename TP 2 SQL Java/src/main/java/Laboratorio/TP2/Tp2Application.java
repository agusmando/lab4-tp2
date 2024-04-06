package Laboratorio.TP2;

import Laboratorio.TP2.Services.CountryService;
import Laboratorio.TP2.Services.DatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SpringBootApplication
public class Tp2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Tp2Application.class, args);
		System.out.println("-- Estoy vivo --");

		CountryService countryService = context.getBean(CountryService.class);
		DatabaseService databaseService = context.getBean(DatabaseService.class);
		int count = 0;
		// Iterar sobre los códigos de país del 1 al 300
		for (int codigo = 1; codigo <= 300; codigo++) {
			clearConsole();
			// Obtener la información del país en formato JSON
			try{
				String json = countryService.getCountryInfoJson(codigo);
				// Procesar el JSON y obtener los valores necesarios
				if(extractValuesFromJsonArray(json, databaseService, codigo)){
					count ++;
				};
				System.out.println("Paises agregados: "+ count);
			}catch(Error e){
				System.out.println("No se pudieron obtener datos del codigo "+codigo);
			}
		}

		// Cerrar el contexto de la aplicación
		context.close();
	}

	private static boolean extractValuesFromJsonArray(String jsonArrayStr, DatabaseService databaseService, int codigo) {
		if (jsonArrayStr == null) {
			System.out.println("JSON vacío");
			return false;
		}
		try {
			JSONArray jsonArray = new JSONArray(jsonArrayStr);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String nombrePais = jsonObject.optString("name");
				String capitalPais = jsonObject.optString("capital");
				String region = jsonObject.optString("region");
				long poblacion = jsonObject.optLong("population");
				JSONArray latlng = jsonObject.optJSONArray("latlng");
				double latitud = latlng != null && latlng.length() > 0 ? latlng.getDouble(0) : 0.0;
				double longitud = latlng != null && latlng.length() > 1 ? latlng.getDouble(1) : 0.0;

				// Verificar la longitud de los valores antes de insertarlos
				if (nombrePais != null && nombrePais.length() <= 50 &&
						capitalPais != null && capitalPais.length() <= 50 &&
						region != null && region.length() <= 50) {
					// Insertar los datos en la base de datos solo si los valores son válidos
					databaseService.insertCountryInfo(nombrePais, capitalPais, region, poblacion, latitud, longitud);
					return true;
				} else {
					System.out.println("Alguno de los valores excede los 50 caracteres, no se insertará en la base de datos.");
					return false;
				}
			}
		} catch (JSONException e) {
			System.err.println("Error al procesar el JSON: " + e.getMessage());
			return false;
		}
		return true;
	}

	public static void clearConsole() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
			System.out.println("Error al limpiar la consola: " + e.getMessage());
		}
	}
}

