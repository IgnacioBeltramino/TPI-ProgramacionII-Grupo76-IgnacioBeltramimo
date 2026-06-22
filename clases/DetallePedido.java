package clases;


public class DetallePedido extends Base{

    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido ( int cantidad, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = calcularSubtotal();
    }


    // getters
    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    // setters
    public void setCantidad(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
        this.subtotal = calcularSubtotal();
    }

    public void setSubtotal(double nuevoSubtotal) {
        this.subtotal = calcularSubtotal();
    }

    public void setProducto(Producto nuevoProducto) {
        this.producto = nuevoProducto;
        this.subtotal = calcularSubtotal();
    }

    // metodos de la clase
    private double calcularSubtotal() {
        if (producto == null) {
            return 0.0;
        } else {
            return this.cantidad * producto.getPrecio();
        }
    }

    @Override
    public String toString() {
        return "DetallePedido #" + getId() + ": " + producto.getNombre() + " x " + cantidad + " => Subtotal: $" + subtotal;
    }

}