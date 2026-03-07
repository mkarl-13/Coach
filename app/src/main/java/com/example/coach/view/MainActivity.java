package com.example.coach.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coach.R;
import com.example.coach.contract.ICalculView;
import com.example.coach.presenter.CalculPresenter;

public class MainActivity extends AppCompatActivity implements ICalculView {
    private EditText txtPoids, txtTaille, txtAge;
    private RadioButton rdHomme, rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;

    private CalculPresenter presenter;

    private void chargeObjetsGraphiques() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);

        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);

        lblIMG = (TextView) findViewById(R.id.lblResultat);

        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);

        btnCalc = (Button) findViewById(R.id.btnCalc);
    }

    private void init() {
        chargeObjetsGraphiques();

        presenter = new CalculPresenter(this);

        btnCalc.setOnClickListener(v -> btnCalc_clic());
    }

    private void btnCalc_clic() {
        Integer poids = 0, taille = 0, age = 0, sexe = 0;
        try {
            poids = Integer.parseInt(txtPoids.getText().toString());
            taille = Integer.parseInt(txtTaille.getText().toString());
            age = Integer.parseInt(txtAge.getText().toString());
        }catch (Exception ignored){ }

        if (rdHomme.isChecked()) sexe = 1;

        if (poids == 0 || taille == 0 || age == 0) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        } else {
            presenter.creerProfil(poids, taille, age, sexe);
        }
    }

    @Override
    public void afficherResultat(String image, double img, String message, boolean normal) {
        int imageId = getResources().getIdentifier(image, "drawable", getPackageName());
        if (imageId == 0) { imgSmiley.setImageResource(R.drawable.normal); } else { imgSmiley.setImageResource(imageId); }

        String texte = String.format("%.01f : %s", img, message);
        lblIMG.setText(texte);
        if (normal) { lblIMG.setTextColor(Color.GREEN); } else { lblIMG.setTextColor(Color.RED); }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
    }
}