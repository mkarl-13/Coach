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

    /**
     * Permet de référencer les objets graphiques dans le code
     * */
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

    /**
     * Fonction d'initialisation, s'exécute à la création de l'Activity
     */
    private void init() {
        chargeObjetsGraphiques();

        presenter = new CalculPresenter(this);

        btnCalc.setOnClickListener(v -> btnCalc_clic());
    }

    /**
     * Fonction qui sera ensuite liée a un listener pour s'exécuter au clic du bouton.
     * Récupère les informations depuis les objets graphiques vers des variables locales,
     * puis appelle creerProfil du presenter.
     */
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

    /**
     * Fonction permettant d'afficher le résultat. Est appellée par le presenter.
     * @param image
     * @param img
     * @param message
     * @param normal
     */
    @Override
    public void afficherResultat(String image, double img, String message, boolean normal) {
        int imageId = getResources().getIdentifier(image, "drawable", getPackageName());
        if (imageId == 0) { imgSmiley.setImageResource(R.drawable.normal); } else { imgSmiley.setImageResource(imageId); }

        String texte = String.format("%.01f : IMG %s", img, message);
        lblIMG.setText(texte);
        if (normal) { lblIMG.setTextColor(Color.GREEN); } else { lblIMG.setTextColor(Color.RED); }
    }

    /**
     * Fonction permettant de remplir les champs à partir de données pré-existantes. Est appellée par le presenter.
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    @Override
    public void remplirChamps(Integer poids, Integer taille, Integer age, Integer sexe) {
        txtPoids.setText(poids.toString());
        txtTaille.setText(taille.toString());
        txtAge.setText(age.toString());
        if (sexe == 1) rdHomme.setChecked(true);
        else rdFemme.setChecked(true);
    }

    /**
     * Fonction qui s'exécute au lancement de l'activité.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
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