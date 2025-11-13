import java.io.Serializable;
import java.util.ArrayList;

public abstract class Personaje implements Serializable{
    private int ataqueBase;
    private int vida;
    private ArrayList<Personaje>objetivos;

    //Constructor
    public Personaje(int ataqueBase, int vida){
        this.ataqueBase = ataqueBase;
        this.vida = vida;
        objetivos = new ArrayList<>();
    }

    //Getters y setters (Solo para visual)
    public int getAtaqueBase() {
        return ataqueBase;
    }


    public void setAtaqueBase(int ataqueBase) {
        this.ataqueBase = ataqueBase;
    }


    public int getVida() {
        return vida;
    }


    public void setVida(int vida) {
        this.vida = vida;
    }


    //Metodos
    public void atacarObjetivos(){
        for(Personaje p : objetivos){
            atacar(p);
            //if(p.getVida() < 0){
            //    objetivos.remove(p);
            //}
        }
        objetivos.removeIf(p -> p.getVida() <=0);
    }

    public abstract void atacar(Personaje objetivo);

    public String toString() {
        return "Ataque base: " + getAtaqueBase() +
            "\nVida: " + getVida();
    }


}
