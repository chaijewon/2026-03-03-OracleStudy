package com.sist.temp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.util.List;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.util.*;

public class AttendanceSwingApp extends JFrame {

    private DefaultTableModel model;
    private Random random = new Random();

    public AttendanceSwingApp() {
        setTitle("출퇴근 시뮬레이터");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"사원ID", "상태", "출근시간", "퇴근시간"};
        model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton button = new JButton("데이터 생성");
        button.addActionListener(e -> generateData());

        add(scrollPane, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }

    private void generateData() {
        model.setRowCount(0);

        List<String> employees = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            employees.add("EMP" + i);
        }

        Collections.shuffle(employees);

        // 95명 출근 / 5명 기타
        List<String> attendGroup = employees.subList(0, 95);
        List<String> leaveGroup = employees.subList(95, 100);

        // 출근자 중 5명을 야근으로
        Collections.shuffle(attendGroup);
        List<String> overtimeGroup = attendGroup.subList(0, 5);
        List<String> normalGroup = attendGroup.subList(5, 95);

        // ✅ 정상근무
        for (String emp : normalGroup) {
            LocalTime inTime = LocalTime.of(8, 0)
                    .plusMinutes(random.nextInt(60));

            LocalTime outTime = LocalTime.of(18, 0)
                    .plusMinutes(random.nextInt(60));

            model.addRow(new Object[]{
                    emp,
                    "정상근무",
                    inTime.toString(),
                    outTime.toString()
            });
        }

        // ✅ 야근 (출근자 중 일부만)
        for (String emp : overtimeGroup) {
            LocalTime inTime = LocalTime.of(8, 0)
                    .plusMinutes(random.nextInt(60));

            LocalTime outTime = LocalTime.of(19 + random.nextInt(3),
                    random.nextInt(60));

            model.addRow(new Object[]{
                    emp,
                    "야근",
                    inTime.toString(),
                    outTime.toString()
            });
        }

        // ✅ 휴가/외출/조퇴 (야근 없음)
        String[] states = {"휴가", "외출", "조퇴"};

        for (String emp : leaveGroup) {
            String state = states[random.nextInt(states.length)];

            LocalTime inTime = null;
            LocalTime outTime = null;

            if (state.equals("외출")) {
                inTime = LocalTime.of(8, 0)
                        .plusMinutes(random.nextInt(60));
                outTime = LocalTime.of(10 + random.nextInt(6),
                        random.nextInt(60));
            }

            if (state.equals("조퇴")) {
                inTime = LocalTime.of(8, 0)
                        .plusMinutes(random.nextInt(60));
                outTime = LocalTime.of(12 + random.nextInt(5),
                        random.nextInt(60));
            }

            model.addRow(new Object[]{
                    emp,
                    state,
                    inTime != null ? inTime.toString() : "",
                    outTime != null ? outTime.toString() : ""
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AttendanceSwingApp().setVisible(true);
        });
    }
}