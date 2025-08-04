package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private int mesa;
    private String estado;
    private List<Producto> productos;
    
    public Pedido(int mesa) {
        this.mesa = mesa;
        this.estado = "PENDIENTE";
        this.productos = new ArrayList<>();
    }
    
    public void agregarProducto(Producto producto, int cantidad) {
        this.productos.add(producto);
    }
    

    public int getId() {
        return id;
    }
    
    public int getMesa() {
        return mesa;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }
}
