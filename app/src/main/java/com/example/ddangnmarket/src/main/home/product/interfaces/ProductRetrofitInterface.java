package com.example.ddangnmarket.src.main.home.product.interfaces;

import com.example.ddangnmarket.src.main.home.product.models.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductRetrofitInterface {
    @GET("/product/{productNo}")
    Call<ProductResponse> getProductQuery(
            @Path("productNo") final int productNo
    );
}
