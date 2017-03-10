<%@ taglib prefix="s" uri="/struts-tags" %>
<style>

    button {
        margin: 0 5px;
    }

    .mini {
        width: 250px;
        height: auto;
        background-color: #0c43b7;
        padding: 5px;
        bottom: 100px;
        position: fixed;
        right: 130px;
        border-radius: 6px;
    }

    .modal-content {
        height: 100%;
        position: relative;
    }

    .modal_footer_lg {
        position: absolute;
        bottom: 5px;
        width: 100%;
    }

    .full_screen {
        width: 90%;
        height: 700px;
    }

    .modal_email {
        width: 80%;
        border: 1px solid black !important;
        margin: 10px auto;
        float: none;
        display: block;
        max-width: 320px;
        border-radius: 6px;
        height: 40px;
        padding: 5px;
    }

    .modal_email:focus {
        border: 1px solid #a9a9a9 !important;
    }

    .mini button {
        float: right;
        border: none;
        padding: 1px;
        background-color: transparent;
        color: white;

    }

    .modal_ok {
        border: none;
        border-radius: 6px;
        background-color: #27ccc0;
        color: #f0f0f0;
        padding: 6px 12px;
    }

    .modal_cancel {
        border: none;
        border-radius: 6px;
        background-color: #ff3f54;
        color: #f0f0f0;
        padding: 6px 12px;
    }

    .modal_ok:hover {
        background-color: #24baaf;
    }

    .modal_cancel:hover {
        background-color: #d13545;
    }

    .summer {
        width: 80%;
        margin: 0 auto;
    }

    .close_popup{
        width: 300px;
        height: 100px;
        background-color: #dce2e1;
        position: absolute;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        margin: auto;
        z-index: 1;
        text-align: center;
        padding: 10px;
        border: 1px solid rgba(0, 0, 0, .2);
        border-radius: 6px;
        -webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
        box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
    }

</style>


<script type="text/ng-template" id="resend_activation_code">
    <%--
        modal for resend activation code
        ================================
    --%>
    <div class="close_popup"  ng-if="open_close_popup">
        <h2>Are you sure you want to close it?</h2>
        <button class="btn btn-success" ng-click="ctrl.cancel()">Yes</button>
        <button class="btn btn-danger" ng-click="ctrl.no_cancel()">NO</button>
    </div>

    <div class="modal-header">
        <button type="button" class="close" ng-click="$ctrl.cancel()">
            <i class="fa fa-times-circle" aria-hidden="true"></i>
        </button>

        <button type="button" ng-show="$ctrl.show_full_lg" class="close" ng-click="$ctrl.full_screen_large( )">
            <i class="fa fa-clone" aria-hidden="true"></i>
        </button>

        <button type="button" ng-show=" $ctrl.show_full_sm" class="close" ng-click="$ctrl.full_screen_large( )">
            <i class="fa fa-square-o" aria-hidden="true"></i>
        </button>

        <button type="button" class="close" ng-click="$ctrl.minimize()">
            <i class="fa fa-minus-square-o" aria-hidden="true"></i>
        </button>

        <h3 class="modal-title" id="modal-title">
            <s:text name="page.main.resend.activation">Resend activation code</s:text>
        </h3>
    </div>
    <div class="modal-body" id="modal-body">
        <form action="">
            <input type="email" required class="modal_email"
                   placeholder="<s:text name="page.home.placeholder.company.request.form.Contact.Email">Contact E-mail</s:text>">
            <div class="summer">
                <summernote name="message_summer" config="optionsSummernote"
                            on-init="init()" on-enter="enter()" on-focus="focus(evt)"
                            on-blur="blur(evt)" on-paste="paste(evt)" on-keyup="keyup(evt)"
                            on-keydown="keydown(evt)"
                            on-change="change(contents)"
                            on-image-upload="imageUpload(files)"
                            editable="editable" editor="editor">
                </summernote>
            </div>


            <div class="modal-footer">
                <button class="modal_ok" type="submit" ng-click="$ctrl.ok()">
                    <s:text name="button.ok">OK</s:text>
                </button>
                <button class="   modal_cancel" type="button" ng-click="$ctrl.cancel()">
                    <s:text name="button.cancel">Cancel</s:text>
                </button>
            </div>
        </form>
    </div>
</script>

<script type="text/ng-template" id="wallet_detail">

    <%--
    modal for wallet details
    ===========================
    --%>
<div class="wallet_detail">
    <div class="close_popup"  ng-if="open_close_popup">
        <h2>Are you sure you want to close it?</h2>
        <button class="btn btn-success" ng-click="ctrl.cancel()">Yes</button>
        <button class="btn btn-danger" ng-click="ctrl.no_cancel()">NO</button>
    </div>

    <div class="modal-header" >

        <button type="button" class="close" ng-click="ctrl.open_popup()">
            <i class="fa fa-times-circle" aria-hidden="true"></i>
        </button>

        <button type="button" ng-show="ctrl.show_full_lg" class="close" ng-click="ctrl.full_screen_large( )">
            <i class="fa fa-clone" aria-hidden="true"></i>
        </button>

        <button type="button" ng-show=" ctrl.show_full_sm" class="close" ng-click="ctrl.full_screen_large( )">
            <i class="fa fa-square-o" aria-hidden="true"></i>
        </button>

        <button type="button" class="close" ng-click="ctrl.minimize()">
            <i class="fa fa-minus-square-o" aria-hidden="true"></i>
        </button>
        {{wallet.name}}

        <h3 class="modal-title" id="modal-title" ng-if="withdrawMethod">
            <s:text name="page.cachier.withdraw.transaction">Withdraw Transaction</s:text>
            /
            <strong>{{wallet.name}} {{wallet.surname}}  </strong>
        </h3>

        <h3 class="modal-title" id="modal-title" ng-if="depositMethod">
            <s:text name="page.cachier.deposit.transaction">Deposit Transaction</s:text>
            /
            <strong>{{wallet.name}} {{wallet.surname}}  </strong>
        </h3>

    </div>

    <div class="modal-body" id="modal-body" ng-controller = "walletController">

        <div class="x_content" >

            <section class="content invoice">
                <!-- title row -->
                <div class="flip_iteven">
                    <div class="flip_center">
                        <div class="time_isteven_parent_div">
                            <isteven-multi-select class="ist_mult" on-item-click="onTimeSelect()"
                                                  input-model="timeselect"
                                                  output-model="timeselectOut"
                                                  button-label="description"
                                                  item-label="description"
                                                  tick-property="ticked"
                                                  selection-mode="single"
                                                  helper-elements="none"
                                                  translation="select_time"
                            >
                            </isteven-multi-select>

                            <div style="clear: both"></div>
                        </div>
                        <div class="flip_clock_div">
                            <h1>
                                <div class="clock pull-right"><%--Date: 16/08/2016--%></div>
                                <%--<button   ng-click = "startClock()">StartTime</button>--%>
                                <div style="clear:both;"></div>
                            </h1>
                            <h3 class="message">{{info_message}}</h3>

                        </div>
                        <div style="clear: both"></div>
                    </div>
                </div>


                <!-- info row -->
                <div class="row invoice-info">
                    <div class="col-sm-4 invoice-col">
                        <s:text name="cashier.transaction.wallet.from">From</s:text>
                        <address>
                            <div>
                                <span><em> <s:text
                                        name="cashier.name.surname">Cashier name surname: </s:text></em></span>
                                <span> <strong>{{cashier.name}}  {{cashier.surname}} </strong> </span>
                            </div>

                            <div>
                                <span><em> <s:text
                                        name="placeholder.company.request.form.Company.Address">Company Address:</s:text> </em></span>
                                <span> <strong>{{company.address}} </strong> </span>
                            </div>

                            <div>
                                <span><em><s:text name="cashier.phone">Cashier phone:</s:text>  </em></span>
                                <span> <strong> + {{cashier.phoneCode}} {{cashier.phone}}  </strong> </span>
                            </div>

                            <div>
                                <span><em> <s:text name="cashier.email">Cashier email:</s:text> </em></span>
                                <span> <strong> {{cashier.email}}  </strong> </span>
                            </div>

                            <div>
                                <span><em> <s:text name="company.phone">Company phone:</s:text></em></span>
                                <span> <strong> + {{company.phoneCode}} {{company.phone}}  </strong> </span>
                            </div>

                            <div>
                                <span><em> <s:text name="company.email">Company email:</s:text> </em></span>
                                <span> <strong> {{company.email}}  </strong> </span>
                            </div>


                        </address>
                    </div>
                    <!-- /.col -->
                    <div class="col-sm-4 invoice-col">
                        <s:text name="cashier.transaction.wallet.to">To</s:text>

                        <address>
                            <div>
                                <span><em> <s:text name="wallet.name.surname">Wallet name surname:</s:text> </em></span>
                                <span> <strong>{{wallet.name}}  {{wallet.surname}} </strong> </span>
                            </div>


                            <div>
                                <span><em><s:text name="wallet.email">wallet email:</s:text>   </em></span>
                                <span> <strong> {{wallet.email}}  </strong> </span>
                            </div>

                        </address>
                    </div>
                    <!-- /.col -->
                    <div class="col-sm-4 invoice-col">
                        <div>
                            <span><em> <s:text name="cashier.transaction.invoice">Invoice:</s:text>    </em></span>
                            <span> <strong> {{invoice}} </strong> </span>
                        </div>


                        <div>
                            <span><em><s:text name="cashier.transaction.order.id"> Order ID:</s:text> </em></span>
                            <span> <strong> {{order_id}}  </strong> </span>
                        </div>

                        <div>
                            <span><em> <s:text name="cashier.transaction.payment.due">Payment Due:</s:text> </em></span>
                            <span> <strong> {{payment_due}}  </strong> </span>
                        </div>

                        <div>
                            <span><em> <s:text
                                    name="cashier.transaction.payment.due.closed">Payment Closed Due:</s:text> </em></span>
                            <span> <strong> {{payment_due_closed}}  </strong> </span>
                        </div>

                        <div>
                            <span><em> <s:text name="cashier.transaction.account">Account:</s:text>  </em></span>
                            <span> <strong> {{account}} </strong> </span>
                        </div>


                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-8">
                        <input  only-digits id="amount" ng-model="amount_val" class="form-control col-md-7 col-xs-8"
                               required="required" type="text"/>
                        <div class="currency_type_div">
                            <isteven-multi-select ng-show="wRateValues != null"
                                                  on-item-click="setCuerrencyType()"
                                                  input-model="wRateValues"
                                                  output-model="currencyTypeSelected"
                                                  button-label="name"
                                                  item-label="name"
                                                  selection-mode="single"
                                                  helper-elements="filter"
                                                  tick-property="ticked"
                            >
                            </isteven-multi-select>

                            <span ng-show="wRateValues == null">
                                {{curr_type_text}}
                        </span>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <!-- /.col -->
                    <div class="col-xs-12">

                        <p class="lead">
                            <s:text name="cashier.transaction.amount.due">Amount Due</s:text>
                            {{cdate | date: 'dd/MM/yyyy' }} </p>

                        <div class="col-xs-6">
                            <address>
                                <div  class="logos_parent">
                                <div class="merchant_logo">

                                </div>
                                    <span class="tax_class_text"><s:text name="cashier.transaction.surcharge">Trasaction Surcharge</s:text></span>
                                </div>
                                <div>
                                    <span><em> <s:text
                                            name="cashier.transaction.withdraw.amount"> Withdraw Amount:</s:text></em></span>
                                    <span> <strong> {{withdrawAmount}} {{withdrawAmountCurrencyType}}</strong> </span>
                                </div>

                                <div>
                                    <span><em> <s:text name="cashier.transaction.tax"> Tax</s:text></em></span>
                                    <span> <strong>{{withdrawCashierCashBoxTotalTax}} {{withdrawCashierCashBoxTotalTaxCurrencyType}}</strong> </span>
                                </div>

                                <div>
                                    <span><em> <s:text
                                            name="page.branches.modal.upload.uploader.Total"> Total :</s:text> </em></span>
                                    <span> <strong> {{cashierTotalAmount}} {{cashierTotalAmountCurrencyType}}  </strong> </span>
                                </div>

                            </address>
                        </div>
                        <div class="col-xs-6">
                            <div class="logos_parent">
                            <div class="wallet_logo" style="background-image: url({{imgUrl}})">
                            </div>
                            <span class="tax_class_text">
                                <s:text name="cashier.transaction.surcharge">Trasaction Surcharge</s:text>
                            </span>
                            </div>
                                <div>
                                    <span><em> <s:text
                                            name="cashier.transaction.wallet.transaction"> Wallet Total Price:</s:text> </em></span>
                                <span> <strong> {{walletTotalPrice}} {{walletTotalPriceCurrencyType}}  </strong> </span>
                            </div>
                        </div>
                    </div>

                    <!-- /.col -->
                </div>
                <!-- /.row -->

                <!-- this row will not appear when printing -->
                <div class="row no-print">
                    <div class="col-xs-12">

                        <button ng-show="show_tax" ng-if ="withdrawMethod"  class="btn btn-success pull-right" ng-disabled="disable_buttons"
                                ng-click="withdrawDepositCheckTax('withdraw-check-tax.htm')">
                            <i class="fa fa-credit-card"></i>
                            <s:text name="cashier.transaction.show.tax">Show tax</s:text>
                        </button>
                        <button ng-show="show_tax" ng-if ="depositMethod"  class="btn btn-success pull-right" ng-disabled="disable_buttons"
                                ng-click="withdrawDepositCheckTax('deposit-check-tax.htm')">
                            <i class="fa fa-credit-card"></i>
                            <s:text name="cashier.transaction.show.tax">Show tax</s:text>
                        </button>
                        <button ng-show="approve_trans" ng-if ="withdrawMethod" class="btn btn-success pull-right" ng-disabled="disable_buttons"
                                ng-click="withdrawMakePayment($event, 'withdraw-make-payment.htm')">
                            <i class="fa fa-credit-card"></i>
                            <s:text name="cashier.transaction.approve.transaction">Approve transaction</s:text>
                        </button>
                        <button ng-show="approve_trans" ng-if ="depositMethod" class="btn btn-success pull-right" ng-disabled="disable_buttons"
                                ng-click="withdrawMakePayment($event, 'deposit-make-payment.htm')">
                            <i class="fa fa-credit-card"></i>
                            <s:text name="cashier.transaction.approve.transaction">Approve transaction</s:text>
                        </button>

                        <button ng-show="approve_trans" class="btn btn-danger pull-right" ng-disabled="disable_buttons"
                                ng-click="cancel_transaction($event)">
                            <i class="fa fa-credit-card"></i>
                            <s:text name="cashier.transaction.cancel.transaction">Cancel transaction</s:text>
                        </button>
                    </div>
                </div>
            </section>
        </div>


        <form action="">
            <div class="modal-footer">

            </div>
        </form>
    </div>
</div>
</script>

<script type="text/ng-template" id="wallet_detail_view">

    <%--
    modal for wallet details view
    ===========================
    --%>

<div ng-controller = "walletControllerView">
    <div class="close_popup"  ng-if="open_close_popup">
        <h2>Are you sure you want to close it?</h2>
        <button class="btn btn-success" ng-click="ctrl.cancel()">Yes</button>
        <button class="btn btn-danger" ng-click="ctrl.no_cancel()">NO</button>
    </div>

    <div class="modal-header" >

        <button type="button" class="close" ng-click="ctrl.open_popup()">
            <i class="fa fa-times-circle" aria-hidden="true"></i>
        </button>

        <button type="button" ng-show="ctrl.show_full_lg" class="close" ng-click="ctrl.full_screen_large( )">
            <i class="fa fa-clone" aria-hidden="true"></i>
        </button>

        <button type="button" ng-show=" ctrl.show_full_sm" class="close" ng-click="ctrl.full_screen_large( )">
            <i class="fa fa-square-o" aria-hidden="true"></i>
        </button>

        <button type="button" class="close" ng-click="ctrl.minimize()">
            <i class="fa fa-minus-square-o" aria-hidden="true"></i>
        </button>
        {{wallet.name}}

        <h3 class="modal-title" id="modal-title" ng-if="withdrawMethod">
            <s:text name="page.cachier.withdraw.transaction">Withdraw Transaction</s:text>
            /
            <strong>{{walletToname}} {{walletTosurname}}  </strong>
        </h3>
        <h3 class="modal-title" id="modal-title" ng-if="depositMethod">
            <s:text name="page.cachier.deposit.transaction">Deposit Transaction</s:text>
            /
            <strong>{{walletToname}} {{walletTosurname}}  </strong>
        </h3>
    </div>

    <div class="modal-body" id="modal-body" >

        <div class="x_content">
            <section class="content invoice">
                <!-- title row -->
                <div class="flip_iteven">
                    <div class="flip_center">
                        <div class="time_isteven_parent_div">
                            <isteven-multi-select class="ist_mult" on-item-click="onTimeSelect()"
                                                  input-model="timeselect"
                                                  output-model="timeselectOut"
                                                  button-label="description"
                                                  item-label="description"
                                                  tick-property="ticked"
                                                  selection-mode="single"
                                                  helper-elements="none"
                                                  translation="select_time"
                                                  disable-property="disabled"
                                                  is-disabled="true"
                            >
                            </isteven-multi-select>

                            <div style="clear: both"></div>
                        </div>
                        <div class="flip_clock_div">
                            <h1>
                                <div class="clock pull-right"> </div>
                                <div style="clear:both;"></div>
                            </h1>
                            <h3 class="message">{{info_message}}</h3>

                        </div>
                        <div style="clear: both"></div>
                    </div>
                </div>


                <!-- info row -->
                <div class="row invoice-info">
                    <div class="col-sm-4 invoice-col">
                        <s:text name="cashier.transaction.wallet.from">From</s:text>
                        <address>
                            <div>
                                <span><em> <s:text
                                        name="cashier.name.surname">Cashier name surname: </s:text></em></span>
                                <span> <strong><s:property value="#session.cashier.name"/>  <s:property value="#session.cashier.surname"/></strong> </span>
                            </div>



                            <div>
                                <span><em><s:text name="cashier.phone">Cashier phone:</s:text>  </em></span>
                                <span> <strong> + <s:property value="#session.cashier.phoneCode"/> <s:property value="#session.cashier.phone"/>  </strong> </span>
                            </div>

                            <div>
                                <span><em> <s:text name="cashier.email">Cashier email:</s:text> </em></span>
                                <span> <strong> <s:property value="#session.cashier.email"/>  </strong> </span>
                            </div>





                        </address>
                    </div>
                    <!-- /.col -->
                    <div class="col-sm-4 invoice-col">
                        <s:text name="cashier.transaction.wallet.to">To</s:text>

                        <address>
                            <div>
                                <span><em> <s:text name="wallet.name.surname">Wallet name surname:</s:text> </em></span>
                                <span> <strong>{{walletToname}}  {{walletTosurname}} </strong> </span>
                            </div>

                            <div>
                                <span><em><s:text name="wallet.email">wallet email:</s:text>   </em></span>
                                <span> <strong> {{walletToemail}}  </strong> </span>
                            </div>

                        </address>
                    </div>
                    <!-- /.col -->
                    <div class="col-sm-4 invoice-col">
                        <div>
                            <span><em> <s:text name="cashier.transaction.invoice">Invoice:</s:text>    </em></span>
                            <span> <strong> {{invoice}} </strong> </span>
                        </div>


                        <div>
                            <span><em><s:text name="cashier.transaction.order.id"> Order ID:</s:text> </em></span>
                            <span> <strong> {{order_id}}  </strong> </span>
                        </div>

                        <div>
                            <span><em> <s:text name="cashier.transaction.payment.due">Payment Due:</s:text> </em></span>
                            <span> <strong> {{payment_due}}  </strong> </span>
                        </div>

                        <div>
                            <span><em> <s:text
                                    name="cashier.transaction.payment.due.closed">Payment Closed Due:</s:text> </em></span>
                            <span> <strong> {{payment_due_closed}}  </strong> </span>
                        </div>

                        <div>
                            <span><em> <s:text name="cashier.transaction.account">Account:</s:text>  </em></span>
                            <span> <strong> {{account}} </strong> </span>
                        </div>


                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-8">
                        <input  only-digits id="amount" ng-model="withdrawAmount" readonly = "readonly"  class="form-control col-md-7 col-xs-8"
                                required="required" type="text"/>
                        <div class="currency_type_div">
                            <%--<isteven-multi-select ng-show="wRateValues != null"--%>
                                                  <%--on-item-click="setCuerrencyType()"--%>
                                                  <%--input-model="wRateValues"--%>
                                                  <%--output-model="currencyTypeSelected"--%>
                                                  <%--button-label="name"--%>
                                                  <%--item-label="name"--%>
                                                  <%--selection-mode="single"--%>
                                                  <%--helper-elements="filter"--%>
                                                  <%--tick-property="ticked"--%>

                            <%-->--%>
                            <%--</isteven-multi-select>--%>

                            <span>
                                {{withdrawAmountCurrencyType}}
                        </span>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <!-- /.col -->
                    <div class="col-xs-12">

                        <p class="lead">
                            <s:text name="cashier.transaction.amount.due">Amount Due</s:text>
                            {{cdate | date: 'dd/MM/yyyy' }} </p>

                        <div class="col-xs-6">
                            <address>
                                <div  class="logos_parent">
                                    <div class="merchant_logo">

                                    </div>
                                    <span class="tax_class_text"><s:text name="cashier.transaction.surcharge">Trasaction Surcharge</s:text></span>
                                </div>
                                <div>
                                    <span><em> <s:text
                                            name="cashier.transaction.withdraw.amount"> Withdraw Amount:</s:text></em></span>
                                    <span> <strong> {{withdrawAmount}} {{withdrawAmountCurrencyType}}</strong> </span>
                                </div>

                                <div>
                                    <span><em> <s:text name="cashier.transaction.tax"> Tax</s:text></em></span>
                                    <span> <strong>{{withdrawCashierCashBoxTotalTax}} {{withdrawCashierCashBoxTotalTaxCurrencyType}}</strong> </span>
                                </div>

                                <div>
                                    <span><em> <s:text
                                            name="page.branches.modal.upload.uploader.Total"> Total :</s:text> </em></span>
                                    <span> <strong> {{cashierTotalAmount}} {{cashierTotalAmountCurrencyType}}  </strong> </span>
                                </div>

                            </address>
                        </div>
                        <div class="col-xs-6">
                            <div class="logos_parent">
                                <div class="wallet_logo">
                                </div>
                                <span class="tax_class_text"><s:text name="cashier.transaction.surcharge">Trasaction Surcharge</s:text></span>
                            </div>
                            <div>
                                    <span><em> <s:text
                                            name="cashier.transaction.wallet.transaction"> Wallet Total Price:</s:text> </em></span>
                                <span> <strong> {{walletTotalPrice}} {{walletTotalPriceCurrencyType}}  </strong> </span>
                            </div>
                        </div>
                    </div>

                    <!-- /.col -->
                </div>
                <!-- /.row -->

                <!-- this row will not appear when printing -->
                <div class="row no-print">
                    <div class="col-xs-12">


                        <button   class="btn btn-danger pull-right" ng-if = "withdrawMethod"
                                ng-click="transaction_timeout('withdraw-time-out.htm')">
                            <i class="fa fa-credit-card"></i>
                            <s:text name="cashier.transaction.transaction.timeout">Transaction timeout</s:text>
                        </button>

                        <button   class="btn btn-danger pull-right" ng-if = "depositMethod"
                                  ng-click="transaction_timeout('deposit-time-out.htm')">
                            <i class="fa fa-credit-card"></i>
                            <s:text name="cashier.transaction.transaction.timeout">Transaction timeout</s:text>
                        </button>
                    </div>
                </div>
            </section>
        </div>


        <form action="">
            <div class="modal-footer">

            </div>
        </form>
    </div>
</div>
</script>

<script type="text/ng-template" id="wallet_notification_view">

    <%--
    modal for wallet notification view
    ===========================
    --%>

    <div ng-controller = "notificationControllerView">
        <div class="close_popup"  ng-if="open_close_popup">
            <h2>Are you sure you want to close it?</h2>
            <button class="btn btn-success" ng-click="ctrl.cancel()">Yes</button>
            <button class="btn btn-danger" ng-click="ctrl.no_cancel()">NO</button>
        </div>

        <div class="modal-header" >

            <button type="button" class="close" ng-click="ctrl.open_popup()">
                <i class="fa fa-times-circle" aria-hidden="true"></i>
            </button>

            <button type="button" ng-show="ctrl.show_full_lg" class="close" ng-click="ctrl.full_screen_large( )">
                <i class="fa fa-clone" aria-hidden="true"></i>
            </button>

            <button type="button" ng-show=" ctrl.show_full_sm" class="close" ng-click="ctrl.full_screen_large( )">
                <i class="fa fa-square-o" aria-hidden="true"></i>
            </button>

            <button type="button" class="close" ng-click="ctrl.minimize()">
                <i class="fa fa-minus-square-o" aria-hidden="true"></i>
            </button>
            {{wallet.name}}
            <h3 class="modal-title" id="modal-title">
                <s:text name="page.cachier.withdraw.transaction">Withdraw Transaction</s:text>
                /
                <strong>{{notification_popup_info.walletToname}} {{notification_popup_info.walletTosurname}}  </strong>
            </h3>
        </div>

        <div class="modal-body" id="modal-body" >

            <div class="x_content">
                <section class="content invoice">
                    <!-- title row -->
                    <div class="flip_iteven">
                        <div class="flip_center">
                            <div class="time_isteven_parent_div">
                                <isteven-multi-select class="ist_mult" on-item-click="onTimeSelect()"
                                                      input-model="timeselect"
                                                      output-model="timeselectOut"
                                                      button-label="description"
                                                      item-label="description"
                                                      tick-property="ticked"
                                                      selection-mode="single"
                                                      helper-elements="none"
                                                      translation="select_time"
                                                      disable-property="disabled"
                                                      is-disabled="true"
                                >
                                </isteven-multi-select>

                                <div style="clear: both"></div>
                            </div>
                            <div class="flip_clock_div">
                                <h1>
                                    <div class="clock pull-right"> </div>
                                    <div style="clear:both;"></div>
                                </h1>
                                <h3 class="message">{{info_message}}</h3>

                            </div>
                            <div style="clear: both"></div>
                        </div>
                    </div>


                    <!-- info row -->
                    <div class="row invoice-info">
                        <div class="col-sm-4 invoice-col">
                            <s:text name="cashier.transaction.wallet.from">From</s:text>
                            <address>
                                <div>
                                <span><em> <s:text
                                        name="cashier.name.surname">Cashier name surname: </s:text></em></span>
                                    <span> <strong><s:property value="#session.cashier.name"/>  <s:property value="#session.cashier.surname"/></strong> </span>
                                </div>



                                <div>
                                    <span><em><s:text name="cashier.phone">Cashier phone:</s:text>  </em></span>
                                    <span> <strong> + <s:property value="#session.cashier.phoneCode"/> <s:property value="#session.cashier.phone"/>  </strong> </span>
                                </div>

                                <div>
                                    <span><em> <s:text name="cashier.email">Cashier email:</s:text> </em></span>
                                    <span> <strong> <s:property value="#session.cashier.email"/>  </strong> </span>
                                </div>





                            </address>
                        </div>
                        <!-- /.col -->
                        <div class="col-sm-4 invoice-col">
                            <s:text name="cashier.transaction.wallet.to">To</s:text>

                            <address>
                                <div>
                                    <span><em> <s:text name="wallet.name.surname">Wallet name surname:</s:text> </em></span>
                                    <span> <strong>{{notification_popup_info.walletToname}} {{notification_popup_info.walletTosurname}}  </strong> </span>
                                </div>

                                <div>
                                    <span><em><s:text name="wallet.email">wallet email:</s:text>   </em></span>
                                    <span> <strong>{{notification_popup_info.walletToemail}} </strong> </span>
                                </div>

                            </address>
                        </div>
                        <!-- /.col -->
                        <div class="col-sm-4 invoice-col">
                            <div>
                                <span><em> <s:text name="cashier.transaction.invoice">Invoice:</s:text>    </em></span>
                                <span> <strong> {{notification_popup_info.invoice}} </strong> </span>
                            </div>


                            <div>
                                <span><em><s:text name="cashier.transaction.order.id"> Order ID:</s:text> </em></span>
                                <span> <strong> {{notification_popup_info.order_id}}  </strong> </span>
                            </div>

                            <div>
                                <span><em> <s:text name="cashier.transaction.payment.due">Payment Due:</s:text> </em></span>
                                <span> <strong> {{notification_popup_info.payment_due}}  </strong> </span>
                            </div>

                            <div>
                            <span><em> <s:text
                                    name="cashier.transaction.payment.due.closed">Payment Closed Due:</s:text> </em></span>
                                <span> <strong> {{notification_popup_info.payment_due_closed}}  </strong> </span>
                            </div>

                            <div>
                                <span><em> <s:text name="cashier.transaction.account">Account:</s:text>  </em></span>
                                <span> <strong> {{notification_popup_info.account}} </strong> </span>
                            </div>


                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <input  only-digits id="amount" ng-model="notification_popup_info.withdrawAmount" readonly = "readonly"  class="form-control col-md-7 col-xs-8"
                                    required="required" type="text"/>
                            <div class="currency_type_div">
                                <%--<isteven-multi-select ng-show="wRateValues != null"--%>
                                <%--on-item-click="setCuerrencyType()"--%>
                                <%--input-model="wRateValues"--%>
                                <%--output-model="currencyTypeSelected"--%>
                                <%--button-label="name"--%>
                                <%--item-label="name"--%>
                                <%--selection-mode="single"--%>
                                <%--helper-elements="filter"--%>
                                <%--tick-property="ticked"--%>

                                <%-->--%>
                                <%--</isteven-multi-select>--%>

                                <span>
                                {{notification_popup_info.withdrawAmountCurrencyType}}
                        </span>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <!-- /.col -->
                        <div class="col-xs-12">

                            <p class="lead">
                                <s:text name="cashier.transaction.amount.due">Amount Due</s:text>
                                {{cdate | date: 'dd/MM/yyyy' }} </p>

                            <div class="col-xs-6">
                                <address>
                                    <div  class="logos_parent">
                                        <div class="merchant_logo">

                                        </div>
                                        <span class="tax_class_text"><s:text name="cashier.transaction.surcharge">Trasaction Surcharge</s:text></span>
                                    </div>
                                    <div>
                                    <span><em> <s:text
                                            name="cashier.transaction.withdraw.amount"> Withdraw Amount:</s:text></em></span>
                                        <span> <strong> {{notification_popup_info.withdrawAmount}} {{notification_popup_info.withdrawAmountCurrencyType}}</strong> </span>
                                    </div>

                                    <div>
                                        <span><em> <s:text name="cashier.transaction.tax"> Tax</s:text></em></span>
                                        <span> <strong>{{notification_popup_info.withdrawCashierCashBoxTotalTax}} {{notification_popup_info.withdrawCashierCashBoxTotalTaxCurrencyType}}</strong> </span>
                                    </div>

                                    <div>
                                    <span><em> <s:text
                                            name="page.branches.modal.upload.uploader.Total"> Total :</s:text> </em></span>
                                        <span> <strong> {{notification_popup_info.cashierTotalAmount}} {{notification_popup_info.cashierTotalAmountCurrencyType}}  </strong> </span>
                                    </div>

                                </address>
                            </div>
                            <div class="col-xs-6">
                                <div class="logos_parent">
                                    <div class="wallet_logo">
                                    </div>
                                    <span class="tax_class_text"><s:text name="cashier.transaction.surcharge">Trasaction Surcharge</s:text></span>
                                </div>
                                <div>
                                    <span><em> <s:text
                                            name="cashier.transaction.wallet.transaction"> Wallet Total Price:</s:text> </em></span>
                                    <span> <strong> {{notification_popup_info.walletTotalPrice}} {{notification_popup_info.walletTotalPriceCurrencyType}}  </strong> </span>
                                </div>
                            </div>
                        </div>

                        <!-- /.col -->
                    </div>
                    <!-- /.row -->

                    <!-- this row will not appear when printing -->
                    <div class="row no-print">
                        <div class="col-xs-12">


                            <button   class="btn btn-danger pull-right"
                                      ng-click="transaction_timeout($event)">
                                <i class="fa fa-credit-card"></i>
                                <s:text name="cashier.transaction.transaction.timeout">Transaction timeout</s:text>
                            </button>
                        </div>
                    </div>
                </section>
            </div>


            <form action="">
                <div class="modal-footer">

                </div>
            </form>
        </div>
    </div>
</script>
<%--todo create custom modal and overide id needs it --%>
<script type="text/ng-template" id="admin_company_provide_detail">

    <%--
    modal for wallet notification view
    ===========================
    --%>

    <div>
        <div class="close_popup"  ng-if="open_close_popup">
            <h2>Are you sure you want to close it?</h2>
            <button class="btn btn-success" ng-click="ctrl.cancel()">Yes</button>
            <button class="btn btn-danger" ng-click="ctrl.no_cancel()">NO</button>
        </div>

        <div class="modal-header" >

            <button type="button" class="close" ng-click="ctrl.open_popup()">
                <i class="fa fa-times-circle" aria-hidden="true"></i>
            </button>

            <button type="button" ng-show="ctrl.show_full_lg" class="close" ng-click="ctrl.full_screen_large( )">
                <i class="fa fa-clone" aria-hidden="true"></i>
            </button>

            <button type="button" ng-show=" ctrl.show_full_sm" class="close" ng-click="ctrl.full_screen_large( )">
                <i class="fa fa-square-o" aria-hidden="true"></i>
            </button>

            <button type="button" class="close" ng-click="ctrl.minimize()">
                <i class="fa fa-minus-square-o" aria-hidden="true"></i>
            </button>
            {{wallet.name}}
            <h3 class="modal-title" id="modal-title">
                <s:text name="page.cachier.withdraw.transaction">Provide to branches</s:text>
                /
                <strong>{{branchIdes}} --> {{cdate}}  </strong>
            </h3>
        </div>

        <div class="modal-body" id="modal-body"  ng-controller = "providerController">

            <div class="x_content">
                <section class="content invoice">
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <input id="provideApproveAmount"  class="form-control col-md-7 col-xs-8"
                                    required="required" type="text"/>
                            <div class="currency_type_div">
                                <span>
                                    USD
                                </span>
                            </div>
                        </div>
                    </div>

                    <!-- this row will not appear when printing -->
                    <div class="row no-print"  >
                        <div class="col-xs-12">
                            <button   class="btn btn-success pull-right"
                                      ng-click="provideApprove($event)">
                                <i class="fa fa-credit-card"></i>
                                Transfer
                            </button>
                        </div>
                    </div>
                </section>
            </div>


            <form action="">
                <div class="modal-footer">

                </div>
            </form>
        </div>
    </div>
</script>
<script type="text/ng-template" id="admin_company_take_back_detail">

    <%--
    modal for wallet notification view
    ===========================
    --%>

    <div>
        <div class="close_popup"  ng-if="open_close_popup">
            <h2>Are you sure you want to close it?</h2>
            <button class="btn btn-success" ng-click="ctrl.cancel()">Yes</button>
            <button class="btn btn-danger" ng-click="ctrl.no_cancel()">NO</button>
        </div>

        <div class="modal-header" >

            <button type="button" class="close" ng-click="ctrl.open_popup()">
                <i class="fa fa-times-circle" aria-hidden="true"></i>
            </button>

            <button type="button" ng-show="ctrl.show_full_lg" class="close" ng-click="ctrl.full_screen_large( )">
                <i class="fa fa-clone" aria-hidden="true"></i>
            </button>

            <button type="button" ng-show=" ctrl.show_full_sm" class="close" ng-click="ctrl.full_screen_large( )">
                <i class="fa fa-square-o" aria-hidden="true"></i>
            </button>

            <button type="button" class="close" ng-click="ctrl.minimize()">
                <i class="fa fa-minus-square-o" aria-hidden="true"></i>
            </button>
            {{wallet.name}}
            <h3 class="modal-title" id="modal-title">
                <s:text name="page.cachier.withdraw.transaction">Take back from branches</s:text>
                /
                <strong>{{branchIdes}} --> {{cdate}}  </strong>
            </h3>
        </div>

        <div class="modal-body" id="modal-body"  ng-controller = "providerController">

            <div class="x_content">
                <section class="content invoice">
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <input id="takeBackApproveAmount"  class="form-control col-md-7 col-xs-8"
                                    required="required" type="text"/>
                            <div class="currency_type_div">
                                <span>
                                    USD
                                </span>
                            </div>
                        </div>
                    </div>

                    <!-- this row will not appear when printing -->
                    <div class="row no-print"  >
                        <div class="col-xs-12">
                            <button   class="btn btn-success pull-right"
                                      ng-click="takeBackApprove($event)">
                                <i class="fa fa-credit-card"></i>
                                Take back
                            </button>
                        </div>
                    </div>
                </section>
            </div>


            <form action="">
                <div class="modal-footer">

                </div>
            </form>
        </div>
    </div>
</script>


<div class="mini ng-cloak"  ng-show="show_mini">
    <button type="button" ng-click="close_from_mini()">
        <i class="fa fa-times-circle" aria-hidden="true"></i>
    </button>
    <button type="button" ng-click="full_screen()">
        <i class="fa fa-square-o" aria-hidden="true"></i>
    </button>
    <div style="clear: both"></div>
</div>


