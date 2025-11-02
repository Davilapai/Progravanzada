import java.util.ArrayList;

public class Premium extends User{
    private int puntos;
    private ArrayList<Cancion>descargadas;
    
    //Getters y setters
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    //Constructor
    public Premium(String username, String password){
        super(username,password);
        descargadas = new ArrayList<>();
    }

    //Metodos
    public void reproducirCancion(Cancion c) {
        super.reproducirCancion(c); // Llama al método del padre para mantener la funcionalidad base
        puntos += 2; // Suma 2 puntos al usuario premium
    }

    public void mostrarPuntuacion(){
        System.out.println("Usted tiene " + puntos + " puntos.");
    }
    
    public void mostrarDescargadas(){
        super.imprimirLista(descargadas);
    }

    public void descargarCancion(Cancion c){
        descargadas.add(c);
    }
    
}
