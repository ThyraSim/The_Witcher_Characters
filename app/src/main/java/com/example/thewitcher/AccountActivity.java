package com.example.thewitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
//ici va etre la liste des personnage lier au compte
public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.itemAdd) {
            Toast.makeText(this, "Add new post", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.itemConfig) {
            Toast.makeText(this, "Enter into config", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.itemLogout) {
            finish();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;

    }
}

