package com.progettois.entity;

import java.sql.Date;
import java.util.ArrayList;

import com.progettois.abstractClass.Ordine;
import com.progettois.database.OrdineCartaceoDAO;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class EntityOrdineCartaceo extends Ordine {

    protected ArrayList<EntityLibroCartaceo> libri;
    private Date dataConsegna;
    private StatiOrdineCartaceo stato;
    private double prezzo;
    

    private long idClienteRegistrato;

    

    public EntityOrdineCartaceo(long idClienteRegistrato) {
        this.libri = new ArrayList<EntityLibroCartaceo>();
        prezzo = 0;
        this.idClienteRegistrato = idClienteRegistrato;
        
    }

    public Date getDataConsegna(){

        return dataConsegna;

    }

    public void setDataConsegna(Date dataConsegna){

        this.dataConsegna = dataConsegna;
        
    }

    public StatiOrdineCartaceo getStato() {
        return stato;
    }

    public void setStato(StatiOrdineCartaceo stato) {
        this.stato = stato;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public long getIdClienteRegistrato() {
        return idClienteRegistrato;
    }

    public void setIdClienteRegistrato(long idClienteRegistrato) {
        this.idClienteRegistrato = idClienteRegistrato;
    }

    public String toString(){
        
        StringBuffer buf = new StringBuffer();

        for (EntityLibroCartaceo l : libri){
            buf.append("CodiceISBN: " + l.getCodiceISBN() + " Titolo: " + l.getTitolo() + " Autori: " + l.getAutori() +
            " Casa Editrice: " + l.getCasaEditrice() + " Genere: " + l.getGenere() + " Prezzo: " + l.getPrezzo() + 
            " Quantità disponibile " + l.getQtdisponibile());
        }

        return buf.toString();
    }
    
    public long createOrdineCartaceo() throws DAOException, DBConnectionException{
        return OrdineCartaceoDAO.createOdineCartaceo(this);
    }

    public void updatePrezzo() throws DAOException, DBConnectionException {
        OrdineCartaceoDAO.updatePrezzo(this);
    }

    public void updateStato() throws DAOException, DBConnectionException{
        OrdineCartaceoDAO.updateStato(this);
    }
}
