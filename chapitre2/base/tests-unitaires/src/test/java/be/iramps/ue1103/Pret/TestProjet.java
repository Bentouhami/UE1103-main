package be.iramps.ue1103.Pret;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

@Disabled
public class TestProjet {
    private static Projet projet;
    static Projet mockedProject;

    // @Disabled
    @BeforeAll
    public static void setup() {
        projet = new Projet();
        mockedProject = Mockito.spy(projet);

    }

    @Disabled
    @Test
    public void calculDroitEnregistrement() {

        mockedProject.setPrixHabitation(45_000.00);
        mockedProject.setRevenuCadastral(150);

        Mockito.doReturn(40_000.00).when(mockedProject).calculAbattement();

        Assertions.assertEquals(300.00, mockedProject.calculDroitEnregistrement());

    }

    @Nested
    @DisplayName("Testing Calcul")
    class TestCalculTVA {
        // testTVATransferormation(){}
        // calculFraisTVATransferormationSimple(){}

       // @Disabled
        @Test
        public void calculTvaFraisTransformationValeurPositif() {

            Assertions.assertAll(

                    () -> {
                        projet.setFraisTransformation(90_000.00);
                        Assertions.assertEquals(5_400.00, projet.calculTVAFraisTransformation());
                    },

                    () -> {
                        projet.setFraisTransformation(0.00);
                        Assertions.assertEquals(0.00, projet.calculTVAFraisTransformation());
                    },

                    () -> {
                        projet.setFraisTransformation(100_000.00);
                        Assertions.assertEquals(6_000.00, projet.calculTVAFraisTransformation());
                    },

                    () -> {
                        projet.setFraisTransformation(59_595.00);
                        Assertions.assertEquals(3_575.70, projet.calculTVAFraisTransformation());
                    },

                    () -> {
                        projet.setFraisTransformation(1.00);
                        Assertions.assertEquals(0.06, projet.calculTVAFraisTransformation());
                    });

        }

        @Disabled
        @Test
        @DisplayName("TvaFraisTransformationValeurNegatif")
        public void calculTvaFraisTransformationValeurNegatif() {

            Assertions.assertThrows(Exception.class, () -> {
                projet.setFraisTransformation(-1.00);
                projet.calculTVAFraisTransformation();

            }, "Erreur: Les frais transformation ne dois pas etre negatif.");
        }

        //@Disabled
        @ParameterizedTest
        @MethodSource("getArgsTVA")
        public void calculTvaFraisTransformationValeurNegatif(double x, double y) {

            // Assertions.assertThrows(Exception.class, () -> {
            // projet.setFraisTransformation(-1.00);
            // projet.calculTVAFraisTransformation();

            // });

            Projet projet = new Projet();
            projet.setFraisTransformation(x);
            Assertions.assertEquals(y, projet.calculTVAFraisTransformation());

            // projet.setFraisTransformation(x);
            // Assertions.assertThrows(Exception.class,()->
            // projet.calculTVAFraisTransformation());

        }

        private static Stream<Arguments> getArgsTVA() {
            return Stream.of(
                    Arguments.arguments(250_000.00, 15_000.00),
                    Arguments.arguments(90_000.00, 5400.00));

        }
    }

}
