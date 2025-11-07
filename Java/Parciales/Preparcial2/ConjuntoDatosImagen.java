public class ConjuntoDatosImagen extends ConjuntoDatos{
    private int dpi;

    //Constructor
    public ConjuntoDatosImagen(String nombre, int tamanio, int dpi){
        super(nombre, tamanio);
        this.dpi = dpi;
    }

    //Setters
    public void setDpi(int dpi){this.dpi = dpi;}

    //Getters
    public int getDpi(){return dpi;}

    //Metodos
    @Override
    public String describir(){
        // Usamos los getters de la clase base para obtener nombre y tamaño
        return "Conjunto de imágenes '" + getNombre() + "' de tamaño " + getTamanio() + " y DPI " + dpi + ".";
    }
}
