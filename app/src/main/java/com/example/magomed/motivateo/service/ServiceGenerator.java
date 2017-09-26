package com.example.magomed.motivateo.service;

import retrofit.RestAdapter;

public class ServiceGenerator {

//    private static final String BASE_URL = "http://192.168.56.1:8080/";
//
//    private static Retrofit.Builder builder =
//            new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create());
//
//    private static Retrofit retrofit = builder.build();
//
//    private static OkHttpClient.Builder httpClient =
//            new OkHttpClient.Builder();
//
//    public static <S> S createService(
//            Class<S> serviceClass) {
//        return retrofit.create(serviceClass);
//    }

    static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }
}