controllers.providerController = ['$scope', '$rootScope', '$http', '$filter', '$window', '$sce', '$timeout', function ($scope, $rootScope, $http, $filter, $window, $sce, $timeout) {

    /*Wallet*/
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

        var provideApproveAmount = document.createElement('INPUT');
        provideApproveAmount.type = 'HIDDEN';
        provideApproveAmount.name = 'amount';
        provideApproveAmount.value = $('#takeBackApproveAmount').val();
        takeBackApproveForm.appendChild(provideApproveAmount);
        //todo  handle from isteven
        var provideApproveCurrency = document.createElement('INPUT');
        provideApproveCurrency.type = 'HIDDEN';
        provideApproveCurrency.name = 'currencyType';
        provideApproveCurrency.value = 152;
        takeBackApproveForm.appendChild(provideApproveCurrency);


        document.body.appendChild(takeBackApproveForm);

        if(!confirm("Are you sure ?")){
            return false;
        } else {
            takeBackApproveForm.submit();
        }


    };


}]