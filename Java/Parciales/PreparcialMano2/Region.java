import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Region implements Serializable{
    Scanner sc = new Scanner(System.in);

    private String capital;
    private int recursos;
    private ArrayList <Personaje> ejercito;

    //Metodos
    public void añadirPersonaje(String tipo) throws Exception {
        Personaje p;
        int costo = tipo.equals("Dragon") ? 10 : 5;
        if (recursos < costo) throw new Exception("No money");
        recursos -= costo;

        int atkBase = sc.nextInt(), vida = sc.nextInt();
        if (tipo.equals("Dragon")) {
            int fuego = sc.nextInt();
            p = new Dragon(atkBase, vida, fuego);
        } else {
            int atk = sc.nextInt(), dur = sc.nextInt();
            p = new Soldado(atkBase, vida, atk, dur);
        }
        ejercito.add(p);
    }

    public void añadirPersonajeJuls(String tipoPersonaje) throws Exception{

		int costo=0;
		Personaje nuevoPersonaje=null;
		String tipoMinusculas= tipoPersonaje.toLowerCase().replace("ó", "o");
	
		if(tipoMinusculas.equals("soldado")){
			costo=5;
			if(recursos<costo){
				throw new Exception("No hay recursos suficientes");
			}
			
			nuevoPersonaje= new Soldado(12,12,12,12);
	
		}else if (tipoMinusculas.equals("dragon")){
			costo=10;
			if(recursos<costo){
				throw new Exception("No hay recursos suficientes");
			}
			
			nuevoPersonaje = new Dragon(12,12,12);
		}
		recursos-=costo;
		ejercito.add(nuevoPersonaje);

	}


    public int cantidadDragonesPoderosos(){
        int cantidad = 0;

        for(Personaje p: ejercito){
            if(p instanceof Dragon && ((Dragon)p).getDanioFuego()>50){
                cantidad++;
            }
        }
        return cantidad;
    }

    public void guardarPersonajesBinario(String archivo){
        try{
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(archivo));
            file.writeObject(ejercito);
            file.close();
        }catch(Exception e){
            e.getMessage();
        }
    }

    public void guardarPersonajesTexto(String archivo){
        try{
            BufferedWriter file = new BufferedWriter(new FileWriter(archivo));
            for(Personaje p: ejercito){
                file.write(p.toString());
                file.newLine();
            }
            file.close();
        }catch(Exception e){
        }
    }

}
