public class Soldado extends Personaje {
    private Arma arma;
    
    //Constructor
    public Soldado(int ataqueBase, int vida, int ataque, int durabilidad){
        super(ataqueBase, vida);
        this.arma = new Arma(ataque, durabilidad);
    }

    @Override
    public void atacar(Personaje objetivo){
        objetivo.setVida(objetivo.getVida()-this.getAtaqueBase()-arma.getAtaque());
        arma.setDurabilidad(arma.getDurabilidad()-1);
    }
    
    @Override
    public String toString() {
        return "Tipo de personaje: Soldado\n" +
            super.toString() +
            "\nAtaque adicional (arma): " + arma.getAtaque() + "/nDurabilidad del arma: " + arma.getDurabilidad();
    }

    String descripcion = toString();

}