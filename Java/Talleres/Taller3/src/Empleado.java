public class Empleado {
    private String nombre;
    private long cedula;
    private double salario;
    public static final double SALARIOMIN = 1300000;
    private double bonoEmpleados;
    
    // getters
    public String getNombre() {return nombre;}
    public long getCedula() {return cedula;}
    public double getSalario() {return salario;}

    //setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public void setSalario(double salario) {
        if(salario < SALARIOMIN ){
            this.salario = SALARIOMIN;
        }else{
            this.salario = salario;
        }
    }

    public void setBonoEmpleados(double bonoEmpleados) {
        this.bonoEmpleados = bonoEmpleados;
    }

    //crear constructor
    public Empleado() {
    }
    
    //metodos
    private double subsidioTransporte(){
        if (salario < (2*SALARIOMIN))return 90000;
        return 0;
    }

    private double totalDevengado(){
        return salario + subsidioTransporte();
    }

    private double calcularIBC(){
        return totalDevengado()*0.75;
    }

    private double calcularDescuentoPension(){
        return calcularIBC()*0.03875;
    }

    private double calcularDescuentoSalud(){
        return calcularIBC()*0.045;
    }

    private double calcularDescuentoFS(){
        if(salario<(4*SALARIOMIN)){
            return 0;
        }
        return calcularIBC()*0.01;
    }

    private double calcularDescuentoTotal(){
        return calcularDescuentoPension()+calcularDescuentoSalud()+calcularDescuentoFS();
    }

    public double calcularTotalNominal(){
        return totalDevengado()-calcularDescuentoTotal();
    }

    public double calcularTotalNominal(double regaloCumple){
        return (totalDevengado()-calcularDescuentoTotal()+bonoEmpleados+regaloCumple);
    }

}
