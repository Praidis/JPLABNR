package org.example;


public class Limpieza extends Producto {
    private String tipoAplicacion;

    public Limpieza(String id, String descripcion, int cantidad, double precio, double porcentajeGanancia, String tipoAplicacion) {
        super(validarId(id, "AZ"), descripcion, cantidad, precio, porcentajeGanancia);
        this.tipoAplicacion = tipoAplicacion;
    }

    public String getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(String tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }

    public boolean isValidTipoAplicacion() {
        String[] tiposValidos = {"COCINA", "BAÑO", "ROPA", "MULTIUSO"};
        for (String tipo : tiposValidos) {
            if (tipoAplicacion.equals(tipo)) {
                return true;
            }
        }
        return false;
    }

    private static String validarId(String id, String expectedPrefix) {
        if (!id.startsWith(expectedPrefix) || id.length() != 5 || !id.substring(2).matches("\\d{3}")) {
            throw new IllegalArgumentException("El ID debe respetar el formato " + expectedPrefix + "XXX, donde XXX son dígitos.");
        }
        return id;
    }
}
