package com.example.utazasiiroda;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.time.LocalDate;
import java.util.Optional;

public class util {
    public void hiba(String ablakCim, String hibaUzenet) {
        Alert confirm = new Alert(Alert.AlertType.ERROR, null, ButtonType.OK);
        confirm.setTitle(ablakCim);
        confirm.setHeaderText(hibaUzenet);
        Optional<ButtonType> answer = confirm.showAndWait();

    }
// Tesztelt
    public String kedvezmenyesArSzamolo(String alapErtek, int szazalek) {
        return String.valueOf(Integer.parseInt(alapErtek) - (Integer.parseInt(alapErtek) * szazalek / 100));
    }
    //Tesztelt
    public boolean Ketto_tizennyolcE(int ev) {

        return  ev > 2 && ev <= 18 ? true : false;
    }
    //Tesztelt
    public boolean keteves(int ev) {
        return ev <= 2  ? true : false;}

    //Tesztelt
    public boolean uresEdatum(LocalDate datum) {
        System.out.println("Üres - datum = " + datum);
        return datum == null ? false : true;
    }
    /**Kiír egy metődust
        - változót
            és az ő értékét*/

    public void tesztUzenet(String metódus, String valtozo, int ertek) {
        System.out.print("*** "+metódus + " -- "+ "Változó név :  "+ valtozo +" - " +"Értéke : "+ertek  );
        System.out.println();
    }
}
