/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
*
* COPYRIGHT (c) 2023 by Ian Schrauth. Github: www.github.com/IHS5452
 */
package business;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ian
 */
@WebServlet(name = "getmetadata_servlet", urlPatterns = "/getmetadata")
@MultipartConfig
public class getmetadata_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
                PrintWriter out = response.getWriter();
                                    out.println("<center><h1>Image metadata</h1></center>");

            response.setContentType("text/html;charset=UTF-8");
            
            Part filePart = request.getPart("image");
            File file = new File(filePart.getSubmittedFileName());
            filePart.write(file.getAbsolutePath());
            
            // Extract metadata from the image
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            Map<String, String> metadataMap = new HashMap<>();
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    metadataMap.put(tag.getTagName(), tag.getDescription());
                }

                out.println("<style type=\"text/css\">\n" +
".tg  {margin-left:auto; margin-right:auto;border-collapse:collapse;border-spacing:0;}\n" +
".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
"  overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
"  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
".tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}\n" +
".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
"</style>\n" +
"<table class=\"tg\">\n" +

"<tbody>\n");
                
                
//out.println("<html>");
//    out.println("<head>");
//    out.println("<title>Map Display</title>");
//    out.println("</head>");
//    out.println("<body>");

    for (Map.Entry<String, String> entry : metadataMap.entrySet()) {
        out.println("<tr>");
            out.println("<td class=\"tg-0lax\">" + entry.getKey() + "</td>");
      out.println("<td class=\"tg-0lax\">" + entry.getValue() + "</td>");
              out.println("</tr>");

    }
    out.println(
"</thead>\n" +
"</table>");

    out.println("</body>");
    out.println("</html>");
          }   
        } catch (ImageProcessingException ex) {
            Logger.getLogger(getmetadata_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
           
   class ImageMetadata {
  private Map<String, String> metadata;

  public ImageMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }

  public Map<String, String> getMetadata() {
    return metadata;
  }
} 

        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
