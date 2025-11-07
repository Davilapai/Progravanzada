import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// 1. Crear una instancia de Spotify y agregar 10 canciones distintas
		Spotify app = new Spotify();

		List<Cancion> catalogo = new ArrayList<>();
		catalogo.add(crearCancion("Tu con el", "Frankie Ruiz", "Salsa", 1985));
		catalogo.add(crearCancion("Todas las hojas son del viento", "Luis Alberto Spinetta", "Rock", 1973));
		catalogo.add(crearCancion("Lo artesanal", "Viejas Locas", "Rock", 2004));
		catalogo.add(crearCancion("Puchos", "Perras on the beach", "Rock", 2016));
		catalogo.add(crearCancion("Fuego", "Intoxicados", "Rock", 2003));
		catalogo.add(crearCancion("Al lado del camino", "Fito Páez", "Rock/Pop", 1999));
		catalogo.add(crearCancion("Diamante roto", "EMUPM", "Rock", 2023)); 
		catalogo.add(crearCancion("Para no olvidar", "Los Rodríguez", "Rock", 1993));
		catalogo.add(crearCancion("Aqui murio el payaso", "Santiago Insane", "Rock", 2019)); 
		catalogo.add(crearCancion("Volver a casa", "Airbag", "Rock", 2011)); 

		for (Cancion c : catalogo) app.agregarCancion(c);

		//2. Inicio del programa 
		System.out.println("\tBIENVENIDO A SPOTIFY");
		System.out.println("\t1. Registrarse");
		System.out.println("\t2. Ingresar");
		System.out.print("Su opcion: ");

		int opcion = sc.nextInt();
		sc.nextLine();
		
	

		public void registrar(){
			System.out.println("\tBienvenido al registro");
			System.out.print("Ingrese su nombre de usuario: ");
			String nUsuario = sc.nextLine();
			System.out.print("Ingrese su contrasenia: ");
			String password = sc.nextLine();

			System.out.print("¿Desea ser premium? (1 = Si/2 = No): ");
			int decision = sc.nextInt();
			sc.nextLine();

			if(decision == 1){
				Premium nuevo = new Premium(nUsuario, password);
				
			}else{
				Freemium nuevo = new Freemium(nUsuario, password);
			}
		}

		public Cancion crearCancion(String nombre, String autor, String genero, int anio) {
			Cancion c = new Cancion();
			c.setNombre(nombre);
			c.setAutor(autor);
			c.setGenero(genero);
			c.setAnio(anio);
			return c;
		}
	}
}
