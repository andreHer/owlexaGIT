
package com.ametis.cms.util.file;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// imports+ 

// imports- 

public class FileUtils

// extends+ 

// extends- 
{

	/*
		getting content of files in bytes
		@param fileName the stored url file name in database
		@param documentStoragePath the path for file storage
		@return array of bytes content of the file
	*/

	public static byte[] getFileContent(String fileName,String documentStoragePath) throws Exception{
		byte[] result = null;
		File file = new File(documentStoragePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		result = new byte[(int) file.length()];
		inputStream.read(result);
		return result;
	}

	/*
		store content of files in bytes
		@param fileName the stored url file name in database
		@param documentStoragePath the path for file storage
		@param data content of the file
	*/


	public static void storeFileContent(String filename, String documentStoragePath,byte[] data)
			throws Exception {

		FileOutputStream outStream = new FileOutputStream(documentStoragePath
				+ filename);
		outStream.write(data);
		outStream.close();
	}


// class+ 

// class- 
}
/*

Snippet for servlet

	public void viewPagePerformed(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Check Authentication and Authorization
		// ----------------------------------------------------------------------------------------

		if (userType == null) {
			request.setAttribute("alert", alertproperties
					.getValue("not.authorized"));
			request.setAttribute("content", "/error.jsp");
			forward("/main.jsp", request, response);
			return;

		} else {
			if (userType.equals("officer")) {
				String actionId = "DETAILPAGE";
				boolean isAuthorize = userSecurityController.isAuthorize(
						officer.getOfficerId().intValue(), actionId, "lihat");

				if (!isAuthorize) {
					// you are not authorized to use this feature
					request.setAttribute("alert", "<b>"
							+ alertproperties.getValue("not.authorized")
							+ "</b>");

					forward("/error.jsp", request, response);
					return;

				}
				OfficerAction officerAction = userSecurityController
						.getAuthorize(officer.getOfficerId().intValue(),
								actionId);
				request.setAttribute("officeraction", officerAction);
			} else if (userType.equals("registrant")) {

			} else {
				request.setAttribute("alert", alertproperties
						.getValue("not.authorized"));
				// you are not authorized to use this feature
				request.setAttribute("content", "/error.jsp");
				forward("/main.jsp", request, response);
				return;

			}

		}

		// Business Action
		// ----------------------------------------------------------------------------------------

		String document_page_pk = request.getParameter("document_page_pk");
		HttpSession session = request.getSession(false);

		if (StringUtils.isNumeric(document_page_pk)) {

			try {
				String documentBase = "tempe/";
				if (document_page_pk != null) {
					byte[] data =problemAttachmentController.getAttachmentContent(Integer.parseInt(document_page_pk));
					 ServletContext ctx = session.getServletContext();
					 String physicalPath = ctx.getRealPath("/" + documentBase);
						ProblemAttachment problemAttachment = 
							problemAttachmentController.getProblemAttachment(Integer.parseInt(document_page_pk));
						String fileUrl = problemAttachment.getProblemAttachmentUrl();
						int i = fileUrl.lastIndexOf('.');
						String ext = fileUrl.substring(i);
						/**/
/* --
					 response.setContentType("application/x-pdf");
						response.setHeader("Pragma", "no-cache");
						response.setHeader("Cache-Control", "no-cache");
						response.setDateHeader("Expires", 0);
						response.setHeader("Content-disposition",
								"inline; filename=" + problemAttachment.getProblemAttachmentUrl()
										);
						
						ServletOutputStream sos = response.getOutputStream();

						response.setHeader("Content-length", Integer
								.toString(data.length));
						sos.write(data);
						sos.close();
					 return;
					 /**/
					/*
					// check apakah linux atau windows ?
					String separator = System.getProperty("file.separator");

					File file = new File(physicalPath + separator
							+ session.getId() + ext);
					FileOutputStream outstream = new FileOutputStream(file);
					outstream.write(data);
					outstream.close();

					request.setAttribute("document_path", documentBase
							+ session.getId() + ext);

					request.setAttribute("document_page", ""
							+ "");
					forward("/jsp/viewDocumentPage.jsp", request, response);
					return;
					*/
/*--
				}

			} catch (Exception he) {

				request.setAttribute("alert", alertproperties
						.getValue("system.error")
						+ "<br>" + he.getMessage());
				request.setAttribute("content", "/error.jsp");
				forward("/main.jsp", request, response);
				return;

			}

		} else {

			request.setAttribute("alert", "<b>"
					+ alertproperties.getValue("invalid.pk") + "</b><br>");
			forward("/error.jsp", request, response);
			return;

		}

		// ----------------------------------------------------------------------------------------
		// End of Business Action

		// set Page to forward
		forward("/jsp/viewDocumentPage.jsp", request, response);
	}
	public byte[] getDocument(String filename) {
		byte[] result = null;

		try {

			Configuration config = configurationController.getConfiguration();

			String directory = config.getDocumentPath();

			File file = new File(directory + filename);
			FileInputStream inputStream = new FileInputStream(file);

			result = new byte[(int) file.length()];
			inputStream.read(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

*/
