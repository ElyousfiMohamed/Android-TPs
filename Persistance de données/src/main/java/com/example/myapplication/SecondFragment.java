package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText nom=binding.nom;
        EditText  prenom=binding.prenom;
        EditText  age=binding.age;
        EditText  email=binding.email;
        EditText  telephone=binding.tel;
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fileout = getActivity().openFileOutput("Students.txt", Context.MODE_APPEND);
                    PrintWriter pw=new PrintWriter(fileout,true);
                    pw.println("Nom:"+nom.getText().toString()
                            +"|Prenom:"+prenom.getText().toString()
                            +"|Age:"+age.getText().toString()
                            +"|Email:"+email.getText().toString()
                            +"|Telephone:"+telephone.getText().toString());

                    pw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}