package client;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import entities.Trip;
import entities.TripRequest;

public class RestClientMain {
	public static final String MY_SERVER_URL="http://localhost:8080/myapp/";
	public static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(MY_SERVER_URL);
		WebTarget rootWebTarget = webTarget.path("myresource/trips");
		int option = 0;
		menu();
		option = in.nextInt();
		while(option!=0) {
			switch (option) {
			case 1:
				listarPaseos(rootWebTarget);
				break;
			case 2:
				borrarPaseo(rootWebTarget);
				break;
			case 3:
				actualizarPaseo(rootWebTarget);
				break;
			case 4:
				crearPaseo(rootWebTarget);
				break;
			}
			menu();
			option = in.nextInt();
		}
		System.out.println("Adiós");
	}
	
	private static void menu() {
		System.out.println("-----------Escoja una opción-----------");
		System.out.println("1. Listar Paseos");
		System.out.println("2. Borrar un Paseo");
		System.out.println("3. Actualizar un Paseo");
		System.out.println("4. Crear un Paseo");
		System.out.println("0. Salir");
		
	}
	private static void listarPaseos(WebTarget rootWebTarget) {
		Invocation.Builder invocationBuilder = rootWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		List<Trip> trips= Arrays.asList(new Gson().fromJson(response.readEntity(String.class), Trip[].class));
		System.out.println("Los paseos que hay son: ");
		for (Trip trip : trips) {
			System.out.println(trip);
		}
	}
	private static void borrarPaseo(WebTarget rootWebTarget) {
		System.out.println("Ingrese el id del paseo que desea eliminar");
		int idTrip = in.nextInt();
		WebTarget deleteTrip = rootWebTarget.path("/"+idTrip);
		Invocation.Builder invocationBuilder = deleteTrip.request();
		Response response = invocationBuilder.delete();
		int status = response.getStatus();
		String body = response.readEntity(String.class);
		System.out.println("Respuesta: "+status+" Contenido: "+body);
	}
	private static void actualizarPaseo(WebTarget rootWebTarget) {
		System.out.println("Ingrese el id del paseo que desea actualizar");
		int idTrip = in.nextInt();
		System.out.println("Ingrese el nuevo origen del paseo");
		in.nextLine();
		String departure = in.nextLine();
		System.out.println("Ingrese el nuevo destino del paseo");
		String arrival = in.nextLine();
		WebTarget updateTrip = rootWebTarget.path("/"+idTrip).queryParam("departure", departure).queryParam("arrival",arrival);
		Invocation.Builder invocationBuilder = updateTrip.request();
		Response response = invocationBuilder.put(Entity.text(""));
		int status = response.getStatus();
		String body = response.readEntity(String.class);
		System.out.println("Respuesta: "+status+" Contenido: "+body);
	}
	private static void crearPaseo(WebTarget rootWebTarget) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		in.nextLine();
		System.out.println("Ingrese el nombre nuevo paseo");
		String name = in.nextLine();
		System.out.println("Ingrese el origen del nuevo paseo");
		String departure = in.nextLine();
		System.out.println("Ingrese el destino del nuevo paseo");
		String arrival = in.nextLine();
		System.out.println("Ingrese la fecha de salida del paseo en formato: yyyy-MM-dd:");
		String departureDate = in.nextLine();
		System.out.println("Ingrese la fecha de llegada del paseo en formato: yyyy-MM-dd:");
		String arrivalDate = in.nextLine();

        TripRequest trip = new TripRequest(name,departure,arrival,departureDate,arrivalDate);
        
        
		Invocation.Builder invocationBuilder = rootWebTarget.request();
		Response response = invocationBuilder.post(Entity.entity(trip,
				MediaType.APPLICATION_JSON));
		int status = response.getStatus();
		String body = response.readEntity(String.class);
		System.out.println("Respuesta: "+status+" Contenido: "+body);
	}
}
