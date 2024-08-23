package com.example.myportafolio;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backgroundAnimation();


        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);



        // Cargar la animación desde un archivo XML
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.animacion_personalizada);

        // Asignar un Listener a la animación
        animacion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // La animación ha comenzado
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // La animación ha terminado, iniciar la actividad deseada
                Intent intent = new Intent(MainActivity.this, LoginActivty.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // La animación se ha repetido
            }
        });

        // Iniciar la animación en una vista específica
        View vistaAnimada = findViewById(R.id.vista_animada); // Reemplaza R.id.vista_animada con el ID de tu vista
        vistaAnimada.startAnimation(animacion);
    }
    public void backgroundAnimation() {
        ConstraintLayout layout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }
}