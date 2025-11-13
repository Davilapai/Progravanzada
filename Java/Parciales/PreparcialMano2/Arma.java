public class Arma {
    private int ataque;
    private int durabilidad;

    //Getters y setters solo para vscode
    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(int durabilidad) {
        this.durabilidad = durabilidad;
    }

    //Constructor
    public Arma(int ataque, int durabilidad){
        this.ataque = ataque;
        this.durabilidad = durabilidad;
    }
    



}
