package com.andres.personaltrainer.payment;

public class paymentData {

    public String user, meses, fechaInicio, fechaFinal, abono, saldo;

    public paymentData(String user, String meses, String fechaInicio, String fechaFinal, String abono, String saldo) {
        this.user = user;
        this.meses = meses;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.abono = abono;
        this.saldo = saldo;
    }
}
