import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 1. Crear una instancia de Spotify y agregar 10 canciones distintas
		Spotify app = new Spotify();

	List<Cancion> catalogo = new ArrayList<>();
	catalogo.add(crearCancion("Tu con el", "Frankie Ruiz", "Salsa", 1985));
	catalogo.add(crearCancion("Todas las hojas son del viento", "Luis Alberto Spinetta", "Rock", 1973));
	catalogo.add(crearCancion("Lo artesanal", "Viejas Locas", "Rock", 2004)); // por confirmar
	catalogo.add(crearCancion("Puchos", "Perras on the beach", "Rock", 2016)); // por confirmar
	catalogo.add(crearCancion("Fuego", "Intoxicados", "Rock", 2003)); // por confirmar año
	catalogo.add(crearCancion("Al lado del camino", "Fito Páez", "Rock/Pop", 1999));
	catalogo.add(crearCancion("Diamante roto", "EMUPM", "Rock", 2023)); // por confirmar
	catalogo.add(crearCancion("Para no olvidar", "Los Rodríguez", "Rock", 1993));
	catalogo.add(crearCancion("Aqui murio el payaso", "Santiago Insane", "Rock", 2019)); // por confirmar si es tema o solo álbum
	catalogo.add(crearCancion("Volver a casa de aribag", "Airbag", "Rock", 2011)); // título dado así, probable "Volver a casa"

		for (Cancion c : catalogo) app.agregarCancion(c);

		// 1. Mostrar todas las canciones disponibles
		System.out.println("=== Canciones disponibles ===");
		app.mostrarCanciones();

		// 2. Crear dos usuarios premium y agregarlos
		app.registrarUsuario(new User("premium1", "123"), true);
		app.registrarUsuario(new User("premium2", "123"), true);

		// 3. Crear dos usuarios freemium y agregarlos
		app.registrarUsuario(new User("free1", "123"), false);
		app.registrarUsuario(new User("free2", "123"), false);

		// 4. A cada uno de los 4 usuarios agregar 4 canciones a favoritos (like)
		String[] primeras4 = { catalogo.get(0).getNombre(), catalogo.get(1).getNombre(), catalogo.get(2).getNombre(), catalogo.get(3).getNombre() };

		likearCanciones(app, "premium1", "123", primeras4, catalogo);
		likearCanciones(app, "premium2", "123", primeras4, catalogo);
		likearCanciones(app, "free1", "123", primeras4, catalogo);
		likearCanciones(app, "free2", "123", primeras4, catalogo);

		// 5. Pedir usuario y añadir una quinta canción a sus likes. Luego pedir otra para dislike
		System.out.println("\n=== Buscar usuario para agregar/quitar canciones de Me Gusta ===");
		System.out.print("Nombre de usuario: ");
		String nombreBuscado = sc.nextLine().trim();
		System.out.print("Contraseña: ");
		String passBuscada = sc.nextLine().trim();

		User encontrado = app.buscarUsuario(nombreBuscado, passBuscada);
		if (encontrado != null) {
			System.out.println("\nUsuario encontrado. Canciones disponibles:");
			app.mostrarCanciones();

			System.out.print("\nNombre de la canción para dar LIKE: ");
			String nombreLike = sc.nextLine().trim();
			Cancion cLike = app.seleccionarCancion(nombreLike);
			if (cLike != null) {
				encontrado.like(cLike);
				System.out.println("Se agregó a Me Gusta: " + cLike.getNombre());
			} else {
				System.out.println("No se encontró la canción indicada para LIKE.");
			}

			System.out.print("Nombre de la canción para dar DISLIKE: ");
			String nombreDislike = sc.nextLine().trim();
			Cancion cDislike = app.seleccionarCancion(nombreDislike);
			if (cDislike != null) {
				encontrado.dislike(cDislike);
				System.out.println("Se quitó de Me Gusta: " + cDislike.getNombre());
			} else {
				System.out.println("No se encontró la canción indicada para DISLIKE.");
			}
		} else {
			System.out.println("Usuario no encontrado. Se omite el paso 5.");
		}

		// 6.a Recorrer freemiums y reproducir playlist de me gusta
		System.out.println("\n=== Reproduciendo Me Gusta de FREEMIUM ===");
		for (Freemium f : app.getFreemiums()) {
			System.out.println("-- Usuario: " + f.getUsername());
			f.verLiked(false); // reproduce/imprime su lista de me gusta
		}

		// 6.b Recorrer premiums y reproducir playlist de me gusta
		System.out.println("\n=== Reproduciendo Me Gusta de PREMIUM ===");
		for (Premium p : app.getPremiums()) {
			System.out.println("-- Usuario: " + p.getUsername());
			p.verLiked(false); // reproduce/imprime su lista de me gusta (y suma puntos por reproducción)
		}

		// 7. Para premium2 realizar acciones
		User u2 = app.buscarUsuario("premium2", "123");
		if (u2 instanceof Premium) {
			Premium premium2 = (Premium) u2;
			// 7.a Agregar canciones a descargas
			premium2.descargarCancion(catalogo.get(4));
			premium2.descargarCancion(catalogo.get(5));
			premium2.descargarCancion(catalogo.get(6));

			// 7.b Mostrar canciones descargadas
			System.out.println("\n=== Descargas de premium2 ===");
			premium2.mostrarDescargadas();

			// 7.c Mostrar puntos del usuario (se sumaron al reproducir en 6.b)
			System.out.println("\n=== Puntos de premium2 ===");
			premium2.mostrarPuntuacion();

			// 7.d Mostrar canciones que le gustan (en orden)
			System.out.println("\n=== Me Gusta (ordenadas) de premium2 ===");
			premium2.verLiked(true);
		}

		sc.close();
	}

	private static Cancion crearCancion(String nombre, String autor, String genero, int anio) {
		Cancion c = new Cancion();
		c.setNombre(nombre);
		c.setAutor(autor);
		c.setGenero(genero);
		c.setAnio(anio);
		return c;
	}

	private static void likearCanciones(Spotify app, String username, String password, String[] nombres, List<Cancion> catalogo) {
		User u = app.buscarUsuario(username, password);
		if (u == null) return;
		for (String nombre : nombres) {
			// Como tenemos el catálogo local, podemos buscar por nombre de forma directa
			Cancion c = app.seleccionarCancion(nombre);
			if (c != null) u.like(c);
		}
	}
}
