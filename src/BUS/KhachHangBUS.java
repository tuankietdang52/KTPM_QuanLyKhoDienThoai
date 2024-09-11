package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

public class KhachHangBUS extends BaseBUS<KhachHangDTO>{
    public KhachHangBUS() {
        super(new KhachHangDAO());
    }

    @Override
    public int getIndexByCode(int makhachhang) {
        int i = 0;
        int vitri = -1;
        while (i < this.list.size() && vitri == -1) {
            if (list.get(i).getMaKH() == makhachhang) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    @Override
    public boolean delete(KhachHangDTO kh) {
        boolean check = DAO.delete(Integer.toString(kh.getMaKH())) != 0;
        if (check) {
            this.list.remove(getIndexByCode(kh.getMaKH()));
        }
        return check;
    }

    @Override
    public boolean update(KhachHangDTO kh) {
        boolean check = DAO.update(kh) != 0;
        if (check) {
            this.list.set(getIndexByCode(kh.getMaKH()), kh);
        }
        return check;
    }

    public ArrayList<KhachHangDTO> search(String text, String type) {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (KhachHangDTO i : this.list) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text) || i.getHoten().toLowerCase().contains(text) || i.getDiachi().toLowerCase().contains(text) || i.getSdt().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã khách hàng" -> {
                for (KhachHangDTO i : this.list) {
                    if (Integer.toString(i.getMaKH()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên khách hàng" -> {
                for (KhachHangDTO i : this.list) {
                    if (i.getHoten().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (KhachHangDTO i : this.list) {
                    if (i.getDiachi().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (KhachHangDTO i : this.list) {
                    if (i.getSdt().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
        }

        return result;
    }

    public String getTenKhachHang(int makh) {
        String name = "";
        for (KhachHangDTO khachHangDTO : list) {
            if (khachHangDTO.getMaKH() == makh) {
                name = khachHangDTO.getHoten();
            }
        }
        return name;
    }

    public String[] getArrTenKhachHang() {
        int size = list.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = list.get(i).getHoten();
        }
        return result;
    }

    public KhachHangDTO selectKh(int makh) {
        return DAO.selectById(makh + "");
    }

}
