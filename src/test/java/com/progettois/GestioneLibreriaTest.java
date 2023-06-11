package com.progettois;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;
import org.mockito.Mockito;

import com.progettois.boundary.ApplicationBoundary;
import com.progettois.boundary.BoundaryClienteRegistrato;
import com.progettois.boundary.BoundaryDipendente;
import com.progettois.control.GestioneLibreria;
import com.progettois.entity.EntityClienteRegistrato;
import com.progettois.entity.EntityDipendente;
import com.progettois.exception.ISBNAlreadyBoundedException;
import com.progettois.exception.OperationException;


public class GestioneLibreriaTest {

    @Test
    public void carrelloVuotoTest(){
    
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1")
                .thenReturn("tommaso")
                .thenReturn("tommaso")
                .thenReturn("3")
                .thenReturn(null);

        BoundaryClienteRegistrato bC = new BoundaryClienteRegistrato(scannerMock, new EntityClienteRegistrato(10, "fmirra", "1", "Federico", "Mirra", "via Pietro Mascagni, 5", 81041,380641874,"Bellona",
         "federicomirra01@gmail.com", 9));

        bC.completaAcquisto();

        verify(scannerMock, times(5)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void qtRichiestaTest(){

        Scanner scannerMock = Mockito.mock(Scanner.class);

        /*when(scannerMock.nextLine())
                .thenReturn("1")
                .thenReturn("tommaso")
                .thenReturn("tommaso")
                .thenReturn("3")
                .thenReturn(null);*/


        BoundaryClienteRegistrato bC = new BoundaryClienteRegistrato(scannerMock, new EntityClienteRegistrato(10, "fmirra", "1", "Federico", "Mirra", "via Pietro Mascagni, 5", 81041,380641874,"Bellona",
         "federicomirra01@gmail.com", 9));

        //bC.popolaCarrello();

        bC.completaAcquisto();

        //verify(scannerMock, times(1)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }





    
    

    @Test
    public void inserisciLibroTest1(){

        GestioneLibreria gl = GestioneLibreria.getInstance();
        
        String Titolo = "Io speriamo che me la cavo";
        String Autori = "Marcello D’Orta";
        String CasaEditrice = "Mondadori";
        long CodiceISBN = 8804388668L;
        String Genere = "Narrativa";
        double Prezzo = 6.00;
        int QuantitàDisponibile = 10;
        
        
        try {
            gl.inserisciLibro(CodiceISBN, Titolo, Autori, CasaEditrice, Genere, Prezzo, QuantitàDisponibile);
        } catch (OperationException e) {
            e.printStackTrace();
        } catch (ISBNAlreadyBoundedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void inserisciLibroTest2(){
        
        String Titolo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
       
        long CodiceISBN = 1234567899L;
       

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN), Titolo).thenReturn(null);
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(4)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
        
    }

    @Test
    public void inserisciLibroTest3(){

        
        String Titolo = "dko@§";
        long CodiceISBN = 4362781904L;
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN), Titolo).thenReturn(null);
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(4)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
        
    }

    @Test
    public void inserisciLibroTest4(){
        
        String Titolo = "La coscienza di Zeno";
        String Autori = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        long CodiceISBN = 8807900491L;
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN), Titolo, Autori).thenReturn(null);
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(5)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void inserisciLibroTest5(){

        
        String Titolo = "Il piacere";
        String Autori = "]§#DKDO";
        long CodiceISBN = 2748301563L;
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN), Titolo, Autori).thenReturn(null);
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(5)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void inserisciLibroTest6(){
        
        String Titolo = "Le bizzarre avventure di Jojo";
        String Autori = "Hirohiko Araki";
        String CasaEditrice = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        long CodiceISBN = 3627859301L;
        
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN), Titolo, Autori, CasaEditrice).thenReturn(null);
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(6)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void inserisciLibroTest7(){
        
        String Titolo = "Fallen";
        String Autori = "Linda Castillo";
        String CasaEditrice = "[#@§?ekfni";
        long CodiceISBN = 9483722930L;
        
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN), Titolo, Autori, CasaEditrice).thenReturn(null);
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(6)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void inserisciLibroTest8(){
        
        long CodiceISBN = 948302833L;
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN)).thenThrow(new RuntimeException("Test"));
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(3)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void inserisciLibroTest9(){

        long CodiceISBN = 11111119743L;
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN)).thenThrow(new RuntimeException("Test"));
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(3)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void inserisciLibroTest10(){

        
       
        long CodiceISBN = 0007527526;
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN)).thenThrow(new RuntimeException("Test"));
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(3)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void inserisciLibroTest11(){
        
        String Titolo = "Il postino di Neruda";
        String Autori = "Antonio Skarmeta";
        String CasaEditrice = "Mondadori";
        long CodiceISBN = 9384777222L;
        String Genere = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN), Titolo, Autori, CasaEditrice, Genere).thenReturn(null);
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(7)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void inserisciLibroTest12(){
        
        String Titolo = "Il piccolo principe";
        String Autori = "Antoine De Saint-Exupéry";
        String CasaEditrice = "Feltrinelli";
        long CodiceISBN = 9993338273L;
        String Genere = "+-§.sjejw";
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN), Titolo, Autori, CasaEditrice, Genere).thenReturn(null);
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(7)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void inserisciLibroTest13(){
        
        String Titolo = "Storia di una ladra di libri";
        String Autori = "Markua Zusak";
        String CasaEditrice = "Pickwick";
        long CodiceISBN = 1111223244L;
        String Genere = "Drammatico";
        double Prezzo = 7.99;
        int QuantitàDisponibile = 0;
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine()).thenReturn("1", Long.toString(CodiceISBN), Titolo, Autori, CasaEditrice, Genere, Double.toString(Prezzo), Integer.toString(QuantitàDisponibile)).thenThrow(new RuntimeException("Test"));
    
        BoundaryDipendente bD = new BoundaryDipendente(scannerMock, new EntityDipendente("dipendente", "dipendente"));

        bD.inserisciLibro();
        
        Mockito.verify(scannerMock, Mockito.times(9)).nextLine();
        Mockito.verifyNoMoreInteractions(scannerMock);
    }



}

