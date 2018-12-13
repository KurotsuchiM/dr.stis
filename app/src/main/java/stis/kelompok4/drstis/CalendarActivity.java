package stis.kelompok4.drstis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView textView;
    private ZoneId timeZone;
    private LocalDate todayDate;

    private final String[] listDay = {"Senin","Selasa","Rabu","Kamis","Jumat","Sabtu","Minggu"};
    private final String[] listMonths = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

    private Button select;
    private TextView selectText;
    private String selectedDay;
    private String selectedDate;
    private String selectedMonth;
    private String selectedYear;
    private String selectedFullDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        calendarView    = (CalendarView) findViewById(R.id.tanggal_itself);
        select          = (Button) findViewById(R.id.tanggal_button_pesan);
        selectText      = (TextView) findViewById(R.id.tanggal_button_textview);

        try {

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayofMonth) {
                    setSelectedDate(String.valueOf(dayofMonth));
                    setSelectedDay(Integer.valueOf(dayofMonth).toString());
                    setSelectedMonth(Integer.valueOf(month).toString());
                    setSelectedYear(Integer.valueOf(year).toString());

                    setSelectedFullDate("pesan untuk " + toDay(dayofMonth,month,year) + ", " + getSelectedDate() + " " + toMonth(month) + " " + getSelectedYear());
                    selectText.setText(getSelectedFullDate());

                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent();
                intent.putExtra("Tanggal", getSelectedFullDate());
            }
        });
    }

    public String toDay(int date, int month, int year)
    {
        date = date + 2;
        String today = date + "/" + month + "/" + year;
        DateFormat dayName = new SimpleDateFormat("EEEE");
        try
        {
            today = dayName.format(new SimpleDateFormat("dd/MM/yyyy").parse(today));
        }catch (Exception e)
        {
            System.out.println("Exception at toDay() function. ");
            e.printStackTrace();
        }
        if(today.equalsIgnoreCase("Monday"))
        {
            today = "Senin";
        }
        else if(today.equalsIgnoreCase("Tuesday"))
        {
            today = "Selasa";
        }
        else if(today.equalsIgnoreCase("Wednesday"))
        {
            today = "Rabu";
        }
        else if(today.equalsIgnoreCase("Thursday"))
        {
            today = "Kamis";
        }
        else if(today.equalsIgnoreCase("Friday"))
        {
            today = "Jumat";
        }
        else if(today.equalsIgnoreCase("Saturday"))
        {
            today = "Sabtu";
        }
        else
        {
            today = "Minggu";
        }
        return today;
    }
    public String toMonth(int month)
    {
        return listMonths[month];
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

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(String selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    public String[] getListDay() {
        return listDay;
    }

    public String[] getListMonths() {
        return listMonths;
    }

    public String getSelectedFullDate() {
        return selectedFullDate;
    }

    public void setSelectedFullDate(String selectedFullDate) {
        this.selectedFullDate = selectedFullDate;
    }
}
