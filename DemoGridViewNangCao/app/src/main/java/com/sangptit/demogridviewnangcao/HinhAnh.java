package com.sangptit.demogridviewnangcao;
public class HinhAnh {
    private int hinh;      // Lưu resource ID của hình
    private String ten;    // Tên mô tả hình

    // Constructor
    public HinhAnh(int hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
    }

    // Getter và Setter
    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}