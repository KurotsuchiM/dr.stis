package stis.kelompok4.drstis;


import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Mahendri on 06/08/2017.
 */

public class TextTimePicker implements TimePickerDialog.OnTimeSetListener, View.OnClickListener{

    private Context mContext;
    private EditText mEditText;
    private Calendar mCalendar;

    public TextTimePicker(Context context, int editTextId){
        Activity activity = (Activity) context;
        mEditText = (EditText) activity.findViewById(editTextId);
        mEditText.setOnClickListener(this);
        mContext = context;
        mCalendar = Calendar.getInstance();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mCalendar.set(Calendar.MINUTE, minute);
        mCalendar.set(Calendar.SECOND, 0);
        updateEditText();
    }

    @Override
    public void onClick(View v) {
        TimePickerDialog dialog = new TimePickerDialog(mContext, this,
                mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true);
        dialog.show();
    }

    public void updateEditText(){
        String format = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        mEditText.setText(sdf.format(mCalendar.getTime()));
    }

    public void updateEditText(long timeMillis){
        mCalendar.setTimeInMillis(timeMillis);
        updateEditText();
    }

    public String getText(){
        return mEditText.getText().toString();
    }
    public Calendar getCalendar(){return mCalendar;}
}
