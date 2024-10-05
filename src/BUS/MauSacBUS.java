/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.MauSacDAO;
import DAO.XuatXuDAO;
import DTO.ThuocTinhSanPham.MauSacDTO;
import DTO.ThuocTinhSanPham.XuatXuDTO;

/**
 *
 * @author Tran Nhat Sinh
 */
public class MauSacBUS extends BaseBUS<MauSacDTO>{
    public MauSacBUS() {
        super(new MauSacDAO());
    }

    public String[] getArrTenMauSac() {
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).getTenmau();
        }
        return result;
    }

    public MauSacDTO getByIndex(int index) {
        return this.list.get(index);
    }

    @Override
    public boolean delete(MauSacDTO msac) {
        boolean check = DAO.delete(Integer.toString(msac.getMamau())) != 0;
        if (check) {
            list.removeIf(e -> e == msac);;
        }
        return check;
    }

    @Override
    public int getIndexByCode(int mamau) {
        int i = 0;
        int vitri = -1;
        while (i < this.list.size() && vitri == -1) {
            if (list.get(i).getMamau() == mamau) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public String getTenMau(int mamau) {
        int index = this.getIndexByCode(mamau);
        System.out.println(index);
        return this.list.get(index).getTenmau();
    }

    @Override
    public boolean update(MauSacDTO msac) {
        boolean check = DAO.update(msac) != 0;
        if (check) {
            list.set(getIndexByCode(msac.getMamau()), msac);
        }
        return check;
    }

    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < list.size() && check == true) {
            if (this.list.get(i).getTenmau().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } 
            else i++;
        }
        return check;
    }

    public boolean isUsingByProduct(MauSacDTO model){
        MauSacDAO colorDAO = (MauSacDAO) DAO;  
        return colorDAO.isUsing(model);
    }
}
