/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gumilang
 */
class User {
    private int no_regist;
    private String Nama, Unit, Barang, Pinjam, Kembali, Status;
    
    public User(int no_regist, String Nama, String Unit, String Barang, String Pinjam, String Kembali, String Status){
        this.no_regist=no_regist;
        this.Nama=Nama;
        this.Unit=Unit;
        this.Barang=Barang;
        this.Pinjam=Pinjam;
        this.Kembali=Kembali;
        this.Status=Status;
    }
    
    public int getno_regist(){
        return no_regist;
    }
    public String getNama(){
        return Nama;
    }
    public String getUnit(){
        return Unit;
    }
    public String getBarang(){
        return Barang;
    }
    public String getPinjam(){
        return Pinjam;
    }
    public String getKembali(){
        return Kembali;
    }
    public String getStatus(){
        return Status;
    }
}

