controllers.notificationControllerView = ['$scope', '$rootScope', '$http', '$interval', '$window','$timeout', '$document', function ($scope, $rootScope, $http, $interval, $window,$timeout,$document) {
$scope.title_ = "Merchant";


    function browser_notification(count) {
        if (!("Notification" in window)) {
            alert("This browser does not support desktop notification");
        }
        else if (Notification.permission === "granted") {
            var notification = new Notification("Hello!",
                {
                    body: "you have new" +count+"transactions",
                    icon : context+"/img/general/logo.png"
                });
        }

        else if (Notification.permission !== 'denied') {
            Notification.requestPermission(function (permission) {
                if (permission === "granted") {
                    var notification = new Notification("Hello!",
                        {
                            body: "you have new" +count+"transactions",
                            icon : context+"/img/general/logo.png"
                        });
                }
            });
        }
        $timeout(function () {
            notification.close();
        },5000)

        $document[0].title = "(" +count+")"+$scope.title_ ;
    }




    
    if (typeof(Storage) !== "undefined") {
       $scope.storage_  = $window.localStorage;

        if($scope.storage_ .getItem('stor') == null){
            var  stor=[];
            $scope.storage_ .setItem('stor',stor);
            console.log("$scope.storage_ .getItem('stor')", $scope.storage_ .getItem('stor'));
        }


    }

    function ul_li_list_count(notification_count) {

        if(notification_count == 0){
            angular.element("#menu1").css("border", "none");
            $document[0].title = $scope.title_;
        }
        else {
            angular.element("#menu1").css("border", "1px solid #D9DEE4");
        }
    }


    $scope.clockSettings = function (time) {
        console.log("clockic barevner");
        var clock, transactionTime;
        if (time) {
            transactionTime = time
        }
        else {
            transactionTime = $scope.duration ? $scope.duration : 300;
        }

        clock = angular.element('.clock').FlipClock(transactionTime, {
            clockFace: 'SecondCounter',
            autoStart: false,
            countdown: true,
            language: $scope.current_language,
            callbacks: {
                stop: function () {
                    $timeout(function () {
                        $scope.info_message = 'The clock has stopped!'
                    }, 1000);
                }
            }
        });

        $scope.startClock = function () {
            clock.start();

        }
    };


    $scope.transaction_notification =function (timerId, refresh) {

        $http({
            method: 'post',
            url: "transaction-notification.htm",
            dataType: 'json'
        }).then(
            function (response) {

                if (response) {

                    var result = response.data.dto;
console.log("response wallet tansaction notification",response)
                    if (result.responseStatus == 'SUCCESS') {

                        var notification_array_data = result.response.data;

                        console.log("result notification", notification_array_data);

                        if (notification_array_data.length > 0) {

                            console.log("notification_array_data.length", notification_array_data.length);

                            var notify_finish_arr = [];

                            for(var i=0; i< notification_array_data.length; i++){
                                if($scope.storage_ .getItem('stor').split(',').indexOf(notification_array_data[i].withdrawId) == -1){
                                    notify_finish_arr.push(notification_array_data[i])
                                }

                                $scope["not_list_"+""+i+""]= true;// notification list show-hide
                            }

                            console.log("notify_finish_arr",notify_finish_arr);
                            console.log("notification_array_data",notification_array_data);

                            $scope.notific_array = notify_finish_arr;
                            if(notify_finish_arr.length > 0) {
                                browser_notification(notify_finish_arr.length);
                            }
                            $scope.notification_count = notify_finish_arr.length;
                            if(refresh) {
                                $scope.notification_count_refresh = notify_finish_arr.length;
                                if(notify_finish_arr.length > 0) {
                                    $scope.open_notified_transaction('', notify_finish_arr[0].withdrawId, 0)
                                }
                            }

                            $scope.img_url = $rootScope.IMAGE_BASE_URL + notification_array_data[0].img;
                            $rootScope.notification_curr_duration = notification_array_data[0].duration;

                            ul_li_list_count($scope.notification_count);

                            console.log("$rootScope.notification_curr_duration", $rootScope.notification_curr_duration);

                            if ($rootScope.notification_curr_duration > 0) {
                                $scope.clockSettings($rootScope.notification_curr_duration);
                                $scope.startClock();
                            }
                            else {
                                $scope.clockSettings($rootScope.notification_curr_duration * (-1));
                                $scope.info_message = "The transaction was expired";
                            }

                            console.log("$scope.notification_count", $scope.notification_count);
                        }

                        if(timerId){
                            $interval.cancel(timerId);// must removed
                        }


                        console.log("timerId timerId timerId timerId ",timerId)
                    }
                }
                else {
                    console.log(" http error")
                }
            });
    };

    $scope.transaction_notification_interval = function () {
        var timerId = $interval(function () {
            $scope.transaction_notification(timerId);
        },  3000);
    };


    $scope.transaction_notification_interval();

    $scope.open_notified_transaction = function (item, withdrawId, index) {

        $scope["not_list_"+""+index+""]=false;
        $scope.notification_count--;
        ul_li_list_count($scope.notification_count);

        var withdrawId = withdrawId ? parseInt(withdrawId) : null;

        if(!$scope.storage_ .getItem('stor').split(',')){
            var  stor=[];
        }
        else {
            var stor = $scope.storage_ .getItem('stor').split(',');
        }

        stor.push(withdrawId);

        $scope.storage_ .setItem('stor', stor);



        if (withdrawId != null) {

            var requestJson = {
                withdrawId: withdrawId
            };
            requestJson = JSON.stringify(requestJson);

            $http({
                method: 'post',
                url: "transaction-view-notification.htm",
                data: {
                    requestJson: requestJson
                },
                dataType: 'json'
            }).then(
                function (response) {

                    if (response) {
                        console.log("response", response);

                        var result = response.data.dto;

                        if (result.responseStatus == 'SUCCESS') {

                            var transactionDto = result.response.data;

                            console.log("result transactionDto", transactionDto);
                            $rootScope.notification_popup_info = {
                                walletToname: transactionDto.walletDto.name,
                                walletTosurname: transactionDto.walletDto.surname,
                                walletToemail: transactionDto.walletDto.email,
                                invoice: transactionDto.finalState,
                                order_id: transactionDto.orderCode,
                                payment_due: transactionDto.openedAt,
                                payment_due_closed: transactionDto.closedAt,
                                account: transactionDto.account,
                                withdrawAmountCurrencyType: transactionDto.withdrawAmountCurrencyType,
                                withdrawAmount: transactionDto.withdrawAmount,
                                withdrawCashierCashBoxTotalTax: transactionDto.withdrawCashierCashBoxTotalTax,
                                withdrawCashierCashBoxTotalTaxCurrencyType: transactionDto.withdrawCashierCashBoxTotalTaxCurrencyType,
                                cashierTotalAmount: transactionDto.cashierTotalAmount,
                                cashierTotalAmountCurrencyType: transactionDto.cashierTotalAmountCurrencyType,
                                walletTotalPrice: transactionDto.walletTotalPrice,
                                walletTotalPriceCurrencyType: transactionDto.walletTotalPriceCurrencyType
                            };

                            $scope.open_modal('lg', '', 'wallet_notification_view');
                        }
                    }
                    else {
                        console.log(" http error")
                    }
                });

        }
        else {
            console.log("you have not withdraw id")
        }
    };


    /*
     time select isteven
     ===================
     */

    $scope.timeselect = [];

    $scope.select_time = {
        nothingSelected: "Select transaction duration"
    };

    var description = "I18N_LOCALE_MESSAGES_" + $scope.current_language;
    description = $window[description];
    angular.forEach(TransactionRationalDuration, function (value, key) {

        $scope.timeselect.push({
            description: description[value.description],
            duration: value.duration,
            id: value.id,
            ticked: false
        })
    });

    $scope.timeselect[0].ticked = true;
    $scope.onTimeSelect = function () {
        $scope.duration = $scope.timeselectOut[0].duration;
        $scope.durationId = $scope.timeselectOut[0].id;
        $scope.clockSettings();
    };

    $scope.setCuerrencyType = function () {
        $rootScope.curr_type = $scope.currencyTypeSelected[0].id;
    };

    /*
     time select isteven end
     =======================
     */

}]