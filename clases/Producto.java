package clases;

public class Producto extends Base{
    
        private String nombre;
        private double precio;
        private String descripcion;
        private int stock;
        private String imagen;
        private boolean disponible;
        private Categoria categoria;


    public Producto (String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria) {
        super();
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
    }


    // getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    // setters
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void setPrecio(double nuevoPrecio) {
        this.precio = nuevoPrecio;
    }

    public void setDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
    }

    public void setStock(int nuevoStock) {
        this.stock = nuevoStock;
    }

    public void setImagen(String nuevaImagen) {
        this.imagen = nuevaImagen;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setCategoria(Categoria nuevaCategoria) {
        this.categoria = nuevaCategoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        return getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Producto [id=" + getId() + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + ", disponible=" + disponible + ", categoria=" + categoria.getNombre() + "]";
    }

}