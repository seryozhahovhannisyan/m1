<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 9/22/2016
  Time: 03:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
 Created by IntelliJ IDEA.
 User: Serozh
 Date: 7/29/2016
 Time: 4:58 PM
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div id="message" aria-hidden="true" role="dialog" tabindex="-1" class="modal fade" style="display: none; padding-right: 17px;" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog lg-modal resizable_style" id="draggable_add" >
        <div class="modal-content resizable_style" >
            <div class="modal-header">
                <button data-dismiss="modal" class="close close_modal" type="button"><span aria-hidden="true">×</span>
                </button>
                <div class="add_modal_restore">
                    <img alt="<s:text name="page.branches.modal.add.Maximize.Minimize">Maximize/Minimize</s:text>"
                         src="<%=request.getContextPath()%>/img/branches/maximize.png">
                </div>
                <div class="add_modal_minimize">
                    <i class="glyphicon glyphicon-minus"></i>
                </div>
                <h4 id="myModalLabel" class="modal-title">
                    <s:text name="page.branches.modal.add.new.branch">Add New Branch</s:text>
                </h4>
            </div>
            <div class="modal-body">
                <div class="add_modal_form_block" >
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">
                    <s:text name="page.branches.modal.add.modal.Close">Close</s:text>
                </button>
            </div>

        </div>
    </div>
</div>
