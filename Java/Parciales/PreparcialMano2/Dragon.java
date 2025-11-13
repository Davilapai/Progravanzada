public class Dragon extends Personaje {
    private int danioFuego;

    public int getDanioFuego(){return danioFuego;}

    //Constructor
    public Dragon(int ataqueBase, int vida, int danioFuego){
        super(ataqueBase, vida);
        this.danioFuego = danioFuego;
    }

    @Override
    public void atacar(Personaje objetivo){
        objetivo.setVida(objetivo.getVida()-danioFuego-this.getAtaqueBase());
    }

    @Override
    public String toString() {
        return "Tipo de personaje: Dragon\n" + super.toString()
        + "\nAtaque adicional: " + danioFuego;
    }

}
