/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.XuatXuDAO;
import DTO.ThuocTinhSanPham.XuatXuDTO;

/**
 *
 * @author 84907
 */
public class XuatXuBUS extends BaseBUS<XuatXuDTO>{
    public XuatXuBUS() {
        super(new XuatXuDAO());
    }

    public String[] getArrTenXuatXu() {
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).getTenxuatxu();
        }
        return result;
    }

    @Override
    public boolean delete(XuatXuDTO xuatxu) {
        boolean check = DAO.delete(Integer.toString(xuatxu.getMaxuatxu())) != 0;
        if (check) {
            list.removeIf(e -> e == xuatxu);
        }
        return check;
    }

    @Override
    public int getIndexByCode(int maxx) {
        int i = 0;
        int vitri = -1;
        while (i < list.size() && vitri == -1) {
            if (list.get(i).getMaxuatxu() == maxx) vitri = i;
            else i++;
        }
        return vitri;
    }

    public String getTenXuatXu(int maxx) {
        int index = this.getIndexByCode(maxx);
        return list.get(index).getTenxuatxu();
    }

    @Override
    public boolean update(XuatXuDTO xuatxu) {
        boolean check = DAO.update(xuatxu) != 0;
        if (check) {
            list.set(getIndexByCode(xuatxu.getMaxuatxu()), xuatxu);
        }
        return check;
    }
    
    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < list.size() && check == true) {
            if (list.get(i).getTenxuatxu().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } 
            else i++;
        }
        return check;
    }

    public boolean isUsingByProduct(XuatXuDTO model){
        XuatXuDAO xuatXuDAO = (XuatXuDAO) DAO;  
        return xuatXuDAO.isUsing(model);
    }
}
