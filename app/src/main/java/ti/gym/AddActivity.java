package ti.gym;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    String curDate;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = getIntent().getExtras().getString("name");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(this);

        final CalendarView calendarView = findViewById(R.id.calendarView2);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int day) {
                curDate = month+"月"+day+"日";
            }
        });
        DateFormat df = new SimpleDateFormat("M月d日");
        curDate = df.format(Calendar.getInstance().getTime());
    }

    @Override
    public void onClick(View view) {
            Snackbar.make(view, "已为您预约"+curDate+"的"+name, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

    }
}
