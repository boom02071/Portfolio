
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class BookDao {
	//List<Book> list = new ArrayList<Book>();

	public BookDao() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/book?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8","ssafy","rhrnak123");
			
			String sql = "truncate book";
			//String sql = "insert into book values('안녕1','안녕2','안녕3','4',5)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void connectXML(String url) {
	    
	    InputSource input = new InputSource ( new StringReader(url));
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;

	    try {

	        builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(input);
	        doc.getDocumentElement().normalize();

	        Element rss = doc.getDocumentElement();
	    

	        NodeList childNodes = doc.getElementsByTagName("item");
	        for (int i = 0; i < childNodes.getLength(); i++) {
	            Node node = childNodes.item(i);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) node;
	                String nodeName = element.getNodeName();
	                switch (nodeName) {
	                case "item": {
	                    NodeList dataChildNodes = element.getChildNodes();
	                    Book b = new Book();
	                    for (int j = 0; j < dataChildNodes.getLength(); j++) {
	                        Node childNode = dataChildNodes.item(j);
	                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {

	                            Element childElement = (Element) childNode;
	                            String childTextContent = childElement.getTextContent();
	                            String childNodeName = childElement.getNodeName();
	                            switch (childNodeName) {
	                            case "title": {
	                                b.setTitle(ssss(childTextContent));
	                                break;
	                            }
	                            case "author": {
	                                b.setAuthor(childTextContent);
	                                break;
	                            }
	                            case "price": {
	                                b.setPrice(Integer.parseInt(childTextContent));
	                                break;
	                            }
	                            case "publisher": {
	                                b.setPublisher(childTextContent);
	                                break;
	                            }
	                            case "isbn": {
	                            	b.setIsbn(childTextContent);
	                            	break;
	                            }
	                            case "description": {
	                            	b.setDescription(childTextContent);
	                            	break;
	                            }
	                       

	                            }
	                        }
	                    }
	                    insert(b);
	                    break;
	                }
	                }
	            }

	        }

	    } catch (Exception e) {
	    	System.out.println("오류가 난다구");
	    }
	}
	String ssss(String str) {
		String result = "";
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='<') {
				i+=2;
				continue;
			}
			if(str.charAt(i)=='>')
				continue;
			result += str.charAt(i);
		}
		return result;
	}
	Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/book?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8","ssafy","rhrnak123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	void insert(Book book) throws SQLException {
		Connection conn = getConnection();
		String sql = "insert into book values(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, book.getIsbn());
		ps.setString(2, book.getTitle());
		ps.setString(3, book.getAuthor());
		ps.setString(4, book.getPublisher());
		ps.setInt(5, book.getPrice());
		ps.executeUpdate();
		ps.close();
		conn.close();		
	}
	
	void delete(String title) throws SQLException {
		Connection conn = getConnection();
		String sql = "DELETE FROM book WHERE title = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, title);
		ps.executeUpdate();
		ps.close();
		conn.close();		
	}
	
	public void updateBook(String title, int price ) throws SQLException {
		Connection conn = getConnection();
		String sql = "UPDATE book SET price = ? WHERE title like '%"+title+"%'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, price);
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	
	public List<Book> AllList() throws SQLException {
		List<Book> l = new ArrayList<>();
		Connection conn = getConnection();
		String sql = "SELECT * FROM book";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);
		while (rs.next()) {
			Book b = new Book();
			b.setIsbn(rs.getString(1));
			b.setTitle(rs.getString(2));
			b.setAuthor(rs.getString(3));
			b.setPublisher(rs.getString(4));
			b.setPrice(rs.getInt(5));
			l.add(b);
		}
		conn.close();
		rs.close();
		ps.close();
		return l;
	}
	
}
