import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
    private String username;
    private String password;
    private ArrayList <Cancion> liked;

    //Getters
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public ArrayList<Cancion> getLiked(){return this.liked;}

    //Setters 
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}

    //Constructor
    public User(String username, String password){
        this.username = username;
        this.password = password;
        liked = new ArrayList<>();
    }

    //Metodos
    public abstract void reproducirCancion(Cancion c);

    public void reproducirPlaylist(ArrayList<Cancion> canciones){
        for(Cancion c:canciones){
            reproducirCancion(c);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void imprimirLista(ArrayList<Cancion> canciones){
        for(Cancion c:canciones){
            System.out.println("Cancion: " + c.getNombre());
            System.out.println("Autor : " + c.getAutor());
            System.out.println("Genero: " + c.getGenero());
            System.out.println("Anio: " + c.getAnio()); 
            System.out.println("--------------");
        }
    }

    public void verLiked(boolean valido){
        ArrayList<Cancion>temp = liked;
        
        if(valido){
            temp.sort((a,b)-> a.getNombre().compareTo(b.getNombre()));
        }

        imprimirLista(temp);
    }

    public void like(Cancion c){
        liked.add(c);
    }

    public void dislike(Cancion c){
        liked.remove(c);
    }
}
