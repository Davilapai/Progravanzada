	import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String raza;
    protected GregorianCalendar fechaNacimiento;
    protected float peso;
    protected String nombre;
    protected GregorianCalendar fechaAdopcion;
    protected GregorianCalendar ultimaInteraccion;
    protected Persona duenio;

    //Getters
    public String getRaza() {return raza;}
    public GregorianCalendar getFechaNacimiento() {return fechaNacimiento;}
    public float getPeso() {return peso;}
    public String getNombre() {return nombre;}
    public GregorianCalendar getFechaAdopcion() {return fechaAdopcion;}
    public GregorianCalendar getUltimaInteraccion() {return ultimaInteraccion;}
    public Persona getDuenio() {return duenio;}

    //Setters
    public void setRaza(String raza) {this.raza = raza;}
    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}
    public void setPeso(float peso) {this.peso = peso;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setFechaAdopcion(GregorianCalendar fechaAdopcion) {this.fechaAdopcion = fechaAdopcion;}
    public void setUltimaInteraccion(GregorianCalendar ultimaInteraccion) {this.ultimaInteraccion = ultimaInteraccion;}
    public void setDuenio(Persona duenio) {this.duenio = duenio;}
    

    //Constructor
    public Mascota(String raza, GregorianCalendar fechaNacimiento, float peso, String nombre, GregorianCalendar ultimaInteraccion) {
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.nombre = nombre;
        this.ultimaInteraccion = ultimaInteraccion;
    }

    //Metodos
    public int calcularEdad(){
        //Para sacar la fecha de hoy
        Calendar hoy = Calendar.getInstance();

        int años = hoy.get(Calendar.YEAR)-fechaNacimiento.get(Calendar.YEAR);
        int meses = hoy.get(Calendar.MONTH)-fechaNacimiento.get(Calendar.MONTH);
        int dias = hoy.get(Calendar.DAY_OF_MONTH)-fechaNacimiento.get(Calendar.DAY_OF_MONTH);

        //Al hacer la resta por ejemplo si el mes de hoy es 7 y el de la fecha de nacimiento es 8 va a dar un resultado negativo, pero en años se va a mantener igual.
        //Esto significa que aun no cumple años, por lo tanto restamos el año que se va extra y sumamos 12 meses para eliminar el mes negativo, teniendo asi que tiene 11 meses.
        //Lo mismo para los dias. 

        if (dias < 0) {
            dias += 30; // Aproximación
            meses--;
        }

        if (meses < 0) {
            años--;
            meses += 12;
        }
        

        //Esto es para q todo quede en años
        double edad = años + (meses / 12.0) + (dias / 365.0);

        return (int)edad;
    }

    public abstract void jugar(int opcion);
    public abstract String generarInformacion()throws Exception;
}
