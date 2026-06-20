package app.ui;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarPanel extends JPanel {

    private final JCalendar calendar;
    private final JTextArea resultArea;
    private final SimpleDateFormat dateFormat;

    public CalendarPanel(JTextArea resultArea) {
        this.resultArea = resultArea;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createTitledBorder("Kalendarz"));

        calendar = new JCalendar();

        calendar.getDayChooser().addPropertyChangeListener("day", event -> saveSelectedDate());

        add(calendar, BorderLayout.CENTER);
    }

    private void saveSelectedDate() {
        Date selectedDate = calendar.getDate();
        String formattedDate = dateFormat.format(selectedDate);

        resultArea.append("Wybrano datę: " + formattedDate + "\n");
    }
}