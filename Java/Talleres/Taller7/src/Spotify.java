import java.io.*;
import java.util.ArrayList;

public class Spotify implements Serializable {
    private ArrayList<User> usuarios;
    private ArrayList<Cancion> canciones;
    private static final String ARCHIVO_USUARIOS = "usuarios.dat";
    private static final String ARCHIVO_CANCIONES = "canciones.dat";

    // Getters
    public ArrayList<User> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    //Constructor
    public Spotify(){
        cargarUsuarios(); // Cargar usuarios existentes al inicializar
        cargarCanciones(); // Cargar canciones existentes al inicializar
    }

    //Metodos
    public void registrarUsuario(User usuario) {
        usuarios.add(usuario);
    }

    public void agregarCancion(Cancion c) {
        if (c != null) canciones.add(c);
    }

    public User buscarUsuario(String nombre, String password){
        for(User u : usuarios){
            if(u.getUsername().equals(nombre) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }

    public void mostrarCanciones(){
        for(Cancion c:canciones){
            System.out.println("Cancion: " + c.getNombre());
            System.out.println("Autor : " + c.getAutor());
            System.out.println("Genero: " + c.getGenero());
            System.out.println("Anio: " + c.getAnio()); 
            System.out.println("--------------");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Cancion seleccionarCancion(String nombre){
        for(Cancion c:canciones){
            if(c.getNombre().equals(nombre)) return c;
        }
        return null;
    }

    // Métodos para persistencia de datos
    public void guardarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usuarios);
            System.out.println("Usuarios guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarUsuarios() {
        File archivo = new File(ARCHIVO_USUARIOS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
                usuarios = (ArrayList<User>) ois.readObject();
                System.out.println("Usuarios cargados exitosamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar usuarios: " + e.getMessage());
                usuarios = new ArrayList<>();
            }
        } else {
            System.out.println("No se encontró archivo de usuarios previo. Iniciando con lista vacía.");
            usuarios = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarCanciones() {
        File archivo = new File(ARCHIVO_CANCIONES);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_CANCIONES))) {
                canciones = (ArrayList<Cancion>) ois.readObject();
                System.out.println("Canciones cargadas exitosamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar canciones: " + e.getMessage());
                canciones = new ArrayList<>();
            }
        } else {
            System.out.println("No se encontró archivo de canciones previo. Iniciando con lista vacía.");
            canciones = new ArrayList<>();
        }
    }
}
