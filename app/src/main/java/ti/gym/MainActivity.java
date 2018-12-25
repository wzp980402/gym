package ti.gym;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import io.swagger.client.model.Sport;
import ti.gym.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener, ItemFragment.OnListFragmentInteractionListener {

    public static String username;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_dashboard:
                fragment = new DashboardFragment();
                break;
            case R.id.navigation_notifications:
                fragment = PersonalFragment.newInstance(username, "");
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());

    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 1);
    }

    public void messages(View view) {
        Toast.makeText(getBaseContext(), "你还没有消息", Toast.LENGTH_SHORT).show();
    }

    public void favorites(View view) {
        Toast.makeText(getBaseContext(), "你还没有收藏", Toast.LENGTH_SHORT).show();
    }

    public void wallet(View view) {
        Toast.makeText(getBaseContext(), "你还没有钱包", Toast.LENGTH_SHORT).show();
    }

    public void createCourse(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        // 获取点击的按钮
        TextView text = (TextView)((LinearLayout) view).getChildAt(1);
        intent.putExtra("name", text.getText());
        startActivity(intent);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                username = data.getData().toString();
                ((TextView)findViewById(R.id.textView3)).setText(username);
                ((TextView)findViewById(R.id.textView)).setText("欢迎您，尊敬的用户。");
                // OR
                // String returnedResult = data.getDataString();
            }
        }
    }

    @Override
    public void onListFragmentInteraction(Sport item) {

    }
}
