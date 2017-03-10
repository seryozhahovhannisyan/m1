controllers.walletController = ['$scope','$rootScope', '$http', '$filter', '$window', '$sce', 'ngTableParams', '$timeout', function ($scope, $rootScope, $http, $filter, $window, $sce, ngTableParams, $timeout) {

    $scope.isteven_currencies = typeof isteven_currencies === 'undefined' ? null : isteven_currencies;
    $scope.translation = typeof localeFilter === 'undefined' ? null : localeFilter;
    $scope.localLang = typeof localeFilter === 'undefined' ? null : localeFilter;
    $rootScope.wallet;
    $scope.show_tax = true;
    $scope.approve_trans = false;
    $scope.cancel_trans = false;
    $scope.disable_buttons = false;
    $rootScope.open_close_popup = false;


    $scope.clockSettings = function(time){
        var clock,transactionTime;
        if(time){
            transactionTime = time
        }
        else {
            transactionTime = $scope.duration ? $scope.duration : 300;
        }

            clock = angular.element('.clock').FlipClock(transactionTime, {
                clockFace: 'SecondCounter',
                autoStart : false,
                countdown: true,
                language: $scope.current_language,
                callbacks: {
                    stop: function() {
                        $timeout(function () {
                            $scope.info_message ='The clock has stopped!'
                        },1000);
                    }
                }
            });

        $scope.startClock = function () {
            clock.start();

        }
    } ;

    $scope.clockSettings();
    $scope.set_null_tax = function () {
        $scope.invoice = "-------";
        $scope.order_id = "-------";
        $scope.payment_due = "-------";
        $scope.payment_due_closed = "-------";
        $scope.account = "-------";
        $scope.withdrawAmount = "-------";
        $scope.withdrawCashierCashBoxTotalTax = "-------";
        $scope.cashierTotalAmount = "-------";
        $scope.walletTotalPrice = "-------";
        $scope.withdrawAmountCurrencyType = "";
        $scope.cashierTotalAmountCurrencyType = "";
        $scope.walletTotalPriceCurrencyType = "";
        $scope.withdrawCashierCashBoxTotalTaxCurrencyType = "";
        $rootScope.info_message = ""
    };
    function img_src  (item) {
        var img_src = angular.element(item.currentTarget).parent("td").parent("tr").find("td:nth-child(1)").find("img").attr("src");
        return img_src
    }

    /*Wallet*/

    $scope.withdrawDepositStart = function (item, index, url) {

if(url.indexOf('withdraw-start.htm') > -1){
    $rootScope.depositMethod = false;
    $rootScope.withdrawMethod = true;
}
if(url.indexOf('deposit-start.htm') > -1){
    $rootScope.depositMethod = true;
    $rootScope.withdrawMethod = false;

}


        $scope.cdate = new Date();
        var path = url;
        var id = item.currentTarget.getAttribute("data-id");
        $http({
            method: 'post',
            url: path,
            data: {
                walletId: id
            },
            async : true,
            dataType: 'json'
        }).then(
            function (response) {

                var result = response.data.dto;
                console.log("response from custom row",result);
                if (result.responseStatus == 'SUCCESS' && result.fielderrors == null) {
                    $rootScope.imgUrl = img_src(item);
                    $scope.set_null_tax();
                    var cashier = result.response.cashier;
                    var company = result.response.company;
                    var wallet = result.response.wallet;
                    var role = cashier.role;
                    var availableRateValues = role.availableRateValues;
                    var currencyType = company.currentCashBox.currencyType;

                    $rootScope.walletId = wallet.id;

                    $rootScope.wallet = wallet;

                    $rootScope.cashier = cashier;
                    $rootScope.company = company;
                    $rootScope.currencyType = currencyType;
                    $scope.currencyTypeSelected = [];
                    $scope.cashierLogo =  $rootScope.IMAGE_BASE_URL + result.response.cashier.id;



                    var wRateValues = [];

                    if(availableRateValues){

                        var rateValues = availableRateValues.split(',');
                        for(var rate in rateValues){
                            var r = valueOfCurrencyType(rateValues[rate]);
                            wRateValues.push({id: r.id, name: r.name, ticked: currencyType == r.code});
                        }
                        $rootScope.wRateValues = wRateValues;
                        console.log("$rootScope.wRateValues",$rootScope.wRateValues);

                        $scope.currencyTypeSelected = [{
                            id: 152, name: "US Dollar ($)"
                        }];
                        $rootScope.curr_type =$scope.currencyTypeSelected[0].id;
                    }
                    else{

                        $rootScope.curr_type_text = cashier.currentCashBox.currencyType;

                        $rootScope.curr_type =valueOfCurrencyTypeByCode($scope.curr_type_text).id;
                    }

                    $scope.transactionType = 'Withdraw Transaction';
                    $scope.open_modal('lg','','wallet_detail');
                }
                else {
                    $scope["toltip_field_error_"+""+index+""]=result.fielderrors.walletId;
                    // $scope.toltip_field_error = result.fielderrors.walletId;
                    return false;
                }

            });



    };


    /*
    time select isteven
    ===================
    */

    $scope.timeselect = [];

    $scope.select_time = {
        nothingSelected: "Select transaction duration"
    };
    var description = "I18N_LOCALE_MESSAGES_"+$scope.current_language;
        description = $window[description];
    angular.forEach(TransactionRationalDuration, function (value, key) {

        $scope.timeselect.push({
            description:  description[value.description],
            duration: value.duration,
            id: value.id,
            ticked: false
        })
    });

        $scope.timeselect[0].ticked =true;
        $scope.onTimeSelect = function () {
        $scope.duration =$scope.timeselectOut[0].duration;
        $scope.durationId =$scope.timeselectOut[0].id;
        $scope.clockSettings();

    };

    $scope.setCuerrencyType = function () {
        $rootScope.curr_type =$scope.currencyTypeSelected[0].id;

    };

    /*
     time select isteven end
     =======================
     */



    $scope.withdrawDepositCheckTax = function (url) {


    if($scope.amount_val != "" && $scope.amount_val != undefined) {

        angular.element("#amount").css("border", "1px solid #ccc");

        var path = url;
        $scope.show_loader();

        var data = {
            amount: $scope.amount_val,
            walletId: $rootScope.walletId,
            currencyType: $rootScope.curr_type,
            rationalDuration: $scope.durationId ? $scope.durationId : 1
        };

        $http({
            method: 'post',
            url: path,
            data: data,
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;

console.log("response",response);

                if (result.responseStatus == 'SUCCESS') {

                    var transactionDto = result.response.transactionDto;

                    $scope.invoice = transactionDto.finalState;
                    $scope.order_id = transactionDto.orderCode;
                    $scope.payment_due_closed = transactionDto.closedAt;
                    $scope.payment_due = transactionDto.openedAt;
                    $scope.account = transactionDto.account;
                    $scope.withdrawAmount = transactionDto.withdrawAmount;
                    $scope.withdrawAmountCurrencyType = transactionDto.withdrawAmountCurrencyType;
                    $scope.withdrawCashierCashBoxTotalTax = transactionDto.withdrawCashierCashBoxTotalTax;
                    $scope.withdrawCashierCashBoxTotalTaxCurrencyType = transactionDto.withdrawCashierCashBoxTotalTaxCurrencyType;
                    $scope.cashierTotalAmount = transactionDto.cashierTotalAmount;
                    $scope.cashierTotalAmountCurrencyType = transactionDto.cashierTotalAmountCurrencyType;
                    $scope.walletTotalPrice = transactionDto.walletTotalPrice;
                    $scope.walletTotalPriceCurrencyType = transactionDto.walletTotalPriceCurrencyType;


                    $scope.show_tax = false;
                    $scope.approve_trans = true;
                    $scope.cancel_trans = true;
                    $scope.hide_loader();
                    $scope.info_message = "The data of the transactions is set forth below";


                    console.log("withdrawCashierCashBoxTotalTaxCurrencyType",$scope.withdrawCashierCashBoxTotalTaxCurrencyType)

                }
                else {
                    $scope.hide_loader();
                    $rootScope.info_message = "please try later"
                }
            },function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
                console.log("error calback")
        })
    }
    else{

        angular.element("#amount").css("border", "1px solid red");
        $scope.show_tax = true;
        $scope.approve_trans = false;
        $scope.cancel_trans = false;
    }


    };

    $scope.withdrawMakePayment = function (item) {
        var path = 'withdraw-make-payment.htm';

        var data = {
            amount: $scope.amount_val,
            walletId: $rootScope.walletId,
            currencyType: $rootScope.curr_type,
            rationalDuration: $scope.durationId ? $scope.durationId : 1
        };

        $http({
            method: 'post',
            url: path,
            data: data,
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;
                if (result.responseStatus == 'SUCCESS') {
                    $scope.disable_buttons = true;
                    $scope.startClock();
                }
            });


    };
    $scope.cancel_transaction =  function () {

        $scope.set_null_tax();
        angular.forEach($scope.timeselect, function (value, key) {
            value.ticked = false
        });
        angular.forEach($scope.wRateValues, function (value, key) {
            value.ticked = false

        });
        $scope.timeselect[0].ticked =true;
        $scope.durationId = 1;
        if($scope.wRateValues){
            console.log("$scope.wRateValues",$scope.wRateValues)
            angular.forEach($scope.wRateValues, function (value, key) {
                if(value.name.indexOf("US Dollar ($)") > -1){
                    value.ticked = true
                }
            });
            $rootScope.curr_type = 152;


        }

        $scope.amount_val = "";
        $scope.approve_trans = false;
        $scope.show_tax = true;
        $scope.cancel_trans = false;
        $scope.info_message = "";
        $scope.clockSettings(300);



    };
    $scope.withdrawReject = function (item) {
        var path = 'withdraw-reject.htm';
        var id = item.currentTarget.getAttribute("data-id");
        var target = item.currentTarget.getAttribute("data-target");

        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;
                if (result.responseStatus == 'SUCCESS') {
                }
            })

        $('#detail').modal('show');
    };
    $scope.withdrawReject = function (item) {
        var path = 'withdraw-time-out.htm';
        var id = item.currentTarget.getAttribute("data-id");
        var target = item.currentTarget.getAttribute("data-target");

        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;
                if (result.responseStatus == 'SUCCESS') {
                }
            })

        $('#detail').modal('show');
    };


    $scope.depositStart = function (item) {
        var path = 'deposit-start.htm';
        var id = item.currentTarget.getAttribute("data-id");
        var target = item.currentTarget.getAttribute("data-target");

        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;
                if (result.responseStatus == 'SUCCESS') {
                }
            })

        $('#add').modal('show');
    };
    $scope.depositCheckTax = function (item) {
        var path = 'deposit-check-tax.htm';
        var id = item.currentTarget.getAttribute("data-id");
        var target = item.currentTarget.getAttribute("data-target");

        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;
                if (result.responseStatus == 'SUCCESS') {
                }
            })

        $('#detail').modal('show');
    };
    $scope.depositPreview = function (item) {
        var path = 'deposit-preview.htm';
        var id = item.currentTarget.getAttribute("data-id");
        var target = item.currentTarget.getAttribute("data-target");

        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;
                if (result.responseStatus == 'SUCCESS') {
                }
            })

        $('#detail').modal('show');
    };
    $scope.depositMakePayment = function (item) {
        var path = 'deposit-make-payment.htm';
        var id = item.currentTarget.getAttribute("data-id");
        var target = item.currentTarget.getAttribute("data-target");

        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;
                if (result.responseStatus == 'SUCCESS') {
                }
            })

        $('#detail').modal('show');
    };
    $scope.depositApprove = function (item) {
        var path = 'deposit-approve.htm';
        var id = item.currentTarget.getAttribute("data-id");
        var target = item.currentTarget.getAttribute("data-target");

        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;
                if (result.responseStatus == 'SUCCESS') {
                }
            })

        $('#detail').modal('show');
    };
    $scope.depositReject = function (item) {
        var path = 'deposit-reject.htm';
        var id = item.currentTarget.getAttribute("data-id");
        var target = item.currentTarget.getAttribute("data-target");

        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data.dto;
                if (result.responseStatus == 'SUCCESS') {
                }
            })

        $('#detail').modal('show');
    };


}]