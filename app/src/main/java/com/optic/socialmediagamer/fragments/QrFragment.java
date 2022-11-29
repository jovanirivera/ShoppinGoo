package com.optic.socialmediagamer.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.optic.socialmediagamer.R;

import javax.annotation.Nullable;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class QrFragment extends Fragment {


    View mView;
    EditText txtDatos;
    Button botontex, btnScanner;
    ImageView codeqr;
    TextView TextQr;


    public QrFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_qr, container, false);
        txtDatos=mView.findViewById(R.id.txtDatos);
        botontex=mView.findViewById(R.id.botontex);
        codeqr=mView.findViewById(R.id.codeqr);
        btnScanner=mView.findViewById(R.id.btnscan);
        TextQr = mView.findViewById(R.id.TextQr);




        botontex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap=barcodeEncoder.encodeBitmap(txtDatos.getText().toString(), BarcodeFormat.QR_CODE,  750, 750 );
                    codeqr.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });

    btnScanner.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            IntentIntegrator.forSupportFragment(QrFragment.this)
                    .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                    .setBeepEnabled(true)
                    .setPrompt("Escaneando Codigo...")
                    .initiateScan();
        }
    });



        return mView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,
                resultCode,
                data);
        //Llamar a la información.
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //Obtener la información en un String
        String datos = result.getContents();
        TextQr.setText(datos);
    }
}