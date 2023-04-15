package GUI.Panel;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import GUI.Main;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import helper.Formater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieuNhap extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar, right;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tablePhieuNhap;
    JScrollPane scrollTablePhieuNhap;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    TaoPhieuNhap nhapKho;
    Main m;
    NhanVienDTO nv;

    PhieuNhapBUS phieunhapBUS = new PhieuNhapBUS();
    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();
    ArrayList<PhieuNhapDTO> listPhieu;

    Color BackgroundColor = new Color(240, 247, 250);

    public PhieuNhap(Main m, NhanVienDTO nv) {
        this.m = m;
        this.nv = nv;
        this.listPhieu = phieunhapBUS.getAll();
        initComponent();
        loadDataTalbe(this.listPhieu);
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 20));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 20));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(20, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(20, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tablePhieuNhap = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên nhập", "Thời gian", "Tổng tiền", "Trạng thái"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuNhap.setModel(tblModel);
        tablePhieuNhap.setDefaultEditor(Object.class, null);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhieuNhap.setDefaultRenderer(Object.class, centerRenderer);
        tablePhieuNhap.setFocusable(false);
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainFunction = new MainFunction();
        functionBar.add(mainFunction);

        //        //Add Event MouseListener
        mainFunction.btnAdd.addActionListener(this);
        mainFunction.btnDelete.addActionListener(this);
        mainFunction.btnDetail.addActionListener(this);
        mainFunction.btnEdit.addActionListener(this);
        mainFunction.btnNhapExcel.addActionListener(this);
        mainFunction.btnXuatExcel.addActionListener(this);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);

        main.add(scrollTablePhieuNhap);

//        right = new PanelBorderRadius();
//        right.setPreferredSize(new Dimension(400, 0));
//        right.setLayout(new FlowLayout(1, 15, 40));
//        contentCenter.add(right, BorderLayout.EAST);
    }

    public void loadDataTalbe(ArrayList<PhieuNhapDTO> listphieunhap) {
        tblModel.setRowCount(0);
        int size = listphieunhap.size();
        for (int i = 0; i < size; i++) {
            tblModel.addRow(new Object[]{
                i + 1, listphieunhap.get(i).getMaphieu(),
                nccBUS.getTenNhaCungCap(listphieunhap.get(i).getManhacungcap()),
                nvBUS.getNameById(listphieunhap.get(i).getManguoitao()),
                Formater.FormatTime(listphieunhap.get(i).getThoigiantao()),
                Formater.FormatVND(listphieunhap.get(i).getTongTien()),
                listphieunhap.get(i).getTrangthai() == 1 ? "Đã nhập" : "Huỷ"
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mainFunction.btnAdd) {
            nhapKho = new TaoPhieuNhap(nv, "create",m);
            m.setPanel(nhapKho);
        } else if (source == mainFunction.btnDetail) {
            int index = getRowSelected();
            if (index != -1) {
                nhapKho = new TaoPhieuNhap(nv, "view", listPhieu.get(index),m);
                m.setPanel(nhapKho);
            }
        }
    }

    public int getRowSelected() {
        int index = tablePhieuNhap.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu nhập");
        }
        return index;
    }

}
