package com.manuel.workshop.dto;

public class HabitacionDTO {
    private Integer numero;
    private String tipoHabitacion;
    private Integer precioBase;

    public HabitacionDTO(Integer numero, String tipoHabitacion, Integer precioBase) {
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.precioBase = precioBase;
    }

    public HabitacionDTO() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public Integer getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Integer precioBase) {
        this.precioBase = precioBase;
    }
}
