package com.progettois.entity;

import com.progettois.abstractClass.Utente;

public class EntityAmministratore extends Utente {

    public EntityAmministratore(String nomeUtente, String password, String email) {
        super(nomeUtente, password);
        this.email = email;
    }

    private String email;

    public String getEmail(){

        return email;

    }

    public void setEmail(String email){

        this.email = email;
        
    }
    
}
