package com.progettois;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.util.Scanner;
import org.junit.Test;
import org.mockito.Mockito;
import com.progettois.boundary.ApplicationBoundary;

public class CompletaAcquistoTest {

    @Test
    public void carrelloVuotoTest(){
    
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1")
                .thenReturn("tommaso") //nomeutente
                .thenReturn("tommaso") //password
                .thenReturn("3") //completaAcquisto
                .thenReturn(null);

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(5)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }

    @Test
    public void qtRichiestaTest(){

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //accedi
                .thenReturn("tommaso") //nomeutente
                .thenReturn("tommaso")  //password
                .thenReturn("2") //popola carrello
                .thenReturn("1") //cartaceo
                .thenReturn("1111111111") //ISBN libro test
                .thenReturn("1") //quantità richiesta
                .thenReturn("n") 
                .thenReturn("3") //completaAcquisto
                .thenReturn(null);

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(10)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }
    @Test
    public void qtRichiestaTest1(){

        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1") //accedi
                .thenReturn("tommaso") //nomeutente
                .thenReturn("tommaso")  //password
                .thenReturn("2") //popola carrello
                .thenReturn("1") //cartaceo
                .thenReturn("1111111111") //ISBN libro test
                .thenReturn("21") //quantità richiesta
                .thenReturn("n") 
                .thenReturn("3") //completaAcquisto
                .thenReturn(null);

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

        verify(scannerMock, times(10)).nextLine();
        verifyNoMoreInteractions(scannerMock);
    }
}