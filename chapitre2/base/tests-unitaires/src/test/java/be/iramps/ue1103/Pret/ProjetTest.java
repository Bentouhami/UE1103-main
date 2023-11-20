package be.iramps.ue1103.Pret;

import static org.junit.jupiter.api.Assertions.*;

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

//@Disabled
public class ProjetTest {

    private static Projet projet;
    static Projet mockedProjet;

    private static final String ARRANDIR_ERROR = "Erreur: La méthode doit retourner la valeur arrondi à deux décimales";
    private static final String PRIX_HABITATION_IS_ZERO = "La méthode doit lever une exception si la valeur de l'habitation est inférieure au calcul de l'abattement fiscal.";
    private static final String PRIX_HABITATION_NEGATIVE_ERROR = "Throws Exception Erreur : La méthode doit lever une exception si la valeur de l'habitation est négative.";
    private static final String TVA_IS_NEGATIVE_ERROR = "Throws Exception Erreur : La méthode doit lever une exception si la valeur de TVA est négative.";
    private static final String MAX_VALUE_ERROR = "MAX_VALUE Error: la valeur prixHabitation est trop grande";
    // private static Exception negativeException = new Exception(isExtre);

    @BeforeAll
    static void setup() {
        projet = new Projet();
        mockedProjet = Mockito.spy(projet);
    }

    // nested class to test calculTotalProjetAchat
    @Disabled
    @Nested
    @DisplayName("Testing calculTotalProjetAchat() total Projet achat")
    class testCalculTotalProjetAchat {

        //@Disabled
        @Test
        @DisplayName("Testing calculTotalProjetAchat positive normal values")
        public void calculTotalProjetAchat_testPositifNomalValues() {

            Assertions.assertAll(

                    // testing normal positif values wetween 350_000.00 & 500_000.00
                    // Test 1
                    () -> {
                        projet.setPrixHabitation(350_000.00);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);

                        Mockito.doReturn(18_600.00).when(mockedProjet).calculDroitEnregistrement();
                        Mockito.doReturn(600.00).when(mockedProjet).calculTVAFraisTransformation();

                        assertEquals(384_200.00, projet.calculTotalProjetAchat(), ARRANDIR_ERROR);
                    },

                    // Test 2
                    () -> {
                        projet.setPrixHabitation(500_000.00);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);

                        Mockito.doReturn(28_800.00).when(mockedProjet).calculDroitEnregistrement();
                        Mockito.doReturn(600.00).when(mockedProjet).calculTVAFraisTransformation();

                        assertEquals(544_400.00, projet.calculTotalProjetAchat(), ARRANDIR_ERROR);
                    },

                    // Test 3
                    () -> {
                        projet.setPrixHabitation(150_000.00);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);

                        Mockito.doReturn(6_600.00).when(mockedProjet).calculDroitEnregistrement();
                        Mockito.doReturn(600.00).when(mockedProjet).calculTVAFraisTransformation();

                        assertEquals(172_200.00, projet.calculTotalProjetAchat(), ARRANDIR_ERROR);
                    },

                    // Test 4
                    () -> {
                        projet.setPrixHabitation(400_000.00);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);

                        Mockito.doReturn(22_000.00).when(mockedProjet).calculDroitEnregistrement();
                        Mockito.doReturn(600.00).when(mockedProjet).calculTVAFraisTransformation();

                        assertEquals(437_600.00, projet.calculTotalProjetAchat(), ARRANDIR_ERROR);
                    },

                    // Test 5
                    () -> {
                        projet.setPrixHabitation(550_000.00);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);

                        Mockito.doReturn(31_800.00).when(mockedProjet).calculDroitEnregistrement();
                        Mockito.doReturn(300.00).when(mockedProjet).calculTVAFraisTransformation();

                        assertEquals(597_400.00, projet.calculTotalProjetAchat(), ARRANDIR_ERROR);
                    });
        }// End test positifNormalValues

        //@Disabled
        @Test
        @DisplayName("Testing calcule Total Projet Achat negatif values")
        void calculTotalProjetAchat_testNegativeValues() {
            Assertions.assertAll(

                    // Test 1
                    () -> {
                        projet.setPrixHabitation(-450_000.00);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);

                        Mockito.when(mockedProjet.calculDroitEnregistrement())
                                .thenThrow(new IllegalArgumentException(PRIX_HABITATION_NEGATIVE_ERROR));
                        Mockito.when(mockedProjet.calculTVAFraisTransformation())
                                .thenThrow(new IllegalArgumentException(TVA_IS_NEGATIVE_ERROR));
                        // Mockito.doReturn(-2_399.94).when(mockedProjet).calculDroitEnregistrement();
                        // Mockito.doReturn(-143_111.9964).when(mockedProjet).calculTVAFraisTransformation();

                        assertThrows(Exception.class, () -> {
                            projet.calculTotalProjetAchat();
                        }, "should throw: " + PRIX_HABITATION_NEGATIVE_ERROR);
                    },

                    // Test 2
                    () -> {
                        projet.setPrixHabitation(-300_000.00);
                        projet.setFraisNotaireAchat(-4_000.00);
                        projet.setFraisTransformation(-5_000.00);

                        Mockito.when(mockedProjet.calculDroitEnregistrement())
                                .thenThrow(new IllegalArgumentException(PRIX_HABITATION_NEGATIVE_ERROR));
                        Mockito.when(mockedProjet.calculTVAFraisTransformation())
                                .thenThrow(new IllegalArgumentException(TVA_IS_NEGATIVE_ERROR));

                        assertThrows(Exception.class, () -> {
                            projet.calculTotalProjetAchat();
                        }, "should throw: " + PRIX_HABITATION_NEGATIVE_ERROR);
                    });

        }// end testNegativeValues() testing method

        //@Disabled
        @ParameterizedTest
        @MethodSource("providArgs_calculTotalProjetAchat_testLimitsValueWithArgs")
        @DisplayName("Testing calcule Total Projet Achat limits values")
        void calculTotalProjetAchat_testLimitsValueWithArgs(double prixHabitation, double fraisNotaireAchat,
                double mockDroitEnregistrement, double fraisTransformation,
                double mockTVAFraisTransformation, double expectedTotal) {
            projet.setPrixHabitation(prixHabitation);
            projet.setFraisNotaireAchat(fraisNotaireAchat);
            projet.setFraisTransformation(fraisTransformation);

            Mockito.when(mockedProjet.calculDroitEnregistrement()).thenReturn(mockDroitEnregistrement);
            Mockito.when(mockedProjet.calculTVAFraisTransformation()).thenReturn(mockTVAFraisTransformation);

            assertEquals(expectedTotal, projet.calculTotalProjetAchat());

        }

        static Stream<Arguments> providArgs_calculTotalProjetAchat_testLimitsValueWithArgs() {
            return Stream.of(
                    Arguments.arguments(349_999.99, 5_000.00, 18_600.00, 10_000.00, 600.00, 384_199.99),
                    Arguments.arguments(350_000.99, 5_000.00, 18_600.07, 10_000.00, 600.00, 384_201.06),
                    Arguments.arguments(499_999.99, 5_000.99, 28_800.00, 9_999.99, 600.00, 544_400.97),
                    Arguments.arguments(500_000.99, 4_999.99, 28_800.06, 10_999.99, 654.00, 545_461.03));
        };

        //@Disabled
        @Test
        @DisplayName("Testing calcul Total Projet Achat extreme values")
        public void calculTotalProjetAchat_testExtremValues() {
            // testing extreme values
            assertAll(
                    // 1 -> testing MIN_VALUE
                    () -> {
                        projet.setPrixHabitation(Double.MIN_VALUE);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);
                        Mockito.when(mockedProjet.calculDroitEnregistrement()).thenReturn(-2_400.00);
                        Mockito.when(mockedProjet.calculTVAFraisTransformation()).thenReturn(600.00);
                        assertThrows(Exception.class, () -> {
                            projet.calculTotalProjetAchat();
                        }, PRIX_HABITATION_NEGATIVE_ERROR);
                    },

                    // 2 -> testing MAX_VALUE
                    () -> {
                        projet.setPrixHabitation(Double.MAX_VALUE);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);
                        Mockito.when(mockedProjet.calculDroitEnregistrement()).thenReturn(-2_400.00);
                        Mockito.when(mockedProjet.calculTVAFraisTransformation()).thenReturn(600.00);
                        assertThrows(Exception.class, () -> {
                            projet.calculTotalProjetAchat();
                        }, MAX_VALUE_ERROR);
                    },

                    // 3 -> testing 0.00 value
                    () -> {
                        projet.setPrixHabitation(0.00);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);
                        Mockito.when(mockedProjet.calculDroitEnregistrement()).thenReturn(-2_400.00);
                        Mockito.when(mockedProjet.calculTVAFraisTransformation()).thenReturn(600.00);
                        assertThrows(Exception.class, () -> {
                            projet.calculTotalProjetAchat();
                        }, PRIX_HABITATION_IS_ZERO);
                    }

            );
        }

    }// End calculTotalProjetAchat_TestPositifExtremValues() testing method

    @Disabled
    @Nested
    @DisplayName("Test calcul apport Minimal")
    class calculApportMinimal_normalPositifValues {
        //@Disabled
        @ParameterizedTest
        @MethodSource("getArgsNormalPositiveValueForCalculApportMinimal")
        void calculApportMinimal_testNormalPositifValues(double prixHabitation,
                double fraisNotaireAchat,
                double setFraisTransformation,
                double calculTVAFraisTransformation,
                double calculDroitEnregistrement,
                double excepted) {

            projet.setPrixHabitation(prixHabitation);
            projet.setFraisNotaireAchat(fraisNotaireAchat);
            projet.setFraisTransformation(setFraisTransformation);

            Mockito.when(mockedProjet.calculDroitEnregistrement()).thenReturn(calculDroitEnregistrement);
            Mockito.when(mockedProjet.calculTVAFraisTransformation()).thenReturn(calculTVAFraisTransformation);
            assertEquals(excepted, projet.calculApportMinimal(), ARRANDIR_ERROR);

        }// end test calculApportMinimal_testNormalPositifValues

        static Stream<Arguments> getArgsNormalPositiveValueForCalculApportMinimal() {
            return Stream.of(
                    Arguments.arguments(100_000.00, 5_000.00, 15_000.00, 900.00, 3_600.00, 20_190.00),
                    Arguments.arguments(350_000.00, 3_500.00, 10_500.00, 630.00, 18_600, 58_213.00),
                    Arguments.arguments(450_000.00, 4_000.00, 25_000.00, 1500.00, 25_400.00, 77_050.00),
                    Arguments.arguments(500_000.00, 5_500.00, 35_000.00, 2_100.00, 28_800.00, 88_010.00));
        }// getArgsNormalPositiveValueForCalculApportMinimal()

        //@Disabled
        @Test
        @MethodSource("getArgsLimitsValueForCalculApportMinimal")
        void calculApportMinimal_testLimitsValues() {

            // testing extreme values
            assertAll(
                    // 1 -> testing MIN_VALUE
                    () -> {
                        projet.setPrixHabitation(Double.MIN_VALUE);
                        projet.setFraisNotaireAchat(20_000.00);
                        projet.setFraisTransformation(50_000.00);
                        Mockito.when(mockedProjet.calculDroitEnregistrement()).thenReturn(12_497.00);
                        Mockito.when(mockedProjet.calculTVAFraisTransformation()).thenReturn(3000.00);
                        assertThrows(Exception.class, () -> {
                            projet.calculTotalProjetAchat();
                        }, PRIX_HABITATION_NEGATIVE_ERROR);
                    },

                    // 2 -> testing MAX_VALUE
                    () -> {
                        projet.setPrixHabitation(Double.MAX_VALUE);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);
                        Mockito.when(mockedProjet.calculDroitEnregistrement()).thenReturn(-2_400.00);
                        Mockito.when(mockedProjet.calculTVAFraisTransformation()).thenReturn(600.00);
                        assertThrows(Exception.class, () -> {
                            projet.calculTotalProjetAchat();
                        }, MAX_VALUE_ERROR);
                    },

                    // 3 -> testing 0.00 value
                    () -> {
                        projet.setPrixHabitation(0.00);
                        projet.setFraisNotaireAchat(5_000.00);
                        projet.setFraisTransformation(10_000.00);
                        Mockito.when(mockedProjet.calculDroitEnregistrement()).thenReturn(-2_400.00);
                        Mockito.when(mockedProjet.calculTVAFraisTransformation()).thenReturn(600.00);
                        assertThrows(Exception.class, () -> {
                            projet.calculTotalProjetAchat();
                        }, PRIX_HABITATION_IS_ZERO);
                    });

        }// end test testCalculApportMinimal_normalPositifValues

    }// end testing calculApportMinimal()

    //@Disabled
    @Test
    @DisplayName("testing getters & setters")
    void testingGetters_Setters() {
        assertAll(
                () -> {
                    projet.setRevenuCadastral(700);
                    assertEquals(700, projet.getRevenuCadastral());

                },
                () -> {
                    projet.setPrixHabitation(350_000.00);
                    assertEquals(350_000.00, projet.getPrixHabitation());

                },
                () -> {
                    projet.setFraisNotaireAchat(3_000.00);
                    assertEquals(3_000.00, projet.getFraisNotaireAchat());

                },
                () -> {
                    projet.setFraisTransformation(35_000.00);
                    assertEquals(35_000.00, projet.getFraisTransformation());
                }

        );
    }
}// Fin de test de la classe Projet
