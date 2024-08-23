package org.example;

public class Envasado extends Producto {
    private String tipoEnvase;
    private boolean importado;

    public Envasado(String id, String descripcion, int cantidad, double precio, double porcentajeGanancia, String tipoEnvase, boolean importado) {
        super(validarId(id, "AB"), descripcion, cantidad, precio, porcentajeGanancia);
        this.tipoEnvase = tipoEnvase;
        this.importado = importado;
    }

    public String getTipoEnvase() {
        return tipoEnvase;
    }

    public void setTipoEnvase(String tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
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

    private static String validarId(String id, String expectedPrefix) {
        if (!id.startsWith(expectedPrefix) || id.length() != 5 || !id.substring(2).matches("\\d{3}")) {
            throw new IllegalArgumentException("El ID debe respetar el formato " + expectedPrefix + "XXX, donde XXX son dígitos.");
        }
        return id;
    }
}
