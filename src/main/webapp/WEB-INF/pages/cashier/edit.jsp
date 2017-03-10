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

    $(document).ready(function () {
        console.log('edit');
    });

</script>

<div id="edit" aria-hidden="true" role="dialog" tabindex="-1" class="modal fade in" style="display: none; padding-right: 17px;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span>
                </button>
                <h4 id="myModalLabel" class="modal-title">edit Modal title</h4>
            </div>
            <div class="modal-body">
                <h4>Text in a modal</h4>
                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.</p>
                <p>Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla.</p>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
                <button class="btn btn-primary" type="button">Save changes</button>
            </div>

        </div>
    </div>
</div>