package Clases;

import java.util.ArrayList;

public class Robot {
    private String codigo;
    private double pesoMaximo;
    private ArrayList<Componente> componentes;
    private Catalogo duenio;

    // Getters
    public String getCodigo(){return codigo;}
    public double getPesoMaximo(){return pesoMaximo;}
    public Catalogo getDuenio(){return duenio;}

    // Setters 
    public void setCodigo(String codigo){this.codigo = codigo;}
    public void setPesoMaximo(double pesoMaximo){this.pesoMaximo = pesoMaximo;}
    public void setDuenio(Catalogo duenio){this.duenio = duenio;}

    //Constructor por parametros
    public Robot(String codigo, double pesoMaximo){
        this.codigo = codigo;
        this.pesoMaximo = pesoMaximo;
        componentes = new ArrayList<>();
    }

    // Metodos

    public double sumatoriaComp(){
        double sumatoria = 0;

        for(Componente c : componentes){
            sumatoria += c.getPeso();
        }

        return sumatoria;
    }

    public void agregarComponente(int id, String nombre, double peso) throws Exception{
        Componente nuevito = new Componente(id, nombre, peso);

        componentes.add(nuevito);

        if(sumatoriaComp()>pesoMaximo) {
            componentes.remove(nuevito);
            throw new Exception("El peso robot ha excedido su peso maximo");
        }

        nuevito.setDuenio(this);
    }
}