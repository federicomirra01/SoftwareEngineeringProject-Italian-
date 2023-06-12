package com.progettois;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Test;
import org.mockito.Mockito;

import com.progettois.boundary.ApplicationBoundary;

public class PopolaCarrelloTest {

    @Test
    public void PopolaCarrelloTest01(){
        
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("1") // genere libro
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(7)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }

    
    @Test
    public void PopolaCarrelloTest02(){
        
        
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("9") // tipo libro
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(7)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }

    
    @Test
    public void PopolaCarrelloTest03(){

        long CodiceISBN=1234567899;//codice ISBN errato

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("1") // tipo libro
                .thenReturn(Long.toString(CodiceISBN)) //codice ISBN 
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(8)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }

   

    @Test
    public void PopolaCarrelloTest04(){
        
        long CodiceISBN=1234567;//codice ISBN errato

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("1") // genere libro
                .thenReturn(Long.toString(CodiceISBN))//codice ISBN 
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(8)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void PopolaCarrelloTest05(){
        
        long CodiceISBN = 123456789010121212L;
        

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("1") // genere libro
                .thenReturn(Long.toString(CodiceISBN))//codice ISBN 
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(8)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void PopolaCarrelloTest06(){

        long CodiceISBN=1234567890;
        int qtRichiesta=1;

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("1") // genere libro
                .thenReturn(Long.toString(CodiceISBN)) //codice ISBN 
                .thenReturn(Integer.toString(qtRichiesta)) //quantita richiesta
                .thenReturn("n") //ferma
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(9)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }
    @Test
    public void PopolaCarrelloTest07(){
        
        long CodiceISBN=1234567890;
        int qtRichiesta=1;

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("1") // genere libro
                .thenReturn(Long.toString(CodiceISBN)) //codice ISBN 
                .thenReturn(Integer.toString(qtRichiesta)) //quantita richiesta
                .thenReturn("Y") //continua
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(10)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void PopolaCarrelloTest08(){
            
        long CodiceISBN=1234567890;
        int qtRichiesta=1;

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("1") // genere libro
                .thenReturn(Long.toString(CodiceISBN)) //codice ISBN 
                .thenReturn(Integer.toString(qtRichiesta)) //quantita richiesta
                .thenReturn("n") //ferma
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(9)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }


    @Test
    public void PopolaCarrelloTest09(){
            
        long CodiceISBN=1234567890;
        int qtRichiesta=3;

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("1") // genere libro
                .thenReturn(Long.toString(CodiceISBN)) //codice ISBN 
                .thenReturn(Integer.toString(qtRichiesta)) //quantita richiesta
                .thenReturn("n") //ferma
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(9)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void PopolaCarrelloTest10(){
            
        long CodiceISBN=1234567890;
        int qtRichiesta=5;
        //test controllato con quantità disponibile 4 (thinking in java)

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //aaccesso
                .thenReturn("CarmineTest") // nome utente
                .thenReturn("1") //pssw
                .thenReturn("2") // funzione
                .thenReturn("1") // genere libro
                .thenReturn(Long.toString(CodiceISBN)) //codice ISBN 
                .thenReturn(Integer.toString(qtRichiesta)) //quantita richiesta
                .thenReturn("n") //ferma
                .thenReturn(null); //ferma

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(9)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }


}
    


