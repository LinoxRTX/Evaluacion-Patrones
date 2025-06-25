package modelo;

public class ProductoExterno {
    private String codigo;
    private int costo;

    public ProductoExterno(String codigo, int costo) {
        this.codigo = codigo;
        this.costo = costo;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCosto() {
        return costo;
    }
}
