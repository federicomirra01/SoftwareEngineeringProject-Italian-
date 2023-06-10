package com.progettois.entity;

import com.progettois.abstractClass.Libro;
import com.progettois.database.LibroCartaceoDAO;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class EntityLibroCartaceo extends Libro {

    
	private int qtDisponibile;
    
	public EntityLibroCartaceo(long codiceISBN, String titolo, String autori, String casaEditrice, String genere, double prezzo, int qtDisponibile) {
		
		super(codiceISBN, titolo, autori, casaEditrice, genere, prezzo);
		this.qtDisponibile = qtDisponibile;
	}
	
	public void createLibroCartaceo() throws DAOException, DBConnectionException{
		
		LibroCartaceoDAO.createLibroCartaceo(this);
	}
	
	public void updateQtLibroCartaceo() throws DAOException, DBConnectionException{
		LibroCartaceoDAO.updateQtLibroCartaceo(this);
	}


    public int getQtdisponibile(){

        return qtDisponibile;

    }

    public void setQtDisponibile(int qtDisponibile){

        this.qtDisponibile = qtDisponibile;
        
    }

	@Override
	public String toString(){
		return "Codice ISBN: " + codiceISBN + "\n| Titolo: " + titolo + "\n| Autori: " + autori + "\n| Casa editrice: "
		+ casaEditrice + "\n| Genere: " + genere + "\n| Prezzo: " + prezzo + "\n| Quantità disponibile: " + qtDisponibile;
	}
    
    
}