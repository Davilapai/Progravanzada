public class ConjuntoDatosTabular extends ConjuntoDatos{
    private int nColumnas;
    private int nFilas;

    //Constructor
    public ConjuntoDatosTabular(String nombre, int tamanio, int nColumnas, int nFilas){
        super(nombre, tamanio);
        this.nColumnas = nColumnas;
        this.nFilas = nFilas;
    }

    //Getters
    public int getNFilas(){return nFilas;}
    public int getNColumnas(){return nColumnas;}

    //Setters
    public void setNFilas(int nFilas){this.nFilas = nFilas;}
    public void setNColumnas(int nColumnas){this.nColumnas = nColumnas;}

    //Metodos
    public String describir(){
        return getNombre()+getTamanio()+nColumnas+nFilas;
    }
}