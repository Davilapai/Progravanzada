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
    public void reproducirCancion(Cancion c) {
        super.reproducirCancion(c); // Llama al método del padre para mantener la funcionalidad base
        cancionesPublicidad++;
        if((cancionesPublicidad % 3) == 0)mostrarPublicidad();
    }

    public void mostrarPublicidad(){
        System.out.println("Esto es publicidad de spotify :D");
    }
}
