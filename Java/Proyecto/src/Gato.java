import java.util.GregorianCalendar;
import java.util.Scanner;

public class Gato extends Mascota {
    Scanner sc = new Scanner(System.in);

    private boolean uniasLargas;

    //Setters
    public void setUniasLargas(boolean uniasLargas){this.uniasLargas = uniasLargas;}

    //Getters
    public boolean getUniasLargas(){return uniasLargas;}

    //Constructor
    public Gato(String raza, GregorianCalendar fechaNacimiento, float peso, String nombre, GregorianCalendar ultimaInteraccion,
                boolean uniasLargas){
        super(raza, fechaNacimiento, peso, nombre, ultimaInteraccion);
        this.uniasLargas = uniasLargas;
    }

    //Metodos
    @Override
    public void jugar(int opcion){
        if(opcion == 1){
            System.out.println("Has afilado las unias del gato");
            uniasLargas = true;
        }else{
            System.out.println("Acariciaste al gato");
        }
    }

    public void cortarUnias(){
        System.out.println("Cortaste las unias del gato");
        uniasLargas = false;
    }

}
