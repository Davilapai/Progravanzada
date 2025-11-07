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
    }

    public void agregarComponente(String codigoR, int id, String nombre, double peso) throws Exception {
        Robot pepito = buscaRobot(codigoR);
        pepito.agregarComponente(id, nombre, peso);
    }


    public void eliminarRobot(String codigo) throws Exception{
        if(buscaRobot(codigo) == null) throw new Exception("El robot no existe :c");
        robots.remove(buscaRobot(codigo));
    }

    public ArrayList<Componente> listaComponentesRobots() {
        ArrayList<Componente> lista = new ArrayList<>();

        for (Robot r : robots) {
            for (Componente c : r.getComponentes()) {
                boolean repetido = false;

                for (Componente existe : lista) {
                    if (existe.getNombre().equals(c.getNombre())) {
                        repetido = true;
                        break;
                    }
                }

                if (!repetido) {
                    lista.add(c);
                }
            }
        }
        return lista;
    }
}
