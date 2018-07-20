package com.example.michel.lostandfoundufms.view;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michel.lostandfoundufms.R;
import com.example.michel.lostandfoundufms.interfaces.ObjectRegisterInterface;
import com.example.michel.lostandfoundufms.presenter.ObjectRegisterPresenter;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObjectRegisterActivity extends AppCompatActivity implements ObjectRegisterInterface.View{

    @BindView(R.id.registerObjectsToolbar)
    Toolbar registerObjectsToolbar;

    @BindView(R.id.registerObjectId)
    EditText registerObjectId;

    @BindView(R.id.registerObjectTitle)
    EditText registerObjectTitle;

    @BindView(R.id.registerDateEditText)
    EditText registerDateEditText;

    @BindView(R.id.spinnerSituation)
    Spinner spinnerSituation;

    @BindView(R.id.spinnerClassification)
    Spinner spinnerClassification;

    @BindView(R.id.registerObjectImage)
    ImageView registerObjectImage;

    @BindView(R.id.registerObjectDescription)
    EditText registerObjectDescription;

    @BindView(R.id.registerObjectPageTitle)
    TextView registerObjectPageTitle;

    @BindView(R.id.registerObjectsProgressBar)
    ProgressBar registerObjectsProgressBar;

    private ObjectRegisterInterface.Presenter presenter;
    private int PICK_IMAGE_REQUEST = 111;
    private Bitmap bitmap;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private int day, month, year;
    private ArrayAdapter<String> spinnerSituationAdapter;
    private ArrayAdapter<String> spinnerClassificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_object);
        ButterKnife.bind(this);

        presenter = new ObjectRegisterPresenter(this);

        registerObjectsToolbar.setTitle("Cadastrar Objetos");
        setSupportActionBar(registerObjectsToolbar);
        registerObjectsToolbar.setTitleTextAppearance(this, R.style.ubuntuFont);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadSpinners();

        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        month = month + 1;
        registerDateEditText.setKeyListener(null);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            registerObjectsToolbar.setTitle("Editar Objetos");
            registerObjectPageTitle.setText("Editar Objeto");
            registerObjectId.setText(bundle.getString("objectId"));
            registerObjectTitle.setText(bundle.getString("objectTitle"));
            registerDateEditText.setText(bundle.getString("objectDate"));
            spinnerSituation.setSelection(spinnerSituationAdapter.getPosition(bundle.getString("objectSituation")));
            spinnerClassification.setSelection(spinnerClassificationAdapter.getPosition(bundle.getString("objectClassification")));
            Picasso.get().load(bundle.getString("objectImage")).into(registerObjectImage);
            registerObjectDescription.setText(bundle.getString("objectDescription"));
        }
    }

    @OnClick(R.id.registerDateEditText)
    void onClickRegisterDateEditText(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(ObjectRegisterActivity.this, R.style.TimePickerTheme ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;
                String monthString = String.valueOf(monthOfYear);
                if (monthString.length() == 1) {
                    monthString = "0" + monthString;
                }
                String date = dayOfMonth +"/"+ monthString +"/"+ year;
                registerDateEditText.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.registerObjectSelectImageBtn)
    void onClickRegisterObjectSelectImageBtn(View view){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Selecionar Imagem"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                registerObjectImage.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadSpinners(){
        spinnerSituationAdapter = new ArrayAdapter<>(ObjectRegisterActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.objectSituation));
        spinnerSituationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSituation.setAdapter(spinnerSituationAdapter);

        spinnerClassificationAdapter = new ArrayAdapter<>(ObjectRegisterActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.objectClassification));
        spinnerClassificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassification.setAdapter(spinnerClassificationAdapter);
    }

    @OnClick(R.id.registerObjectSaveBtn)
    void onClickRegisterObjectSaveBtn(View view){
        registerObjectsProgressBar.setVisibility(View.VISIBLE);
        String id = registerObjectId.getText().toString();
        String title = registerObjectTitle.getText().toString();
        String date = registerDateEditText.getText().toString();
        String situation = spinnerSituation.getSelectedItem().toString();
        String classification = spinnerClassification.getSelectedItem().toString();
        String image = "";

        if(bitmap != null){
            if(bitmap.getByteCount() > 0){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                image = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
        }
        String description = registerObjectDescription.getText().toString();
        presenter.requestSaveObject(id, title, date, situation, classification, image, description);
    }

    @Override
    public void objectSaveSuccess(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.toastRoot));

        LinearLayout linearLayout = layout.findViewById(R.id.toastRoot);
        TextView toastText = layout.findViewById(R.id.toastText);
        ImageView toastIcon = layout.findViewById(R.id.toastIcon);

        linearLayout.setBackgroundResource(R.color.toastSuccess);
        toastText.setText(message);
        toastIcon.setImageResource(R.drawable.ic_success_icon);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        registerObjectsProgressBar.setVisibility(View.GONE);

        startActivity(new Intent(ObjectRegisterActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void objectSaveError(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.toastRoot));

        LinearLayout linearLayout = layout.findViewById(R.id.toastRoot);
        TextView toastText = layout.findViewById(R.id.toastText);
        ImageView toastIcon = layout.findViewById(R.id.toastIcon);

        linearLayout.setBackgroundResource(R.color.toastError);
        toastText.setText(message);
        toastIcon.setImageResource(R.drawable.ic_error_icon);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        registerObjectsProgressBar.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
