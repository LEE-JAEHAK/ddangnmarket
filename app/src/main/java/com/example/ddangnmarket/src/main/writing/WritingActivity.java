package com.example.ddangnmarket.src.main.writing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.login.LoginService;
import com.example.ddangnmarket.src.login.models.RequestJwt;
import com.example.ddangnmarket.src.login.models.RequestPhoneCert;
import com.example.ddangnmarket.src.main.MainActivity;
import com.example.ddangnmarket.src.main.writing.interfaces.WritingActivityView;
import com.example.ddangnmarket.src.main.writing.models.RequestProduct;
import com.example.ddangnmarket.src.main.writing.models.Result;

public class WritingActivity extends BaseActivity implements WritingActivityView {
    EditText mEtTitle, mEtPrice, mEtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        mEtTitle = findViewById(R.id.writing_et_title);
        mEtPrice = findViewById(R.id.writing_et_price);
        mEtDescription = findViewById(R.id.writing_et_description);
    }

    public void writingOnClick(View view) {
        switch (view.getId()) {
            case R.id.writing_btn_back:
                onBackPressed();
                break;
            case R.id.writing_btn_submit:
                submit();
                break;
            case R.id.writing_btn_categories:
                showCategories();
                break;
            case R.id.writing_btn_upload:
                uploadImage();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void submit() {
        System.out.println("writingActivity submit : ");
        WritingService writingService = new WritingService(this);
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setTitle(mEtTitle.getText().toString());
        requestProduct.setPrice(Integer.parseInt(mEtPrice.getText().toString()));
        requestProduct.setText(mEtDescription.getText().toString());
        requestProduct.setCategoriesNo(1);   //수정요망
        writingService.postProduct(requestProduct);
    }

    public void showCategories() {

    }

    public void uploadImage() {

    }

    @Override
    public void validateProductSuccess(boolean isSuccess, int code, String message, Result result) {
        if (code == 100) {
            showCustomToast(message);

            Intent intent = new Intent(WritingActivity.this, MainActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (code == 200) showCustomToast(message);
        else if (code == 201) showCustomToast(message);
        else if (code == 202) showCustomToast(message);

    }

    @Override
    public void validateProductFailure(String message) {
        showCustomToast(message);
    }
}
