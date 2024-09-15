/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuVucKhoDAO;
import DTO.KhuVucKhoDTO;
import java.util.ArrayList;

public class KhuVucKhoBUS extends BaseBUS<KhuVucKhoDTO>{
    public KhuVucKhoBUS getInstance() {
        return new KhuVucKhoBUS();
    }
    
    public KhuVucKhoBUS() {
        super(new KhuVucKhoDAO());
    }

    @Override
    public int getIndexByCode(int makhuvuc) {
        int i = 0;
        int vitri = -1;
        while (i < this.list.size() && vitri == -1) {
            if (list.get(i).getMakhuvuc() == makhuvuc) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    @Override
    public boolean delete(KhuVucKhoDTO kvk) {
        boolean check = DAO.delete(Integer.toString(kvk.getMakhuvuc())) != 0;
        if (check) {
            list.removeIf(e -> e == kvk);
        }
        return check;
    }

    @Override
    public boolean update(KhuVucKhoDTO kvk) {
        boolean check = DAO.update(kvk) != 0;
        if (check) {
            this.list.set(getIndexByCode(kvk.getMakhuvuc()), kvk);
        }
        return check;
    }

    public ArrayList<KhuVucKhoDTO> search(String txt, String type) {
        ArrayList<KhuVucKhoDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (KhuVucKhoDTO i : list) {
                    if (Integer.toString(i.getMakhuvuc()).contains(txt) || i.getTenkhuvuc().toLowerCase().contains(txt)){
                        result.add(i);
                    }
                }
            }
            case "Mã khu vực kho" -> {
                for (KhuVucKhoDTO i : list) {
                    if (Integer.toString(i.getMakhuvuc()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Tên khu vực kho" -> {
                for (KhuVucKhoDTO i : list) {
                    if (i.getTenkhuvuc().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }
    
    public String[] getArrTenKhuVuc() {
        int size = list.size();
        String[] result = new String[size];
        for(int i = 0; i < size; i++) {
            result[i] = list.get(i).getTenkhuvuc();
        }
        return result;
    }
    
    public String getTenKhuVuc(int makhuvuc) {
        return this.list.get(this.getIndexByCode(makhuvuc)).getTenkhuvuc();
    }
}
