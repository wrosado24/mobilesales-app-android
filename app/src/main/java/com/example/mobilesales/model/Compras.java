package com.example.mobilesales.model;

public class Compras {

    private Integer id;

    private Integer id_cliente;

    private String nombre_cliente;

    private String nombre_producto;

    private String marca_producto;

    private Double unidades;

    private Double precio;

    private Double total;

    private String fecha_compra;

    public Compras(){

    }

    public Compras(Integer id_cliente, String nombre_cliente, String nombre_producto, String marca_producto, Double unidades, Double precio, Double total) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.nombre_producto = nombre_producto;
        this.marca_producto = marca_producto;
        this.unidades = unidades;
        this.precio = precio;
        this.total = total;
    }

    public Compras(Integer id_cliente, String nombre_cliente, String nombre_producto, String marca_producto, Double unidades, Double precio, Double total, String fecha_compra) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.nombre_producto = nombre_producto;
        this.marca_producto = marca_producto;
        this.unidades = unidades;
        this.precio = precio;
        this.total = total;
        this.fecha_compra = fecha_compra;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }
    public String getNombre_cliente() {
        return nombre_cliente;
    }
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }
    public String getNombre_producto() {
        return nombre_producto;
    }
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
    public String getMarca_producto() {
        return marca_producto;
    }
    public void setMarca_producto(String marca_producto) {
        this.marca_producto = marca_producto;
    }
    public Double getUnidades() {
        return unidades;
    }
    public void setUnidades(Double unidades) {
        this.unidades = unidades;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getFecha_compra() {
        return fecha_compra;
    }
    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }
}
