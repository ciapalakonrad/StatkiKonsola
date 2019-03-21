import java.util.*;
import java.util.Scanner;

public
    class Statki{

    public static void main(String[] args) {
        char[][] plansza = new char[19][77];
        plansza(plansza);

        char[][] plansza2 = new char[19][77];
        plansza(plansza2);

        boolean gracz1 = true;
        boolean gracz2 = false;
        boolean gameRun = true;

        statek(plansza, 1);
        statek(plansza, 1);
        statek(plansza, 1);
        statek(plansza, 1);
        statek(plansza, 2);
        statek(plansza, 2);
        statek(plansza, 2);
        statek(plansza, 3);
        statek(plansza, 3);
        statek(plansza, 4);

        statek(plansza2, 1);
        statek(plansza2, 1);
        statek(plansza2, 1);
        statek(plansza2, 1);
        statek(plansza2, 2);
        statek(plansza2, 2);
        statek(plansza2, 2);
        statek(plansza2, 3);
        statek(plansza2, 3);
        statek(plansza2, 4);

        int strzaly = 40;
        int trafienia = 0;
        int strzaly2 = 40;
        int trafienia2 = 0;
        int numerGracza = 1;

        while(gameRun == true){
            if(gracz1 == true){
                if(strzaly > 0 && trafienia < 20) {
                    wyswietlPlansze(plansza);
                    wyswietlPlansze(plansza2);
                    numerGracza = 1;
                    trafienia = rozgrywka(plansza, strzaly, trafienia, numerGracza);
                    strzaly--;
                    gracz1 = false;
                    gracz2 = true;
                }
                else{
                    podsumowanie(trafienia, strzaly, numerGracza);
                    gameRun = false;
                }
            }

            if(gracz2 == true) {
                if(strzaly2 > 0 && trafienia2 < 20) {
                    wyswietlPlansze(plansza);
                    wyswietlPlansze(plansza2);
                    numerGracza = 2;
                    trafienia2 = rozgrywka(plansza2, strzaly2, trafienia2, numerGracza);
                    strzaly2--;
                    gracz1 = true;
                    gracz2 = false;
                }
                else{
                    podsumowanie(trafienia2, strzaly2, numerGracza);
                    gameRun = false;
                }
            }
        }
    }

    public static void plansza(char[][] plansza) {          //tworzenie planszy do gry
        for (int w = 0; w < plansza.length; w++) {
            for (int k = 0; k < plansza[0].length; k++) {
                plansza[w][k] = '%';
            }
        }
    }

    public static void oddzielPlansze() {
        System.out.println("==========================================================================================================================================================");
        System.out.println("");
    }

    public static final boolean DEBUG = true;

    public static void wyswietlPlansze(char[][] plansza) {      //wystwietlanie na konsolę
        oddzielPlansze();
        for (int w = 0; w < plansza.length; w++) {
            if (DEBUG == true) {
                for (int k = 0; k < plansza[0].length; k++) {
                    System.out.print(" " + plansza[w][k]);
                }
                System.out.println("");
            } else {
                for (int k = 0; k < plansza[0].length; k++) {
                    if (plansza[w][k] == 'S') {
                        System.out.print(" " + '%');            //zakrywanie statków
                    } else {
                        System.out.print(" " + plansza[w][k]);
                    }
                }
                System.out.println("");
            }
        }
        oddzielPlansze();
    }

    public static void statek(char[][] plansza, int rozmiar) {      //tworzenie statku
        if (Math.random() < 1.0 ) {
            //pozioma wersja statku
            int kolumna = (int) (Math.random() * 73);       //zeby statek nie wyszedl poza tablice
            int wiersz = (int) (Math.random() * 15);
            for (int i = 0; i < rozmiar; i++) {
                plansza[wiersz][kolumna + i] = 'S';
            }
        } else {
            //pionowa wersja statku
            int kolumna = (int) (Math.random() * 15);
            int wiersz = (int) (Math.random() * 73);
            for (int i = 0; i < rozmiar; i++) {
                plansza[wiersz + i][kolumna] = 'S';
            }
        }
    }

    public static int rozgrywka(char[][] plansza, int strzaly, int trafienia, int numerGracza) {         //faktyczna rozgrywka
        Scanner wejscie = new Scanner(System.in);
        int kolumna;
        int wiersz;

        System.out.println("Gracz" + numerGracza);
        System.out.println("Zostało Ci: " + strzaly + " strzałów!");

        System.out.println("Wybierz wiersz, w który uderzysz: ");
        wiersz = wejscie.nextInt();
        //przypadek, gdy uzytkownik wybral zbyt duza/mala liczbe
            while (wiersz > 19 || wiersz < 1)
            {
                System.out.println("Wybierz wiersz spośród 1/19");
                wiersz = wejscie.nextInt();
            }
         System.out.println("Wybierz kolumnę, w którą uderzysz: ");
         kolumna = wejscie.nextInt();

            //przypadek, gdy uzytkownik wybral zbyt duza/mala liczbe
            while (kolumna > 77 || kolumna < 1)
            {
                System.out.println("Wybierz kolumnę spośród 1/77");
                kolumna = wejscie.nextInt();
            }
            if (plansza[wiersz - 1][kolumna - 1] == 'S') {
                trafienia++;
                System.out.println("Trafiłeś!!!!!!!!!");
                plansza[wiersz - 1][kolumna - 1] = '!';
            } else {
                System.out.println("Spróbuj jeszcze raz!!!!!");
                plansza[wiersz - 1][kolumna - 1] = 'M';
            }
        return trafienia;
    }

    public static void podsumowanie(int trafienia, int strzaly, int numerGracza) {                       //dodatkowy tekst na zakonczenie gry
        if (trafienia < 20)     //laczna suma wszystkich masztow w statkach(4,33,222,1111)
            System.out.println("Przegrałeś :( graczu: " + numerGracza);
        if (strzaly < 1)
            System.out.println("Wykorzystałeś wszystkie strzały, graczu: " + numerGracza);
        else if (trafienia >= 20) {
            System.out.println("Wygrałęś! Graczu: " + numerGracza);
        }
        System.out.println("Dobrze Ci poszło!");
    }
}

