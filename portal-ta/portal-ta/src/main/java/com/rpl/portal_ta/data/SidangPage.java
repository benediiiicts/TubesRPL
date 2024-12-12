package com.rpl.portal_ta.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SidangPage {
    private String topicTA;
    private String tanggal;
    private String waktu;
    private String tempat;
    private String mahasiswa;
    private String dosenPembimbingUtama;
    private String dosenPembimbingPendamping;
    private String dosenKetuaPenguji;
    private String dosenAnggotaPenguji;
}
