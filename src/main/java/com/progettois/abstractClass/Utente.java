package com.progettois.abstractClass;

public abstract class Utente {
    
    protected String nomeUtente, password;

    

    public Utente(String nomeUtente, String password) {
        this.nomeUtente = nomeUtente;
        this.password = password;
    }

    public String getNomeUtente(){

        return nomeUtente;

    }

    public void setNomeUtente(String nomeUtente){

        this.nomeUtente = nomeUtente;

    }

    public String getPassword(){

        return password;

    }

    public void setPassword(String password){

        this.password = password;
        
    }

    

}
