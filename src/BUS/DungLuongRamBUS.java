/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DungLuongRamDAO;
import DTO.ThuocTinhSanPham.DungLuongRamDTO;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DungLuongRamBUS extends BaseBUS<DungLuongRamDTO> {
    public DungLuongRamBUS getInstance() {
        return new DungLuongRamBUS();
    }
    
    public DungLuongRamBUS() {
        super(new DungLuongRamDAO());
    }

    @Override
    public int getIndexByCode(int maram) {
        int i = 0;
        int vitri = -1;
        while (i < this.list.size() && vitri == -1) {
            if (list.get(i).getMadlram()== maram) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    @Override
    public boolean delete(DungLuongRamDTO dlram) {
        boolean check = DAO.delete(Integer.toString(dlram.getMadlram())) != 0;
        if (check) {
            list.remove(dlram);
        }
        return check;
    }

    @Override
    public boolean update(DungLuongRamDTO dlram) {
        boolean check = DAO.update(dlram) != 0;
        if (check) {
            this.list.set(getIndexByCode(dlram.getMadlram()), dlram);
        }
        return check;
    }
    
    public boolean checkDup(int dl) {
        boolean check = true;
        int i = 0;
        while(i < list.size() && check == true) {
            if (list.get(i).getDungluongram() == dl) {
                check = false;
            } 
            else i++;
        }
        return check;
    }
    
    public int getKichThuocById(int madlram) {
        return this.list.get(this.getIndexByCode(madlram)).getDungluongram();
    }
    
    public String[] getArrKichThuoc() {
        String[] result = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            result[i] = Integer.toString(list.get(i).getDungluongram())+"GB";
        }
        return result;
    }
}
