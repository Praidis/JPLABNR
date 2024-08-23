package org.example;

public class Envasado extends Producto {
    private String tipoEnvase;
    private boolean importado;

    public Envasado(String id, String descripcion, int cantidad, double precio, double porcentajeGanancia, String tipoEnvase, boolean importado) {
        super(validarId(id, "AB"), descripcion, cantidad, precio, validarPorcentajeGanancia(porcentajeGanancia, 15));
        this.tipoEnvase = tipoEnvase;
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

    public String getTipoEnvase() {
        return tipoEnvase;
    }

    public void setTipoEnvase(String tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
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
}
