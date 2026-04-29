package com.sist.temp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
public class AttendanceApp extends JFrame {

    private DefaultTableModel model;
    private List<Employee> employees = new ArrayList<>();
    private Random random = new Random();
    private JTable table;

    public AttendanceApp() {
        setTitle("출퇴근 관리");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] col = {"사원ID", "상태", "출근", "퇴근"};
        model = new DefaultTableModel(col, 0);
        table = new JTable(model);

        initEmployees();
        refreshTable();

        JPanel panel = new JPanel();

        JButton inBtn = new JButton("출근");
        JButton outBtn = new JButton("퇴근");
        JButton outGoBtn = new JButton("외출");
        JButton nightBtn = new JButton("월차");
        JButton earlyBtn = new JButton("조퇴");

        inBtn.addActionListener(e -> handleCheckIn());
        outBtn.addActionListener(e -> handleCheckOut());
        outGoBtn.addActionListener(e -> handleOuting());
        nightBtn.addActionListener(e -> handleOvertime());
        earlyBtn.addActionListener(e -> handleEarlyLeave());

        panel.add(inBtn);
        panel.add(outBtn);
        panel.add(outGoBtn);
        panel.add(nightBtn);
        panel.add(earlyBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    private void initEmployees() {
        for (int i = 1; i <= 100; i++) {
            employees.add(new Employee("EMP" + i));
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Employee e : employees) {
            model.addRow(new Object[]{
                    e.id,
                    e.status,
                    e.inTime == null ? "" : e.inTime.toString(),
                    e.outTime == null ? "" : e.outTime.toString()
            });
        }
    }

    // 출근
    private void handleCheckIn() {
    	Set<Integer> lateIndexes = new HashSet<>();
        while (lateIndexes.size() < 3) {
            lateIndexes.add(random.nextInt(employees.size()));
        }

        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);

            if (e.inTime == null) {
                if (lateIndexes.contains(i)) {
                    // 지각 (9시 이후)
                    e.inTime = LocalTime.of(9, 0)
                            .plusMinutes(random.nextInt(60));
                    e.status = "지각";
                } else {
                    // 정상 출근 (8시~9시)
                    e.inTime = LocalTime.of(8, 0)
                            .plusMinutes(random.nextInt(60));
                    e.status = "출근";
                }
            }
        }
        refreshTable();
    }

    // 퇴근
    private void handleCheckOut() {
        for (Employee e : employees) {
            if (e.inTime != null && e.outTime == null) {
                e.outTime = LocalTime.of(18, 0)
                        .plusMinutes(random.nextInt(60));
                e.status = "퇴근";
            }
        }
        refreshTable();
    }

    // 야근
    private void handleOvertime() {
        for (Employee e : employees) {
            if (e.inTime != null) {
                e.outTime = LocalTime.of(19 + random.nextInt(3),
                        random.nextInt(60));
                e.status = "야근";
            }
        }
        refreshTable();
    }

    // 외출
    private void handleOuting() {
        for (Employee e : employees) {
            if (e.inTime != null) {
                e.status = "외출";
            }
        }
        refreshTable();
    }

    // 조퇴
    private void handleEarlyLeave() {
        for (Employee e : employees) {
            if (e.inTime != null) {
                e.outTime = LocalTime.of(12 + random.nextInt(5),
                        random.nextInt(60));
                e.status = "조퇴";
            }
        }
        refreshTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceApp().setVisible(true));
    }
}
