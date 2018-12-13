package stis.kelompok4.drstis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView textView;
    private ZoneId timeZone;
    private LocalDate todayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


//        setTimeZone(ZoneId.of("Bangkok"));
//        setTodayDate(LocalDate.now(getTimeZone()));
//
//        setTextView((TextView) findViewById(R.id.tanggal_text_view));
//        getTextView().setText("Tanggal : " + getTodayDate());
    }

    public CalendarView getCalendarView() {
        return calendarView;
    }

    public void setCalendarView(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }
}
