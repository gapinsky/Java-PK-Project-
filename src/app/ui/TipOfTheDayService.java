package app.ui;

import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

import javax.swing.*;

/**
 * Klasa pomocnicza odpowiedzialna za wyświetlanie okna Tip of the Day.
 * <p>
 * Okno z poradą dnia uruchamiane jest przy starcie aplikacji i zawiera
 * kilka wskazówek dotyczących obsługi programu.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public class TipOfTheDayService {

    /**
     * Prywatny konstruktor blokujący tworzenie obiektów klasy pomocniczej.
     */
    private TipOfTheDayService() {
    }

    /**
     * Wyświetla okno z poradą dnia.
     *
     * @param owner okno nadrzędne, względem którego wyświetlana jest porada
     */
    public static void showTip(JFrame owner) {
        DefaultTipModel tipModel = new DefaultTipModel();

        tipModel.add(new DefaultTip(
                "Porada 1",
                "Wpisz liczbę, wybierz wiersz i kolumnę za pomocą suwaków, a następnie kliknij 'Wstaw do tabeli'."
        ));

        tipModel.add(new DefaultTip(
                "Porada 2",
                "Puste komórki tabeli są traktowane jako wartość 0 podczas obliczeń."
        ));

        tipModel.add(new DefaultTip(
                "Porada 3",
                "Wykres kołowy pokazuje udział wartości dodatnich, ujemnych oraz zer w tabeli."
        ));

        tipModel.add(new DefaultTip(
                "Porada 4",
                "Wybór daty w kalendarzu jest zapisywany w polu wyników w formacie yyyy-MM-dd."
        ));

        JTipOfTheDay tipOfTheDay = new JTipOfTheDay(tipModel);
        tipOfTheDay.showDialog(owner);
    }
}