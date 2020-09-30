package com.example.pokemonapplivecoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView pokemonImageView;
    private Button selectPokemon;
    private RadioGroup pokemonSelection;
    private TextView textViewSelection;
    private TextView labelRadioGoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonImageView = findViewById(R.id.imageView);

        pokemonSelection = findViewById(R.id.pokemonSelection);
        labelRadioGoup = findViewById(R.id.textView);
        selectPokemon = findViewById(R.id.selectPokemon);
        textViewSelection = findViewById(R.id.textViewSelection);
    }

    public void selectPokemonClicked(View view) {
        pokemonImageView.setImageResource(R.drawable.pokebal);
        selectPokemon.setVisibility(View.INVISIBLE);
        pokemonSelection.setVisibility(View.INVISIBLE);
        labelRadioGoup.setVisibility(View.INVISIBLE);

        textViewSelection.setVisibility(View.VISIBLE);

        RadioButton pokemonButtonSelected = findViewById(pokemonSelection.getCheckedRadioButtonId());

        textViewSelection.setText("Congratulation, you are selected : " + pokemonButtonSelected.getText());
    }

    public void pokemonSelectionClicked(View view) {
        switch (view.getId()) {
            case R.id.charmander:
                pokemonImageView.setImageResource(R.drawable.charmander);
                break;
            case R.id.squirtle:
                pokemonImageView.setImageResource(R.drawable.squirtle);
                break;
            case R.id.bulbasaur:
            default:
                pokemonImageView.setImageResource(R.drawable.bulbasaur);
                break;
        }
    }
}