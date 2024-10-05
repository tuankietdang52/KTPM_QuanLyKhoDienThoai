/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DungLuongRomDAO;
import DAO.XuatXuDAO;
import DTO.ThuocTinhSanPham.DungLuongRomDTO;
import DTO.ThuocTinhSanPham.XuatXuDTO;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DungLuongRomBUS extends BaseBUS<DungLuongRomDTO>{
    public DungLuongRomBUS getInstance() {
        return new DungLuongRomBUS();
    }

    public DungLuongRomBUS() {
        super(new DungLuongRomDAO());
    }

    @Override
    public int getIndexByCode(int marom) {
        int i = 0;
        int vitri = -1;
        while (i < this.list.size() && vitri == -1) {
            if (list.get(i).getMadungluongrom() == marom) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    @Override
    public boolean delete(DungLuongRomDTO dlrom) {
        boolean check = DAO.delete(Integer.toString(dlrom.getMadungluongrom())) != 0;
        if (check) {
            list.removeIf(e -> e == dlrom);
        }
        return check;
    }

    @Override
    public boolean update(DungLuongRomDTO dlrom) {
        boolean check = DAO.update(dlrom) != 0;
        if (check) {
            list.set(getIndexByCode(dlrom.getMadungluongrom()), dlrom);
        }
        return check;
    }

    public int getKichThuocById(int madlrom) {
        return this.list.get(this.getIndexByCode(madlrom)).getDungluongrom();
    }

    public String[] getArrKichThuoc() {
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = Integer.toString(list.get(i).getDungluongrom()) + "GB";
        }
        return result;
    }

    public boolean checkDup(int dl) {
        boolean check = true;
        int i = 0;
        while (i < list.size() && check == true) {
            if (list.get(i).getDungluongrom() == dl) {
                check = false;
            }
            else i++;
        }
        return check;
    }

    public boolean isUsingByProduct(DungLuongRomDTO model){
        DungLuongRomDAO romDAO = (DungLuongRomDAO) DAO;  
        return romDAO.isUsing(model);
    }
}
