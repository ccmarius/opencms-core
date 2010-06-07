<%@page import="org.opencms.ade.galleries.CmsGalleryActionElement"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms" %><%
  CmsGalleryActionElement gallery = new CmsGalleryActionElement(pageContext, request, response);
%><!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title><%= gallery.getTitle() %></title>
    <script type="text/javascript" src="<cms:link>/system/modules/org.opencms.ade.galleries/resources/resources.nocache.js</cms:link>"></script>
    <%= gallery.exportAll() %>
    <% if (gallery.isEditorMode()){ /* opened from rich text editor (FCKEditor, CKEditor...) include necessary scripts */ %>
    <script type="text/javascript" src="<cms:link>/system/workplace/editors/fckeditor/plugins/galleries/integrator.js</cms:link>"></script>
    <% } %>
  </head>
  <body>
  </body>
</html>