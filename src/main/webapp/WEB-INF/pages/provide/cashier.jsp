<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 7/29/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="right_col" role="main" style="min-height: 2519px;">

    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">

                <div class="x_title">
                    <h2>
                        Branch
                    </h2>

                    <div class="clearfix"></div>
                </div>

                <div class="x_content">

                    <div id="datatable-responsive_wrapper"
                         class="dataTables_wrapper form-inline dt-bootstrap no-footer">

                        <div class="row">
                            <div class="add_div_brch_parent">
                                <div class="add_div_brch"  data-target="#add" data-toggle="modal"></div>
                                <div>Edit</div>
                            </div>
                            <div class="add_div_brch_parent">
                                <div class="delete_div_brch" data-target="#add" data-toggle="modal"></div>
                                <div>Cashiers</div>
                            </div>
                        </div>
                        <div class="row">

                            <div ng-controller="listController" id="listContent">
                                name : <s:property value="branch.name"/>
                                address: <s:property value="branch.address"/>
                                city: <s:property value="branch.city"/>
                                street: <s:property value="branch.street"/>
                                zip: <s:property value="branch.zip"/>
                                email: <s:property value="branch.email"/>
                                phoneCode: <s:property value="branch.phoneCode"/>
                                phone: <s:property value="branch.phone"/>
                                policyPhoneCode: <s:property value="branch.policyPhoneCode"/>
                                policyPhone: <s:property value="branch.policyPhone"/>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
