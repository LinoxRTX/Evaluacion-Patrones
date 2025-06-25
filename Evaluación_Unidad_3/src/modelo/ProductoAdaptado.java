package modelo;

// Adapter: adapta un ProductoExterno (no compatible con nuestro sistema)
// para que pueda ser tratado como un Producto estándar dentro del almacén.
// Permite integrar productos de sistemas externos sin modificar su código original.
public class ProductoAdaptado extends Producto {
    public ProductoAdaptado(ProductoExterno externo, String categoria) {
        // Se llama al constructor de Producto con datos adaptados del ProductoExterno
        super(externo.getCodigo(), categoria, externo.getCosto());
    }
}
