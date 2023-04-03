package cz.czechitas.lekce8.losovani;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Filip Jirsák
 */
class LosovaciZarizeniTest {
  private final LosovaciZarizeni losovaciZarizeni = new LosovaciZarizeni();

  @RepeatedTest(100)
  void getSazkuHlavniTah() {
    List<Integer> vylosovanaCisla = losovaciZarizeni.getSazkaHlavniTah();
    testujSazkuHlavniTah(vylosovanaCisla);
  }

  @RepeatedTest(100)
  void getSazkuHlavniTahDuplicity() {
    List<Integer> vylosovanaCisla = losovaciZarizeni.getSazkaHlavniTahDuplicitni();
    testujSazkuHlavniTahPovolDuplicity(vylosovanaCisla);
  }

  @RepeatedTest(100)
  void losujSudaCislaFilter() {
    IntStream sudaCisla = losovaciZarizeni.losujSeznamSudychCisel(2, 60, 10);
    overSudaCisla(sudaCisla);
  }

  @RepeatedTest(100)
  void losujSudaCislaMap() {
    IntStream sudaCisla = losovaciZarizeni.losujSeznamSudychCiselJakoMapa(2, 60, 10);
    overSudaCisla(sudaCisla);
  }

  /**
   * Otestuje, že je vylosovaných čísel 6 a že jsou v rozsahu 1–49.
   *
   * Neověřuje, zda čísla nejsou duplicitní.
   */
  private void testujSazkuHlavniTahPovolDuplicity(List<Integer> vylosovanaCisla) {
    assertEquals(6, vylosovanaCisla.size(), "Vylosovaných čísel není 6.");
    assertTrue(vylosovanaCisla.stream()
                    .allMatch(cislo -> cislo >= 1 && cislo <= 49),
            () -> String.format(
                    "Vylosovaná čísla nejsou v rozsahu 1–49. Nejmenší tažené číslo: %d. největší tažené číslo: %d.",
                    vylosovanaCisla.stream().min(Integer::compareTo).get(),
                    vylosovanaCisla.stream().max(Integer::compareTo).get()
            ));
  }

  /**
   * Otestuje, že je vylosovaných čísel 6, že jsou v rozsahu 1–49 a ž enejsou duplicitní.
   */
  private void testujSazkuHlavniTah(List<Integer> vylosovanaCisla) {
    testujSazkuHlavniTahPovolDuplicity(vylosovanaCisla);
    assertEquals(6, vylosovanaCisla.stream()
                    .distinct()
                    .count(),
            () ->
                    String.format("Vylosovaná čísla nejsou unikátní: %s", vylosovanaCisla.stream()
                            .map(cislo -> Integer.toString(cislo))
                            .collect(Collectors.joining(", ")))
    );
  }

  private void overSudaCisla(IntStream sudaCisla) {
    sudaCisla.forEach(cislo -> {
      assertEquals(0, cislo % 2, "Vylosované číslo není sudé.");
      assertTrue(cislo >= 2, "Vylosované číslo je menší než 2.");
      assertTrue(cislo <= 60, "Vylosované číslo je větší než 60");
    });
  }
}
