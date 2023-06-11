package com.progettois.boundary;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.progettois.control.GestioneLibreria;
import com.progettois.entity.EntityDipendente;
import com.progettois.exception.AlreadyExistsException;
import com.progettois.exception.ISBNAlreadyBoundedException;
import com.progettois.exception.OperationException;

public class BoundaryDipendente {

	private EntityDipendente dipendente = null;
	private Scanner input;

	public BoundaryDipendente(Scanner input, EntityDipendente dipendente){
		this.input = input;
		this.dipendente = dipendente;
	}
	
	public void runBoundaryDipendente() {
		boolean exit = false;
		String op = null;

		
		while(!exit) {
			System.out.println("Dipendente: " + dipendente.getNomeUtente());
			System.out.println("1. Inserisci Libro");
			System.out.println("2. Rimuovi libro");
			System.out.println("3. Modifica stato ordine");
			System.out.println("4. Esci");
			System.out.println();
			System.out.println("-------------------------------------------");
			System.out.println();
			
			op = input.nextLine();
			
			if(op.equals("1")) {
				inserisciLibro();				
			} else if(op.equals("4")){
				exit = true;
			} else {
				System.out.println("Operazione non disponibile");
				System.out.println();
			}
		}
		
		System.out.println("Uscita");
	}
	
	public void inserisciLibro() {
		
		String tipo = null;
		String titolo = null;
		String autori = null;
		String casaEditrice = null;
		String genere = null;
		long codiceISBN = 0;
		double prezzo = 0;
		int qtDisponibile = 0;
		GestioneLibreria gL = GestioneLibreria.getInstance();
		boolean valido = false;

		try {
			
			while(!valido) {
				
				try {
				System.out.println("Inserire tipo di libro da inserire, scegliere 1 per libro cartaceo e 2 per libro digitale");
				System.out.println("1. Libro cartaceo");
				System.out.println("2. Libro digitale");
				
				tipo = input.nextLine();
				
				if(tipo.equals("1") || tipo.equals("2"))
					valido = true;
				} catch(NoSuchElementException e) {
					e.printStackTrace();
				}catch(IllegalStateException e){
					e.printStackTrace();
				}
			}
			
			valido = false;
			while(!valido) {
				try {
					System.out.println("Inserire codice ISBN");
					codiceISBN = Long.parseLong(input.nextLine());
					if(codiceISBN < 10000000000L && codiceISBN > 999999999L ){
						valido = true;
					} else {
						System.out.println("CodiceISBN non valido, deve essere composto da 10 cifre");
					}
						
				} catch(NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
			valido = false;
			while(!valido) {
				try {
					System.out.println("Inserire titolo");
					titolo = input.nextLine();
					if(titolo.length() < 100){
						valido = true;
					} else {
						System.out.println("Titolo troppo lungo, input non valido");
					}
					
				} catch(NoSuchElementException e) {
					e.printStackTrace();
				} catch(IllegalStateException e){
					e.printStackTrace();
				}
			}
			
			valido = false;
			while(!valido) {
				try {
					System.out.println("Inserire autori");
					autori = input.nextLine();
					if(autori.length() < 100){
						valido = true;
					} else {
						System.out.println("Autori troppo lungo, input non valido");
					}
				} catch(NoSuchElementException e) {
					e.printStackTrace();
				} catch(IllegalStateException e){
					e.printStackTrace();
				}
			}
			
			valido = false;
			while(!valido) {
				try {
					System.out.println("Inserire casa editrice");
					casaEditrice = input.nextLine();
					if(casaEditrice.length() < 100){
						valido = true;
					} else {
						System.out.println("Casa Editrice troppo lungo, input non valido");
					}
				} catch(NoSuchElementException e) {
					e.printStackTrace();
				} catch(IllegalStateException e){
					e.printStackTrace();
				}
			}
			
			valido = false;
			while(!valido) {
				try {
					System.out.println("Inserire genere");
					genere = input.nextLine();
					if(genere.length() < 100){
						valido = true;
					} else {
						System.out.println("Genere troppo lungo, input non valido");
					}
				} catch(NoSuchElementException e) {
					e.printStackTrace();
				}catch(IllegalStateException e){
					e.printStackTrace();
				}
			}
			
			valido = false;
			while(!valido) {
				try {	
					System.out.println("Inserire prezzo");
					prezzo = Double.parseDouble(input.nextLine());
					if(prezzo >= 0){
						valido = true;
					} else {
						System.out.println("Prezzo non valido, non può essere negativo");
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
			
			if(tipo.equals("1")) {
				valido = false;
				try {
					while(!valido){
						System.out.println("Inserire quantità disponibile");
						qtDisponibile = Integer.parseInt(input.nextLine());
						if(qtDisponibile > 0){
							valido = true;
						} else {
							System.out.println("Quantità disponibile non valida, deve essere maggiore di 0");
						}
					}

				} catch(NumberFormatException e) {
					e.printStackTrace();
				} 
					gL.inserisciLibro(codiceISBN, titolo, autori, casaEditrice, genere, prezzo, qtDisponibile);
				
			} else if(tipo.equals("2")) {
				
				gL.inserisciLibro(codiceISBN, titolo, autori, casaEditrice, genere, prezzo);
			} 
			
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}catch(AlreadyExistsException e) {
			System.out.println(e.getMessage());
		}catch (ISBNAlreadyBoundedException e){
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println("Unexpected exception, riprovare");
			System.out.println();
		}
		
		
	}

	
}
