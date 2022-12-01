package com.example.Otherss;

public  class Suma {
    private  String numarCont;
    private float bilantInitial;
    private  float sumaCreditoare;
    private  float  sumaDebitoare;

    public Suma() {
    }

    public Suma(String numarCont, float bilantInitial, float sumaCreditoare, float sumaDebitoare) {
        this.numarCont = numarCont;
        this.bilantInitial = bilantInitial;
        this.sumaCreditoare = sumaCreditoare;
        this.sumaDebitoare = sumaDebitoare;
    }

    public String getNumarCont() {
        return numarCont;
    }

    public void setNumarCont(String numarCont) {
        this.numarCont = numarCont;
    }

    public float getBilantInitial() {
        return bilantInitial;
    }

    public void setBilantInitial(float bilantInitial) {
        this.bilantInitial = bilantInitial;
    }

    public float getSumaCreditoare() {
        return sumaCreditoare;
    }

    public void setSumaCreditoare(float sumaCreditoare) {
        this.sumaCreditoare = sumaCreditoare;
    }

    public float getSumaDebitoare() {
        return sumaDebitoare;
    }

    public void setSumaDebitoare(float sumaDebitoare) {
        this.sumaDebitoare = sumaDebitoare;
    }
}