package app.ui;

import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

import javax.swing.*;

public class TipOfTheDayService {

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