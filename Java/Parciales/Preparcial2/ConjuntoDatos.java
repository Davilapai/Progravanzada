public abstract class ConjuntoDatos{
    protected String nombre;
    protected int tamanio;

    //Construtor
    public ConjuntoDatos(String nombre, int tamanio){
        this.nombre = nombre;
        this.tamanio = tamanio;
    }

    //Getters
    public String getNombre(){return nombre;}
    public int getTamanio(){return tamanio;}

    //Setters
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setTamanio(int tamanio){this.tamanio = tamanio;}

    //Metodos
    public abstract String describir();
}