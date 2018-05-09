

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogicaServlet
 */
@WebServlet("/LogicaServlet")
public class LogicaServlet extends HttpServlet {
	 static String bdd="jdbc:mysql://localhost/usuarios";
	 static String user="andres";
	 static String pass="andres"; 
	 static Connection con=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogicaServlet() {
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
			String consulta="SELECT * FROM user";
			expresion=con.createStatement();	
			ResultSet result = expresion.executeQuery(consulta);
			boolean bandera=false;
			while(result.next())
				if (result.getString("login").equals(request.getParameter("txtUsuario")) && 
						result.getString("password").equals(request.getParameter("txtContrasenia"))){
					w.println("Bienvenido al sistema "+request.getParameter("txtUsuario"));
					bandera=true;
					break;
				}
			if (bandera==false)
					w.println("Usuario No registrado");
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