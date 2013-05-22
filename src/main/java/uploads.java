package main.java;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "uploads",urlPatterns = {"/uploads/*"})
public class uploads extends HttpServlet {

  static final int BUFFER_LENGTH = 4096;
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

    private File getFile(HttpServletRequest request){
      String destFile = request.getRequestURI().replace("/uploads/","");
      return destFile.equals("") ? null : new File(System.getenv("OPENSHIFT_DATA_DIR") + destFile);
    }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    File file = getFile(request);
    if(file == null){
        response.sendRedirect("/index.jsp") ;
        return;
    }
    // response.getWriter().println("DEL"+file.toURI());
    boolean success = file.delete();
    if(!success){response.sendError(HttpServletResponse.SC_NOT_FOUND);}
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    File file = getFile(request);
    if(file == null){
        response.sendRedirect("/index.jsp") ;
        return;
    }
    InputStream input = new FileInputStream(file);

    response.setContentLength((int) file.length());
    response.setContentType(new MimetypesFileTypeMap().getContentType(file));

    OutputStream output = response.getOutputStream();
    byte[] bytes = new byte[BUFFER_LENGTH];
    for(int read = 0; read != -1; read = input.read(bytes, 0, BUFFER_LENGTH)) {
        if (read != -1) { output.write(bytes, 0, read); }
    }

    input.close();
    output.close();
  }


}
