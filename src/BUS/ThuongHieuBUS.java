/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThuongHieuDAO;
import DAO.XuatXuDAO;
import DTO.ThuocTinhSanPham.ThuongHieuDTO;
import DTO.ThuocTinhSanPham.XuatXuDTO;

import java.util.ArrayList;

/**
 *
 * @author 84907
 */
public class ThuongHieuBUS extends BaseBUS<ThuongHieuDTO>{
    public ThuongHieuBUS() {
        super(new ThuongHieuDAO());
    }

    @Override
    public int getIndexByCode(int maloaihang) {
        int i = 0;
        int vitri = -1;
        while (i < list.size() && vitri == -1) {
            if (list.get(i).getMathuonghieu() == maloaihang) {
                vitri = i;
            } 
            else i++;
        }
        return vitri;
    }

    public Boolean add(String name) {
        ThuongHieuDTO lh = new ThuongHieuDTO(DAO.getAutoIncrement(), name);
        boolean check = DAO.insert(lh) != 0;
        if (check) {
            list.add(lh);
        }
        return check;
    }

    @Override
    public boolean delete(ThuongHieuDTO lh) {
        boolean check = DAO.delete(Integer.toString(lh.getMathuonghieu())) != 0;
        if (check) {
            list.removeIf(e -> e == lh);
        }
        return check;
    }

    @Override
    public boolean update(ThuongHieuDTO lh) {
        boolean check = DAO.update(lh) != 0;
        if (check) {
            list.set(getIndexByCode(lh.getMathuonghieu()), lh);
        }
        return check;
    }

    public ArrayList<ThuongHieuDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<ThuongHieuDTO> result = new ArrayList<>();
        for (ThuongHieuDTO i : list) {
            if (Integer.toString(i.getMathuonghieu()).toLowerCase().contains(text) || i.getTenthuonghieu().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public String[] getArrTenThuongHieu() {
        int size = list.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = list.get(i).getTenthuonghieu();
        }
        return result;
    }

    public String getTenThuongHieu(int mathuonghieu) {
        return list.get(this.getIndexByCode(mathuonghieu)).getTenthuonghieu();
    }

    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < list.size() && check == true) {
            if (list.get(i).getTenthuonghieu().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } 
            else i++;
        }
        return check;
    }

    public boolean isUsingByProduct(ThuongHieuDTO model){
        ThuongHieuDAO brandDAO = (ThuongHieuDAO) DAO;  
        return brandDAO.isUsing(model);
    }
}
