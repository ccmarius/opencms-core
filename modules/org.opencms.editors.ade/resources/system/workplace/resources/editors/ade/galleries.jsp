<%@ page import="org.opencms.workplace.galleries.*" 
%><jsp:useBean id="gallery" class="org.opencms.workplace.galleries.CmsGallerySearchServer">
 <% gallery.init(pageContext, request, response); %>
 </jsp:useBean><%
 	if (request.getParameter(CmsGallerySearchServer.ReqParam.ACTION.getName())!= null){
 	    gallery.serve();
 	}else{ 
// this is the initial call to the gallery so build the html first
%><!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="lib/jquery-1.3.2.js"></script>
<script type="text/javascript" src="lib/jquery-ui-1.8a1.js"></script>
<script type="text/javascript" src="lib/json2.js"></script>
<script type="text/javascript" src="lib/jquery.pagination.js"></script>
<script type="text/javascript">
var cms = { html: {}, previewhandler:{}, galleries: {}, messages: {} };
</script>
<script type="text/javascript" src="/opencms/opencms/system/workplace/editors/ade/cms.messages.jsp"></script>
<script type="text/javascript" src="js/cms.html.js"></script>
<script type="text/javascript" src="js/cms.selectbox.js"></script>
<script type="text/javascript" src="js/cms.directinput.js"></script>
<script type="text/javascript" src="js/cms.previewhandler.js"></script>
<script type="text/javascript" src="js/cms.galleries.js"></script>
<script type="text/javascript">  
	// set the ajax server path
    var vfsPathAjaxJsp = "${gallery.galleryUri}";  
 	// read and handle the request params
    var tabs = ${gallery.listConfig};	
    var requestData = ${gallery.initialSearch};
    cms.galleries.initValues['dialogMode'] = "${(not empty param.dialogmode) ? param.dialogmode : 'null'}";
    cms.galleries.initValues['fieldId'] = "${(not empty param.fieldid) ? param.fieldid : 'null'}";
    $(function() {        
        cms.galleries.initAddDialog(tabs, requestData);                                 
	});

    
              
</script> 
<title>Search demo</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/custom-theme/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/advanced_direct_edit.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/galleries.css" />
<!--[if IE 7]>
<style>
div.cms-selectbox{
    zoom: 1;
    display: inline;
    }
</style>
<![endif]-->
	</head>
	<body>
	    <div id="cms-gallery-main"> 
		    <div id="cms-gallery-tabs">
                <ul>
                     <li><a href="#tabs-result">Search results</a></li>
                     <li><a href="#tabs-types">Type</a></li>
                     <li><a href="#tabs-galleries">Galleries</a></li>
                     <li><a href="#tabs-categories">Categories</a></li>
                     <li><a href="#tabs-fulltextsearch">Full Text Search</a></li>
                </ul>
            </div>            
        </div>
	</body>
</html>
<% } %>