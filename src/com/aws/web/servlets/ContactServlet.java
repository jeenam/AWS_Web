package com.aws.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;






import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import com.aws.web.util.MailAccountInfo;
import com.aws.web.util.SnsSender;

public class ContactServlet extends HttpServlet {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    MailAccountInfo mailInfo = new MailAccountInfo();
    String sTo = "jgeorge@acr.org",
	    sContent = "",
	    sSubject = "Contact";

    /**
     * Constructor of the object.
     */
    public ContactServlet() {
	super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    @Override
    public void destroy() {
	super.destroy();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

   
    	
	process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	   process(request, response);
    }

    private void process(HttpServletRequest request,
	    HttpServletResponse response) throws IOException {
 
     String payload = getBody(request);   
     System.out.println(payload);
     SnsSender  snsSender = new SnsSender();
     snsSender.sendToArn(payload);
     
    }
    

    public void writeToAwsSNS(String sFirstName, String sLastName, String sEmail, String sPhoneNo, String sCompany) {
	    sContent = " Name: " + sFirstName + " " + sLastName + " \n " + 
	    		" Email: " + sEmail + " \n " + " Phone: " + sPhoneNo + " \n " + " Company: " + sCompany; 
	    System.out.println("-------a----");		
	    SnsSender  snsSender = new SnsSender();
        snsSender.sendToArn(sContent);
	    System.out.println("-------b----");
    }
    
    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
    
    
}