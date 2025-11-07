import java.util.ArrayList;

public class Spotify {
    private ArrayList<User> usuarios;
    private ArrayList<Cancion> canciones;

    // Getters
    public ArrayList<User> getUsuarios() {
        return usuarios;
    }

    //Constructor
    public Spotify(){
        usuarios = new ArrayList<>();
        canciones = new ArrayList<>();
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

    

}
