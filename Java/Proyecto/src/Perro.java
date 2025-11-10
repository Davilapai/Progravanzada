import java.util.GregorianCalendar;
import java.util.Scanner;

public class Perro extends Mascota{
    Scanner sc = new Scanner(System.in);

    private GregorianCalendar fechaBanio;
    private boolean limpio;

    //Setters
    public void setFechaBanio(GregorianCalendar fechaBanio){this.fechaBanio = fechaBanio;}
    public void setLimpio(boolean limpio){this.limpio = limpio;}

    //Getters 
    public GregorianCalendar getFechaBanio(){return fechaBanio;}
    public boolean getLimpio(){return limpio;}

    //Constructor
    public Perro(String raza, GregorianCalendar fechaNacimiento, float peso, String nombre, GregorianCalendar ultimaInteraccion,
                GregorianCalendar fechaBanio, boolean limpio){
        super(raza, fechaNacimiento, peso, nombre, ultimaInteraccion);
        this.fechaBanio = fechaBanio;
        this.limpio = limpio;
    }

    //Metodos
    @Override
    public void jugar(int opcion){
        if(opcion == 1){
            System.out.println("El perro fue a buscar la pelota");
            limpio = false;
        }else if (opcion == 2){
            System.out.println("Saliste al parque con el perro");
            limpio = false;
        }else{
            System.out.println("El perro jugó con otros perros");
            limpio = false;
        }
    }

    public void baniar(){
        System.out.println("Baniaste al perro");
        fechaBanio = new GregorianCalendar();
        limpio = true;
    }
}
