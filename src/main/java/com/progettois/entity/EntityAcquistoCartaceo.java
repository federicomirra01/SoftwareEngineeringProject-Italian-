package com.progettois.entity;

import com.progettois.database.AcquistoCartaceoDAO;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class EntityAcquistoCartaceo {

    long idOrdineCartaceo, codiceISBN;
    int quantitàRichiesta;

   

    public EntityAcquistoCartaceo(long idOrdineCartaceo, long codiceISBN, int quantitàRichiesta) {
        this.idOrdineCartaceo = idOrdineCartaceo;
        this.codiceISBN = codiceISBN;
        this.quantitàRichiesta = quantitàRichiesta;
    }



    public long getIdOrdineCartaceo() {
        return idOrdineCartaceo;
    }



    public void setIdOrdineCartaceo(long idOrdineCartaceo) {
        this.idOrdineCartaceo = idOrdineCartaceo;
    }



    public long getCodiceISBN() {
        return codiceISBN;
    }



    public void setCodiceISBN(long codiceISBN) {
        this.codiceISBN = codiceISBN;
    }



    public int getQuantitàRichiesta() {
        return quantitàRichiesta;
    }



    public void setQuantitàRichiesta(int quantitàRichiesta) {
        this.quantitàRichiesta = quantitàRichiesta;
    }



    public void createAcquistoCartaceo() throws DAOException, DBConnectionException{
        AcquistoCartaceoDAO.createAcquistoCartaceo(this.idOrdineCartaceo, this.codiceISBN, this.quantitàRichiesta);
    }

}
    

