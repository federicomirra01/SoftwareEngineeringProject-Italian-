package com.progettois;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Test;
import org.mockito.Mockito;

import com.progettois.boundary.ApplicationBoundary;
import com.progettois.boundary.BoundaryClienteRegistrato;
import com.progettois.entity.EntityClienteRegistrato;

public class CompletaAcquistoTest {

    @Test
    public void carrelloVuotoTest(){
    
        Scanner scannerMock = Mockito.mock(Scanner.class);

        when(scannerMock.nextLine())
                .thenReturn("1")
                .thenReturn("tommaso")
                .thenReturn("tommaso")
                .thenReturn("3")
                .thenReturn(null);

        ApplicationBoundary aB = new ApplicationBoundary(scannerMock);

        aB.runApplication();

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
    
}
