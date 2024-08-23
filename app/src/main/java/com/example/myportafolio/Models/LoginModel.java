package com.example.myportafolio.Models;

import com.example.myportafolio.Utils.HashUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginModel {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    // Constructor de la clase LoginModel
    public LoginModel() {
        // Obtener una instancia de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        // Obtener una instancia de FirebaseFirestore
        db = FirebaseFirestore.getInstance();
    }

    public void loginUser(String email, String password, LoginCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            db.collection("usuarios").document(user.getUid()).get()
                                    .addOnSuccessListener(documentSnapshot -> {
                                        if (documentSnapshot.exists()) {
                                            String storedHash = documentSnapshot.getString("password");
                                            if (storedHash != null && storedHash.equals(HashUtil.hashPassword(password))) {
                                                callback.onSuccess(user);
                                            } else {
                                                callback.onFailure(new Exception("Contraseña incorrecta"));
                                            }
                                        } else {
                                            callback.onFailure(new Exception("Usuario no encontrado"));
                                        }
                                    })
                                    .addOnFailureListener(callback::onFailure);
                        }
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    // Interfaz de callback para manejar los resultados del inicio de sesión
    public interface LoginCallback {
        // Método llamado cuando el inicio de sesión es exitoso
        void onSuccess(FirebaseUser user);
        // Método llamado cuando hay un error en el inicio de sesión
        void onFailure(Exception e);
    }
}