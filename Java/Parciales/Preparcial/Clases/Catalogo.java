package Clases;

import java.util.ArrayList;

public class Catalogo {
    ArrayList<Robot> robots;

    public Catalogo(){
        robots = new ArrayList<>();
    }

    //Metodos
    public Robot buscaRobot(String codigo){
        for(Robot r : robots){
            if(r.getCodigo().equals(codigo)){
                return r;
            }
        }
        return null;
    }

    public void agregarRobot(String codigo, double pesoMaximo) throws Exception{
        if(buscaRobot(codigo)!= null){
            throw new Exception("El robot ya existe");
        }
        Robot nuevito = new Robot(codigo, pesoMaximo);
        robots.add(nuevito);
        nuevito.setDuenio(this);
    }

    public void agregarComponente(String codigoR, int id, String nombre, double peso){
        try{
            Robot pepito = buscaRobot(codigoR);
            pepito.agregarComponente(id, nombre, peso);
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void eliminarRobot(String codigo) throws Exception{
        if(buscaRobot(codigo)== null) throw new Exception("El robot no existe :c");
        robots.remove(buscaRobot(codigo));
    }

    public void listaComponentesRobots(){
        
    }
}
