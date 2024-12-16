package com.rpl.portal_ta.data;

public class SidangTA {
    public Sidang sidang;
    public TA ta;
    public Mahasiswa mahasiswa;

    public SidangTA(Sidang sidang, TA ta){
        this.sidang = sidang;
        this.ta = ta;
    }
    public Sidang getSidang() {
        return sidang;
    }
    public TA getTa() {
        return ta;
    }
    public Mahasiswa getMahasiswa(){
        return this.mahasiswa;
    }
    public void setMahasiswa(Mahasiswa mahasiswa){
        this.mahasiswa = mahasiswa;
    }
}