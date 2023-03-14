package com.tuto.taffmediator.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tuto.taffmediator.DI.ViewModelFactory;
import com.tuto.taffmediator.R;
import com.tuto.taffmediator.list.ListActivity;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel; // pour quoi ici je peux pas mettre final alors qu'a la ligne 38 cela est possible?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.itemname);
        EditText price = findViewById(R.id.itemprice);
        Button minus =findViewById(R.id.buttonminus);
        Button plus = findViewById(R.id.buttonplus);
        TextView messageText = findViewById(R.id.message);
        Button addButton =findViewById(R.id.addbutton);

        mViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MainViewModel.class);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.onNameChanged(s.toString());
            }
        });

        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.onPriceChanged(s.toString());
            }
        });



        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    Toast.makeText(MainActivity.this, "Vous ne pouvez pas saisir une quantité négative", Toast.LENGTH_SHORT).show();
                mViewModel.onDecreaseButtonClick();

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.onIncreaseButtonClick();
            }
        });

        mViewModel.getMessageLiveData().observe(this, new Observer<MainViewState>() {
            @Override
            public void onChanged(MainViewState mainViewState) {
                messageText.setText(mainViewState.getSentence());
                minus.setEnabled(mainViewState.isMinusButtonEnabled());
            }
        });



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.onAddButtonClicked();
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });


    }
}