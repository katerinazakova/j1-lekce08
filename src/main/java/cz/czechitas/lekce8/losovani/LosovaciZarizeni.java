package cz.czechitas.lekce8.losovani;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Třída pro losování náhodných čísel.
 */
public class LosovaciZarizeni {

  private static final int NEJMENSI_CISLO = 1;
  private static final int NEJVETSI_CISLO = 49;
  private static final int POCET_LOSOVANYCH_CISEL = 6;
  private final Random random = new Random();

  /**
   * Losuje seznam náhodných čísel.
   * <p>
   * Losovaná čísla se mohou opakovat.
   *
   * @param minimum Nejmenší možná vylosovaná hodnota.
   * @param maximum Nejvyšší možná vylosovaná hodnota.
   * @param pocet   Počet vylosovaných čísel.
   * @return Stream vylosovaných čísel.
   */
  public IntStream losujSeznamCisel(int minimum, int maximum, int pocet) {
    return random.ints(pocet, minimum, maximum + 1);
  }

  /**
   * Losuje 6 čísel v rozmezí 1–49.
   * <p>
   * Losovaná čísla se nesmí opakovat.
   *
   * @return Stream vylosovaných čísel.
   */
  public IntStream losujSazkuHlavniTah() {
    return random.ints(NEJMENSI_CISLO, NEJVETSI_CISLO + 1) // 49 + 1, protože parametr horní hranice musí být mimo rozsah vracených hodnot.
            .distinct()
            .limit(POCET_LOSOVANYCH_CISEL);
  }

  /**
   * Losuje 6 čísel v rozmezí 1–49.
   * <p>
   * Losovaná čísla se nesmí opakovat.
   *
   * @return Seznam vylosovaných čísel.
   */
  public List<Integer> getSazkaHlavniTah() {
    return losujSazkuHlavniTah()
            .boxed()
            .collect(Collectors.toList());
  }

  /**
   * Losuje 6 čísel v rozmezí 1–49.
   * <p>
   * Losovaná čísla se mohou opakovat.
   *
   * @return Seznam vylosovaných čísel.
   */
  public List<Integer> getSazkaHlavniTahDuplicitni() {
    return losujSeznamCisel(NEJMENSI_CISLO, NEJVETSI_CISLO, POCET_LOSOVANYCH_CISEL)
            .boxed()
            .collect(Collectors.toList());
  }

  /**
   * Losuje seznam čísel – všechna tažená čísla musí být sudá.
   * <p>
   * Implementováno pomocí filtru, který propustí jen sudá čísla.
   *
   * @param minimum Nejmenší možná vylosovaná hodnota.
   * @param maximum Nejvyšší možná vylosovaná hodnota.
   * @param pocet   Počet vylosovaných čísel.
   * @return Stream vylosovaných čísel.
   */
  public IntStream losujSeznamSudychCisel(int minimum, int maximum, int pocet) {
    return random.ints(pocet, minimum, maximum)
            .filter(cislo -> cislo % 2 == 0);
  }

  /**
   * Losuje seznam čísel – všechna tažená čísla musí být sudá.
   * <p>
   * Implementováno pomocí mapy, která všechna tažená čísla zdovjnásobí.
   *
   * @param minimum Nejmenší možná vylosovaná hodnota.
   * @param maximum Nejvyšší možná vylosovaná hodnota.
   * @param pocet   Počet vylosovaných čísel.
   * @return Stream vylosovaných čísel.
   */
  public IntStream losujSeznamSudychCiselJakoMapa(int minimum, int maximum, int pocet) {
    return random.ints(pocet, minimum / 2, maximum / 2)
            .map(cislo -> cislo * 2);
  }

}
