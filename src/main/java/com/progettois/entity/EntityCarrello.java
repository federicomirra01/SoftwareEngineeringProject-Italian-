package com.progettois.entity;

import java.util.ArrayList;

import com.progettois.abstractClass.Libro;
import com.progettois.database.CarrelloDAO;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class EntityCarrello {

    private long idCarrello;
    private ArrayList<Libro> libri = new ArrayList<Libro>();
    private double prezzoTotale;


    

    public EntityCarrello(long idCarrello, ArrayList<Libro> libri, double prezzoTotale) {
        this.idCarrello = idCarrello;
        this.libri = libri;
        this.prezzoTotale = prezzoTotale;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }


    public long createCarrello() throws DAOException, DBConnectionException{
        return CarrelloDAO.createCarrello();
    }

    public long getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(long idCarrello) {
        this.idCarrello = idCarrello;
    }
    
    public void addLibro(Libro l){
        libri.add(l);
    }

    public ArrayList<Libro> getLibri(){
        return libri;
    }

    public String toString(){
        
        StringBuffer buf = new StringBuffer();

        for (Libro l : libri){
            buf.append("CodiceISBN: " + l.getCodiceISBN() + " Titolo: " + l.getTitolo() + " Autori: " + l.getAutori() +
            " Casa Editrice: " + l.getCasaEditrice() + " Genere: " + l.getGenere() + " Prezzo: " + l.getPrezzo());
            
            if(l instanceof EntityLibroCartaceo){
                EntityLibroCartaceo lC = (EntityLibroCartaceo) l;
                buf.append("Quantità disponibile " + lC.getQtdisponibile());
            }

        }

        return buf.toString();
    }

    public void updatePrezzo() throws DAOException, DBConnectionException {
        CarrelloDAO.updatePrezzo(this);
    }

}
