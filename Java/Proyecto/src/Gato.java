import java.util.GregorianCalendar;

public class Gato extends Mascota {
    private static final long serialVersionUID = 1L;

    private boolean uniasLargas;

    // Setters
    public void setUniasLargas(boolean uniasLargas) {
        this.uniasLargas = uniasLargas;
    }

    // Getters
    public boolean getUniasLargas() {
        return uniasLargas;
    }

    // Constructor
    public Gato(String raza, GregorianCalendar fechaNacimiento, float peso, String nombre,
            GregorianCalendar ultimaInteraccion,
            boolean uniasLargas) {
        super(raza, fechaNacimiento, peso, nombre, ultimaInteraccion);
        this.uniasLargas = uniasLargas;
    }

    // Mutates
    @Override
    public void jugar(int opcion) {
        if (opcion == 1) {
            System.out.println("Has afilado las uñas del gato");
            uniasLargas = true;
        } else {
            System.out.println("Acariciaste al gato");
        }
    }

    public void cortarUnias() {
        System.out.println("Cortaste las uñas del gato");
        uniasLargas = false;
    }

    @Override
    public String generarInformacion() throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new Exception("Este no tiene nombresito");
        }
        return "Nombre: " + nombre +
                "- Edad: " + calcularEdad() +
                "- Peso: " + peso +
                "- Raza: " + raza;
    }

}
