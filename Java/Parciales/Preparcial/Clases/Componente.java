package Clases;

public class Componente {
    private int id;
    private String nombre;
    private double peso;
    private Robot duenio;
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public double getPeso() {return peso;}
    public void setPeso(double peso) {this.peso = peso;}

    public Robot getDuenio(){return duenio;}
    public void setDuenio(Robot duenio){this.duenio = duenio;}
    
    public Componente(int id, String nombre, double peso) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
    }
    
    

    
    
}
