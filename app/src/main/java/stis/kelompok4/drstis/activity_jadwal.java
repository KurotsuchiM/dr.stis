package stis.kelompok4.drstis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Boolean.FALSE;

public class activity_jadwal extends AppCompatActivity implements View.OnClickListener
{
    public static final String TEXT_JAM = "sharedJam";

    private boolean selectedTimeStatusAvailable = false;
    private String selectedDate;
    private TextView selectedDateText;
    private ActivityJadwalAdapter adapter;
    private String selectedTime;

    private boolean availableAt0900;
    private boolean availableAt0930;
    private boolean availableAt1000;
    private boolean availableAt1030;
    private boolean availableAt1100;
    private boolean availableAt1130;
    private boolean availableAt1200;
    private boolean availableAt1300;
    private boolean availableAt1330;
    private boolean availableAt1400;
    private boolean availableAt1430;
    private boolean availableAt1500;
    private boolean availableAt1530;
    private boolean availableAt1600;

    private TextView jam0900;
    private TextView jam0930;
    private TextView jam1000;
    private TextView jam1030;
    private TextView jam1100;
    private TextView jam1130;
    private TextView jam1200;
    private TextView jam1300;
    private TextView jam1330;
    private TextView jam1400;
    private TextView jam1430;
    private TextView jam1500;
    private TextView jam1530;
    private TextView jam1600;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);



        init();
        checkAvailability();


    }

    public void init()
    {
        GridLayout grid = (GridLayout) findViewById(R.id.jamGridLayout_itself);
        int childCounter = grid.getChildCount();

        sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        selectedDateText = (TextView) findViewById(R.id.selectedDate) ;
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Button pesanJadwal = findViewById(R.id.jadwal_button);

        //STUB: textView listeners

        for (int i = 0; i < childCounter; i++)
        {
            grid.
            RelativeLayout relativeLayout = (RelativeLayout) grid.getChildAt(i);
            relativeLayout.setOnClickListener(new View.OnClickListener()
            {

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                public void onClick(View view)
                {
                    //jam0900.setBackground(getResources().getDrawable(R.drawable.button_submit_active));
                    //int identifier = grid.getChildAt(i).getResources().getIdentifier("button_submit", "drawable", activity_jadwal.getPackageName());
                }
            });
        }


        // inisialisasi jam availability
        setAvailableAt0900(true);
        setAvailableAt0930(true);
        setAvailableAt1000(true);
        setAvailableAt1030(false);
        setAvailableAt1100(true);
        setAvailableAt1130(true);
        setAvailableAt1200(true);
        setAvailableAt1300(false);
        setAvailableAt1330(true);
        setAvailableAt1400(true);
        setAvailableAt1430(false);
        setAvailableAt1500(true);
        setAvailableAt1530(true);
        setAvailableAt1600(true);


        //TODO: Buat per Jam individual supaya tiap Jam bisa di set manual background nya ke Merah/Hijau
        jam0900 = (TextView) findViewById(R.id.grid_jam0900);
        jam0930 = (TextView) findViewById(R.id.grid_jam0930);
        jam1000 = (TextView) findViewById(R.id.grid_jam1000);
        jam1030 = (TextView) findViewById(R.id.grid_jam1030);
        jam1100 = (TextView) findViewById(R.id.grid_jam1100);
        jam1130 = (TextView) findViewById(R.id.grid_jam1130);
        jam1200 = (TextView) findViewById(R.id.grid_jam1200);
        jam1300 = (TextView) findViewById(R.id.grid_jam1300);
        jam1330 = (TextView) findViewById(R.id.grid_jam1330);
        jam1400 = (TextView) findViewById(R.id.grid_jam1400);
        jam1430 = (TextView) findViewById(R.id.grid_jam1430);
        jam1500 = (TextView) findViewById(R.id.grid_jam1500);
        jam1530 = (TextView) findViewById(R.id.grid_jam1530);
        jam1600 = (TextView) findViewById(R.id.grid_jam1600);



        RecyclerView listView = (RecyclerView) findViewById(R.id.jadwal_list_itself);
        listView.setLayoutManager(new GridLayoutManager(this, 3));


        listView.setAdapter(adapter);

        if(bundle != null)
        {
            // intent mode
            setSelectedDate((String) bundle.get("Tanggal"));
        }

        // sharedPrefs mode

        //setSelectedDate(sharedPreferences.getString(CalendarActivity.TEXT_TANGGAL, ""));

        selectedDateText.setText(getSelectedDate());

        pesanJadwal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setSelectedTime("");

                // sharedPref mode
                // editor.putString(TEXT_JAM, getSelectedTime());
                // editor.commit();

                // intent mode
                Intent intent1 = new Intent(getApplicationContext(), FormulirActivity.class);
                intent1.putExtra("Jam", getSelectedTime());
                intent1.putExtra("Tanggal", getSelectedDate());

                startActivity(intent1);

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void checkAvailability()
    {
        //int id = getResources().getIdentifier("")
        if(isAvailableAt0900())
        {
            jam0900.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam0900.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt0930())
        {
            jam0930.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam0930.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1000())
        {
            jam1000.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1000.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1030())
        {
            jam1030.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1030.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1100())
        {
            jam1100.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1100.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1130())
        {
            jam1130.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1130.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1200())
        {
            jam1200.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1200.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1300())
        {
            jam1300.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1300.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1330())
        {
            jam1330.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1330.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1400())
        {
            jam1400.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1400.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1430())
        {
            jam1430.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1430.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1500())
        {
            jam1500.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1500.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1530())
        {
            jam1530.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1530.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
        if(isAvailableAt1600())
        {
            jam1600.setBackground(getResources().getDrawable(R.drawable.button_submit));
        }else
        {
            jam1600.setBackground(getResources().getDrawable(R.drawable.disbutton_submit));
        }
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public boolean isSelectedTimeStatusAvailable() {
        return selectedTimeStatusAvailable;
    }

    public void setSelectedTimeStatusAvailable(boolean selectedTimeStatus) {
        this.selectedTimeStatusAvailable = selectedTimeStatus;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }

    public boolean isAvailableAt0900() {
        return availableAt0900;
    }

    public void setAvailableAt0900(boolean availableAt0900) {
        this.availableAt0900 = availableAt0900;
    }

    public boolean isAvailableAt0930() {
        return availableAt0930;
    }

    public void setAvailableAt0930(boolean availableAt0930) {
        this.availableAt0930 = availableAt0930;
    }

    public boolean isAvailableAt1000() {
        return availableAt1000;
    }

    public void setAvailableAt1000(boolean availableAt1000) {
        this.availableAt1000 = availableAt1000;
    }

    public boolean isAvailableAt1030() {
        return availableAt1030;
    }

    public void setAvailableAt1030(boolean availableAt1030) {
        this.availableAt1030 = availableAt1030;
    }

    public boolean isAvailableAt1100() {
        return availableAt1100;
    }

    public void setAvailableAt1100(boolean availableAt1100) {
        this.availableAt1100 = availableAt1100;
    }

    public boolean isAvailableAt1130() {
        return availableAt1130;
    }

    public void setAvailableAt1130(boolean availableAt1130) {
        this.availableAt1130 = availableAt1130;
    }

    public boolean isAvailableAt1200() {
        return availableAt1200;
    }

    public void setAvailableAt1200(boolean availableAt1200) {
        this.availableAt1200 = availableAt1200;
    }

    public boolean isAvailableAt1300() {
        return availableAt1300;
    }

    public void setAvailableAt1300(boolean availableAt1300) {
        this.availableAt1300 = availableAt1300;
    }

    public boolean isAvailableAt1330() {
        return availableAt1330;
    }

    public void setAvailableAt1330(boolean availableAt1330) {
        this.availableAt1330 = availableAt1330;
    }

    public boolean isAvailableAt1400() {
        return availableAt1400;
    }

    public void setAvailableAt1400(boolean availableAt1400) {
        this.availableAt1400 = availableAt1400;
    }

    public boolean isAvailableAt1430() {
        return availableAt1430;
    }

    public void setAvailableAt1430(boolean availableAt1430) {
        this.availableAt1430 = availableAt1430;
    }

    public boolean isAvailableAt1500() {
        return availableAt1500;
    }

    public void setAvailableAt1500(boolean availableAt1500) {
        this.availableAt1500 = availableAt1500;
    }

    public boolean isAvailableAt1530() {
        return availableAt1530;
    }

    public void setAvailableAt1530(boolean availableAt1530) {
        this.availableAt1530 = availableAt1530;
    }

    public boolean isAvailableAt1600() {
        return availableAt1600;
    }

    public void setAvailableAt1600(boolean availableAt1600) {
        this.availableAt1600 = availableAt1600;
    }

    @Override
    public void onClick(View view) {

    }
}
