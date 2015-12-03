package com.siwei.carmen;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by wkd on 15-12-3.
 */
public class CardManageActivity extends Activity {

    private Button btnSure;
    private Button btnCancel;
    private EditText etAlias;
    private EditText etBillDay;
    private EditText etDueDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.card_edit);

        etAlias =(EditText)findViewById(R.id.etAlias);
        etBillDay=(EditText)findViewById(R.id.etBillDay);
        etDueDay=(EditText)findViewById(R.id.etDueDay);
        btnSure = (Button) findViewById(R.id.btnSure);
        btnCancel= (Button)findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        Card editingCard = (Card)intent.getSerializableExtra("newCard");
        if(editingCard != null) {
            int billDay = editingCard.getBillDay();
            etAlias.setText(editingCard.getAlias());
            etBillDay.setText(String.valueOf(editingCard.getBillDay()));
            etDueDay.setText(String.valueOf(editingCard.getDueDay()));
        }

        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }


}
