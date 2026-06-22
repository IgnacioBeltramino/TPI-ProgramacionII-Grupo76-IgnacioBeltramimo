package clases;

import java.util.ArrayList;
import java.util.List;

public class Categoria extends Base {
    private String nombre;
    private String descripcion;
    private List<Producto> productos = new ArrayList<Producto>();


    public Categoria (String nombre, String descripcion) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    // getters 
    public String getNombre (){
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }


    // setters
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void setDescripcion (String nuevaDesc) {
        this.descripcion = nuevaDesc;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // metodos 
    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }



    @Override
    public String toString() {
        return "Categoria [id=" + getId() + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }

}
