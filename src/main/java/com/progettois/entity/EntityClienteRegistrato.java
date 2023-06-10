package com.progettois.entity;

import com.progettois.abstractClass.Utente;
import com.progettois.database.CarrelloDAO;
import com.progettois.database.ClienteRegistratoDAO;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class EntityClienteRegistrato extends Utente {

    long idClienteRegistrato, telefono, idCarrello;
    

    String nome, cognome, indirizzo, città, email;
    int cap; 

    public EntityClienteRegistrato(long idClienteRegistrato, String nomeUtente, String password, String nome, String cognome, String indirizzo,
        int cap, long telefono, String città, String email, long idCarrello){
        
        super(nomeUtente, password);
        this.idClienteRegistrato = idClienteRegistrato;
        this.idCarrello = idCarrello;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.città = città;
        this.email = email;
        this.cap = cap;
        this.telefono = telefono;
        
    }

    public long getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(long idCarrello) {
        this.idCarrello = idCarrello;
    }

    public long getIdClienteRegistrato() {
        return idClienteRegistrato;
    }

    public void setIdClienteRegistrato(long idClienteRegistrato) {
        this.idClienteRegistrato = idClienteRegistrato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void createClienteRegistrato() throws DAOException, DBConnectionException{

        ClienteRegistratoDAO.createClienteRegistrato(this, CarrelloDAO.createCarrello());
    }
    
    
}