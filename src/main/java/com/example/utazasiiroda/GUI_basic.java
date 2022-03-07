package com.example.utazasiiroda   ;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class GUI_basic extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    util util = new util();

    GridPane rootNode = new GridPane();

    DatePicker szuletesNap = new DatePicker();
    DatePicker utazasNap = new DatePicker();
    DatePicker visszateresNap = new DatePicker();

    TextField alapErtek = new TextField();
    TextField kedvezmenyMertek = new TextField();
    TextField kedvezmenyesAr = new TextField();
    CheckBox usaUtazas = new CheckBox("USA-ba utazik");
    Button kedvezmenyGomb = new Button("Várható kedvezmény %ban");
    Button okGomb = new Button("Ok");
    Button kilepesGomb = new Button("Kilépés");
    Button resetGomb = new Button("Reset");
    Scene scene = new Scene(rootNode, 500, 350);

    int szazalek = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Utazási iroda");
        rootNode.setPadding(new Insets(15));
        rootNode.setHgap(5);
        rootNode.setVgap(5);
        rootNode.setAlignment(Pos.CENTER);
        GridPane.setHalignment(kedvezmenyGomb, HPos.LEFT);
        GridPane.setHalignment(okGomb, HPos.CENTER);
        GridPane.setHalignment(resetGomb, HPos.CENTER);
        GridPane.setHalignment(kilepesGomb, HPos.CENTER);

        reset();


        alapErtek.setEditable(false);
        kedvezmenyMertek.setEditable(false);
        kedvezmenyesAr.setEditable(false);

        szuletesNap.setEditable(false);
        utazasNap.setEditable(false);
        visszateresNap.setEditable(false);

        primaryStage.setScene(scene);
		primaryStage.setResizable(false);

        rootNode.add(new Label("Kérem a születési dátumot"), 0, 0);
        rootNode.add(szuletesNap, 0, 1);
        rootNode.add(new Label("Utazás napja"), 1, 0);
        rootNode.add(utazasNap, 1, 1);
        rootNode.add(new Label("Vissza út"), 2, 0);
        rootNode.add(visszateresNap, 2, 1);

        rootNode.add(new Label(" "), 0, 2);

        rootNode.add(usaUtazas, 0, 3);
        rootNode.add(kedvezmenyGomb, 2, 3);

        rootNode.add(new Label(" "), 0, 4);

        rootNode.add(new Label("Alap érték"), 0, 5);
        rootNode.add(alapErtek, 0, 6);
        rootNode.add(new Label("Kedvezmény mértéke"), 1, 5);
        rootNode.add(kedvezmenyMertek, 1, 6);
        rootNode.add(new Label("Kedvedzényes ár"), 2, 5);
        rootNode.add(kedvezmenyesAr, 2, 6);

        rootNode.add(new Label(" "), 0, 7);
        rootNode.add(new Label(" "), 0, 8);

        rootNode.add(okGomb, 0, 9);
        rootNode.add(kilepesGomb, 1, 9);
        rootNode.add(resetGomb, 2, 9);

		rootNode.isResizable();
        primaryStage.close();
        primaryStage.show();

//******************************************************** Tesztadatok Betőltése********************************
        tesztAdatok();


        kilepesGomb.setOnAction(eventB);
        kedvezmenyGomb.setOnAction(eventB);
        resetGomb.setOnAction(eventB);
        okGomb.setOnAction(eventB);
    }
// *************************************************************************************Teszt Adatok**************************************************************
    public void tesztAdatok() {

        szuletesNap.setValue(LocalDate.of(2021, 12, 19));
        utazasNap.setValue (LocalDate.of(2022, 10, 19));
        visszateresNap.setValue(LocalDate.of(2022, 10, 26));

    }
//********************************************************Kilikkek****************************************************
    EventHandler<ActionEvent> eventB = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == kilepesGomb) {
                System.out.println("Kilépés gomb");
                Platform.exit();
            } else if (actionEvent.getSource() == kedvezmenyGomb) {

                System.out.println("kedvezmenyGomb");
                kedveznéyGomb();

                kedvezmenyMertek.setText(String.valueOf(szazalek));
                kedvezmenyesAr.setText(util.kedvezmenyesArSzamolo(alapErtek.getText(), szazalek));


            } else if (actionEvent.getSource() == resetGomb) {
               reset();

                System.out.println(resetGomb);
            } else if (actionEvent.getSource() == okGomb) {
                boolean helyesE;
                helyesE = kitoltesMegfelelo();

                if (!helyesE) {
                    //util.hiba("Hiba a dátum mezőben", "Utazás nap vagy visszatérés nap dátum hiba");
                    return;
                } else {
                    szamitasV2();
                    kedvezmenyMertek.setText(String.valueOf(szazalek));
                    kedvezmenyesAr.setText(util.kedvezmenyesArSzamolo(alapErtek.getText(), szazalek));

                }

                System.out.println(okGomb);
            }

        }
    //*********************************************************Kedvezmény gomb ***********************************************


    private void kedveznéyGomb() {
        LocalDate now = LocalDate.now();
        int nap = 0,
                ev = 0;

        if (util.uresEdatum(szuletesNap.getValue())) {
            ev = (int) ChronoUnit.YEARS.between(szuletesNap.getValue(), now);
        }

        if ((util.uresEdatum(utazasNap.getValue())) && (util.uresEdatum(visszateresNap.getValue()))) {
            nap = (int) ChronoUnit.DAYS.between(utazasNap.getValue(), visszateresNap.getValue());
        }

        szazalek = nap >= 6 ? 10 : 0;

        if (util.keteves(ev)) {
            util.tesztUzenet("GUI_basic.kedveznéyGomb","ev",ev);
            szazalek = 100;
            return;

    }else if (util.Ketto_tizennyolcE(ev)){
        szazalek+=40;
            System.out.println("GUI_basic.kedveznéyGomb");
            System.out.println("szazalek = " + szazalek);

    }


        System.out.println("****GUI_basic.kedveznéyGomb");
        System.out.println("nap = " + nap);
        System.out.println("ev = " + ev);
        }

    //*********************************************************Számítás 2 ***********************************************
        private void szamitasV2() {
            // értékadások
            LocalDate now = LocalDate.now();
            int ev=(int) ChronoUnit.YEARS.between(szuletesNap.getValue(), now);
            int nap=(int) ChronoUnit.DAYS.between(utazasNap.getValue(), visszateresNap.getValue());

            System.out.println("****GUI_basic.szamitasV2");
            System.out.println("ev = " + ev);
            System.out.println("nap = " + nap);
            // százalék kezdő értéke
            szazalek = nap >= 6 ? 10 : 0;

            //Ha 2 éves
            if(util.keteves(ev)){
                szazalek = 100;
                System.out.println("****GUI_basic.szamitasV2");
                System.out.println("2 éves");
                return;
            // ha 2-18 év közötti
            }else if (util.Ketto_tizennyolcE(ev)){
                szazalek+=40;
            // 18 év feletti USAba utazik ! Hétfő vagy péntek
           }
            else if (ev> 18 && usaUtazas.isSelected()==true && !(utazasNap.getValue().getDayOfWeek() ==DayOfWeek.FRIDAY || utazasNap.getValue().getDayOfWeek() == DayOfWeek.MONDAY))
            {   szazalek+=20;
                System.out.println("IDE");
            }

            if (usaUtazas.isSelected()==false &&!(utazasNap.getValue().getDayOfWeek() ==DayOfWeek.FRIDAY || utazasNap.getValue().getDayOfWeek() == DayOfWeek.MONDAY))
            {
                szazalek+=25;
            }
            System.out.println("szazalek (Visszatérés előtt)= " + szazalek);


        }


        // **********************************************************************Hónap kitőltés ellenőrzése************************************************************

        private boolean kitoltesMegfelelo() {
            boolean visszErtek = true;
            LocalDate now = LocalDate.now();
            int nap = 0;
            int napos = 0;

            // Életkor kiszámítás
            if ((util.uresEdatum(szuletesNap.getValue())) && (util.uresEdatum(utazasNap.getValue()))) {
               napos = (int) ChronoUnit.DAYS.between(szuletesNap.getValue(), utazasNap.getValue());

            } else {
                visszErtek = false;
                szuletesNap.setStyle("-fx-background-color: RED");
                util.hiba("Dátum mező Hiba", "Születésnap vagy dátummező hiba");
            }
            // Utazási napok kiszámítása - Hiba üzenettel

            if ((util.uresEdatum(utazasNap.getValue())) && (util.uresEdatum(visszateresNap.getValue()))) {
                nap = (int) ChronoUnit.DAYS.between(utazasNap.getValue(), visszateresNap.getValue());
            } else {
                visszErtek = false;
                utazasNap.setStyle("-fx-background-color: RED");
                visszateresNap.setStyle("-fx-background-color: RED");
                util.hiba("Dátum mező Hiba", "Utazás nap vagy visszatérés nap dátum hiba");
            }




            // int oregebEMintMa = (int) ChronoUnit.DAYS.between(utazasNap.getValue(), now);
                                //*******//
            System.out.println("***GUI_basic.kitoltesMegfelelo");
            System.out.println("now = " + now);

            //System.out.println("oregebEMintMa = " + oregebEMintMa);



                if (szuletesNap.getEditor().getText() == "") {
                    visszErtek = false;
                    szuletesNap.setStyle("-fx-background-color: RED");
                } else {
                    szuletesNap.setStyle("-fx-background-color: black");
                }

            if (utazasNap.getEditor().getText() == "") {
                visszErtek = false;
                utazasNap.setStyle("-fx-background-color: RED");
            } else {
                utazasNap.setStyle("-fx-background-color: black");
            }
            if (visszateresNap.getEditor().getText() == "") {
                visszErtek = false;
                visszateresNap.setStyle("-fx-background-color: RED");
            } else {
                visszateresNap.setStyle("-fx-background-color: black");
            }
            if (nap < 0) {
                visszateresNap.setStyle("-fx-background-color: RED");
                utazasNap.setStyle("-fx-background-color: RED");
                visszErtek = false;
                util.hiba("Dátum hiba","A visszaérkezés nap megelőzi az utazás napot");
            }
            if (napos < 0) {
                utazasNap.setStyle("-fx-background-color: RED");
                szuletesNap.setStyle("-fx-background-color: RED");
                visszErtek = false;
                util.hiba("Dátum hiba","Születésnapja nem lehet nagyobb az utazás napjánál");
                //*****//

                System.out.println("GUI_basic.kitoltesMegfelelo");
                System.out.println("napos = " + napos);

            }

       /*  if (< 0){
                szuletesNap.setStyle("-fx-background-color: RED");
                visszErtek = false;
                System.out.println("napos = " + napos);
            }*/


            return visszErtek;
        }

    };

   // **********************************************************************RESET************************************************************

    public void reset() {


        /*szuletesNap.setValue(LocalDate.now());
        utazasNap.setValue(LocalDate.now());
        visszateresNap.setValue(LocalDate.now());*/
        utazasNap.getEditor().clear();
        utazasNap.setValue(null);

        visszateresNap.getEditor().clear();
        visszateresNap.setValue(null);

        szuletesNap.getEditor().clear();
        szuletesNap.setValue(null);



        kedvezmenyesAr.setText("");
        kedvezmenyMertek.setText("");
        usaUtazas.setSelected(false);

        alapErtek.setText("1000");

        szuletesNap.setStyle("-fx-background-color : black;");
        utazasNap.setStyle("-fx-background-color: black");
        visszateresNap.setStyle("-fx-background-color: black");

        szazalek = 0;

    }


//********************************************************************Gégi kódok **************************************

    /*EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource()==kilepesGomb)
            {
                Platform.exit();
            }
            else if (actionEvent.getSource()==resetGomb)
            {
                szuletesNap.getEditor().clear();
                utazasNap.getEditor().clear();
                visszateresNap.getEditor().clear();

                kedvezmenyesAr.setText("");
                kedvezmenyMertek.setText("");

                usaUtazas.setSelected(false);
            }
            else if (actionEvent.getSource()==kedvezmenyGomb)
            {
                Date maiNap = new Date();
                boolean isUsaUticel = usaUtazas.isSelected();
                double kedvezmenySzazalek = 0;

                if (isUsaUticel == false && !(utazasNap.getValue().getDayOfWeek() == DayOfWeek.FRIDAY || utazasNap.getValue().getDayOfWeek() == DayOfWeek.MONDAY))
                {
                    kedvezmenySzazalek += 25;
                }

                if (visszateresNap.getValue().minusDays(6).isAfter(utazasNap.getValue()))
                {
                    kedvezmenySzazalek += 10;
                }

                kedvezmenyMertek.setText(Double.toString(kedvezmenySzazalek));

            }
        }
    };
    static int eletkorSzamolo(Date szuletes)
    {
        float milisecs = System.currentTimeMillis() - szuletes.getTime();
        int years = Math.round(milisecs/31556952000L);
        return years;
    }*/

    // **********************************************************************Számolás************************************************************
      /*  private void szamitas() {
            LocalDate now = LocalDate.now();
            int ev=(int) ChronoUnit.YEARS.between(szuletesNap.getValue(), now);
            int nap=(int) ChronoUnit.DAYS.between(utazasNap.getValue(), visszateresNap.getValue());
            System.out.println("ev = " + ev);
            System.out.println("nap = " + nap);
            szazalek = 0;

         *//*   if (ev<=2){
                szazalek = 100;
                return ;
            }else if (ev >2 && ev <=18 ){
                szazalek+=40;
            }else if (nap > 6){
                szazalek+=10;
            }*//*


        if (usaUtazas.isSelected() == false && (utazasNap.getValue().getDayOfWeek() !=DayOfWeek.FRIDAY || utazasNap.getValue().getDayOfWeek() != DayOfWeek.MONDAY ))
                szazalek+=25;
            System.out.println("usaUtazas.isSelected() = " + usaUtazas.isSelected());
        }*/



    //      if (usaUt; == false && (utazasNap.getValue().getDayOfWeek() != DayOfWeek.FRIDAY || utazasNap.getValue().getDayOfWeek() != DayOfWeek.MONDAY))

}