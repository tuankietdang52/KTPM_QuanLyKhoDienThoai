/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DTO.ChiTietSanPhamDTO;
import DTO.SanPhamDTO;
import GUI.Component.CheckListItem;
import GUI.Component.CheckListRenderer;
import GUI.Panel.TaoPhieuXuat;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author robot
 */
public class SelectImei extends JDialog{

    private DefaultListModel<Object> listMode;
    private TaoPhieuXuat taoPhieuXuat;
    private ArrayList<ChiTietSanPhamDTO> ct;
    private JTextField findImei;
    private JList list;
    
    public SelectImei(JFrame owner, String title, boolean modal, TaoPhieuXuat taoPhieuXuat, ArrayList<ChiTietSanPhamDTO> ct){
        super(owner, title, modal);
        this.taoPhieuXuat = taoPhieuXuat;
        this.ct = ct;
        init();
        setVisible(true);
    }
    public void init(){
        setSize(new Dimension(300,500));
        setLayout(new BorderLayout());
        listMode = new DefaultListModel<>();
        findImei = new JTextField("");
        loadImei();
        findImei.setPreferredSize(new Dimension(0,40));
        findImei.putClientProperty("JTextField.placeholderText", "Tìm kiếm mã IMEI ...");
        findImei.putClientProperty("JTextField.showClearButton", true);
        findImei.addKeyListener(new KeyAdapter(){
        @Override
            public void keyReleased(KeyEvent e) {
            loadImei();
            }
        });
//        for (ChiTietSanPhamDTO chiTietSanPhamDTO : ct) {
//            CheckListItem check = new CheckListItem(chiTietSanPhamDTO.getImei());
//            if(taoPhieuXuat.checkImeiArea(chiTietSanPhamDTO.getImei())){
//                check.setSelected(true);
//            }
//            listMode.addElement(check);
//        }
//        list = new JList(listMode);
//        list.setCellRenderer(new CheckListRenderer());
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent event) {
//                JList list = (JList) event.getSource();
//                int index = list.locationToIndex(event.getPoint());// Get index of item
//                CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
//                item.setSelected(!item.isSelected()); // Toggle selected state
//                list.repaint(list.getCellBounds(index, index));// Repaint cell
//            }
//        });
        this.add(findImei,BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(list));
        setLocationRelativeTo(null);
    }
    
    public void loadImei(){
        String search = findImei.getText();
        System.out.println(search);
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        for (ChiTietSanPhamDTO i : ct) {
            if (i.getImei().toLowerCase().contains(search)) {
                result.add(i);
            }
        }
        listMode.setSize(0);
        for (ChiTietSanPhamDTO chiTietSanPhamDTO : result) {
            CheckListItem check = new CheckListItem(chiTietSanPhamDTO.getImei());
            if(taoPhieuXuat.checkImeiArea(chiTietSanPhamDTO.getImei())){
                check.setSelected(true);
            }
            listMode.addElement(check);
        }
        list = new JList(listMode);
        list.setCellRenderer(new CheckListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                int index = list.locationToIndex(event.getPoint());// Get index of item
                CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
                if(!item.isSelected()){
                    System.out.println(item.toString());
                    taoPhieuXuat.textAreaImei.append(item.toString() + "\n");
                } else {
                    String txt = taoPhieuXuat.textAreaImei.getText().replaceAll("(" + item.toString() + ")\n", "");
                    taoPhieuXuat.textAreaImei.setText(txt);
                }
                item.setSelected(!item.isSelected()); // Toggle selected state
                list.repaint(list.getCellBounds(index, index));// Repaint cell
            }
        });
    }
}
