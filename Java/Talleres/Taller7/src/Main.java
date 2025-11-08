import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. Crear una instancia de Spotify
        Spotify app = new Spotify();

        // Las canciones se cargan automáticamente desde el archivo binario
        System.out.println("Canciones disponibles: " + app.getCanciones().size());

        // 2. Inicio del programa con bucle principal
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n\tBIENVENIDO A SPOTIFY");
            System.out.println("\t1. Registrarse");
            System.out.println("\t2. Ingresar");
            System.out.println("\t3. Guardar y Salir");
            System.out.print("Su opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrar(app);
                    break;
                case 2:
                    ingresar(app);
                    break;
                case 3:
                    app.guardarUsuarios();
                    System.out.println("¡Hasta pronto!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    public static void registrar(Spotify app) {
        System.out.println("\tBienvenido al registro");
        System.out.print("Ingrese su nombre de usuario: ");
        String nUsuario = sc.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = sc.nextLine();

        System.out.print("¿Desea ser premium? (1 = Sí / 2 = No): ");
        int decision = sc.nextInt();
        sc.nextLine();

		User nuevo;

        if (decision == 1) {
            nuevo = new Premium(nUsuario, password);
            System.out.println("Usuario premium creado: " + nUsuario);
			app.getUsuarios().add(nuevo);
        } else {
            nuevo = new Freemium(nUsuario, password);
            System.out.println("Usuario freemium creado: " + nUsuario);
			app.getUsuarios().add(nuevo);
        }

		if(nuevo instanceof Premium){
			menuPre(nuevo, app);
		}else{
			menuFree(nuevo, app);
		}

    }

	public static void ingresar(Spotify app){
		System.out.println("\tBienvenido al login");
        System.out.print("Ingrese su nombre de usuario: ");
        String nUsuario = sc.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = sc.nextLine();

		User usuario = app.buscarUsuario(nUsuario, password);

		if(usuario != null){
			if(usuario instanceof Premium){
				menuPre(usuario, app);
			}else{
				menuFree(usuario, app);
			}
		}else{
			System.out.println("El usuario no existe");
		}


	}

	public static void menuFree(User usuario, Spotify app){
		boolean continuar = true;
		while (continuar) {
			System.out.println("\nMenu Usuario Freemium");
			System.out.println("\t1. Dar like a cancion");
			System.out.println("\t2. Dar dislike a cancion");
			System.out.println("\t3. Reproducir canciones favoritas");
			System.out.println("\t0. Salir");
			System.out.print("Su opción: ");

			int opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
				case 1:
					opcion1(app, usuario);
					break;
				case 2:
					opcion2(app, usuario);
					break;
				case 3:
					opcion3(app, usuario);
					break;
				case 0:
					System.out.println("Regresando al menú principal...");
					continuar = false;
					break;
				default:
					System.out.println("Opción no válida. Intente de nuevo.");
					break;
			}
		}
	}

	public static void menuPre(User usuario, Spotify app){
		boolean continuar = true;
		while (continuar) {
			System.out.println("\nMenu Usuario Premium");
			System.out.println("\t1. Dar like a cancion");
			System.out.println("\t2. Dar dislike a cancion");
			System.out.println("\t3. Reproducir canciones favoritas");
			System.out.println("\t4. Descargar cancion");
			System.out.println("\t5. Mostrar canciones descargadas");
			System.out.println("\t0. Salir");
			System.out.print("Su opción: ");

			int opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
				case 1:
					opcion1(app, usuario);
					break;
				case 2:
					opcion2(app, usuario);
					break;
				case 3:
					opcion3(app, usuario);
					break;
				case 4:
					opcion4(app, usuario);
					break;
				case 5:
					opcion5(app, usuario);
					break;
				case 0:
					System.out.println("Regresando al menú principal...");
					continuar = false;
					break;
				default:
					System.out.println("Opción no válida. Intente de nuevo.");
					break;
			}
		}
	}

	public static void opcion1(Spotify app, User usuario) {
		app.mostrarCanciones();
		System.out.print("Escribe el nombre de la cancion a likear: ");
		String nBuscado = sc.nextLine();

		Cancion buscada = app.seleccionarCancion(nBuscado);

		if(buscada !=null){
			usuario.like(buscada);
		}else{
			System.out.println("La cancion con ese nombre no existe");
		}
	}

	public static void opcion2(Spotify app, User usuario){
		app.mostrarCanciones();
		System.out.println("Escribe el nombre de la cancion a dislikear: ");
		String nBuscado = sc.nextLine();

		Cancion buscada = app.seleccionarCancion(nBuscado);

		if(buscada !=null){
			usuario.dislike(buscada);
		}else{
			System.out.println("La cancion con ese nombre no existe");
		}
	}

	public static void opcion3(Spotify app, User usuario){
		for(Cancion c : usuario.getLiked()){
			usuario.reproducirCancion(c);
		}
	}

	public static void opcion4(Spotify app, User usuario){
		app.mostrarCanciones();
		System.out.println("Escribe el nombre de la cancion a descargar: ");
		String nBuscado = sc.nextLine();

		Cancion buscada = app.seleccionarCancion(nBuscado);

		if(buscada !=null){
			((Premium)usuario).descargarCancion(buscada);
		}else{
			System.out.println("La cancion con ese nombre no existe");
		}
	}

	public static void opcion5(Spotify app, User usuario){
		((Premium)usuario).mostrarDescargadas();
	}
}
