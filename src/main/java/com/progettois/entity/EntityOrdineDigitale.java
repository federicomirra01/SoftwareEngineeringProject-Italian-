package com.progettois.entity;

import java.sql.Date;
import java.util.ArrayList;

import com.progettois.abstractClass.Ordine;
import com.progettois.database.OrdineCartaceoDAO;
import com.progettois.database.OrdineDigitaleDAO;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class EntityOrdineDigitale extends Ordine {

    private ArrayList<EntityLibroDigitale> libri;
    private ArrayList<Long> codiciScaricamento;
    private Date dataScaricamento;
    private StatiOrdineDigitale stato;
    private double prezzo;
    private long idClienteRegistrato;


    public EntityOrdineDigitale(long idClienteRegistrato) {
        libri  = new ArrayList<EntityLibroDigitale>();
        prezzo = 0;
        this.idClienteRegistrato = idClienteRegistrato;
    }

    public ArrayList<Long> getCodiciScaricamento(){

        return codiciScaricamento;

    }

    public void setCodiciScaricamento(ArrayList<Long> codiciScaricamento){

        this.codiciScaricamento = codiciScaricamento;
        
    }

    public Date getDataScaricamento(){

        return dataScaricamento;

    }

    public void setDataScaricamento(Date dataScaricamento){

        this.dataScaricamento = dataScaricamento;
    }

    public StatiOrdineDigitale getStato() {
        return stato;
    }

    public void setStato(StatiOrdineDigitale stato) {
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

        for (EntityLibroDigitale l : libri){
            buf.append("CodiceISBN: " + l.getCodiceISBN() + " Titolo: " + l.getTitolo() + " Autori: " + l.getAutori() +
            " Casa Editrice: " + l.getCasaEditrice() + " Genere: " + l.getGenere() + " Prezzo: " + l.getPrezzo());
        }

        return buf.toString();
    }

    public long createOrdineDigitale() throws DAOException, DBConnectionException{
        return OrdineDigitaleDAO.createOrdineDigitale(this);
    }

    public void updatePrezzo() throws DAOException, DBConnectionException {
        OrdineDigitaleDAO.updatePrezzo(this);
    }

    public void updateStato() throws DAOException, DBConnectionException{
        OrdineDigitaleDAO.updateStato(this);
    }

    
}
