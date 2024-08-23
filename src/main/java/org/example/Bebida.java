package org.example;

public class Bebida extends Producto {
    private double graduacionAlcoholica;
    private boolean importado;

    public Bebida(String id, String descripcion, int cantidad, double precio, double porcentajeGanancia, double graduacionAlcoholica, boolean importado) {
        super(validarId(id, "AC"), descripcion, cantidad, precio, porcentajeGanancia);
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.importado = importado;
    }

    public double getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    public boolean isImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    @Override
    public boolean esImportado() {
        return importado;
    }

    public double calcularCalorias() {
        if (graduacionAlcoholica <= 2) {
            return graduacionAlcoholica;
        } else if (graduacionAlcoholica <= 4.5) {
            return graduacionAlcoholica * 1.25;
        } else {
            return graduacionAlcoholica * 1.5;
        }
    }

    private static String validarId(String id, String expectedPrefix) {
        if (!id.startsWith(expectedPrefix) || id.length() != 5 || !id.substring(2).matches("\\d{3}")) {
            throw new IllegalArgumentException("El ID debe respetar el formato " + expectedPrefix + "XXX, donde XXX son dígitos.");
        }
        return id;
    }
}
