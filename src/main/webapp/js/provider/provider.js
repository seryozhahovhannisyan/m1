controllers.providerController = ['$scope', '$rootScope', '$http', '$filter', '$window', '$sce', '$timeout', function ($scope, $rootScope, $http, $filter, $window, $sce, $timeout) {

    /*Branch*/
    $scope.provideStart = function (item) {
        var branchIdes = [];
        $("input[name='branchId']:checked").each(function () {
            branchIdes.push($(this).attr('data-id'));
        });


        $rootScope.cdate = new Date();
        $rootScope.branchIdes = branchIdes;

        $scope.open_modal('lg', '', 'admin_company_provide_detail');


    };
    $scope.takeBack = function (item) {
        var branchIdes = [];
        $("input[name='branchId']:checked").each(function () {
            branchIdes.push($(this).attr('data-id'));
        });


        $rootScope.cdate = new Date();
        $rootScope.branchIdes = branchIdes;

        $scope.open_modal('lg', '', 'admin_company_take_back_detail');


    };
    $scope.provideApprove = function (item) {

        $("#provideApprove").remove();

        var provideApproveForm = document.createElement('FORM');
        provideApproveForm.id = 'provideApprove';
        provideApproveForm.name = 'provideApprove';
        provideApproveForm.method = 'POST';
        provideApproveForm.action = 'provide-branch.htm';

        var branchIdes = document.createElement('INPUT');
        branchIdes.type = 'HIDDEN';
        branchIdes.name = 'branchIdes';
        branchIdes.value = $rootScope.branchIdes;
        provideApproveForm.appendChild(branchIdes);

        var provideApproveAmount = document.createElement('INPUT');
        provideApproveAmount.type = 'HIDDEN';
        provideApproveAmount.name = 'amount';
        provideApproveAmount.value = $('#provideApproveAmount').val();
        provideApproveForm.appendChild(provideApproveAmount);
        //todo  handle from isteven
        var provideApproveCurrency = document.createElement('INPUT');
        provideApproveCurrency.type = 'HIDDEN';
        provideApproveCurrency.name = 'currencyType';
        provideApproveCurrency.value = 152;
        provideApproveForm.appendChild(provideApproveCurrency);


        document.body.appendChild(provideApproveForm);

        if(!confirm("Are you sure ?")){
            return false;
        } else {
            provideApproveForm.submit();
        }


    };
    $scope.takeBackApprove = function (item) {

        $("#takeBackApprove").remove();

        var takeBackApproveForm = document.createElement('FORM');
        takeBackApproveForm.id = 'takeBackApproveForm';
        takeBackApproveForm.name = 'takeBackApproveForm';
        takeBackApproveForm.method = 'POST';
        takeBackApproveForm.action = 'take-back-branch.htm';

        var branchIdes = document.createElement('INPUT');
        branchIdes.type = 'HIDDEN';
        branchIdes.name = 'branchIdes';
        branchIdes.value = $rootScope.branchIdes;
        takeBackApproveForm.appendChild(branchIdes);

        var takeBackApproveAmount = document.createElement('INPUT');
        takeBackApproveAmount.type = 'HIDDEN';
        takeBackApproveAmount.name = 'amount';
        takeBackApproveAmount.value = $('#takeBackApproveAmount').val();
        takeBackApproveForm.appendChild(takeBackApproveAmount);
        //todo  handle from isteven
        var takeBackApproveCurrency = document.createElement('INPUT');
        takeBackApproveCurrency.type = 'HIDDEN';
        takeBackApproveCurrency.name = 'currencyType';
        takeBackApproveCurrency.value = 152;
        takeBackApproveForm.appendChild(takeBackApproveCurrency);


        document.body.appendChild(takeBackApproveForm);

        if(!confirm("Are you sure ?")){
            return false;
        } else {
            takeBackApproveForm.submit();
        }


    };
    /*Cashier*/
    $scope.provideCashierStart = function (item) {
        var cashierIdes = [];
        $("input[name='cashierId']:checked").each(function () {
            cashierIdes.push($(this).attr('data-id'));
        });

        $rootScope.cdate = new Date();
        $rootScope.cashierIdes = cashierIdes;

        $scope.open_modal('lg', '', 'admin_company_provide_detail_cashier');

    };
    $scope.takeBackCashier = function (item) {
        var cashierIdes = [];
        $("input[name='cashierId']:checked").each(function () {
            cashierIdes.push($(this).attr('data-id'));
        });

        $rootScope.cdate = new Date();
        $rootScope.cashierIdes = cashierIdes;

        $scope.open_modal('lg', '', 'admin_company_take_back_detail_cashier');

    };
    $scope.provideCashierApprove = function (item) {

        $("#provideApprove").remove();

        var provideApproveForm = document.createElement('FORM');
        provideApproveForm.id = 'provideApprove';
        provideApproveForm.name = 'provideApprove';
        provideApproveForm.method = 'POST';
        provideApproveForm.action = 'provide-cashier.htm';

        var cashierIdes = document.createElement('INPUT');
        cashierIdes.type = 'HIDDEN';
        cashierIdes.name = 'cashierIdes';
        cashierIdes.value = $rootScope.cashierIdes;
        provideApproveForm.appendChild(cashierIdes);

        var provideApproveAmount = document.createElement('INPUT');
        provideApproveAmount.type = 'HIDDEN';
        provideApproveAmount.name = 'amount';
        provideApproveAmount.value = $('#provideApproveAmount').val();
        provideApproveForm.appendChild(provideApproveAmount);
        //todo  handle from isteven
        var provideApproveCurrency = document.createElement('INPUT');
        provideApproveCurrency.type = 'HIDDEN';
        provideApproveCurrency.name = 'currencyType';
        provideApproveCurrency.value = 152;
        provideApproveForm.appendChild(provideApproveCurrency);

        document.body.appendChild(provideApproveForm);

        if(!confirm("Are you sure ?")){
            return false;
        } else {
            provideApproveForm.submit();
        }

    };
    $scope.takeBackCashierApprove = function (item) {

        $("#takeBackApprove").remove();

        var takeBackApproveForm = document.createElement('FORM');
        takeBackApproveForm.id = 'takeBackApproveForm';
        takeBackApproveForm.name = 'takeBackApproveForm';
        takeBackApproveForm.method = 'POST';
        takeBackApproveForm.action = 'take-back-cashier.htm';

        var cashierIdes = document.createElement('INPUT');
        cashierIdes.type = 'HIDDEN';
        cashierIdes.name = 'cashierIdes';
        cashierIdes.value = $rootScope.cashierIdes;
        takeBackApproveForm.appendChild(cashierIdes);

        var takeBackApproveAmount = document.createElement('INPUT');
        takeBackApproveAmount.type = 'HIDDEN';
        takeBackApproveAmount.name = 'amount';
        takeBackApproveAmount.value = $('#takeBackApproveAmount').val();
        takeBackApproveForm.appendChild(takeBackApproveAmount);
        //todo  handle from isteven
        var takeBackApproveCurrency = document.createElement('INPUT');
        takeBackApproveCurrency.type = 'HIDDEN';
        takeBackApproveCurrency.name = 'currencyType';
        takeBackApproveCurrency.value = 152;
        takeBackApproveForm.appendChild(takeBackApproveCurrency);

        document.body.appendChild(takeBackApproveForm);

        if(!confirm("Are you sure ?")){
            return false;
        } else {
            takeBackApproveForm.submit();
        }

    };

    /*Company&Branch*/
    $scope.takeBackFromBranch = function (item) {
        var branchIdes = [];
        branchIdes.push(item.currentTarget.getAttribute("data-id"));

        $rootScope.cdate = new Date();
        $rootScope.branchIdes = branchIdes;

        $scope.open_modal('lg', '', 'admin_company_take_back_detail');

    };
    /*Branch&Cashier*/
    $scope.takeBackFromCashier = function (item) {
        var cashierIdes = [];
        cashierIdes.push(item.currentTarget.getAttribute("data-id"));

        $rootScope.cdate = new Date();
        $rootScope.cashierIdes = cashierIdes;

        $scope.open_modal('lg', '', 'admin_company_take_back_detail_cashier');

    };


}]