public class Persona {
    private String nombre;
    private int edad;
    private int id;
    private char genero; // 'H' o 'M'
    private double peso; // en kg
    private double altura; // en metros
    private double salario; // en pesos

    //Metodos constructores
    public Persona() { //este el vacio
    }

    public Persona(String nombre, int edad, char genero) { // el q tiene nombre edad y sexo
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public Persona(String nombre, int edad, int id, char genero, double peso, double altura, double salario) { //constructor
        this.nombre = nombre;
        this.edad = edad;
        this.id = id;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
        this.salario = salario;
    }

    //Metodos
    public double calcularIMC(){
        return peso/(altura*altura);
    }

    public boolean esMayorEdad(){
        return 18 <= edad;
    }
    
    public float salarioMinimo(){
        return (float)(salario/1423500);
    }
    public void imprimirInfoIMC() {
        double imc = calcularIMC();
        String categoriaIMC = "";

        if (imc < 18.5) {
            categoriaIMC = "Bajo peso";
        } else if (imc >= 18.5 && imc <= 24.9) {
            // IMC saludable, no mostrar
            return;
        } else if (imc >= 25 && imc <= 29.9) {
            categoriaIMC = "Sobrepeso";
        } else if (imc >= 30) {
            categoriaIMC = "Obesidad";
        }

        // Imprimir información
        System.out.println("Nombre: " + nombre);
        System.out.println("Cédula: " + id);
        System.out.println("Sexo: " + genero);
        System.out.println("Mayor de edad: " + (esMayorEdad()?"SI":"NO"));
        System.out.println("IMC: " + String.format("%.2f", imc) + " (" + categoriaIMC + ")");
        System.out.println("Salarios mínimos: " + String.format("%.1f", salarioMinimo()));
        System.out.println("--------------------------");
    }


    //Getters y setters
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public char getGenero() {return genero;}
    public void setGenero(char genero) {this.genero = genero;}

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
}
