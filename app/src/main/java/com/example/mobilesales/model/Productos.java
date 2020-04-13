package com.example.mobilesales.model;

import java.io.Serializable;

public class Productos implements Serializable {

    private Integer id;

    private String nombre;

    private String marca;

    private String descripcion;

    private Double precio;

    private Double stock;

    private String alias;

    private int foto;

    public Productos(String nombre, String marca, String descripcion, Double precio, Double stock, String alias, int foto) {
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.alias = alias;
        this.foto = foto;
    }
    /*
    public Productos(String nombre, Double precio, int foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.foto = foto;
    }
    */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
