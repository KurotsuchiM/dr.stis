package stis.kelompok4.drstis;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Mahendri on 06/08/2017.
 */

public class TextDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Context mContext;
    private EditText mEditText;
    private Calendar mCalendar;

    public TextDatePicker(Context context, int editTextId){
        Activity activity = (Activity) context;
        mEditText = (EditText) activity.findViewById(editTextId);
        mEditText.setOnClickListener(this);
        mContext = context;
        mCalendar = Calendar.getInstance();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateEditText();
    }

    @Override
    public void onClick(View v) {
        DatePickerDialog dialog = new DatePickerDialog(mContext, this,
                mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    public void setEditText(long time){
        mCalendar.setTimeInMillis(time * 1000);
    }

    public void updateEditText(){
        String format = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        mEditText.setText(sdf.format(mCalendar.getTime()));
    }

    public String getText(){
        return mEditText.getText().toString();
    }

    public long joinWithTime(Calendar time){
        Calendar fullTgl = mCalendar;
        fullTgl.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        fullTgl.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        return fullTgl.getTimeInMillis();
    }
}
