package com.example.bibliosystem.common;

/**
 * Classe Helper
 * Ensemble de méthodes utiles et réutilisables
 */
public class Helper {

    /**
     * Méthode de vérification de la longueur d'une chaine de charactère
     * @param sToCheck Chaine à vérifier
     * @param iFrom Nombre de charactères minimal
     * @param iTo Nombre de charactères maximal
     * @return un booléen
     */
    public static Boolean checkStringLengthBetween(String sToCheck, Integer iFrom, Integer iTo){
        //vérification des inputs
        if (iFrom < 0){ iFrom = 0; }
        if (iTo < 0 || iTo < iFrom){ iTo = iFrom; }

        if (sToCheck.equals("null")
                || sToCheck.length() < iFrom
                || sToCheck.length() > iTo){
            return false;
        }
        return true;
    }
}
