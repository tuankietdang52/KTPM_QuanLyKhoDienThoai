package BUS;

import DAO.SanPhamDAO;
import DTO.PhienBanSanPhamDTO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class SanPhamBUS extends BaseBUS<SanPhamDTO>{

    public final SanPhamDAO spDAO = new SanPhamDAO();
    public PhienBanSanPhamBUS cauhinhBus = new PhienBanSanPhamBUS();

    public SanPhamBUS() {
        super(new SanPhamDAO());
    }
    
    public SanPhamDTO getByMaSP(int masp) {
        int vitri = -1;
        int i = 0;
        while (i <= this.list.size() && vitri == -1) {
            if (this.list.get(i).getMasp() == masp) {
                vitri = i;
            } else {
                i++;
            }
        }
        return this.list.get(vitri);
    }

    @Override
    public int getIndexByCode(int masanpham) {
        int i = 0;
        int vitri = -1;
        while (i < this.list.size() && vitri == -1) {
            if (list.get(i).getMasp() == masanpham) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    @Override
    public boolean add(SanPhamDTO lh){
        throw new RuntimeException("use Func add with 2 para instead");
    }

    public boolean add(SanPhamDTO lh, ArrayList<PhienBanSanPhamDTO> listch) {
        boolean check = spDAO.insert(lh) != 0;
        if (check) {
            cauhinhBus.add(listch);
            this.list.add(lh);
        }
        return check;
    }

    @Override
    public boolean delete(SanPhamDTO lh) {
        boolean check = spDAO.delete(Integer.toString(lh.getMasp())) != 0;
        if (check) {
            this.list.remove(lh);
        }
        return check;
    }

    @Override
    public boolean update(SanPhamDTO lh) {
        boolean check = spDAO.update(lh) != 0;
        if (check) {
            this.list.set(getIndexByCode(lh.getMasp()), lh);
        }
        return check;
    }

    public ArrayList<SanPhamDTO> getByMakhuvuc(int makv) {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (SanPhamDTO i : this.list) {
            if (i.getKhuvuckho() == makv) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<SanPhamDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (SanPhamDTO i : this.list) {
            if (Integer.toString(i.getMasp()).toLowerCase().contains(text) || i.getTensp().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public SanPhamDTO getSp(int mapb) {
        return spDAO.selectByPhienBan(mapb + "");
    }

    public int getQuantity() {
        int n = 0;
        for(SanPhamDTO i : this.list) {
            if (i.getSoluongton() != 0) {
                n += i.getSoluongton();
            }
        }
        return n;
    }
}
