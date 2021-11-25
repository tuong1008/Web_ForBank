/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TaiKhoan{
    private String soTK;
    private String CMND;
    private BigDecimal soDu;
    private String maCN;
    private Timestamp ngayMoTK;

    public String getSoTK() {
        return soTK;
    }

    public String getCMND() {
        return CMND;
    }

    public BigDecimal getSoDu() {
        return soDu;
    }

    public String getMaCN() {
        return maCN;
    }

    public Timestamp getNgayMoTK() {
        return ngayMoTK;
    }

    public void setSoTK(String soTK) {
        this.soTK = soTK;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public void setSoDu(BigDecimal soDu) {
        this.soDu = soDu;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    public void setNgayMoTK(Timestamp ngayMoTK) {
        this.ngayMoTK = ngayMoTK;
    }
    
}
