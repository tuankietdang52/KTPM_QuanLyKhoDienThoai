package BUS;

import DAO.HeDieuHanhDAO;
import DTO.ThuocTinhSanPham.HeDieuHanhDTO;

/**
 *
 * @author 84907
 */
public class HeDieuHanhBUS extends BaseBUS<HeDieuHanhDTO>{
    public HeDieuHanhBUS() {
        super(new HeDieuHanhDAO());
    }

    public String[] getArrTenHeDieuHanh() {
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).getTenhdh();
        }
        return result;
    }

    @Override
    public boolean delete(HeDieuHanhDTO hdh) {
        boolean check = DAO.delete(Integer.toString(hdh.getMahdh())) != 0;
        if (check) {
            list.remove(hdh);
        }
        return check;
    }

    @Override
    public int getIndexByCode(int mamau) {
        int i = 0;
        int vitri = -1;
        while (i < this.list.size() && vitri == -1) {
            if (list.get(i).getMahdh() == mamau) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public String getTenHdh(int mahdh) {
        int index = this.getIndexByCode(mahdh);
        return this.list.get(index).getTenhdh();
    }

    @Override
    public boolean update(HeDieuHanhDTO hdh) {
        boolean check = DAO.update(hdh) != 0;
        if (check) {
            list.set(getIndexByCode(hdh.getMahdh()), hdh);
        }
        return check;
    }

    public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < list.size() && check == true) {
            if (list.get(i).getTenhdh().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            }
            else i++;
        }
        return check;
    }

}
