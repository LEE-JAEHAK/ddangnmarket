package com.example.ddangnmarket.src.main.home.product;

import com.example.ddangnmarket.src.main.home.product.interfaces.ProductActivityView;
import com.example.ddangnmarket.src.main.home.product.interfaces.ProductRetrofitInterface;
import com.example.ddangnmarket.src.main.home.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class ProductService {
    private final ProductActivityView productActivityView;

    public ProductService(ProductActivityView productActivityView) {
        this.productActivityView = productActivityView;
    }

    void getProduct(int productNo){
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getProductQuery(productNo).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                final ProductResponse productResponse = response.body();
                productActivityView.validateProductSuccess(productResponse.getIsSuccess(),productResponse.getCode(),productResponse.getMessage(),productResponse.getResult());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                    productActivityView.validateProductFailure(t.getMessage());
            }
        });
    }
}
