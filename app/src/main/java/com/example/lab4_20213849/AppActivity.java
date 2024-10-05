package com.example.lab4_20213849;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class AppActivity extends AppCompatActivity {

    private String fragmentoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentoActual = "Ligas";

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        NavController navController = navHostFragment.getNavController();

        Button botonLigas = findViewById(R.id.botonLigas);
        Button botonPosiciones = findViewById(R.id.botonPosiciones);
        Button botonResultados = findViewById(R.id.botonResultados);

        botonLigas.setOnClickListener(view -> {
            if(fragmentoActual.equals("Posiciones")){
                navController.navigate(R.id.action_posicionesFragment_to_ligasFragment);
                fragmentoActual="Ligas";
            }else if(fragmentoActual.equals("Resultados")){
                navController.navigate(R.id.action_resultadosFragment_to_ligasFragment);
                fragmentoActual="Ligas";
            }
        });

        botonPosiciones.setOnClickListener(view -> {
            if(fragmentoActual.equals("Ligas")){
                navController.navigate(R.id.action_ligasFragment_to_posicionesFragment);
                fragmentoActual="Posiciones";

            }else if(fragmentoActual.equals("Resultados")){
                navController.navigate(R.id.action_resultadosFragment_to_posicionesFragment);
                fragmentoActual="Posiciones";
            }
        });

        botonResultados.setOnClickListener(view -> {
            if(fragmentoActual.equals("Ligas")){
                navController.navigate(R.id.action_ligasFragment_to_resultadosFragment);
                fragmentoActual="Resultados";
            }else if(fragmentoActual.equals("Posiciones")){
                navController.navigate(R.id.action_posicionesFragment_to_resultadosFragment);
                fragmentoActual="Resultados";
            }
        });
    }
}