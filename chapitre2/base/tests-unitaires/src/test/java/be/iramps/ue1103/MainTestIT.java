package be.iramps.ue1103;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.iramps.ue1103.Pret.Pret;
import be.iramps.ue1103.Pret.Projet;

public class MainTestIT {
    private Projet projet;
    private Pret pret;

    @BeforeEach
    void setUp() {
        projet = new Projet();
        pret = new Pret();
    }

    @Test
    void testCalculRestantDu_TestValues() {

        Assertions.assertAll(
                () -> {
                    projet.setPrixHabitation(100_000);
                    projet.setRevenuCadastral(700);
                    projet.setFraisNotaireAchat(4_150);
                    projet.setFraisTransformation(60_000);

                    pret.setFraisDossierBancaire(500);
                    pret.setFraisNotaireCredit(5_475);
                    pret.setNombreAnnees(15);
                    pret.setTauxAnnuel(0.04);
                    double montantEmprunt = projet.calculResteAEmprunter();
                    double apportBancaire = pret.calculRestantDu(montantEmprunt);

                    // la valeur attendue
                    assertEquals(147_240.00, montantEmprunt, "valeur attendu pour le montant emprunt");
                    assertEquals(5_975.00, apportBancaire, "valeur attendu pour le montant ");

                },

                () -> {
                    projet.setPrixHabitation(350_000.00);
                    projet.setRevenuCadastral(500);
                    projet.setFraisNotaireAchat(3500.00);
                    projet.setFraisTransformation(10_500);

                    pret.setFraisDossierBancaire(400);
                    pret.setFraisNotaireCredit(4_400.00);
                    pret.setNombreAnnees(15);
                    pret.setTauxAnnuel(0.05);
                    double montantEmprunt = projet.calculResteAEmprunter();
                    double apportBancaire = pret.calculRestantDu(montantEmprunt);

                    // la valeur attendue 
                    assertEquals(325_017.0000 , montantEmprunt, "valeur attendu pour le montant emprunt");
                    assertEquals(4_800.00, apportBancaire, "valeur attendu pour le montant ");

                },
                () -> {
                    projet.setPrixHabitation(600_000.00);
                    projet.setRevenuCadastral(750);
                    projet.setFraisNotaireAchat(3000.00);
                    projet.setFraisTransformation(25_000.00);

                    pret.setFraisDossierBancaire(850);
                    pret.setFraisNotaireCredit(3_000.00);
                    pret.setNombreAnnees(25);
                    pret.setTauxAnnuel(0.08);
                    double montantEmprunt = projet.calculResteAEmprunter();
                    double apportBancaire = pret.calculRestantDu(montantEmprunt);

                    // la valeur attendue 
                    assertEquals(563_850.00 , montantEmprunt, "valeur attendu pour le montant emprunt");
                    assertEquals(3_850.00, apportBancaire, "valeur attendu pour le montant ");

                }
                
                ); // end AssertAll

                
    }
}
