package com.todev.bieon.Model;

import android.util.Log;

public class DataGaram {
    private String Noseri;
    private String Nacl;
    private String Whiteness;
    private String Watercontent;

   /* public DataGaram(String noseri, String nacl, String whiteness, String watercontent) {

    }*/

    public DataGaram(String noseri, String nacl, String whiteness, String watercontent) {
        Noseri = noseri;
        Nacl = nacl;
        Whiteness = whiteness;
        Watercontent = watercontent;

    }

    public String getNoseri() {
        return Noseri;
    }

    public void setNoseri(String noseri) {
        Noseri = noseri;
    }

    public String getNacl() {
        return Nacl;
    }

    public void setNacl(String nacl) {
        Nacl = nacl;
    }

    public String getWhiteness() {
        return Whiteness;
    }

    public void setWhiteness(String whiteness) {
        Whiteness = whiteness;
    }

    public String getWatercontent() {
        return Watercontent;
    }

    public void setWatercontent(String watercontent) {
        Watercontent = watercontent;
    }


}
