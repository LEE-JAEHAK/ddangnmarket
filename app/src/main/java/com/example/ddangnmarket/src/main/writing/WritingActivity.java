package com.example.ddangnmarket.src.main.writing;

import androidx.annotation.Nullable;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.main.MainActivity;
import com.example.ddangnmarket.src.main.writing.interfaces.WritingActivityView;
import com.example.ddangnmarket.src.main.writing.models.RequestProduct;
import com.example.ddangnmarket.src.main.writing.models.RequestProductImage;
import com.example.ddangnmarket.src.main.writing.models.Result;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WritingActivity extends BaseActivity implements WritingActivityView {
    EditText mEtTitle, mEtPrice, mEtDescription;
    ImageView mIvPic1, mIvPic2, mIvPic3;
    final int PICK_IMAGE_MULTIPLE = 1;
    ArrayList<Uri> mUris = new ArrayList<>();
    private StorageReference mStorageReference;
    private FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference();

        mEtTitle = findViewById(R.id.writing_et_title);
        mEtPrice = findViewById(R.id.writing_et_price);
        mEtDescription = findViewById(R.id.writing_et_description);

        mIvPic1 = findViewById(R.id.writing_iv_pic1);
        mIvPic2 = findViewById(R.id.writing_iv_pic2);
        mIvPic3 = findViewById(R.id.writing_iv_pic3);
        mIvPic1.setImageResource(0);
        mIvPic2.setImageResource(0);
        mIvPic3.setImageResource(0);

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

    public void uploadImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

        System.out.println("사진업로드");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && data != null) {
                if (data.getData() != null) {
                    Uri uri;
                    //mUris.add(uri);

                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            uri = item.getUri();
                            System.out.println("uri : " + uri);
                            mUris.add(uri);
                            switch (i) {
                                case 0:
                                    mIvPic1.setImageURI(uri);
                                    break;
                                case 1:
                                    mIvPic2.setImageURI(uri);
                                    break;
                                case 2:
                                    mIvPic3.setImageURI(uri);
                                    break;
                            }
                        }
                    }
                }
            } else {
                showCustomToast("You haven't picked Image");
            }
        } catch (Exception e) {
            showCustomToast("Something went wrong");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void validateProductSuccess(boolean isSuccess, int code, String message, final Result result) {
        if (code == 100) {
            showCustomToast(message);

            for (int i = 0; i < mUris.size(); i++) {
                StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());

                final int finalInt = i;
                ref.putFile(mUris.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                postProductImage(uri, finalInt + 1, result.getProductNo().get(0).getProductNo());
                            }
                        });
                    }
                });
            }
        } else if (code == 200) showCustomToast(message);
        else if (code == 201) showCustomToast(message);
        else if (code == 202) showCustomToast(message);

    }

    public void postProductImage(Uri downloadURL, int index, int productNo) {
        System.out.println(downloadURL + " " + index + " " + productNo);
        WritingService writingService = new WritingService(this);
        RequestProductImage requestProductImage = new RequestProductImage();
        requestProductImage.setImageUrl(downloadURL.toString());
        requestProductImage.setImageIndex(index);
        requestProductImage.setProductNo(productNo);
        writingService.postProductImage(requestProductImage, index);
    }

    @Override
    public void validateProductFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void validateProductImageSuccess(boolean isSuccess, int code, String message, int index) {
        //showCustomToast(message);

        if (isSuccess && mUris.size() == index) {
            showCustomToast(message);
            Intent intent = new Intent(WritingActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateProductImageFailure(String message) {
        showCustomToast(message);
    }
}
