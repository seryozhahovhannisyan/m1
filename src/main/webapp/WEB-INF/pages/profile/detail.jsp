<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 7/29/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">

    var istevenMultiSelectTranslateObject = {
        search       : '<s:text name="page.branches.modal.multi.select.Search">Search</s:text>',
        SelectAll    : '&nbsp;&nbsp;<s:text name="page.branches.modal.multi.select.Select.All">Select All</s:text>',
        SelectNone   : '&nbsp;&nbsp;<s:text name="page.branches.modal.multi.select.Select.None">Select None</s:text>',
        Reset        : '&nbsp;&nbsp;<s:text name="page.branches.modal.multi.select.Reset">Reset</s:text>',
        NoneSelected : '<s:text name="page.branches.modal.multi.select.None.Selected">None Selected</s:text>'
    };

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/isteven-multi-select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/datePicker/ui-bootstrap-tpls-1.3.3.js"></script>
<link href="<%=request.getContextPath()%>/css/template/profile.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/profile/profile-controller.js" type="text/javascript" ></script>
<div class="right_col" role="main" style="min-height: 2519px;" >
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>
                        Cashier Report
                    </h2>

                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <div class="col-md-4 col-sm-4 col-xs-12 profile_center">

                        <div class="profile_img">

                            <!-- end of image cropping -->
                            <div id="crop-avatar" >
                                <div class="upload_new_photo_block">
                                    <div class="upload_new_photo">
                                        <span>Upload new photo</span>
                                    </div>
                                </div>
                                <!-- Current avatar --><%--
                            <img class="img-responsive avatar-view" src="profile_files/picture.jpg"
                                 alt="Avatar" title="Change the avatar">--%>

                                <s:if test="%{isUserThumbFileExist(#session.session_user.id, #session.session_user.photoData.fileName)}">
                                    <img width="220" height="220" class="mg-responsive avatar-view" src="<s:property value='%{getUserThumbImg(#session.session_user.id, #session.session_user.photoData.fileName)}'/>" />
                                </s:if>
                                <s:else>
                                    <img width="220" height="220" class="mg-responsive avatar-view" src="<%=request.getContextPath()%>/img/general/avatars/avatar.png" alt=""/>
                                </s:else>



                                <!-- Loading state -->
                                <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
                            </div>
                            <!-- end of image cropping -->

                        </div>
                        <h3><s:property value="#session.cashier.name"/>&nbsp;<s:property value="#session.cashier.surname"/></h3>

                        <ul class="list-unstyled user_data">
                            <li class="map_marker">
                                <%--<i class="fa fa-map-marker user-profile-icon"></i>--%>
                               <span><s:property value="#session.cashier.privilege.name"/>, <s:property value="#session.cashier.privilege.comment"/></span>
                            </li>

                            <li class="briefcase">
                                <%--<i class="fa fa-briefcase user-profile-icon"></i>--%>
                                <span><s:property value="#session.cashier.role.name"/>,cvbxbxcbxcbxcbvx xbx <s:property value="#session.cashier.role.description"/></span>
                            </li>

                            <li class="external_link">
                                <%--<i class="fa fa-external-link user-profile-icon"></i>--%>
                                <a href="http://www.kimlabs.com/profile/"
                                   target="_blank">head Cashier</a>
                            </li>
                            <li class="skills">
                                <%--<i class="fa fa-external-link user-profile-icon"></i>--%>
                                 <span>skills</span>
                            </li>
                        </ul>

                        <a class="btn btn-success" data-target="#edit_profile" data-toggle="modal" ><i class="fa fa-edit m-right-xs"></i>Edit Profile</a>
                        <br>

                        <!-- start skills -->
                        <%--<h4>Skills</h4>--%>
                        <!-- end of skills -->

                    </div>
                    <div class="col-md-4 col-sm-4"></div>
                    <div class="col-md-4 col-sm-4 col-xs-12">

                        <div class="profile_title">
                            <div class="col-md-6">
                                <h2>User Activity Report</h2>
                            </div>
                            <div class="col-md-6">
                                <div id="reportrange" class="pull-right"
                                     style="margin-top: 5px; background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #E6E9ED">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                    <span>July 12, 2016 - August 10, 2016</span> <b class="caret"></b>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
