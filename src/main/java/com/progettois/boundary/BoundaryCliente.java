package com.progettois.boundary;

import java.util.ArrayList;
import java.util.Scanner;

import com.progettois.abstractClass.Libro;
import com.progettois.control.GestioneLibreria;
import com.progettois.entity.EntityLibroCartaceo;
import com.progettois.exception.OperationException;

public class BoundaryCliente {

	protected Scanner input;
    public BoundaryCliente(Scanner input){
		this.input = input;
	}

    public void runBoundaryCliente() {
        
        boolean exit = false;
		
		while(!exit) {
			System.out.println("Cliente");
			System.out.println("1. Registrati");
			System.out.println("2. Visualizza libri disponibili");
			System.out.println("3. Esci");
			System.out.println();
			System.out.println("-------------------------------------------");
			System.out.println();
			
			String op = input.nextLine();
			
			if(op.equals("1")){
				registrati();
			}else if(op.equals("2")) {
                visualizzaLibriDisponibili();							
			} else if(op.equals("3")){
				exit = true;
			} else {
				System.out.println("Operazione non disponibile");
				System.out.println();
			}
		}
		
		System.out.println("Uscita");
	
    }
    
    protected static void visualizzaLibriDisponibili(){

        GestioneLibreria gL = GestioneLibreria.getInstance();
		ArrayList<Libro> listaLibridisponibili = new ArrayList<Libro>();
        try {
           listaLibridisponibili.addAll(gL.visualizzaLibriDisponibili());
        } catch (OperationException e) {
			System.out.println(e.getMessage());
		}

		if(listaLibridisponibili.isEmpty()){

			System.out.println("Nessun libro disponibile all'acquisto");
		} else {

			System.out.println("Lista Libri disponibili:");
		
			int i = 1;
			for(Libro libro : listaLibridisponibili){

				System.out.println();

				if(libro instanceof EntityLibroCartaceo){

					System.out.println(i + ". Tipologia libro: Cartaceo");
				
				} else{

					System.out.println(i + ". Tipologia libro: Digitale");
				
				}

				System.out.println("| " + libro.toString());

				
				
				i++;

				System.out.println();
			
			}

		}

    }

	protected void registrati(){

		String nomeUtente = null;
		String password = null;

		String nome = null;
		String cognome = null;
		String indirizzo = null;
		String città = null;
		String email = null;

		int cap = 0;
		long telefono = 0;
		boolean valido = false;
		GestioneLibreria gl = GestioneLibreria.getInstance();

		System.out.println("Inserire nomeUtente e password");
		System.out.println("Inserire nomeUtente");
		nomeUtente = input.nextLine();
		System.out.println("Inserire password");
		password = input.nextLine();

		try {
			if(gl.isUser(nomeUtente, password)){

				System.out.println("Utente già registrato");
				
			} else{

				System.out.println("Procedura di registrazione");

				System.out.println("Inserire Nome");
				nome = input.nextLine();

				System.out.println("Inserire Cognome");
				cognome = input.nextLine();

				System.out.println("Inserire Indirizzo");
				indirizzo = input.nextLine();

				while(!valido){
					try{
						System.out.println("Inserire CAP");
						cap = Integer.parseInt(input.nextLine());
						valido = true;
					} catch(NumberFormatException e){
						System.out.println("Errore, inserire numero valido");
					}
				}
				valido = false;

				while(!valido){
					
					try{
						System.out.println("Inserire Telefono");
						telefono = Long.parseLong(input.nextLine());
						valido = true;
					} catch(NumberFormatException e){
						System.out.println("Errore, inserire un numero valido");
					}
				}
				valido = false;

				System.out.println("Inserire Città");
				città = input.nextLine();
				
				while(!valido){
					System.out.println("Inserire Email");
					email = input.nextLine();

					if(email.contains("@") && email.contains(".")){
						valido = true;
					}
					else{
						System.out.println("Email non valida");
					}
				}

				try {
					gl.registrati(nomeUtente, password, nome, cognome, indirizzo, città, email, cap, telefono);
				} catch (OperationException e) {
					System.out.println(e.getMessage());
				}
				
				System.out.println("Registrazione avvenuta");

			}
		} catch (OperationException e) {
			System.out.println(e.getMessage());
		}
	}

}
