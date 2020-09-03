
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class gui {
	private static JFrame frame;
	private static JPanel wrapper_panel;

	private static JTable tableView;
	private static StringBuilder sbuilder;
	private static JScrollPane scrollList;

	static parsing p;
	static BookDao dao;

	public gui() throws SQLException {
		p = new parsing();

		frame = new JFrame("Data");
		// setLayout();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setSize(600, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wrapper_panel = new JPanel(new BorderLayout());
	}

	private static void setLayout() throws SQLException {
		wrapper_panel = new JPanel(new BorderLayout());

		Vector<String> colNames = new Vector<>();
		colNames.add("ISBN");
		colNames.add("제목");
		colNames.add("작가");
		colNames.add("출판사");
		colNames.add("가격");

		List<Book> list = dao.AllList();

		Vector<Object> dataSet = new Vector<>();
		for (int i = 0; i < list.size(); i++) {
			Vector<String> rows = new Vector<>();
			rows.add(list.get(i).getIsbn());
			rows.add(list.get(i).getTitle());
			rows.add(list.get(i).getAuthor());
			rows.add(list.get(i).getPublisher());
			rows.add(Integer.toString(list.get(i).getPrice()));
			dataSet.add(rows);
		}

		tableView = new JTable(dataSet, colNames);
		scrollList = new JScrollPane(tableView);
		wrapper_panel.add(scrollList, BorderLayout.CENTER);
		frame.add(wrapper_panel);

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			gui g = new gui();
//			int n = -1;
//			while (n != 0) {
//				// n=sc.nextInt();
//				dao = p.naver(sc.next());
//				setLayout();
//				wrapper_panel.revalidate();
//				wrapper_panel.repaint();
			

			System.out.println("================= 도서 관리 시스템 =================");
			System.out.println("1. 도서 검색");
			System.out.println("2. 도서 가격 업데이트(고객 마음대로 가능)");
			System.out.println("3. 도서 삭제");
			System.out.println("0. 시스템 종료");
			System.out.print("번호 입력 : ");

			boolean flag = true;
			while (flag) {

				int no = sc.nextInt();

				switch (no) {
				case 1: {
					System.out.println("검색할 도서 키워드를 입력하시오.");	
						// n=sc.nextInt();
						dao = p.naver(sc.next());
						setLayout();
						wrapper_panel.revalidate();
						wrapper_panel.repaint();				
						
						break;
				}
				case 2: {
					System.out.println("업데이트할 도서명과 가격을 입력하시오.");
					dao.updateBook(sc.next(),sc.nextInt());
			
					
					break;
				}
				case 3: {
					System.out.println("삭제할 도서명을 입력하시오");
					dao.delete(sc.next());
					break;
				}
				case 0: {
					System.out.println("종료되었습니다.");
					flag = false;
					break;
				}
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
