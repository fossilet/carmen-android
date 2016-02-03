package com.siwei.carmen;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by wkd on 15-12-3.
 */
public class CardManageActivity extends Activity implements View.OnClickListener {

    private Button btnSure;
    private Button btnCancel;
    private EditText etAlias;
    private EditText etBillDay;
    private EditText etDueDay;
    Boolean isNew = true;
    Card tmpCard =null;


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



        if(intent.getSerializableExtra("NEWCARD")!=null) {
            isNew = true;
            tmpCard = (Card)intent.getSerializableExtra("NEWCARD");
        }
        if(intent.getSerializableExtra("EDITCARD")!=null){
            isNew = false;
            tmpCard = (Card)intent.getSerializableExtra("EDITCARD");
        }
        if(tmpCard != null) {
            int billDay = tmpCard.getBillDay();
            etAlias.setText(tmpCard.getAlias());
            etBillDay.setText(String.valueOf(tmpCard.getBillDay()));
            etDueDay.setText(String.valueOf(tmpCard.getDueDay()));
        }

        btnSure.setOnClickListener(this);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //intent.putExtra("NEWCARD", "");
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSure) {
            CardDAO dao = new CardDAO(MainActivity.getInstance().getDbHelper());

            String newAlias = etAlias.getText().toString();
            String tmpAlias = newAlias.replaceAll("\\s","");
            if(tmpAlias.isEmpty()){
                Toast.makeText(getApplicationContext(), "请输入信用卡别名，别名不能为空白字符！", Toast.LENGTH_SHORT).show();
                return ;
            }
            if(newAlias.length()>20){
                Toast.makeText(getApplicationContext(), "信用卡别名长度不能超过20！", Toast.LENGTH_SHORT).show();
                return;
            }
            if(isNew) {
                if (dao.IsExist(newAlias)) {
                    Toast.makeText(getApplicationContext(), "信用卡别名不能重复的！", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if(isNew ==false)
            {
                if(dao.IsExistExceptId(newAlias,tmpCard.getId()))
                {
                    Toast.makeText(getApplicationContext(), "信用卡别名不能重复的！", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            int newBillDay = Integer.parseInt(etBillDay.getText().toString());
            if (newBillDay < 1 || newBillDay > 31) {
                Toast.makeText(getApplicationContext(), "帐单日需在月份范围内！", Toast.LENGTH_SHORT).show();
                return;
            }
            int newDueDay = Integer.parseInt(etDueDay.getText().toString());
            if (newDueDay < 1 || newDueDay > 31) {
                Toast.makeText(getApplicationContext(), "到期还款日需在月份范围内！", Toast.LENGTH_SHORT).show();
                return;
            }

            tmpCard.setBillDay(newBillDay);
            tmpCard.setDueDay(newDueDay);
            tmpCard.setAlias(newAlias);

            Intent intent = new Intent();
            //save new card to database
            if (isNew) {
                if (dao.InsertCard(tmpCard)) {
                    intent.putExtra("NEWCARD", tmpCard);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    //intent.putExtra("NEWCARD", "");
                    setResult(RESULT_CANCELED, intent);
                    finish();
                }
            } else {

                if (dao.EditCard(tmpCard)) {
                    intent.putExtra("EDITCARD", tmpCard);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    //intent.putExtra("NEWCARD", "");
                    setResult(RESULT_CANCELED, intent);
                    finish();
                }
            }
        }
    }
}
