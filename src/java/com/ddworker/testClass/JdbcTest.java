package com.ddworker.testClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcTest {
	private final static String URL = "jdbc:mysql://localhost:3306/ddworker?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
	private final static String DS_PROP = "properties/database.properties";
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		JdbcTest jt = new JdbcTest();
		jt.getPro();
		
		
		//以前注册驱动方式(现在可以不用了)
//		 Class.forName("com.mysql.jdbc.Driver");
		Driver d = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(d);
		
		//获取properties 文件
		String path = JdbcTest.class.getResource("/" + DS_PROP).getPath();
		FileInputStream fi = new FileInputStream(path);
		Properties po = new Properties();
		po.load(fi);
		po.list(System.out);  //输出到控制台
//		po.setProperty("user", "user");
		
//		System.exit(0);
		try (
				//驱动管理方式加载驱动
				//现在不需要要注册驱动,getConnection能够通过反射去获取对应的驱动class
//				Connection con = DriverManager.getConnection(URL,"root","1234");
				
				//getConnection url + properties 方式, properties 必须有 user 和password 属性
				Connection con = DriverManager.getConnection(URL, po); 
				){
				StringBuffer sbf = new StringBuffer("select * from person");
				
//				Statement st = con.createStatement();
				PreparedStatement ps = con.prepareStatement(sbf.toString());
				ResultSet rs = ps.executeQuery(sbf.toString());
			
			
			while(rs.next()) {
				System.out.println(rs.getString(2));
			}
			
//			st.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取配置文件
	 * @throws IOException
	 */
	public void getPro() throws IOException {
		Properties pro=new Properties();
		
		//通过InputStream 或者 InputStream + BufferedReader 方式读取  
		//通过实例方式获取不需要加"/",通过类方式必须加"/"
//		InputStream is = JdbcTest.class.getResourceAsStream("/properties/database.properties");
//		InputStream is = JdbcTest.class.getClassLoader().getResourceAsStream("properties/database.properties");
		
		//通过当前线程获取流 
//		InputStream is =Thread.currentThread().getClass().getResourceAsStream("/properties/database.properties");
//		InputStream is =Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/database.properties");
		
		//通过resource 获取流
//		InputStream is = Resources.class.getResourceAsStream("/properties/database.properties");
		
		/*   Resources.class.getClassLoader() 为null ?? 所以通过下面方式无法获取到
		InputStream is = Resources.class.getClassLoader().getResourceAsStream("/properties/database.properties");
		*/
		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//		pro.load(reader);
//		pro.load(is);
		
		//(调用方式与流方式一致)通过获取文件地址  + FileInputStream 字节流读取
		String path = JdbcTest.class.getResource("/properties/database.properties").getPath();
		
		FileInputStream fi = new FileInputStream(path);
		pro.load(fi);
		
		String driver=pro.getProperty("driver"); //读取key的值
//		String url=pro.getProperty("url");
		System.out.println(driver);

	}
}
