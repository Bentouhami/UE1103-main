package be.iramps.ue1103;

import java.util.Locale;

import be.iramps.ue1103.Pret.Pret;
import be.iramps.ue1103.Pret.Projet;

public class Main {
    public static void main(String[] args) {
        // Préparation pour le projet hypothécaire
        Projet projet = new Projet();
        projet.setPrixHabitation(100_000.00);
        projet.setRevenuCadastral(700);
        projet.setFraisNotaireAchat(4_150); // Prix forfétaire pour un bien de 195.000,00 €. Varie selon le montant, bien entendu.
        projet.setFraisTransformation(60_000); 

        double apportPersonnel = projet.calculApportMinimal(); // 24_110.00
        double montantEmprunt = projet.calculResteAEmprunter(); // 147_240.00
        
        
        // Résultat du projet:
        System.out.println(String.format(Locale.FRENCH,"Total projet:\t %(,11.2f EUR", projet.calculTotalProjetAchat())); 
        System.out.println(String.format(Locale.FRENCH,"\tApport personnel:\t %(,11.2f EUR", apportPersonnel));
        System.out.println(String.format(Locale.FRENCH,"\tReste à emprunter:\t %(,11.2f EUR", montantEmprunt));
        System.out.println();

        // Préparation pour l'emprunt
        Pret pret = new Pret();
        pret.setFraisDossierBancaire(500); // Prix forfétaire, varie.
        pret.setFraisNotaireCredit(5_475); // Une hypothèque induit un acte notarié. Prix forfétaire sur 195.000,00 €
        pret.setNombreAnnees(15);
        pret.setTauxAnnuel(0.04); // Moyenne en 2023

        double apportBancaire = pret.calculRestantDu(montantEmprunt);

        System.out.println(String.format(Locale.FRENCH,"Total emprunt:\t %(,11.2f EUR", pret.calculTotalProjet(montantEmprunt)));
        System.out.println(String.format(Locale.FRENCH,"\tMensualités:\t\t %(,11.2f EUR", pret.calculMensualites(montantEmprunt)));
        System.out.println(String.format(Locale.FRENCH,"\tTotal des intérets:\t %(,11.2f EUR", pret.calculTotalInterets(montantEmprunt))); 
        System.out.println(String.format(Locale.FRENCH,"\tRestant dû:\t\t %(,11.2f EUR", apportBancaire));
        System.out.println();
        
        // Apport personnel total
        System.out.println(String.format(Locale.FRENCH,"Apport personnel total: %(,11.2f EUR", apportBancaire + apportPersonnel));

    }
}