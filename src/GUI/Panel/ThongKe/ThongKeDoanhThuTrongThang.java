package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeTonKhoDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import GUI.Component.PanelBorderRadius;
import chart1.Chart;
import chart1.ModelChart;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import helper.Formater;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class ThongKeDoanhThuTrongThang extends JPanel {

    PanelBorderRadius pnlChart;
    JPanel pnl_top;
    HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> listSp;
    ThongKeBUS thongkeBUS;
    JMonthChooser monthchooser;
    Chart chart;
    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;

    public ThongKeDoanhThuTrongThang(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        listSp = thongkeBUS.getTonKho();
        initComponent();
        loadThongKeTungNgayTrongThang();
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblChonNam = new JLabel("Chọn tháng thống kê");
        monthchooser = new JMonthChooser();
        monthchooser.addPropertyChangeListener("month", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                int year = (Integer) e.getNewValue();
                loadThongKeTungNgayTrongThang();
            }
        });
        pnl_top.add(lblChonNam);
        pnl_top.add(monthchooser);

        pnlChart = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(pnlChart, BoxLayout.Y_AXIS);
        pnlChart.setLayout(boxly);

        chart = new Chart();
        chart.addLegend("Vốn", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));
        chart.addData(new ModelChart("Tháng 1", new double[]{100, 150, 200}));
        chart.addData(new ModelChart("Tháng 2", new double[]{600, 750, 300}));
        chart.addData(new ModelChart("Tháng 3", new double[]{200, 350, 1000}));
        chart.addData(new ModelChart("Tháng 4", new double[]{480, 150, 750}));
        chart.addData(new ModelChart("Tháng 5", new double[]{100, 150, 200}));
        chart.addData(new ModelChart("Tháng 6", new double[]{600, 750, 300}));
        chart.addData(new ModelChart("Tháng 7", new double[]{200, 350, 1000}));
        chart.addData(new ModelChart("Tháng 8", new double[]{480, 150, 750}));
        chart.addData(new ModelChart("Tháng 9", new double[]{100, 150, 200}));
        chart.addData(new ModelChart("Tháng 10", new double[]{600, 750, 300}));
        chart.addData(new ModelChart("Tháng 11", new double[]{200, 350, 1000}));
        chart.addData(new ModelChart("Tháng 12", new double[]{480, 150, 750}));
        pnlChart.add(chart);
        
        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Ngày", "Chi phí", "Doanh thu","Lợi nhuận"};
        tblModel.setColumnIdentifiers(header);
        tableThongKe.setModel(tblModel);
        tableThongKe.setAutoCreateRowSorter(true);
        tableThongKe.setDefaultEditor(Object.class, null);
        scrollTableThongKe.setViewportView(tableThongKe);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableThongKe.setDefaultRenderer(Object.class, centerRenderer);
        tableThongKe.setFocusable(false);
        scrollTableThongKe.setPreferredSize(new Dimension(0, 300));
        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);
    }
    
    public void loadThongKeTungNgayTrongThang(){
        Calendar calendar = Calendar.getInstance();
        int nam = calendar.get(Calendar.YEAR);
        int thang = monthchooser.getMonth();
        ArrayList<ThongKeTungNgayTrongThangDTO> list = thongkeBUS.getThongKeTungNgayTrongThang(thang, nam);
        pnlChart.remove(chart);
        chart = new Chart();
        chart.addLegend("Vốn", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));
        int sum_chiphi = 0;
        int sum_doanhthu = 0;
        int sum_loinhuan = 0;
        for (int i = 0; i < list.size(); i++) {
            int index = i+1;
            sum_chiphi+=list.get(i).getChiphi();
            sum_doanhthu+=list.get(i).getDoanhthu();
            sum_loinhuan+=list.get(i).getLoinhuan();
            if(index%3==0){
                chart.addData(new ModelChart("Ngày "+(index-2)+ "->"+(index), new double[]{sum_chiphi,sum_doanhthu,sum_loinhuan}));
                sum_chiphi=0;
            sum_doanhthu=0;
            sum_loinhuan=0;
            }
        }
        chart.repaint();
        chart.validate();
        pnlChart.add(chart);
        pnlChart.repaint();
        pnlChart.validate();
        tblModel.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            tblModel.addRow(new Object[]{
                list.get(i).getNgay(),Formater.FormatVND(list.get(i).getChiphi()),Formater.FormatVND(list.get(i).getDoanhthu()),Formater.FormatVND(list.get(i).getLoinhuan())
            });
        }
    }
}
