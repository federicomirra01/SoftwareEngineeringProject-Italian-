package com.progettois.entity;

import java.util.ArrayList;

import com.progettois.database.InserimentoDigitaleDAO;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class EntityInserimentoDigitale {

    long idCarrello, codiceISBN;

    public EntityInserimentoDigitale(long idCarrello, long codiceISBN) {
        this.idCarrello = idCarrello;
        this.codiceISBN = codiceISBN;
    }

    public long getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(long idCarrello) {
        this.idCarrello = idCarrello;
    }

    public long getCodiceISBN() {
        return codiceISBN;
    }

    public void setCodiceISBN(long codiceISBN) {
        this.codiceISBN = codiceISBN;
    }

    public void createInserimentoDigitale() throws DAOException, DBConnectionException{
        InserimentoDigitaleDAO.createInserimentoDigitale(this);
    }

    public ArrayList<Long> readInserimentoDigitale() throws DAOException, DBConnectionException {
        return InserimentoDigitaleDAO.readInserimentoDigitale(idCarrello);
    }
    
}