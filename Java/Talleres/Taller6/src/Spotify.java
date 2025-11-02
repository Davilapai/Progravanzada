import java.util.ArrayList;

public class Spotify {
    private ArrayList<Freemium> listaFreemiums;
    private ArrayList<Premium> listaPremiums;
    private ArrayList<Cancion> canciones;

    // Getters
    public ArrayList<Freemium> getFreemiums() {
        return listaFreemiums;
    }

    public ArrayList<Premium> getPremiums() {
        return listaPremiums;
    }

    //Constructor
    public Spotify(){
        listaFreemiums = new ArrayList<>();
        listaPremiums = new ArrayList<>();
        canciones = new ArrayList<>();
    }

    //Metodos
    public void registrarUsuario(User usuario, boolean premium) {
        if (premium) {
            Premium nuevoPremium = new Premium(usuario.getUsername(), usuario.getPassword());
            listaPremiums.add(nuevoPremium);
        } else {
            Freemium nuevoFreemium = new Freemium(usuario.getUsername(), usuario.getPassword());
            listaFreemiums.add(nuevoFreemium);
        }
    }

    public void agregarCancion(Cancion c) {
        if (c != null) canciones.add(c);
    }

    public User buscarUsuario(String nombre, String password){
        for(Freemium f:listaFreemiums){
            if((f.getUsername().equals(nombre) && f.getPassword().equals(password))){
               return f;
            }
        }

        for(Premium p:listaPremiums){
            if((p.getUsername().equals(nombre) && p.getPassword().equals(password))){
            return p;
            }
        }
        return null;
    }

    public void mostrarCanciones(){
        User user = new User(null, null);
        user.imprimirLista(canciones);
    }

    public Cancion seleccionarCancion(String nombre){
        for(Cancion c:canciones){
            if(c.getNombre().equals(nombre)) return c;
        }
        return null;
    }

    

}
