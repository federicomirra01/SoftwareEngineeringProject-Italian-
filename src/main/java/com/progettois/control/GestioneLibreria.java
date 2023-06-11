package com.progettois.control;

import java.util.ArrayList;
import java.util.Hashtable;

import com.progettois.abstractClass.Libro;
import com.progettois.abstractClass.Utente;
import com.progettois.database.AcquistoCartaceoDAO;
import com.progettois.database.AcquistoDigitaleDAO;
import com.progettois.database.CarrelloDAO;
import com.progettois.database.ClienteRegistratoDAO;
import com.progettois.database.DipendenteDAO;
import com.progettois.database.InserimentoCartaceoDAO;
import com.progettois.database.InserimentoDigitaleDAO;
import com.progettois.database.LibroCartaceoDAO;
import com.progettois.database.LibroDigitaleDAO;
import com.progettois.database.OrdineCartaceoDAO;
import com.progettois.database.OrdineDigitaleDAO;
import com.progettois.entity.EntityInserimentoCartaceo;
import com.progettois.entity.EntityInserimentoDigitale;
import com.progettois.entity.EntityCarrello;
import com.progettois.entity.EntityClienteRegistrato;
import com.progettois.entity.EntityDipendente;
import com.progettois.entity.EntityLibroCartaceo;
import com.progettois.entity.EntityLibroDigitale;
import com.progettois.entity.EntityOrdineCartaceo;
import com.progettois.entity.EntityOrdineDigitale;
import com.progettois.entity.StatiOrdineCartaceo;
import com.progettois.entity.StatiOrdineDigitale;
import com.progettois.exception.AlreadyExistsException;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;
import com.progettois.exception.ISBNAlreadyBoundedException;
import com.progettois.exception.OperationException;


public class GestioneLibreria {

	//private int countCarrello;
	//private static final int RANGECARRELLO = 5;
	private static GestioneLibreria gL = null;

	protected GestioneLibreria() {
	}

	public static GestioneLibreria getInstance() {

		if (gL == null)
			gL = new GestioneLibreria();

		return gL;
	}

	public void inserisciLibro(long CodiceISBN, String titolo, String autori, String casaEditrice, String genere,
			double prezzo, int qtDisponibile) throws OperationException, ISBNAlreadyBoundedException {

		try {
			EntityLibroCartaceo eLC = LibroCartaceoDAO.readLibroCartaceo(CodiceISBN);

			if (eLC == null) {

				eLC = new EntityLibroCartaceo(CodiceISBN, titolo, autori, casaEditrice, genere, prezzo, qtDisponibile);
				eLC.createLibroCartaceo();
				System.out.println("Libro Cartaceo inserito correttamente");
			}

			else if (eLC.getTitolo().equals(titolo) && eLC.getAutori().equals(autori)
					&& eLC.getCasaEditrice().equals(casaEditrice)
					&& eLC.getGenere().equals(genere) && eLC.getPrezzo() == prezzo) {
				eLC.setQtDisponibile(eLC.getQtdisponibile() + qtDisponibile);
				eLC.updateQtLibroCartaceo();
				System.out.println("Aggiornata quantità libro cartaceo già presente nel database");
				System.out.println();
				System.out.println(eLC.toString());
			} else {
				throw new ISBNAlreadyBoundedException(
						"Codice ISBN già presente in database ma corrispondente a un diverso libro");
			}
		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}

	}

	public void inserisciLibro(long codiceISBN, String titolo, String autori, String casaEditrice, String genere,
			double prezzo) throws AlreadyExistsException, OperationException {

		try {
			EntityLibroDigitale eLD = LibroDigitaleDAO.readLibroDigitale(codiceISBN);

			if (eLD == null) {

				eLD = new EntityLibroDigitale(codiceISBN, titolo, autori, casaEditrice, genere, prezzo);
				eLD.createLibroDigitale();
			}

			else {
				throw new AlreadyExistsException("Codice ISBN  già presente nel database");
			}

		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		System.out.println("Libro Digitale inserito correttamente");

	}

	public ArrayList<Libro> visualizzaLibriDisponibili() throws OperationException {

		ArrayList<Libro> listaLibriDisponibili = new ArrayList<Libro>();
		try {
			listaLibriDisponibili.addAll(LibroCartaceoDAO.visualizzaLibriCartaceiDisponibili());
			listaLibriDisponibili.addAll(LibroDigitaleDAO.visualizzaLibriDigitaliiDisponibili());
		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}

		return listaLibriDisponibili;

	}

	public void popolaCarrello(long codiceISBN, int qtRichiesta, EntityClienteRegistrato eCR) throws OperationException {

		/*if((countCarrello + qtRichiesta)>5){
			System.out.println("Limite massimo Carrello raggiunto");
			return;			
		}*/

		try {

			EntityLibroCartaceo eLC = LibroCartaceoDAO.readLibroCartaceo(codiceISBN);
			int quantitàDisponibile = eLC.getQtdisponibile();
			EntityInserimentoCartaceo eIC = null;
			EntityCarrello carrello = CarrelloDAO.readCarrello(eCR.getIdCarrello());

			if (quantitàDisponibile < qtRichiesta) {

				throw new OperationException("Quantità richiesta maggiore della quantità disponibile");

			} else {

				//countCarrello = countCarrello + qtRichiesta;

				eIC = new EntityInserimentoCartaceo(eCR.getIdCarrello(), codiceISBN, qtRichiesta);

				//libro non nel carrello
				if (eIC.readInserimentoCartaceo().get(codiceISBN) == null) {
					eIC.createInserimentoCartaceo();
					carrello.getLibri().add(eLC);
					carrello.setPrezzoTotale(carrello.getPrezzoTotale() + (eLC.getPrezzo() * qtRichiesta));
				}
				//se il libro già c'è aggiorno la quantità
				else{
					carrello.getLibri().add(eLC);

					int qr = eIC.readInserimentoCartaceo().get(codiceISBN).intValue();
					double pr = eLC.getPrezzo();
					
					//tolgo il prezzo relativo al vecchio libro
					carrello.setPrezzoTotale(carrello.getPrezzoTotale()-(qr*pr));					

					carrello.setPrezzoTotale(carrello.getPrezzoTotale() + (eLC.getPrezzo() * qtRichiesta));
					
					eIC.updateQt();
				}
				
				/*carrello.getLibri().add(eLC);
				
				carrello.setPrezzoTotale(carrello.getPrezzoTotale() + (eLC.getPrezzo() * qtRichiesta));*/

				carrello.updatePrezzo();


				/*Hashtable<Long, Integer> listaCodiciISBNCartacei = InserimentoCartaceoDAO
						.readInserimentoCartaceo(eCR.getIdCarrello());

				for (Long codiceISBNCartaceo : listaCodiciISBNCartacei.keySet()) {

					//stampa
					LibroCartaceoDAO.readLibroCartaceo(codiceISBNCartaceo).toString();
					System.out.println();
				}

				

				for(Libro libro : carrello.getLibri()){

					//System.out.println(libro.toString());
					System.out.println("Codice ISBN: " + libro.getCodiceISBN() + "\n| Titolo: " + libro.getTitolo() + "\n| Autori: " 
					+ libro.getAutori() + "\n| Casa editrice: "+ libro.getCasaEditrice() + "\n| Genere: " + libro.getGenere() + "\n| Prezzo: " 
					+ libro.getPrezzo()+ "\n| Quantità richiesta: " +qtRichiesta);
					
				}

			System.out.println("| Prezzo totale carrello: " + carrello.getPrezzoTotale());*/


			}

		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}

	}

	public void popolaCarrelo(long codiceISBN, EntityClienteRegistrato eCR) throws OperationException {

		/*if(countCarrello==RANGECARRELLO){
			System.out.println("Limite massimo Carrello raggiunto");
			return;			
		}

		countCarrello++;*/

		try {

			EntityLibroDigitale eLD = LibroDigitaleDAO.readLibroDigitale(codiceISBN);
			EntityInserimentoDigitale eID = new EntityInserimentoDigitale(eCR.getIdCarrello(), codiceISBN);
			EntityCarrello carrello = CarrelloDAO.readCarrello(eCR.getIdCarrello());

			if (!eID.readInserimentoDigitale().contains(codiceISBN)) {

				eID.createInserimentoDigitale();
				carrello.getLibri().add(eLD);
				carrello.setPrezzoTotale(carrello.getPrezzoTotale() + eLD.getPrezzo());
				carrello.updatePrezzo();

				

			} else {
				System.out.println("Libro digitale già presente nel carrello");
			}


		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}

	}

	public void completaAcquisto(long idCarrello, long idClienteRegistrato) throws OperationException {

		try {

			int banca = (int)(Math.random()*2);			

			EntityOrdineDigitale oD = new EntityOrdineDigitale(idClienteRegistrato);
			EntityOrdineCartaceo oC = new EntityOrdineCartaceo(idClienteRegistrato);
		
			ArrayList<Long> listaCodiciISBNDigitali = InserimentoDigitaleDAO.readInserimentoDigitale(idCarrello);
			ArrayList<EntityLibroDigitale> listaLibriDigitali = new ArrayList<EntityLibroDigitale>();

			for (long codiceISBN : listaCodiciISBNDigitali) {

				listaLibriDigitali.add(LibroDigitaleDAO.readLibroDigitale(codiceISBN));

			}

			Hashtable<Long, Integer> listaCodiciISBNCartacei = InserimentoCartaceoDAO.readInserimentoCartaceo(idCarrello);

			Hashtable<EntityLibroCartaceo, Integer> listaLibriCartacei = new Hashtable<EntityLibroCartaceo, Integer>();

			for (Long codiceISBN : listaCodiciISBNCartacei.keySet()) {

				listaLibriCartacei.put(LibroCartaceoDAO.readLibroCartaceo(codiceISBN), listaCodiciISBNCartacei.get(codiceISBN));
			}

			if(listaLibriCartacei.isEmpty()==true && listaLibriDigitali.isEmpty()==true){
				System.out.println("ERRORE: Carrello vuoto");
				return;
			}
			
		
			//ACCETTATO
			if(banca==0){

				if (!listaLibriDigitali.isEmpty()) {

					long idOrdineDigitale = oD.createOrdineDigitale();
					oD.setIdOrdine(idOrdineDigitale);

					for (EntityLibroDigitale libro : listaLibriDigitali) {

						AcquistoDigitaleDAO.createAcquistoDigitale(idOrdineDigitale, libro.getCodiceISBN());

						oD.setPrezzo(oD.getPrezzo() + libro.getPrezzo());
						oD.updatePrezzo();
						InserimentoDigitaleDAO.delete(idCarrello, libro.getCodiceISBN());
						visualizzaOrdineDigitale(idOrdineDigitale);

					}

				}

				if (!listaLibriCartacei.isEmpty()) {

					long idOrdineCartaceo = oC.createOrdineCartaceo();
					oC.setIdOrdine(idOrdineCartaceo);

					for (EntityLibroCartaceo libro : listaLibriCartacei.keySet()) {

						int qt = listaLibriCartacei.get(libro).intValue();

						if (libro.getQtdisponibile() >= qt) {

							AcquistoCartaceoDAO.createAcquistoCartaceo(idOrdineCartaceo, libro.getCodiceISBN(), qt);
							libro.setQtDisponibile(libro.getQtdisponibile() - qt);
							LibroCartaceoDAO.updateQtLibroCartaceo(libro);

							oC.setPrezzo(oC.getPrezzo() + (libro.getPrezzo() * qt));
							oC.updatePrezzo();

						} else {

							System.out.println("Quantità richiesta maggiore della quantità disponibile per il libro: ");
							System.out.println(libro.toString());
						}

						InserimentoCartaceoDAO.delete(idCarrello, libro.getCodiceISBN());
						visualizzaOrdineCartaceo(idOrdineCartaceo);
					}
				}

				//AZZERA IL PREZZO DEL CARRELLO
				EntityCarrello carrello = CarrelloDAO.readCarrello(idCarrello);
				carrello.setPrezzoTotale(0);
				carrello.updatePrezzo();

				//aggiornare stati
				oC.setStato(StatiOrdineCartaceo.IN_PREPARAZIONE);
				oC.updateStato();
				oD.setStato(StatiOrdineDigitale.ACQUISTATO);
				oD.updateStato();

				System.out.println("Transazione eseguita con successo");
			}
	
		//RIFIUTATO
		else{

			if (!listaLibriDigitali.isEmpty()){

				long idOrdineDigitale = oD.createOrdineDigitale();
				oD.setIdOrdine(idOrdineDigitale);
				oD.setStato(StatiOrdineDigitale.RIFIUTATO);
				oD.updateStato();

				for (EntityLibroDigitale libro : listaLibriDigitali){
					InserimentoDigitaleDAO.delete(idCarrello, libro.getCodiceISBN());
					oD.setPrezzo(oD.getPrezzo() + libro.getPrezzo());
					oD.updatePrezzo();
				}
			}

			if(!listaLibriCartacei.isEmpty()){
				long idOrdineCartaceo = oC.createOrdineCartaceo();
				oC.setIdOrdine(idOrdineCartaceo);
				oC.setStato(StatiOrdineCartaceo.RIFIUTATO);
				oC.updateStato();

				for (EntityLibroCartaceo libro : listaLibriCartacei.keySet()){
					InserimentoCartaceoDAO.delete(idCarrello, libro.getCodiceISBN());
					int qt = listaLibriCartacei.get(libro).intValue();
					oC.setPrezzo(oC.getPrezzo() + (libro.getPrezzo() * qt));
					oC.updatePrezzo();
				}
			}

			//AZZERA IL PREZZO DEL CARRELLO
			EntityCarrello carrello = CarrelloDAO.readCarrello(idCarrello);
			carrello.setPrezzoTotale(0);
			carrello.updatePrezzo();

			System.out.println("ERRORE: Transazione negata");
		}
	
		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}

	}

	public void registrati(String nomeUtente, String password, String nome, String cognome, String indirizzo,
			String città, String email, int cap, long telefono) throws OperationException {

		try {

			EntityClienteRegistrato eCR = new EntityClienteRegistrato(0L, nomeUtente, password, nome, cognome,
					indirizzo, cap, telefono, città, email, 0L);
			eCR.createClienteRegistrato();

			accedi(nomeUtente, password);

		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());

		}

	}

	public Utente accedi(String nomeUtente, String password) throws OperationException {

		EntityClienteRegistrato eCR = null;
		EntityDipendente eD = null;

		try {
			if ((eD = DipendenteDAO.readDipendente(nomeUtente, password)) != null) {

				return eD;

			} else if ((eCR = ClienteRegistratoDAO.readClienteRegistrato(nomeUtente, password)) != null) {

				return eCR;
			}

			else {
				System.out.println("Utente non trovato");
				return null;
			}

		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}

	}

	public boolean isUser(String nomeUtente, String password) throws OperationException {
		boolean result = false;
		try {

			if (DipendenteDAO.readDipendente(nomeUtente, password) != null
					|| ClienteRegistratoDAO.readClienteRegistrato(nomeUtente, password) != null) {

				result = true;

			}

		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		return result;
	}

	private void visualizzaOrdineDigitale(long idOrdineDigitale) throws OperationException{

		try {
			ArrayList<Long> codiciISBNDigitali = AcquistoDigitaleDAO.readAcquistoDigitale(idOrdineDigitale);
			ArrayList<EntityLibroDigitale> libriDigitali = new ArrayList<EntityLibroDigitale>();

			for(long codiceISBN : codiciISBNDigitali){
				libriDigitali.add(LibroDigitaleDAO.readLibroDigitale(codiceISBN));
			}

			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("ORDINE DIGITALE: ");
			System.out.println("--------------------------------------------------------------------------------");

			for(EntityLibroDigitale libro : libriDigitali){
				System.out.println(libro.toString());
			}


			System.out.println("");
			System.out.println("| Prezzo totale pagato: " + OrdineDigitaleDAO.readOrdineDigitalePrezzo(idOrdineDigitale));
		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}

	}

	private void visualizzaOrdineCartaceo(long idOrdineCartaceo) throws OperationException{

		try{
			ArrayList<Long> codiciISBNCartacei = AcquistoCartaceoDAO.readAcquistoCartaceo(idOrdineCartaceo);
			ArrayList<EntityLibroCartaceo> libriCartacei = new ArrayList<EntityLibroCartaceo>();

			for(long codiceISBN : codiciISBNCartacei){
				libriCartacei.add(LibroCartaceoDAO.readLibroCartaceo(codiceISBN));
			}

			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("ORDINE CARTACEO: ");
			System.out.println("--------------------------------------------------------------------------------");

			for(EntityLibroCartaceo libro : libriCartacei){

				System.out.println(libro.toString());
			
			}
			System.out.println("");
			System.out.println("| Prezzo totale pagato: " + OrdineCartaceoDAO.readOrdineCartaceoPrezzo(idOrdineCartaceo));
		} catch(DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}




	}

	//FUNZIONA CHE STAMPA IL CARRELLO
	public void stampaCarrello(long idCarrello) throws OperationException{

		try {

			//DIGITALE

			ArrayList<Long>  listaCodiciISBNDigitali = InserimentoDigitaleDAO.readInserimentoDigitale(idCarrello);

			ArrayList<EntityLibroDigitale> listaLibriDigitali = new ArrayList<EntityLibroDigitale>();

			for (long codiceISBN : listaCodiciISBNDigitali) {

				listaLibriDigitali.add(LibroDigitaleDAO.readLibroDigitale(codiceISBN));
			}

			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("CARRELLO");
			System.out.println("-----------------------------------------------------------------------------");

			for (EntityLibroDigitale libro : listaLibriDigitali){

				System.out.println("Codice ISBN: " + libro.getCodiceISBN() + "\n| Titolo: " + libro.getTitolo() + "\n| Autori: " 
					+ libro.getAutori() + "\n| Casa editrice: "+ libro.getCasaEditrice() + "\n| Genere: " + libro.getGenere() + "\n| Prezzo: " 
					+ libro.getPrezzo()+"\n");	
				
			}

			//CARTACEO

			Hashtable<Long, Integer> listaCodiciISBNCartacei = InserimentoCartaceoDAO.readInserimentoCartaceo(idCarrello);

			Hashtable<EntityLibroCartaceo, Integer> listaLibriCartacei = new Hashtable<EntityLibroCartaceo, Integer>();

			for (Long codiceISBN : listaCodiciISBNCartacei.keySet()) {

				listaLibriCartacei.put(LibroCartaceoDAO.readLibroCartaceo(codiceISBN), listaCodiciISBNCartacei.get(codiceISBN));
			}

			int qt;

			for (EntityLibroCartaceo libro : listaLibriCartacei.keySet()) {

				qt = listaLibriCartacei.get(libro).intValue();

				System.out.println("Codice ISBN: " + libro.getCodiceISBN() + "\n| Titolo: " + libro.getTitolo() + "\n| Autori: " 
					+ libro.getAutori() + "\n| Casa editrice: "+ libro.getCasaEditrice() + "\n| Genere: " + libro.getGenere() + "\n| Prezzo: " 
					+ libro.getPrezzo()+"\n|Quantità richiesta: "+qt+"\n");
			}


			//STAMPA PREZZO TOTALE DEL CARRELLO
			EntityCarrello carrello = CarrelloDAO.readCarrello(idCarrello);

			System.out.println("Prezzo totale: "+carrello.getPrezzoTotale()+"\n");

		}catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
			

			
	}


}


	

