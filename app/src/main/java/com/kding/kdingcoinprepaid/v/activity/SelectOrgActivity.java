package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.SelectOrgImpl;
import com.kding.kdingcoinprepaid.utils.MToast;

import java.util.ArrayList;
import java.util.List;

public class SelectOrgActivity extends BaseCompatActivity implements IUserInterfaceCallBack {

    private ActionProcessButton buttonSelect;
    private SelectOrgImpl selectOrgImpl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectorg);

        selectOrgImpl = new SelectOrgImpl(mContext,this);

        initView();

    }

    private void initView() {
        Button buttonGame = (Button) findViewById(R.id.button_select_game);
        EditText editTextUnion = (EditText) findViewById(R.id.editText_union);
        buttonSelect = (ActionProcessButton)findViewById(R.id.button_select);

        setTooblBar(R.id.common_toolbar, R.string.select_org_title, false, false);

        buttonGame.setText(selectOrgImpl.getGameEditTextText());
        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(selectOrgImpl.getGameEditTextIntent(), ConstantTag.SELECT_REQUESTCODE);
            }
        });


        editTextUnion.setHint(selectOrgImpl.getUnionEditTextText());
        if (selectOrgImpl.getUnionEditTextClickable()){
            editTextUnion.setInputType(InputType.TYPE_CLASS_TEXT);
        }else {
            editTextUnion.setInputType(InputType.TYPE_NULL);
        }

        editTextUnion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selectOrgImpl.getUnionEditTextClickable()) {
                    startActivityForResult(selectOrgImpl.getUnionEditTextIntent(), ConstantTag.SELECT_REQUESTCODE);
                }
            }
        });

        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectOrgImpl.postSelectInfo();
                buttonSelect.setProgress(50);
                buttonSelect.setClickable(false);
            }
        });
    }

    @Override
    public void operationSuc() {
        buttonSelect.setProgress(100);
        buttonSelect.setClickable(true);
        startActivity(new Intent(mContext,UnionActivity.class));
    }

    @Override
    public void operationFail(String errorMsg) {
        buttonSelect.setProgress(-1);
        MToast.showShort(mContext,errorMsg);
        buttonSelect.setClickable(true);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        selectOrgImpl.setPostData(requestCode, resultCode, data);
    }
}
