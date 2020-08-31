package com.andres.personaltrainer.addClient;

public class userInfo {
    public String nombre, apellidos, edad, tel, fecha, correo, clave, estado;

    public userInfo() {
    }

    public userInfo(String nombre, String apellidos, String edad, String tel,  String fecha, String correo, String clave, String estado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.tel = tel;
        this.fecha = fecha;
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
    }
}
