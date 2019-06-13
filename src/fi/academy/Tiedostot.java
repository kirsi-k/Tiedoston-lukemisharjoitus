package fi.academy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Tiedostot {

    public static void main(String[] args) {
        int sanojenmaara = 0;
        String pisinsana = "";
        String lyhyinsana = "";
        double sanojenpituudenka = 0;
        int kaikkiensanojenpituus = 0;

        try (FileReader tiedostonlukija = new FileReader("tekstitiedosto.txt");
        BufferedReader puskuroitulukija = new BufferedReader(tiedostonlukija)){
            StringBuilder teksti = new StringBuilder();
            String rivi;
            while ((rivi = puskuroitulukija.readLine())!=null){
                teksti.append(rivi).append("\n");
                String[] sanat = rivi.split("[\\s\\p{Punct}]+");
                sanojenmaara += sanat.length;
                ArrayList<String> sanalista = new ArrayList(Arrays.asList(sanat));
                pisinsana = sanalista.get(0);
                for (int i= 1; i < sanalista.size(); i++){
                    if (sanalista.get(i).length() > pisinsana.length()){
                        pisinsana = sanalista.get(i);
                    }
                }
                lyhyinsana = sanalista.get(0);
                for (int i= 1; i < sanalista.size(); i++){
                    if (sanalista.get(i).length() < lyhyinsana.length()){
                        lyhyinsana = sanalista.get(i);
                    }
                }
                for (int i= 0; i < sanalista.size(); i++){
                    kaikkiensanojenpituus += sanalista.get(i).length();
                }
                sanojenpituudenka =  1.0 * kaikkiensanojenpituus / sanojenmaara;
            }

        } catch(FileNotFoundException e){
            System.err.println("Ei löytynyt tiedostoa!");
        }catch(IOException e){
            System.err.println("Tapahtui virhe!");
        }
        System.out.println("Tiedostossa on sanoja yhteensä " + sanojenmaara);
        System.out.println("Tiedoston pisin sana on " + pisinsana);
        System.out.println("Tiedoston lyhyin sana on " + lyhyinsana);
        System.out.println("Tiedoston sanojen pituuden keskiarvo on " + sanojenpituudenka);
    }
}
