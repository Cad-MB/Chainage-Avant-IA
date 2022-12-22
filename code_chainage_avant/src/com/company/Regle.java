package com.company;

import java.util.ArrayList;

public class Regle
{
    public ArrayList<String> Gauche = new ArrayList<String>();//la partie gauche de la regle
    public ArrayList<String> Droite = new ArrayList<String>();//la partie droite de la regle

    public Regle() { }
    //getters pour utiliser les valeurs
    public ArrayList<String> getGauche() {
        return Gauche;
    }

    public ArrayList<String> getDroite() {
        return Droite;
    }

}