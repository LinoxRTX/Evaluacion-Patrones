package modelo;

// Prototype
// Esta clase implementa el patrón Prototype para permitir clonar objetos Producto.
// Esto facilita crear nuevos productos duplicando uno existente sin usar constructores explícitos.
public class Producto implements Cloneable {
    protected String nombre;
    protected String categoria;
    protected int precio;

    public Producto(String nombre, String categoria, int precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    // Método que clona el objeto actual
    public Producto clonar() {
        try {
            // Se utiliza el método clone() de Object para hacer una copia superficial
            return (Producto) this.clone();
        } catch (CloneNotSupportedException e) {
            // En caso de error, se crea un nuevo objeto manualmente
            return new Producto(this.nombre, this.categoria, this.precio);
        }
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getPrecio() {
        return precio;
    }

    // Muestra la información del producto en consola
    public void mostrar() {
        System.out.println(nombre + " | " + categoria + " | $" + precio);
    }
}
