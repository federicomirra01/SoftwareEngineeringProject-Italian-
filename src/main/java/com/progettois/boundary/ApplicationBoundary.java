package com.progettois.boundary;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.progettois.abstractClass.Utente;
import com.progettois.control.GestioneLibreria;
import com.progettois.entity.EntityClienteRegistrato;
import com.progettois.entity.EntityDipendente;
import com.progettois.exception.OperationException;

public class ApplicationBoundary {

    private Scanner input;

    public ApplicationBoundary(){
        this.input = new Scanner(System.in);
    }

    public void runApplication(){

        String op = null;
        boolean valido = false;
        
        while(true){
            while(!valido){
                try{
        
                    System.out.println("Benvenuto!");
                    System.out.println("Inserire 1 per effettuare accesso");
                    System.out.println("Inserire 2 per usare l'applicazione come cliente occasionale");
                    System.out.println("Inserire 3 per chiudere l'applicazione");
                    

                    op = input.nextLine();
                    if(op.equals("1") || op.equals("2") || op.equals("3")){
                        valido = true;
                    }
                } catch (NoSuchElementException e){
                    e.printStackTrace();
                } catch (IllegalStateException e){
                    e.printStackTrace();
                }

            }

            if(op.equals("1")){
                accedi();
                valido = false;
            } else if(op.equals("2")) {
                BoundaryCliente bC = new BoundaryCliente(input);
                bC.runBoundaryCliente();
                valido = false;
            } else{
                return;
            }
        }
        
    }
    
    public void accedi(){
        
        String nomeUtente = null;
        String password = null;
        GestioneLibreria gl = GestioneLibreria.getInstance();
        Utente user = null;
        
        try{
            System.out.println("Inserire Nome Utente");
            nomeUtente = input.nextLine();

            System.out.println("Inserire password");
            password = input.nextLine();
        } catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        } catch(IllegalStateException e){
            System.out.println(e.getMessage());
        } 

        try {
            user = gl.accedi(nomeUtente, password);
        } catch (OperationException e) {
            System.out.println(e.getMessage());
        }
        
        if(user instanceof EntityDipendente){
            BoundaryDipendente bD = new BoundaryDipendente(input, (EntityDipendente) user);
            bD.runBoundaryDipendente();
        } else if (user instanceof EntityClienteRegistrato){
            EntityClienteRegistrato cR = (EntityClienteRegistrato) user;
            System.out.println(cR.getIdCarrello());
            
            BoundaryClienteRegistrato bCR = new BoundaryClienteRegistrato(input, cR);
            
            bCR.runBoundaryCliente();
        } else{
            System.out.println("Utente non trovato");
        }

    }


    
}

