package com.sssas.smash.UsersPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sssas.smash.LoginActivity;
import com.sssas.smash.R;
import com.sssas.smash.Utils.DatabaseHelper;
import com.sssas.smash.Utils.UtilsNetwork;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    BottomAppBar bottomNav;
    DatabaseHelper DBSqlite;
    AppCompatImageView icon_wifi;
    FloatingActionButton search_beneficiario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        icon_wifi = findViewById(R.id.icon_wifi_home);
        search_beneficiario = findViewById(R.id.menu_search);

        DBSqlite = new DatabaseHelper(this);

        bottomNav = findViewById(R.id.nav_menu_user);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.content_menu, homeFragment).commit();
        }

        valid_wifi();

        bottomNav.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
                public boolean onMenuItemClick(MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.menu_account:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.menu_logout:
                        logout();
                        break;
                }

                if(fragment != null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_menu, fragment).commit();
                }
                return true;
            }
        });

        search_beneficiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomeActivity.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_search_beneficiario, (LinearLayout)findViewById(R.id.container_modal_search));
                bottomSheetView.findViewById(R.id.btn_search_bene).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(HomeActivity.this, "Buscando...", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                valid_wifi();
            }
        },0,5000);
    }

    private void valid_wifi() {
        if(UtilsNetwork.isOnline(this)){
            icon_wifi.setImageResource(R.drawable.ic_online);
        } else {
            icon_wifi.setImageResource(R.drawable.ic_offline);
        }
    }

    private void logout() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Alerta");
            builder.setMessage("¿Deseas cerrar la sesión?");

            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean isDeleted = DBSqlite.delAllData("TBL_USUARIOS");
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.create();
            builder.show();

        } catch (Exception e) {
            Toast.makeText(this, "Ocurrió un error, intenta más tarde", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Alerta");
        builder.setMessage("¿Estás seguro de salir de la aplicación?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create();
        builder.show();
    }
}