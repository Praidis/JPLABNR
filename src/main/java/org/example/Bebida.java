package org.example;

public class Bebida extends Producto {
    private double graduacionAlcoholica;
    private boolean importado;

    public Bebida(String id, String descripcion, int cantidad, double precio, double porcentajeGanancia, double graduacionAlcoholica, boolean importado) {
        super(validarId(id, "AC"), descripcion, cantidad, precio, validarPorcentajeGanancia(porcentajeGanancia, 20));
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.importado = importado;
    }

    private static String validarId(String id, String expectedPrefix) {
        if (!id.startsWith(expectedPrefix) || id.length() != 5 || !id.substring(2).matches("\\d{3}")) {
            throw new IllegalArgumentException("El ID debe respetar el formato " + expectedPrefix + "XXX, donde XXX son dígitos.");
        }
        return id;
    }

    private static double validarPorcentajeGanancia(double porcentajeGanancia, double maximo) {
        if (porcentajeGanancia < 0 || porcentajeGanancia > maximo) {
            throw new IllegalArgumentException("El porcentaje de ganancia debe ser entre 0 y " + maximo + "%");
        }
        return porcentajeGanancia;
    }

    public double getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    @Override
    public boolean esImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    @Override
    public double calcularPrecioVenta() {
        double precioVenta = getPrecio();
        double discount = precioVenta * getPorcentajeGanancia() / 100;
        precioVenta -= discount; // Apply discount
        if (esImportado()) {
            precioVenta *= 1.12; // Add 12% tax for imported products
        }
        return precioVenta;
    }

    // Método para calcular calorías
    public double calcularCalorias() {
        if (graduacionAlcoholica <= 2) {
            return graduacionAlcoholica;
        } else if (graduacionAlcoholica <= 4.5) {
            return graduacionAlcoholica * 1.25;
        } else {
            return graduacionAlcoholica * 1.5;
        }
    }
}
