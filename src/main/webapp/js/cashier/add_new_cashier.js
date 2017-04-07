moduls_array.push('ngAnimate');
/*===================
 isteven multiselect start
 * ===================*/
controllers.branchIstevenCtrl = ['$scope', function ($scope) {


    $scope.select_country = {
        nothingSelected: "Select cuountry"
    };
    $scope.select_region = {
        nothingSelected: "Select  region "
    };
    $scope.currency_type = {
        nothingSelected: "Select currency type "
    };
    $scope.avail_currency_type = {
        nothingSelected: "Select available currency type "
    };


    $scope.roleCurrencyType = [];
    $scope.roleAllowedList = [];
    $scope.roleCurrencyTyRateValues = [];
    $scope.cashierPhoneCodes = [];

    angular.element(document).ready(function () {
        angular.element(".list_mult_region").find("button:nth-child(1)").attr("disabled", true).css("cursor", "no-drop")
    });

    angular.forEach(Country, function (value, key) {
      $scope.cashierPhoneCodes.push({
                code: '+' + value.phoneCode,
                icon: "<img src ='" + contextPath + "/img/general/icon/language/" + value.abr + ".png'/>",
                country: value.name,
                id: value.id,
                ticked: false
            }
        );

    });

    angular.forEach(CurrencyType, function (value, key) {
        $scope.roleCurrencyType.push({
            name: value.name,
            code: value.code,
            id: value.id,
            ticked: false
        })

    });

    if(roles!= undefined) {
        angular.forEach(roles, function (value, key) {
            $scope.roleAllowedList.push({
                name: value.name,
                id: value.id,
                description: value.description,
                transactionMax: value.transactionMax,
                transactionMin: value.transactionMin,
                ticked: false
            })

        });
    }

    $scope.roleAllowedList[0].ticked = true;
console.log("roles.length",roles.length)

    if(roles.length < 2 ){
        $scope.roleIstevenCount = false;
    }
    else {
        $scope.roleIstevenCount = true;
    }

    console.log("roleCurrencyType", $scope.roleCurrencyType);
    console.log("availableRate", availableRate);

    function allowRateCurrency() {
        if (availableRate == "") {
            availableRateEmpty = false
        }
        else {
            availableRateEmpty = true
        }
    }

    allowRateCurrency();
    for (var i = 0; i < availableRate.length; i++) {
        for (var j = 0; j < $scope.roleCurrencyType.length; j++) {
            if (parseInt(availableRate[i]) == $scope.roleCurrencyType[j].id) {
                $scope.roleCurrencyTyRateValues.push({
                    name: $scope.roleCurrencyType[j].name,
                    code: $scope.roleCurrencyType[j].code,
                    id: $scope.roleCurrencyType[j].id,
                    ticked: true
                })
            }
        }
    }



    $scope.roleAllowedListOut =[{
        id: $scope.roleAllowedList[0].id,
        name: $scope.roleAllowedList[0].name,
        description : $scope.roleAllowedList[0].description,
        transactionMax : $scope.roleAllowedList[0].transactionMax,
        transactionMin : $scope.roleAllowedList[0].transactionMin
    }];

    $scope.roleCurrencyTyRate = availableRate.join(",");

    $scope.curRole = function () {
        $scope.roleName = $scope.roleAllowedListOut[0].name;
        $scope.description = $scope.roleAllowedListOut[0].description;
        $scope.transactionMax = $scope.roleAllowedListOut[0].transactionMax;
        $scope.transactionMin = $scope.roleAllowedListOut[0].transactionMin;
        $scope.roleAllowedListback = $scope.roleAllowedListOut[0].id;
    };

    $scope.curRole();

    $scope.initIstevenSelects = function () {

        angular.forEach(Country, function (value, key) {
            if(value.id == companyCountryId) {

                $scope.cashierPhoneCodes[key].ticked = true;
            }
        })
    };

    $scope.initIstevenSelects();
    angular.forEach($scope.cashierPhoneCodes, function (value, key) {

                if(value.ticked == true){
            $scope.cashierPphoneCode = value.code;

        }

    });


    $scope.curType = function () {
        var rates = [];
        angular.forEach($scope.roleCurrencyTypeOut, function (value, key) {
            rates.push($scope.roleCurrencyTypeOut[key].id)
        });
        $scope.roleCurrencyTyRate = rates.join(",")


    };

    console.log("roelsssssssssssssss", roles);


    $scope.cashierPhoneCodeSet = function () {
        $scope.cashierPphoneCode = $scope.cashierPhoneCodesOut[0].code;
        console.log("$scope.phoneCodeCashier", $scope.cashierPphoneCode)
    };


    /*===================
     isteven multiselect end
     * ===================*/

}];


controllers.addBranchtCtrl = ['$scope', '$timeout', '$rootScope', function ($scope, $timeout, $rootScope) {

    $scope.section1 = true;
    $scope.section2 = false;
    $scope.section3 = false;
    $scope.section4 = false;


    $scope.inputs = {};



    $scope.cashier = {
        nameCashier: '',
        surnameCashier: '',
        emailCashier: '',
        phoneCashier: ''
    };

    $scope.verification = {
        passwordCashier: undefined,
        passwordCashierRepeat: undefined,
        verificationCode: undefined
    };


    $scope.percent = function (percent_count, percent_error) {

        if (parseInt(percent_count) > 100 || parseInt(percent_count) < 0) {
            $scope[percent_error] = true;
            console.log(percent_error, $scope[percent_error])
        }
        else {
            console.log(percent_error, $scope[percent_error]);
            $scope[percent_error] = false;
        }

    }


    $scope.uiSerfCashier = function () {
        // $scope.cashier.phoneCodeCashier = angular.element(".ist_mult_cashier_phone_code").attr("data-casheirPhoneCode");
        if ($scope.cashier.nameCashier != '' && $scope.cashier.surnameCashier != '' && $scope.cashier.emailCashier != '' && $scope.cashier.phoneCashier != '') {
            console.log("$scope.cashier from ui serf", $scope.cashier)
            $scope.section1 = false;
            $timeout(function () {
                $scope.section2 = true;
            }, 510)
            $scope.section3 = false;
            $scope.section4 = false;

        }
        else {
            if ($scope.cashier.nameCashier == '' || $scope.cashier.nameCashier === undefined) {
                $scope.cashier_name_show = true
            }
            else {
                $scope.cashier_name_show = false
            }
            if ($scope.cashier.surnameCashier == '' || $scope.cashier.surnameCashier === undefined) {
                $scope.cashier_surname_show = true
            }
            else {
                $scope.cashier_surname_show = false
            }
            if ($scope.cashier.emailCashier == '' || $scope.cashier.emailCashier === undefined) {
                $scope.cashier_e_mail_show = true
            }
            else {
                $scope.cashier_e_mail_show = false
            }
            if ($scope.cashier.phoneCashier == '' || $scope.cashier.phoneCashier === undefined) {
                $scope.cashier_phone_number_show = true
            }
            else {
                $scope.cashier_phone_number_show = false
            }

        }

        // $scope.serverSendFields();
        // angular.element(document).ready(function () {
        //     $timeout(function () {
        //         $scope.footerplace();
        //         console.log("poxec")
        //     }, 10);
        //
        // });
    };

    $scope.uiSerfRole = function () {
        $scope.section1 = false;
        $scope.section2 = false;
        $scope.section3 = false;
        $timeout(function () {
            $scope.section4 = true;
        }, 510)


        // $scope.serverSendFields();
        // angular.element(document).ready(function () {
        //     $timeout(function () {
        //         $scope.footerplace();
        //
        //     }, 10);
        //
        // });
    };

    $scope.captchaValid = function () {
        var response = grecaptcha.getResponse();
        if (response.length == 0) {
            $scope.captcha_invalid = true;
            return false;
        }
        else {
            $scope.captcha_invalid = false;
            return true
        }
    }

    $scope.renderRecaptcha = function () {
        grecaptcha.render('recaptcha', {
            'sitekey': captchaSiteKey,
            'theme': 'dark',
        });
    };

    $scope.verificat_fields = function () {
        var regexp = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#\$%\^\&*\)\(+=._-]{6,}$/;
        console.log("ankap")
        if (regexp.test($scope.verification.passwordCashier)) {
            if ($scope.verification.passwordCashier !== undefined && $scope.verification.passwordCashierRepeat !== undefined && $scope.verification.verificationCode !== undefined) {

                if ($scope.verification.passwordCashier === $scope.verification.passwordCashierRepeat) {
                    $scope.password_match = false;
                    $scope.show_form = true;
                    console.log("mtav show form")
                }
                else {
                    $scope.password_match = true
                }
            }

        }
        else {
            $scope.show_form = false;
        }
        // $scope.serverSendFields();
    }

    $scope.submity = function (form) {

        if ($scope.captchaValid()) {
            form.$submitted = true;
        }
        else {
            event.preventDefault();
            form.$submitted = false;
        }

    };


    $scope.backCashier = function () {
        $timeout(function () {
        $scope.section1 = true;
        }, 510)
        $scope.section2 = false;
        $scope.section3 = false;
        $scope.section4 = false;

    };

    $scope.backCashierUpload = function () {
        $scope.section1 = false;
        $timeout(function () {
            $scope.section2 = true;
        }, 510)
        $scope.section3 = false;
        $scope.section4 = false;



    };

    $scope.toRole = function () {
        $scope.section1 = false;
        $scope.section2 = false;
        $timeout(function () {
            $scope.section3 = true;
        }, 510)
        $scope.section4 = false;


    };

    // we will store all of our form data in this object
    $scope.companyInfoFormData = {
        company: $scope.company,
        branch: $scope.branch,
        cashier: $scope.cashier,
        verification: $scope.verification
    };


    // $scope.serverSendFields = function () {
    //     angular.forEach($scope.companyInfoFormData, function (val, key) {
    //         angular.forEach(val, function (value, key) {
    //             console.log("val",val,"key",key)
    //             $scope.inputs[key] = value
    //         })
    //
    //     });
    //
    //     console.log("$scope.inputs", $scope.inputs)
    // };


}];

mainApp.directive('fileModel', ['$parse', '$http', '$timeout', function ($parse, $http, $timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {

            console.log("element[0].files", element[0]);

            scope.drag_true = true;
            scope.remove_true = false;
            scope.upload_success = false;
            scope.upload_remove_succsses = false;
            scope.upload_error = false;

            angular.element(".remove").bind('click', function () {
                $timeout(function () {
                    scope.drag_true = true;
                    scope.remove_true = false;
                    console.log(scope.remove_true, scope.drag_true);
                    var file = scope.fileInfo;
                    var formDatas = new FormData();

                    // formDatas.append('resource', 'company');
                    formDatas.append('removeFileName', file.name);
                    $http.post("remove-mixed-uploaded.htm", formDatas, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    })

                        .success(function () {
                            console.log("exav");
                            scope[attrs.imgUrl] = "";
                            scope.upload_success = false;
                            scope.upload_remove_succsses = true;
                            scope.upload_error = false;

                            console.log(scope.fileInfo.name)
                        })
                        .error(function () {
                        });

                }, 10);
            });


            element.bind('change', function () {
                console.log("attrs.file", attrs.file);
                $parse(attrs.fileModel).assign(scope, element[0].files[0]);
                console.log("scope filic", scope);
                scope.$apply();
                var file = scope.fileInfo;
                scope[attrs.scopeName]=scope.fileInfo
                console.log('file is ');
                console.log(file);
                var uploadUrl = "upload-mixed.htm";
                var formDatas = new FormData();
                console.log("formDatas", formDatas);
                console.log("file file.name", file.name);
                formDatas.append('datas', file, file.name);
                formDatas.append('resource', attrs.file);
                console.log("form data", formDatas);

                $http.post(uploadUrl, formDatas, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })

                    .success(function () {
                        scope.drag_true = false;
                        scope.remove_true = true;
                        scope[attrs.imgUrl] = "view-mixed-data?resource=" + attrs.file + "&datasFileName=" + file.name;
                        scope.upload_success = true;
                        scope.upload_remove_succsses = false;

                    })
                    .error(function () {
                        scope.upload_error = true;
                    });

            });
        }
    };
}]);