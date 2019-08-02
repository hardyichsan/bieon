package com.todev.bieon.Model;

public class DataInformasi {

    public String id_content;
    public String judul;
    public String foto;
    public String tgl_dibuat;
    public String isi;

    public String getId_content() {
        return id_content;
    }

    public void setId_content(String id_content) {
        this.id_content = id_content;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTgl_dibuat() {
        return tgl_dibuat;
    }

    public void setTgl_dibuat(String tgl_dibuat) {
        this.tgl_dibuat = tgl_dibuat;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public DataInformasi(String id_content, String judul, String foto, String tgl_dibuat, String isi) {
        this.id_content = id_content;
        this.judul = judul;
        this.foto = foto;
        this.tgl_dibuat = tgl_dibuat;
        this.isi = isi;
    }

    @Override
    public String toString(){
        return
                "BeritaItem{" +
                        ",foto = '" + foto + '\'' +
                        ",id_content = '" + id_content + '\'' +
                        ",judul = '" + judul + '\'' +
                        ",isi = '" + isi+ '\'' +
                        ",tanggal_posting = '" + tgl_dibuat + '\'' +
                        "}";
    }
}
