package com.progettois.entity;

import com.progettois.abstractClass.Libro;
import com.progettois.database.LibroDigitaleDAO;
import com.progettois.exception.AlreadyExistsException;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class EntityLibroDigitale extends Libro {

	public EntityLibroDigitale(long codiceISBN, String titolo, String autori, String casaEditrice, String genere, double prezzo) {
		super(codiceISBN, titolo, autori, casaEditrice, genere, prezzo);
	}
	
	public void createLibroDigitale() throws DAOException, DBConnectionException, AlreadyExistsException {
		LibroDigitaleDAO.createLibroDigitale(this);
	}

	
}
