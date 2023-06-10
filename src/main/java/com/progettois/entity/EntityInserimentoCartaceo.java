package com.progettois.entity;

import java.util.Hashtable;

import com.progettois.database.InserimentoCartaceoDAO;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class EntityInserimentoCartaceo {

    long idCarrello, codiceISBN;
    int quantitàRichiesta;

    public EntityInserimentoCartaceo(long idCarrello, long codiceISBN, int quantitàRichiesta) {
        this.idCarrello = idCarrello;
        this.codiceISBN = codiceISBN;
        this.quantitàRichiesta = quantitàRichiesta;
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

    public int getQuantitàRichiesta() {
        return quantitàRichiesta;
    }

    public void setQuantitàRichiesta(int quantitàRichiesta) {
        this.quantitàRichiesta = quantitàRichiesta;
    }

    public void createInserimentoCartaceo() throws DAOException, DBConnectionException{
        InserimentoCartaceoDAO.createInserimentoCartaceo(this);
    }

    public Hashtable<Long, Integer> readInserimentoCartaceo() throws DAOException, DBConnectionException{
        return InserimentoCartaceoDAO.readInserimentoCartaceo(this.idCarrello);
    }

    public void updateQt() throws DAOException, DBConnectionException {
        InserimentoCartaceoDAO.updateQt(quantitàRichiesta, idCarrello, codiceISBN);
    }
}

