package com.manuel.workshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Id
    @Column(name = "cedula", nullable = false)
    private Integer cedula;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "correo")
    private String email;
    @Column(name = "habilidata")
    private String cuentaHabilitada;
    @Column(name = "contrasena")
    private String contrasena;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, Integer cedula, String direccion, Integer edad, String email, String cuentaHabilitada, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.edad = edad;
        this.email = email;
        this.cuentaHabilitada = cuentaHabilitada;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuentaHabilitada() {
        return cuentaHabilitada;
    }

    public void setCuentaHabilitada(String cuentaHabilitada) {
        this.cuentaHabilitada = cuentaHabilitada;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
