import java.util.Scanner;

import com.progettois.boundary.ApplicationBoundary;

public class GestioneLibreriaMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationBoundary applicationBoundary = new ApplicationBoundary(scanner);
        applicationBoundary.runApplication();
    }
}
