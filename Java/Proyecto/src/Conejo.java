import java.util.GregorianCalendar;

public class Conejo extends Mascota{
	private static final long serialVersionUID = 1L;
	private double alturaSalto;
	
	//Constructor
	public Conejo(String raza, GregorianCalendar fechaNacimiento, float peso, String nombre, GregorianCalendar ultimaInteraccion, double alturaSalto){
		super(raza, fechaNacimiento, peso, nombre, ultimaInteraccion);
		this.alturaSalto = alturaSalto;
	}
	
	public void jugar(int opcion){
    }
	
	@Override
	public String generarInformacion() throws Exception{
		if(nombre == null || nombre.isBlank()) {
			throw new Exception("Este no tiene nombresito");
		}
		return "Nombre: " + nombre +
				"- Edad: " + calcularEdad()+
				"- Peso: " + peso + 
				"- Raza: " + raza + 
				"- AlturaSalto: " + alturaSalto;
	}

}
