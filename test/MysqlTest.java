package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import DAO.DungLuongRomDAO;
import DAO.MauSacDAO;
import DAO.ThuongHieuDAO;
import DTO.ThuocTinhSanPham.DungLuongRomDTO;
import DTO.ThuocTinhSanPham.MauSacDTO;
import DTO.ThuocTinhSanPham.ThuongHieuDTO;

public class MysqlTest {
    @Test
    public void colorDatabaseTest(){
        MauSacDAO dao = new MauSacDAO();     

        MauSacDTO mausac = new MauSacDTO(1, "Xanh");
        boolean result = dao.isUsing(mausac);

        assertTrue("result is false", result);
    }

   @Test 
    public void brandDatabaseTest(){
        ThuongHieuDAO dao = new ThuongHieuDAO();
        ThuongHieuDTO model = new ThuongHieuDTO(1, "blah");

        boolean result = dao.isUsing(model);
        assertTrue("result is false", result);
    }

    @Test 
    public void romDatabaseTest(){
        DungLuongRomDAO dao = new DungLuongRomDAO();
        DungLuongRomDTO model = new DungLuongRomDTO(1, 3213);

        boolean result = dao.isUsing(model);
        assertTrue("result is false", result);
    }
}
