<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 7/29/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<link type="text/css" href="<%=request.getContextPath()%>/libs/css/uploader/Default.css" rel="stylesheet">
<link type="text/css" href="<%=request.getContextPath()%>/libs/css/light_box_photo_gallery/angular-bootstrap-lightbox.css" rel="stylesheet">
<link type="text/css" href="<%=request.getContextPath()%>/libs/css/light_box_photo_gallery/style.css" rel="stylesheet">

<%--<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/uploader/Default.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/uploader/progressbar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/light_box_photo_gallery/angular-bootstrap-lightbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/light_box_photo_gallery/controller.js"></script>--%>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/branches/upload-controller.js"></script>
<div id="upload" aria-hidden="true" role="dialog" tabindex="-1" class="modal fade in" style="display: none; padding-right: 17px;" data-backdrop="static" data-keyboard="false" >
    <div class="modal-dialog modal-lg resizable_style" id="draggable_upload">
        <div class="modal-content resizable_style">
            <div class="modal-header">
                <button data-dismiss="modal" class="close close_modal" type="button"><span aria-hidden="true">×</span>
                </button>
                <div class="upload_modal_restore">
                    <img
                            alt="<s:text name="page.branches.modal.upload.Maximize.Minimize">Maximize/Minimize</s:text>"
                            src="<%=request.getContextPath()%>/img/branches/maximize.png">
                </div>
                <div class="upload_modal_minimize">
                    <i class="glyphicon glyphicon-minus"></i>
                </div>
                <h4 id="myModalLabel" class="modal-title">
                    Branch name <span><s:text name="page.branches.modal.upload.modal.title.Upload">Upload</s:text></span>
                </h4>
            </div>
            <div class="modal-body">

                <%--<section class="container">

                    <section class="droplet" ng-controller="IndexController" ng-class="{ uploading: interface.isUploading() }">
                        <div class="uploader_allowed_extensions"><s:text name="page.branches.modal.upload.uploader.Allowed.Extensions">Allowed extensions </s:text> png, jpg, bmp, gif, pdf, txt</div>
                        <ul class="statistics">

                            <li><label><s:text name="page.branches.modal.upload.uploader.Valid">Valid:</s:text></label> {{interface.getFiles(interface.FILE_TYPES.VALID).length}}</li>
                            <li><label><s:text name="page.branches.modal.upload.uploader.Invalid">Invalid:</s:text></label> {{interface.getFiles(interface.FILE_TYPES.INVALID).length}}</li>
                            <li><label><s:text name="page.branches.modal.upload.uploader.Uploaded">Uploaded:</s:text></label> {{interface.getFiles(interface.FILE_TYPES.UPLOADED).length}}</li>
                            <li><label><s:text name="page.branches.modal.upload.uploader.Deleted">Deleted:</s:text></label> {{interface.getFiles(interface.FILE_TYPES.DELETED).length}}</li>
                            <li><label><s:text name="page.branches.modal.upload.uploader.Total">Total:</s:text></label> {{interface.getFiles().length}}</li>
                        </ul>

                        <section class="toolbar">

                            <input type="button" class="button upload-files" value="Upload Files"
                                   ng-click="interface.uploadFiles()"
                                   ng-hide="interface.isUploading()"
                                   ng-class="{ clickable: interface.isReady() }" />

                            <input type="button" class="button upload-files" value="Uploading..." ng-show="interface.isUploading()" />

                            <div class="add-files">
                                <input type="button" class="button add-files" value="Add Files..." />
                                <droplet-upload-multiple ng-model="interface"></droplet-upload-multiple>
                            </div>

                            <comment class="progress" ng-class="{ visible: interface.isUploading() }">
                                <s:text name="page.branches.modal.upload.uploader.Uploaded">Uploaded:</s:text> {{interface.progress.percent}}%
                            </comment>

                        </section>

                        <droplet ng-model="interface">

                            <div class="loading" ng-class="{ visible: interface.isUploading() }">
                                <svg viewBox="0 0 400 400">
                                    <path class="loading-path" data-progressbar ng-model="interface.progress.percent"
                                          d="M 0,1 L 398,1 L 398,234 L 1,234 L 0,1"
                                          stroke="#D3B2D1" stroke-width="1" fill-opacity="0"
                                          style="stroke-dasharray: 392px, 392px;stroke-dashoffset: 392px;"></path>
                                </svg>
                            </div>

                            <comment></comment>

                            <section class="message success" ng-class="{ visible: success }" ng-click="success = false">
                                <s:text name="page.branches.modal.upload.uploader.Successfully.uploaded">Successfully uploaded</s:text> {{uploadCount}} <s:text name="page.branches.modal.upload.uploader.files">files</s:text>.
                            </section>

                            <section class="message error" ng-class="{ visible: error }" ng-click="error = false">
                                <s:text name="page.branches.modal.upload.uploader.Failed.upload">Failed to upload any of the files.</s:text>
                            </section>

                            <ul class="files">

                                <li ng-repeat="model in interface.getFiles(interface.FILE_TYPES.VALID)">
                                    <droplet-preview ng-model="model"></droplet-preview>
                                    <div class="delete" ng-click="model.deleteFile()">&times;</div>
                                    <div class="size">{{model.file.size / 1024 | number: 1}}KB</div>
                                    <div class="file_name" >{{model.file.name}}</div>

                                </li>

                            </ul>

                        </droplet>

                    </section>

                </section>--%>

                <div class="branch_attached_files">
                    <h2><s:text name="page.branches.modal.upload.Attached.files">Attached files</s:text></h2>
                <ul class="branch_upload_file_review" ng-controller="branchUploadFilePreviewCtrl">
                    <li ng-repeat="file in files">
                        <div class="image view view-first">
                            <div>{{file.name}}</div>
                        <div class="mask branch_upload_file_review_mask">
                            <div class="tools tools-bottom ">
                                <a href="#"><i class="fa fa-link"></i></a>
                                <a href="#"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                    </div>
                    </li>
                </ul>
                </div>
                <div class="branch_image_gallery">
                    <h2><s:text name="page.branches.modal.upload.Gallery.images">Gallery images</s:text></h2>
                <%--<ul class="gallery gallery1" ng-controller="GalleryCtrl">
                    <li ng-repeat="image in images">
                        <a ng-click="openLightboxModal($index)">
                            <div class="image view view-first">
                                <img ng-src="{{image.url}}" class="img-thumbnail gallery_image_active">
                                <div class="mask">
                                    <div class="tools tools-bottom">
                                        <a href="#"><i class="fa fa-link"></i></a>
                                        <a href="#"><i class="glyphicon glyphicon-ok gallery_image_in_active"></i></a>
                                        <a href="#"><i class="fa fa-times"></i></a>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>--%>
                </div>
                <div class="clearfix"></div>
                </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">
                    <s:text name="page.branches.modal.add.modal.Close">Close</s:text>
                </button>
                <button class="btn btn-primary" type="button">
                    <s:text name="page.branches.modal.upload.modal.Save.Changes">Save Changes</s:text>
                </button>
            </div>

        </div>
    </div>
</div>