package fr.legrain.silentPdfPrint;

/*
 * $Id: PrintServlet.java,v 1.4 2008-01-22 11:32:38 nicolas Exp $
 * $Name: not supported by cvs2svn $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://itextdocs.lowagie.com/tutorial/v
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

/**
 * Explains how to print silently via Servlet/Browser.
 * 
 * @author Heiner Jostkleigrewe, Heiner.Jostkleigrewe@gt-net.de
 */
public class PrintServlet extends HttpServlet {
	/** a possible status */
	public static final int ACT_INIT = 0;

	/** a possible status */
	public static final int ACT_REPORT_1 = 1;

	public PrintServlet() {
		super();
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest requ, HttpServletResponse resp)
			throws ServletException, IOException {
		doWork(requ, resp);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest requ, HttpServletResponse resp)
			throws ServletException, IOException {
		doWork(requ, resp);
	}

	/**
	 * The actual business logic.
	 * 
	 * @param requ
	 *            the request object
	 * @param resp
	 *            the response object
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doWork(HttpServletRequest requ, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletOutputStream out = resp.getOutputStream();

		// what did the user request?
		int action = ACT_INIT;
		int sub = ACT_INIT;
		try {
			action = Integer.parseInt(requ.getParameter("action"));
			if(requ.getParameter("sub")!=null)
			sub = Integer.parseInt(requ.getParameter("sub"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch (action) {
		
		case ACT_INIT: {
			htmlHeader(out, requ, resp);
			formular(out, requ, resp, sub);
			break;
		}

		case ACT_REPORT_1: {
			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			try {

				PdfCopy writer = new PdfCopy(document, baos);
				document.open();
				if (requ.getParameter("preview") == null)
					writer.addJavaScript("this.print(false);", false);
				// document.add(new Chunk("Silent Auto Print"));
				LgrSpooler spool = LgrSpooler.getInstance();
				for (Object fichier : spool) {
					if (fichier instanceof String) {
						PdfReader reader = new PdfReader((String) fichier);
						PdfImportedPage page = null;
						for (int i = 1; i <= reader.getNumberOfPages(); i++) {
							// writer.getImportedPage(reader,i);
							page = writer.getImportedPage(reader, i);
							writer.addPage(page);
						}
					} else if (fichier instanceof OutputStream) {
						PdfReader reader = new PdfReader(
								new ByteArrayInputStream(
										((ByteArrayOutputStream) fichier)
												.toByteArray()));
						PdfImportedPage page = null;
						for (int i = 1; i <= reader.getNumberOfPages(); i++) {
							// writer.getImportedPage(reader,i);
							page = writer.getImportedPage(reader, i);
							writer.addPage(page);
						}
					}
				}
				document.close();
//				spool.clear();

			} catch (DocumentException e) {
				e.printStackTrace();
			}
			resp.setContentType("application/pdf");

			resp.setContentLength(baos.size());
			baos.writeTo(out);
			out.flush();

			break;
		}
		
		}
		
	}

	private void htmlHeader(ServletOutputStream out, HttpServletRequest requ,
			HttpServletResponse resp) throws IOException {

		resp.setContentType("text/html; charset=ISO-8859-1");
		resp.setHeader("Cache-Control", "no-cache");
		out.println("<html>");
		out.println("<head>");
		out
				.println("<meta http-equiv='Content-Type' content='text/html;charset=iso-8859-1'>");
		out.println("<meta http-equiv='expires' content='0'>");
		out.println("<meta http-equiv='cache-control' content='no-cache'>");
		out.println("<meta http-equiv='pragma' content='no-cache'>");
		out.println("</head>");
		out.println("<body>");
	}

	private void formular(ServletOutputStream out, HttpServletRequest requ,
			HttpServletResponse resp, int sub) throws IOException {
		out.print("<form method='post' action='");
		out.print(requ.getRequestURI());
		out.print("?action=");
		out.print(ACT_INIT);
		out.print("&sub=");
		out.print(ACT_REPORT_1);
		out.println("'>");
		out.print("<input type='checkbox' name='preview' value='Y'");
		if (requ.getParameter("preview") != null)
			out.print(" checked ");
		out.println(">preview<br>");

		out.println("<input type=submit value='Report 1'>");
		out.println("</form>");
		if (sub != ACT_INIT) {
			if (requ.getParameter("preview") != null) {
				out.println("<script language='JavaScript'>");
				out.print("w = window.open(\"");
				out.print(requ.getRequestURI());
				out.print("?action=");
				out.print(sub);
				out
						.print("&preview=Y\", \"Printing\", \"width=800,height=450,scrollbars,menubar=yes,resizable=yes\");");
				out.println("</script>");
			} else {
				out.print("<iframe src='");
				out.print(requ.getRequestURI());
				out.print("?action=");
				out.print(sub);
				out.println("' width='10' height='10' name='pdf_box'>");
			}
		}
		out.println("</body>");
		out.println("</html>");
	}
}
