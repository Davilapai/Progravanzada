public class Freemium extends User{
    private int cancionesPublicidad;

    //Getters
    public int getCancionesPublicidad(){return cancionesPublicidad;}

    //Setters
    public void setCancionesPublicidad(int cancionesPublicidad){this.cancionesPublicidad = cancionesPublicidad;}

    //Constructor
    public Freemium(String username, String password){
        super(username,password);
    }

    //Metodos
    @Override
    public void reproducirCancion(Cancion c) {
        System.out.println("Reproduciendo la cancion " + c.getNombre());
        System.out.println("Autor : " + c.getAutor());
        System.out.println("Genero: " + c.getGenero());
        System.out.println("Anio: " + c.getAnio()); // Llama al método del padre para mantener la funcionalidad base
        cancionesPublicidad++;
        if((cancionesPublicidad % 3) == 0)mostrarPublicidad();
    }

    public void mostrarPublicidad(){
        System.out.println("Esto es publicidad de spotify :D");
    }
}
