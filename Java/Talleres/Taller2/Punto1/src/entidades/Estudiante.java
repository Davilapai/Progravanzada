package entidades;

public class Estudiante {
	private String nombre;
	private int numero;
	private double promedio;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public double getPromedio() {
		return promedio;
	}
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}
	
	public Estudiante(String nombre, int numero, double promedio) {
		this.nombre = nombre;
		this.numero = numero;
		this.promedio = promedio;
	}
	
	public Estudiante() { //constructor vacio
	}
	
	public void actualizarPromedio(double semestre) {
		if(semestre < 0 || 5 < semestre) {
			System.out.println("Error");
			return; 
		}
		promedio = ((promedio+semestre)/2);
	}
	
	public boolean definirPruebaAcademica() {
		if(promedio < 3.2) {
			return true;
		}
		return false;
	}
	
	
	

	
	
	
	
}
