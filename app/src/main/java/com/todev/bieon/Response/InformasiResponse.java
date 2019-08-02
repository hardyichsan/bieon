package com.todev.bieon.Response;

import com.todev.bieon.Model.DataInformasi;

import java.util.ArrayList;

public class InformasiResponse {

    public Boolean status;
    public ArrayList<DataInformasi> data;


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<DataInformasi> getData() {
        return data;
    }

    public void setData(ArrayList<DataInformasi> data) {
        this.data = data;
    }


    public Boolean isStatus(){
        return status;
    }


    @Override
    public String toString(){
        return
                "ResponseBerita{" +
                        "data = '" + data + '\'' +
                        ",status = '" + status + '\'' + "}";
    }
}
