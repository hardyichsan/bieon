package com.todev.bieon.API;

import retrofit2.Retrofit;

public class ApiClient {

    /*public static final String BASE_URL = "http://192.168.43.68/api-prodi/public/index.php/";
    public static final String BASE_URL_IMAGE = "http://192.168.43.68/prodi_ti/images/publication/";*/

    public static final String BASE_URL = "http://ti.ft.uika-bogor.ac.id/prodi_ti/api-prodi/public/index.php/";
    public static final String BASE_URL_IMAGE = "http://ti.ft.uika-bogor.ac.id/assets/img/content/";


    /* public static final String BASE_URL_IMAGE = "http://192.168.0.100/prodi_ti/images/publication/";*/

    /*public static final String BASE_URL = "http://10.10.1.113/api-prodi/public/index.php/";
    public static final String BASE_URL_IMAGE = "http://10.10.1.113/prodi_ti/images/publication/";*/

    private static Retrofit retrofit = null;
}
