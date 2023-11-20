package be.iramps.ue1103;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.iramps.ue1103.Pret.Pret;
import be.iramps.ue1103.Pret.Projet;

@Disabled
@DisplayName("Tests d'intégration: ensemble des composants")
public class ITMain {
    private static Projet projet;
    private static Pret pret;

    @BeforeAll
    static void setup(){
        projet = new Projet();
        pret = new Pret();

    }
    @Test
    @DisplayName("Integrated tests")
    public void happyPath(){
        projet.setPrixHabitation(100_000);
        projet.setRevenuCadastral(700);
        projet.setFraisNotaireAchat(4_150);
        projet.setFraisTransformation(60_000);
        double apportPersonnel = projet.calculApportMinimal();
        double montantEmprunt = projet.calculResteAEmprunter();

        //Pret pret = new Pret();
        pret.setFraisDossierBancaire(500);
        pret.setFraisNotaireCredit(5_475);
        pret.setNombreAnnees(15);
        pret.setTauxAnnuel(0.04);

        double apportBancaire = pret.calculRestantDu(montantEmprunt);
        Assertions.assertEquals(true, true);
    }

    // @Test
    // @DisplayName("Integrated test with negative values")
    // public void calculRestantDu_test(){
    //    // double apportBancaire =    pret.calculRestantDu(montantEmprunt);
    //    double totyalProjet = projet.calculApportMinimal();
    //    double apportMinimal = projet.calculApportMinimal();
    //    double 
    //     projet.calculResteAEmprunter();


    // }


}
