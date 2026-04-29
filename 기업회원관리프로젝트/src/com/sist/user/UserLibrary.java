package com.sist.user;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class UserLibrary extends JFrame {

    JTable bookTable, myLoanTable;
    DefaultTableModel bookModel, loanModel;

    public UserLibrary() {

        setTitle("사용자 도서 대출 시스템");

        JTabbedPane tab = new JTabbedPane();

        tab.add("도서목록", bookPanel());
        tab.add("내 대출현황", loanPanel());

        add(tab);

        setSize(900, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // =========================
    // 1. 도서 목록 (사용자용)
    // =========================
    public JPanel bookPanel() {

        JPanel p = new JPanel(new BorderLayout());

        bookModel = new DefaultTableModel(
                new String[]{"도서ID","제목","저자","상태"}, 0
        );

        bookTable = new JTable(bookModel);

        // 더미 데이터
        bookModel.addRow(new Object[]{"B001","자바의 정석","남궁성","대출가능"});
        bookModel.addRow(new Object[]{"B002","Effective Java","Bloch","대출가능"});
        bookModel.addRow(new Object[]{"B003","자료구조","Cormen","대출가능"});

        JButton loanBtn = new JButton("대출하기");

        loanBtn.addActionListener(e -> loanBook());

        p.add(loanBtn, BorderLayout.NORTH);
        p.add(new JScrollPane(bookTable), BorderLayout.CENTER);

        return p;
    }

    // =========================
    // 2. 내 대출 현황
    // =========================
    public JPanel loanPanel() {

        JPanel p = new JPanel(new BorderLayout());

        loanModel = new DefaultTableModel(
                new String[]{"도서ID","제목","대출일","상태"}, 0
        );

        myLoanTable = new JTable(loanModel);

        p.add(new JScrollPane(myLoanTable), BorderLayout.CENTER);

        return p;
    }

    // =========================
    // 3. 대출 기능
    // =========================
    public void loanBook() {

        int row = bookTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "도서를 선택하세요");
            return;
        }

        String id = bookTable.getValueAt(row, 0).toString();
        String title = bookTable.getValueAt(row, 1).toString();
        String status = bookTable.getValueAt(row, 3).toString();

        if (!status.equals("대출가능")) {
            JOptionPane.showMessageDialog(this, "이미 대출된 도서입니다");
            return;
        }

        // 상태 변경
        bookTable.setValueAt("대출중", row, 3);

        // 내 대출 목록 추가
        loanModel.addRow(new Object[]{
                id,
                title,
                LocalDate.now().toString(),
                "대출중"
        });

        JOptionPane.showMessageDialog(this, "대출 완료");
    }

    // =========================
    public static void main(String[] args) {
    	try
        {
        	UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        }catch(Exception ex) {}
        new UserLibrary();
    }
}
