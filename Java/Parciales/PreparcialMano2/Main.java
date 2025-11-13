public class Main {
    public static void main(String[] args) {
        Region cundinamarca = new Region();

        try{
        cundinamarca.añadirPersonaje("Dragon");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
