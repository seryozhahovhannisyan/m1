controllers.walletControllerView = ['transaction_info', '$scope', '$rootScope', '$http', '$filter', '$window', '$sce', 'ngTableParams', '$timeout', '$interval', function (transaction_info, $scope, $rootScope, $http, $filter, $window, $sce, ngTableParams, $timeout, $interval) {
    $scope.clockSettings = function (time, _countdown) {

        var clock, transactionTime;
        if (time) {
            transactionTime = time
        }
        else {
            transactionTime = $scope.duration ? $scope.duration : 300;
        }

        var countdown = _countdown == false ? _countdown : true;
        console.log("$rootScope.time", $rootScope.time);
        clock = angular.element('.clock').FlipClock(transactionTime, {
            clockFace: 'SecondCounter',
            autoStart: false,
            countdown: countdown,
            language: $scope.current_language,
            callbacks: {
                stop: function () {
                    $timeout(function () {
                        $scope.info_message = 'The transaction was expired !'
                    }, 1000);

                    if ($rootScope.time == 0) {
                        var time_ = $rootScope.time;
                    }
                    else {
                        var time_ = $rootScope.time;
                    }

                    console.log("$rootScope.time", $rootScope.time, "and time_", time_);

                    $scope.clockSettings(time_, false);

                    $scope.startClock();
                }
            }
        });

        $scope.startClock = function () {
            clock.start();
        }
    };


    $scope.set_transactions_value = function () {
        $scope.invoice = transaction_info.invoice;
        $scope.order_id = transaction_info.order_id;
        $scope.payment_due_closed = transaction_info.payment_due_closed;
        $scope.payment_due = transaction_info.payment_due;
        $scope.account = transaction_info.account;
        $scope.withdrawAmount = transaction_info.withdrawAmount;
        $scope.withdrawAmountCurrencyType = transaction_info.withdrawAmountCurrencyType;
        $scope.withdrawCashierCashBoxTotalTax = transaction_info.withdrawCashierCashBoxTotalTax;
        $scope.withdrawCashierCashBoxTotalTaxCurrencyType = transaction_info.withdrawCashierCashBoxTotalTaxCurrencyType;
        $scope.cashierTotalAmount = transaction_info.cashierTotalAmount;
        $scope.cashierTotalAmountCurrencyType = transaction_info.cashierTotalAmountCurrencyType;
        $scope.walletTotalPrice = transaction_info.walletTotalPrice;
        $scope.walletTotalPriceCurrencyType = transaction_info.walletTotalPriceCurrencyType;
        $scope.walletToname = transaction_info.walletToname;
        $scope.walletTosurname = transaction_info.walletTosurname;
        $scope.walletToemail = transaction_info.walletToemail;
        $scope.walletId = transaction_info.walletId;

        console.log("$scope.invoice", $scope.invoice);

        angular.element(".wallet_logo").css("background-image", "url("+transaction_info.img_url+")");

        // $scope.clockSettings((transaction_info.durationCurrent*(-1)));

    };

    $scope.set_transactions_value();

    function img_src  (item) {
        var img_src = angular.element(item.currentTarget).parent("td").parent("tr").find("td:nth-child(1)").find("img").attr("src");
        return img_src
    }


    function hours(second, minus) {

        console.log("second", second, "minus", minus, $rootScope.time[0], $rootScope.time[1]);
        var hours = Math.floor(second / 3600);
        var minutes = Math.floor((second % 3600) / 60);
        var seconds = Math.floor(second % 60);

        if (minus != undefined) {
            return "-" + hours + "h" + ":" + minutes + "m" + ":" + seconds + "s";
        }
        else {
            return hours + "h" + ":" + minutes + "m" + ":" + seconds + "s";
        }

    }


    $scope.cur_dur = [];
    $scope.row_clases = [];



    function timer_on(index) {
        var count_minutes = $interval(function () {

            if ($rootScope.time[index] > 0) {

                $rootScope.time[index]--;
                $scope.cur_dur[index] = hours($rootScope.time[index]);
                var tr_color = [];
                tr_color[0] = true;
                tr_color[1] = false;
                $scope.row_clases.push(tr_color);
            }
            else {
                $rootScope.time[index]--;
                var miuns_time = $rootScope.time[index] * (-1);
                $scope.cur_dur[index] = hours(miuns_time, "-");
                var tr_color = [];
                tr_color[0] = false;
                tr_color[1] = true;
                $scope.row_clases.push(tr_color);
            }
        }, 1000)
    }


    function count_min(pending_transaction_dto) {

        $rootScope.time = [];

        for (var i = 0; i < pending_transaction_dto.length; i++) {

            $rootScope.time.push(pending_transaction_dto[i].duration);

            // $rootScope.time[i]= 80;


            if ($rootScope.time[i] < 3601 && $rootScope.time[i] > -3601) {

                timer_on(i)
            }
            else {

                if ($rootScope.time[i] > 0) {
                    $scope.cur_dur[i] = hours($rootScope.time[i]);
                    var tr_color = [];
                    tr_color[0] = true;
                    tr_color[1] = false;
                    $scope.row_clases.push(tr_color);
                }
                else {
                    $scope.cur_dur[i] = hours($rootScope.time[i] * (-1), "-");
                    var tr_color = [];
                    tr_color[0] = false;
                    tr_color[1] = true;
                    $scope.row_clases.push(tr_color);
                }
            }

        }
    }

    function dur_timer() {
        var get_pending = $interval(function () {
            if ($rootScope.tabelDatas != undefined) {
                $interval.cancel(get_pending);
                var pending_transaction_dto = $rootScope.tabelDatas.dto.response.data;
                count_min(pending_transaction_dto)
            }
        }, 10);
    }

    function modal_timer() {
var index = $rootScope.time_index;
            if ($rootScope.time[index] < 0) {
                var time = $rootScope.time[index] * (-1);
                $scope.clockSettings(time, false);
                $scope.startClock();
                $rootScope.info_message = "The transaction was expired !"
            }
            else {
                $scope.clockSettings($rootScope.time[index] - 1);
                $scope.startClock();
    }
    }

    console.log("$rootScope.clock_start $rootScope.clock_start $rootScope.clock_start ", $rootScope.clock_start);
    function choose_function() {

        if ($rootScope.clock_start) {
            dur_timer();
            $rootScope.clock_start = false;
            console.log("dur timiccccccccccccc")
        }
        else {
            modal_timer();
            console.log("bacvev modal@@@@@@@@@@")
        }
    }

    choose_function();

    /*Wallet*/
    $scope.withdrawView = function (item, index) {
$rootScope.time_index = index;
        var allDatas = item.currentTarget.getAttribute("data-allData");
        var currentPendingDatas = angular.fromJson(allDatas);
        console.log("currentPendingDatas", currentPendingDatas[index]);

        transaction_info.invoice = currentPendingDatas[index].finalState;
        transaction_info.order_id = currentPendingDatas[index].orderCode;
        transaction_info.payment_due_closed = currentPendingDatas[index].closedAt;
        transaction_info.payment_due = currentPendingDatas[index].openedAt;
        transaction_info.account = currentPendingDatas[index].account;
        transaction_info.withdrawAmount = currentPendingDatas[index].withdrawAmount;
        transaction_info.withdrawAmountCurrencyType = currentPendingDatas[index].withdrawAmountCurrencyType;
        transaction_info.withdrawCashierCashBoxTotalTax = currentPendingDatas[index].withdrawCashierCashBoxTotalTax;
        transaction_info.withdrawCashierCashBoxTotalTaxCurrencyType = currentPendingDatas[index].withdrawCashierCashBoxTotalTaxCurrencyType;
        transaction_info.cashierTotalAmount = currentPendingDatas[index].cashierTotalAmount;
        transaction_info.cashierTotalAmountCurrencyType = currentPendingDatas[index].cashierTotalAmountCurrencyType;
        transaction_info.walletTotalPrice = currentPendingDatas[index].walletTotalPrice;
        transaction_info.walletTotalPriceCurrencyType = currentPendingDatas[index].walletTotalPriceCurrencyType;
        transaction_info.walletToname = currentPendingDatas[index].walletDto.name;
        transaction_info.walletTosurname = currentPendingDatas[index].walletDto.surname;
        transaction_info.walletToemail = currentPendingDatas[index].walletDto.email;
        // transaction_info.durationCurrent = currentPendingDatas[index].duration;
        transaction_info.walletId = currentPendingDatas[index].walletDto.id;

        transaction_info.img_url = img_src(item);

        if(currentPendingDatas[index].withdrawId != null){
            $rootScope.withdrawMethod = true;
            $rootScope.depositMethod = false;
        }
        else {
            $rootScope.withdrawMethod = false;
            $rootScope.depositMethod = true;
        }

        $scope.open_modal('lg', '', 'wallet_detail_view');

         // angular.element(".wallet_logo").css("background-image", "url("+img_src(item)+")");

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


    $scope.transaction_timeout = function (url) {
        console.log("$scope.walletId", $scope.walletId, "$scope.order_id", $scope.order_id);
        var path = url;

        var data = {
            walletId: $scope.walletId,
            orderCode: $scope.order_id
        };

        $http({
            method: 'post',
            url: path,
            data: data,
            dataType: 'json'
        }).then(
            function (response) {
                console.log("response", response);
            });
    };

}];