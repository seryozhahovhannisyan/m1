<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 7/29/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/branches/detail-controller.js"></script>
<div id="detail" aria-hidden="true" role="dialog" tabindex="-1" class="modal fade in" style="display: none; padding-right: 17px;">
    <div class="modal-dialog modal-lg resizable_style" >
        <div class="modal-content resizable_style" >
            <div class="modal-header ">
                <button data-dismiss="modal" class="close close_modal" type="button"><span aria-hidden="true">×</span>
                </button>
                <div class="detail_modal_restore">
                    <img
                        alt="<s:text name="page.branches.modal.detail.Maximize.Minimize">Maximize/Minimize</s:text>"
                        src="<%=request.getContextPath()%>/img/branches/maximize.png">
                </div>
                <div class="detail_modal_minimize">
                    <i class="glyphicon glyphicon-minus"></i>
                </div>
                <h4 id="myModalLabel" class="modal-title">
                    Branch name <span><s:text name="page.branches.modal.detail.modal.title.Detail">Detail</s:text></span>
                </h4>
            </div>
            <div class="modal-body">

                <table class="table table-striped modal_detail_table" >
                    <thead>

                    </thead>
                    <tbody>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.Company.Name">Company Name</s:text></td>
                            <td>Company Name</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.Status">Status</s:text></td>
                            <td>Active</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.Name">Name</s:text></td>
                            <td>Name</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.Address">Address</s:text></td>
                            <td>Address</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.State.Id">State Id</s:text></td>
                            <td>4554</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.City">City</s:text></td>
                            <td>City</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.Zip">Zip</s:text></td>
                            <td>12345</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.E.mail">E-mail</s:text></td>
                            <td>email@google.com</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.Phone.Code">Phone Code</s:text></td>
                            <td>+374</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.Phone">Phone</s:text></td>
                            <td>22 33 33</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.Policy.Phone.Code">Policy Phone Code</s:text></td>
                            <td>22</td>
                        </tr>
                        <tr>
                            <td><s:text name="page.branches.modal.detail.Policy.Phone">Policy Phone</s:text></td>
                            <td>22 33 33</td>
                        </tr>
                    </tbody>
                </table>
             </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button"><s:text name="page.branches.modal.detail.button.Close">Close</s:text></button>
            </div>

        </div>
    </div>
</div>