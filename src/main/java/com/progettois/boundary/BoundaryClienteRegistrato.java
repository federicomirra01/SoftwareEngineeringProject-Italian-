package com.progettois.boundary;

import java.util.NoSuchElementException;
import java.util.Scanner;


import com.progettois.control.GestioneLibreria;
import com.progettois.database.LibroCartaceoDAO;
import com.progettois.database.LibroDigitaleDAO;
import com.progettois.entity.EntityClienteRegistrato;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;
import com.progettois.exception.OperationException;

public class BoundaryClienteRegistrato extends BoundaryCliente{

    private EntityClienteRegistrato clienteRegistrato;

    public BoundaryClienteRegistrato(Scanner input, EntityClienteRegistrato clienteRegistrato){
        super(input);
        this.clienteRegistrato = clienteRegistrato;
    }


    @Override
    public void runBoundaryCliente() {
        
        boolean exit = false;
		
		while(!exit) {
			System.out.println("Cliente registrato: " + clienteRegistrato.getIdClienteRegistrato());
            System.out.println("");
			System.out.println("1. Visualizza libri disponibili");
            System.out.println("2. Popola Carrello");
            System.out.println("3. Completa acquisto");
            System.out.println("4. Scarica libri");
            System.out.println("5. Modifica carrello");
            System.out.println("6. Segnala mancata ricezione");
            System.out.println("7. Conferma ricezione");
			System.out.println("8. Esci");
            System.out.println();
			System.out.println("-------------------------------------------");
			System.out.println();
			
			String op = input.nextLine();
			
			if(op.equals("1")) {
                visualizzaLibriDisponibili();								
			} else if(op.equals("2")){
                popolaCarrello();
            } else if(op.equals("3")){
                completaAcquisto();
            }else if(op.equals("8")){
				exit = true;
			} else {
				System.out.println("Operazione non disponibile");
				System.out.println();
			}
		}
		
		System.out.println("Uscita");
        return;
    }
    

    public void popolaCarrello(){
        
        GestioneLibreria gl = GestioneLibreria.getInstance();
        //funzione per far visualizzare al cliente la lista dei libri disponibili
        visualizzaLibriDisponibili();
        String op = null;
        long codiceISBN = 0L;
        int qtRichiesta = 0;
        boolean valido = false;
        String conferma = null;

        try{
            do{

                while(!valido) {
                    
                    try {
                        System.out.println("Inserire tipo di libro da inserire, scegliere 1 per libro cartaceo e 2 per libro digitale");
                        System.out.println("1. Libro cartaceo");
                        System.out.println("2. Libro digitale");
                        
                        op = input.nextLine();
                        
                        if(op.equals("1") || op.equals("2"))
                            valido = true;
                    } catch(NoSuchElementException e) {
                            e.printStackTrace();
                    }catch(IllegalStateException e){
                            e.printStackTrace();
                    }
                }
                
                valido = false;
            
                if(op.equals("1")){

                    while(!valido){
                        try{

                            System.out.println("Inserire codice ISBN");
                            codiceISBN = Long.parseLong(input.nextLine());
                            //controllo se è presente il codice ISBN nel datbase
                            if(LibroCartaceoDAO.readLibroCartaceo(codiceISBN) != null)
                                valido = true;
                        } catch(NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }catch(IllegalStateException e){
                            e.printStackTrace();
                        } catch (DAOException e) {
                            e.printStackTrace();
                        } catch (DBConnectionException e) {
                            e.printStackTrace();
                        }
                        
                    }

                    valido = false;

                    while(!valido){
                    
                        try{
                            //richiesta della quantità da voler acquistare
                            System.out.println("Inserire quantità da acquistare");
                            qtRichiesta = Integer.parseInt(input.nextLine());
                            //controllo se è maggiore di 0
                            if(qtRichiesta > 0)
                                valido = true;

                        }catch(NoSuchElementException e) {
                            e.printStackTrace();
                        }catch(IllegalStateException e){
                            e.printStackTrace();
                        }
                        
                    }

                    valido = false;

                    try {
                        //chiamo la funzione popolaCarrello per libro cartaceo
                        gl.popolaCarrello(codiceISBN, qtRichiesta, clienteRegistrato);
                    } catch (OperationException e) {
                        System.out.println(e.getMessage());
                    }
                } else{

                    while(!valido){
                        try{

                            System.out.println("Inserire codice ISBN");
                            codiceISBN = Long.parseLong(input.nextLine());
                            if(LibroDigitaleDAO.readLibroDigitale(codiceISBN) != null)
                                valido = true;

                            
                        } catch(NoSuchElementException e) {
                            e.printStackTrace();
                        }catch(IllegalStateException e){
                            e.printStackTrace();
                        } catch (DAOException e) {
                            e.printStackTrace();
                        } catch (DBConnectionException e) {
                            e.printStackTrace();
                        }
                    
                    }

                    try {
                        gl.popolaCarrelo(codiceISBN, clienteRegistrato);
                    } catch (OperationException e) {
                        System.out.println(e.getMessage());
                    }

                    valido = false;
                
                }

                System.out.println("Vuoi continuare ad aggiungere libri? (Y | y))");
                conferma = input.nextLine();

            } while(conferma.equals("y") || conferma.equals("Y"));

            //stampa carrello quando non vuole più aggiungere
            
                gl.stampaCarrello(clienteRegistrato.getIdCarrello());
            
            //System.out.println("Prodotti aggiunti al carrello");

        } catch(OperationException e){
            System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println("Unexpexted exception, riprovare");
			System.out.println();
        }
    }
        

    public void completaAcquisto(){
        
        GestioneLibreria gl = GestioneLibreria.getInstance();

        try {
            gl.completaAcquisto(clienteRegistrato.getIdCarrello(), clienteRegistrato.getIdClienteRegistrato());
        
        } catch (OperationException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println("Unexpexted exception, riprovare");
			System.out.println();
        }
        
        


    }
}
