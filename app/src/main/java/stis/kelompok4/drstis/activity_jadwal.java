package stis.kelompok4.drstis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class activity_jadwal extends AppCompatActivity {

    private String selectedDate;
    private TextView selectedDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        selectedDateText = (TextView) findViewById(R.id.selectedDate) ;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            setSelectedDate((String) bundle.get("Tanggal"));
            selectedDateText.setText(getSelectedDate());
        }

    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }
}
