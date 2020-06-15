package com.example.pilotslogjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends Activity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void openNext (View v) {

        startActivity(new Intent(MainMenu.this, MainActivity.class));
    }
}
