package main.java;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.Part;

@WebServlet(name = "uploadFile",urlPatterns = {"/uploadFile"})
@MultipartConfig
public class fileuploader extends javax.servlet.http.HttpServlet {
  protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {
    processRequest(request,response);
  }

  protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {
    processRequest(request,response);
  }

  protected void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    for (Part part : request.getParts()) {
      InputStream is = request.getPart(part.getName()).getInputStream();
      byte[] b  = new byte[is.available()];
      is.read(b);
      FileOutputStream os = new FileOutputStream(System.getenv("OPENSHIFT_DATA_DIR") + getFileName(part));
      os.write(b);
      is.close();
      response.sendRedirect("/index.jsp");
    }
  }

  private String getFileName(Part part) {
    String res=null;
    for (String cd : part.getHeader("content-disposition").split(";")) {
      if (cd.trim().startsWith("filename")) {
        res= cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
        break;
      }
    }
    return res;
  }
}
