<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 3/18/2017
  Time: 01:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags"  prefix="s" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/jquery/jquery-2.2.4.js"></script>
    <script type="text/javascript">
        function upload_photo1() {
            var fileSelect = document.getElementById('upload_file1');
            // Get the selected files from the input.
            var files = fileSelect.files;
            // Create a new FormData object.
            var formData = new FormData();

            // Loop through each of the selected files.
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                // Check the file type.
                /*if (!file.type.match('image.*')) {
                 continue;
                 }*/
                // Add the file to the request.
                formData.append('datas', file, file.name);
                formData.append('resource', 'cashier');
            }

            // Files formData.append(name, file, filename);
            // Blobs formData.append(name, blob, filename);
            // Strings formData.append(name, value);

            var xhr = new XMLHttpRequest();
            // Set up a handler for when the request finishes.
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // File(s) uploaded.
                    //uploadButton.innerHTML = 'Upload';
                } else {
                    alert('error')
                }
            };
            xhr.open('POST', 'upload-mixed.htm');
            xhr.onreadystatechange = function (response) {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    if (xhr.responseText == 'success') {
                        for (var i = 0; i < files.length; i++) {
                            var file_ = files[i];
                            $("#data_div").append('<div class="aplooaded_file" >' +
                                    '<div class="file_upload_name"> ' + file_.name + '</div>' +
                                    '<div class="file_upload_size"> ' + file_.size + 'kb' + '</div>' +
                                    '<div class="close_x_img  close' + i + '"><img src="<%=request.getContextPath()%>/img/wallet/icon/send_money_upload_delete.png" alt="delete icon"/></div>' +
                                    '</div>');
                            (function (i) {
                                $(".close" + i + "").click(function () {
                                    var file_ = files[i];
                                    var this_ = $(this);

                                    function remove_file() {
                                        this_.closest(".aplooaded_file").remove()
                                    }
                                });
                            }(i));
                        }
                    } else if (response == 'none') {
                        alert("upload_photo", "An error occurred! Empty upload " + files[0].name);
                    } else {
                        alert("upload_photo", "An error occurred! Empty upload " + files[0].name);
                    }
                }
            };

            xhr.send(formData);
        }
        function upload_photo2() {
            var fileSelect = document.getElementById('upload_file2');
            // Get the selected files from the input.
            var files = fileSelect.files;
            // Create a new FormData object.
            var formData = new FormData();

            // Loop through each of the selected files.
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                // Check the file type.
                /*if (!file.type.match('image.*')) {
                 continue;
                 }*/
                // Add the file to the request.
                formData.append('datas', file, file.name);
                formData.append('resource', 'branch');
            }

            // Files formData.append(name, file, filename);
            // Blobs formData.append(name, blob, filename);
            // Strings formData.append(name, value);

            var xhr = new XMLHttpRequest();
            // Set up a handler for when the request finishes.
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // File(s) uploaded.
                    //uploadButton.innerHTML = 'Upload';
                } else {
                    alert('error')
                }
            };
            xhr.open('POST', 'upload-mixed.htm');
            xhr.onreadystatechange = function (response) {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    if (xhr.responseText == 'success') {
                        for (var i = 0; i < files.length; i++) {
                            var file_ = files[i];
                            $("#data_div").append('<div class="aplooaded_file" >' +
                                    '<div class="file_upload_name"> ' + file_.name + '</div>' +
                                    '<div class="file_upload_size"> ' + file_.size + 'kb' + '</div>' +
                                    '<div class="close_x_img  close' + i + '"><img src="<%=request.getContextPath()%>/img/wallet/icon/send_money_upload_delete.png" alt="delete icon"/></div>' +
                                    '</div>');
                            (function (i) {
                                $(".close" + i + "").click(function () {
                                    var file_ = files[i];
                                    var this_ = $(this);

                                    function remove_file() {
                                        this_.closest(".aplooaded_file").remove()
                                    }
                                });
                            }(i));
                        }
                    } else if (response == 'none') {
                        alert("upload_photo", "An error occurred! Empty upload " + files[0].name);
                    } else {
                        alert("upload_photo", "An error occurred! Empty upload " + files[0].name);
                    }
                }
            };

            xhr.send(formData);
        }
        function upload_photo3() {
            var fileSelect = document.getElementById('upload_file3');
            // Get the selected files from the input.
            var files = fileSelect.files;
            // Create a new FormData object.
            var formData = new FormData();

            // Loop through each of the selected files.
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                // Check the file type.
                /*if (!file.type.match('image.*')) {
                 continue;
                 }*/
                // Add the file to the request.
                formData.append('datas', file, file.name);
                formData.append('resource', 'company');
            }

            // Files formData.append(name, file, filename);
            // Blobs formData.append(name, blob, filename);
            // Strings formData.append(name, value);

            var xhr = new XMLHttpRequest();
            // Set up a handler for when the request finishes.
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // File(s) uploaded.
                    //uploadButton.innerHTML = 'Upload';
                } else {
                    alert('error')
                }
            };
            xhr.open('POST', 'upload-mixed.htm');
            xhr.onreadystatechange = function (response) {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    if (xhr.responseText == 'success') {
                        for (var i = 0; i < files.length; i++) {
                            var file_ = files[i];
                            $("#data_div").append('<div class="aplooaded_file" >' +
                                    '<div class="file_upload_name"> ' + file_.name + '</div>' +
                                    '<div class="file_upload_size"> ' + file_.size + 'kb' + '</div>' +
                                    '<div class="close_x_img  close' + i + '"><img src="<%=request.getContextPath()%>/img/wallet/icon/send_money_upload_delete.png" alt="delete icon"/></div>' +
                                    '</div>');
                            (function (i) {
                                $(".close" + i + "").click(function () {
                                    var file_ = files[i];
                                    var this_ = $(this);

                                    function remove_file() {
                                        this_.closest(".aplooaded_file").remove()
                                    }
                                });
                            }(i));
                        }
                    } else if (response == 'none') {
                        alert("upload_photo", "An error occurred! Empty upload " + files[0].name);
                    } else {
                        alert("upload_photo", "An error occurred! Empty upload " + files[0].name);
                    }
                }
            };

            xhr.send(formData);
        }

    </script>
</head>
<body>
<%
    String realPath = application.getRealPath("/");
    File file = new File(realPath);
%>
<%=application.getContextPath()%>--><%=file.getAbsolutePath()%>

<form method="post"
      enctype="multipart/form-data">

    <div class="popup_textarea col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="file_upload">
            <input type="file" class="file_upload_input" name="data" id="upload_file1" multiple onchange="upload_photo1()">
        </div>
    </div>
</form>
<form method="post"
      enctype="multipart/form-data">

    <div class="popup_textarea col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="file_upload">
            <input type="file" class="file_upload_input" name="data" id="upload_file2" multiple onchange="upload_photo2()">
        </div>
    </div>
</form>
<form method="post"
      enctype="multipart/form-data">

    <div class="popup_textarea col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="file_upload">
            <input type="file" class="file_upload_input" name="data" id="upload_file3" multiple onchange="upload_photo3()">
        </div>
    </div>
</form>

<form action="run-test.htm">
    <input type="submit">
</form>


<s:if test="#session.error != null">
    ERROR ::
    <s:iterator value="#session.error" var="error">
        <div class="excerpt"><s:property value="#error"/></div>
    </s:iterator>
</s:if>

</body>
</html>
