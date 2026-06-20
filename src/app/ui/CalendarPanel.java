package app.ui;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Panel kalendarza wykorzystujący komponent JCalendar.
 * <p>
 * Komponent pozwala użytkownikowi wybrać datę, a następnie zapisuje ją
 * w polu tekstowym wyników w formacie yyyy-MM-dd.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public class CalendarPanel extends JPanel {

    /**
     * Komponent kalendarza używany do wyboru daty.
     */
    private final JCalendar calendar;

    /**
     * Pole tekstowe, do którego zapisywana jest wybrana data.
     */
    private final JTextArea resultArea;

    /**
     * Format zapisu daty zgodny z wymaganiem zadania.
     */
    private final SimpleDateFormat dateFormat;

    /**
     * Konstruktor panelu kalendarza.
     *
     * @param resultArea pole tekstowe, w którym zapisywana jest wybrana data
     */
    public CalendarPanel(JTextArea resultArea) {
        this.resultArea = resultArea;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createTitledBorder("Kalendarz"));

        calendar = new JCalendar();

        calendar.getDayChooser().addPropertyChangeListener("day", event -> saveSelectedDate());

        add(calendar, BorderLayout.CENTER);
    }

    /**
     * Pobiera aktualnie wybraną datę z kalendarza i zapisuje ją do pola wyników.
     */
    private void saveSelectedDate() {
        Date selectedDate = calendar.getDate();
        String formattedDate = dateFormat.format(selectedDate);

        resultArea.append("Wybrano datę: " + formattedDate + "\n");
    }
}