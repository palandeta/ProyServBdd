

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("src/RegistroServlet")
public class RegistroServlet extends HttpServlet {
	 static String bdd="jdbc:mysql://mysqlpablo.mysql.database.azure.com/usuarios";
	 static String user="pablolandeta@mysqlpablo";
	 static String pass="Pablo666"; 
	 static Connection con=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver"); 			//registrar el driver
			con = DriverManager.getConnection(bdd, user, pass); //conectar a la bdd		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try{
			if (con!=null)
				con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter w= response.getWriter();
		w.println("<html>"
				+ "<head>"
				+ "<link rel='stylesheet' type='text/css' href='style.css'>"
				+ "</head>"
				+ "<body>");
		Statement expresion;
		try{
			expresion=con.createStatement();
			expresion.executeUpdate("INSERT INTO user VALUES ('"
					+request.getParameter("txtUsua")+"', '"
					+request.getParameter("txtPass")+"')");	
			
			w.println("Usuario Ingresado");
			w.println("<a href='start.html'>Regresar al inicio</a>");
			expresion.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		w.println("</body>"
				+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
