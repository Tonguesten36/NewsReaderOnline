package vn.edu.usth.newsreaderonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SettingActivity extends AppCompatActivity {
    private Switch settingSwitch;
    static boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        BottomNavigationView bottomNavigationView = findViewById(R.id.botNavigation2);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if (item.getItemId() == R.id.BotNavHome && running == true){
                    Intent intent = new Intent(SettingActivity.this, NewsReaderOnlineActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }else if(item.getItemId() == R.id.BotNavSettings){
                    return true;
                }
                return false;
            }
        });


    }
    @Override
    public void onStart(){
        super.onStart();
        running = true;
    }
    @Override
    public void onStop(){
        super.onStop();
        running = false;
    }

}