generalModuls_array.push('ngAnimate');
generalModuls_array.push('ui.router');
generalModuls_array.push('isteven-multi-select');


merchantApp.config(['$httpProvider', '$stateProvider', '$urlRouterProvider', function ($httpProvider, $stateProvider, $urlRouterProvider) {
    $stateProvider
    // route to show our basic form (/form)
        .state('form', {
            url: '/form',
            templateUrl: 'form',
            controller: 'requestCtrl'
        })

        // nested states
        // each of these sections will have their own view
        // url will be nested (/form/company)
        .state('form.companyInfo', {
            url: '/companyInfo',
            templateUrl: 'companyInfo'
        })

        .state('form.company', {
            url: '/company',
            templateUrl: 'company'
        })
        .state('form.companyUpload', {
            url: '/company/upload',
            templateUrl: 'companyUpload'
        })
        .state('form.branch', {
            url: '/branch',
            templateUrl: 'branch'
        })
        .state('form.branchUpload', {
            url: '/branch/upload',
            templateUrl: 'branchUpload'
        })

        .state('form.cashier', {
            url: '/cashier',
            templateUrl: 'cashier'
        })
        .state('form.cashierUpload', {
            url: '/cashier/upload',
            templateUrl: 'cashierUpload'
        })

        .state('form.setup', {
            url: '/setup',
            templateUrl: 'setup'
        })

        .state('form.role', {
            url: '/role',
            templateUrl: 'role'
        })
        .state('form.verification', {
            url: '/verification',
            templateUrl: 'verification'
        });

    // catch all route
    // send users to the form page
    $urlRouterProvider.otherwise('/form/companyInfo');


}]);


//  controller for the form
// =============================================================================

generalControllers.companyReqCtrl = ['$scope', '$window', '$document', function ($scope, $window, $document) {
    $scope.footerplace = function () {

        $scope.doc_height = Math.max(
            $document[0].body.scrollHeight, $document[0].documentElement.scrollHeight,
            $document[0].body.offsetHeight, $document[0].documentElement.offsetHeight,
            $document[0].body.clientHeight, $document[0].documentElement.clientHeight
        );
        $scope.win_height = $window.innerHeight;
        $scope.form = angular.element('.form_div_conteiner  ');
        $scope.footer = angular.element('.main_footer');
        $scope.content = angular.element('.content');
        $scope.footer_height = $scope.footer.outerHeight(true);
        $scope.content_height = $scope.content.outerHeight(true);

        // $scope.content_height = $scope.win_height - $scope.footer_height;

        console.log("$scope.win_height", $scope.win_height, " $scope.content_height", $scope.content_height);

        if ($scope.win_height < $scope.content_height + $scope.footer_height) {
            $scope.footer.removeClass("fixed");
            $scope.content.css("margin-bottom", "20px")


        }
        else {
            // $scope.content.css("height", $scope.content_height);
            $scope.footer.addClass("fixed");
        }

        angular.element(document).ready(function () {

            if ($scope.form.outerHeight(true) < 500) {
                $scope.form.css("margin-top", "100px")
            }
            else {
                $scope.form.css("margin-top", "10px")
            }
        });

    };
    angular.element(document).ready(function () {
        $scope.footerplace();
    });
}];

/*===================
 isteven multiselect start
 * ===================*/


generalControllers.istevenCtrl = ['$scope', function ($scope) {


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

    $scope.companyPolicyyCodes = [];
    $scope.companyCountryArray = [];
    $scope.companyRegionArray = [];
    $scope.roleCurrencyType = [];
    $scope.roleCurrencyTyRateValues = [];
    // $scope.availableCurrencyType = [];
    $scope.branchPhoneCodes = [];
    $scope.branchPolicyCodes = [];
    $scope.branchRegionArray = [];
    $scope.cashierPhoneCodes = [];

    angular.element(document).ready(function () {
        angular.element(".list_mult_region").find("button:nth-child(1)").attr("disabled", true).css("cursor", "no-drop")
    });
    angular.forEach(Country, function (value, key) {


        $scope.companyPolicyyCodes.push({
                code: '+' + value.phoneCode,
                icon: "<img src ='" + context + "/img/general/icon/language/" + value.abr + ".png'/>",
                country: value.name,
                id: value.id,
                ticked: false
            }
        );
        $scope.companyCountryArray.push({
                name: value.name,
                icon: "<img src ='" + context + "/img/general/icon/language/" + value.abr + ".png'/>",
                id: value.id,
                ticked: false
            }
        );
        $scope.branchPhoneCodes.push({
                code: '+' + value.phoneCode,
                icon: "<img src ='" + context + "/img/general/icon/language/" + value.abr + ".png'/>",
                country: value.name,
                id: value.id,
                ticked: false
            }
        );
        $scope.branchPolicyCodes.push({
                code: '+' + value.phoneCode,
                icon: "<img src ='" + context + "/img/general/icon/language/" + value.abr + ".png'/>",
                country: value.name,
                id: value.id,
                ticked: false
            }
        );
        $scope.cashierPhoneCodes.push({
                code: '+' + value.phoneCode,
                icon: "<img src ='" + context + "/img/general/icon/language/" + value.abr + ".png'/>",
                country: value.name,
                id: value.id,
                ticked: false
            }
        );

        $scope.companyPolicyyCodes[0].ticked = true;
        $scope.branchPhoneCodes[0].ticked = true;
        $scope.branchPolicyCodes[0].ticked = true;
        $scope.cashierPhoneCodes[0].ticked = true;

    });

    angular.forEach(CurrencyType, function (value, key) {
        $scope.roleCurrencyType.push({
            name: value.name,
            code: value.code,
            id: value.id,
            ticked: false
        })

    });

    console.log("roleCurrencyType",$scope.roleCurrencyType);
    console.log("availableRate",availableRate)

    for(var i = 0; i < availableRate.length; i++ ){
        for(var j = 0; j < $scope.roleCurrencyType.length; j++) {
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

    $scope.companyPolicyPhoneOut = [{
        code: '+' + USA.phoneCode,
        icon: "<img src ='" + context + "/img/general/icon/language/" + USA.abr + ".png'/>",
        country: USA.name
    }
    ];
    $scope.branchPhoneCodesOut = [{
        code: '+' + USA.phoneCode,
        icon: "<img src ='" + context + "/img/general/icon/language/" + USA.abr + ".png'/>",
        country: USA.name
    }
    ];
    $scope.branchPolicyCodesOut = [{
        code: '+' + USA.phoneCode,
        icon: "<img src ='" + context + "/img/general/icon/language/" + USA.abr + ".png'/>",
        country: USA.name
    }
    ];
    $scope.cashierPhoneCodesOut = [{
        code: '+' + USA.phoneCode,
        icon: "<img src ='" + context + "/img/general/icon/language/" + USA.abr + ".png'/>",
        country: USA.name
    }
    ];



    $scope.companyPolicyCode = $scope.companyPolicyPhoneOut[0].code;
    $scope.companyCountry = '';
    $scope.companyRegion = '';
    $scope.roleCurrencyTyRate = availableRate.join(",");
    $scope.branch_region = '';
    $scope.branchPhoneCode = $scope.branchPhoneCodesOut[0].code;
    $scope.branchPolicyCode = $scope.branchPhoneCodesOut[0].code;
    $scope.cashierPphoneCode = $scope.cashierPhoneCodesOut[0].code;


    $scope.compPolicCode = function () {
        $scope.companyPolicyCode = $scope.companyPolicyPhoneOut[0].code;
    };
    $scope.compCountry = function () {

        angular.forEach(CountryRegion, function (value, key) {

            if (value.countryId == $scope.companyCountryOut[0].id) {
                $scope.companyRegionArray.push({
                        name: value.name,
                        countryId: value.countryId,
                        id: value.id,
                        ticked: false
                    }
                );
                $scope.branchRegionArray.push({
                    name: value.name,
                    countryId: value.countryId,
                    id: value.id,
                    ticked: false
                })
            }
        });


        angular.element(".list_mult_region").find("button:nth-child(1)").attr("disabled", false).css("cursor", "pointer");
        console.log("$scope.companyCountryOut", $scope.companyCountryOut);
        $scope.companyCountry = $scope.companyCountryOut[0].id;
    };
    $scope.compRegion = function () {
        $scope.companyRegion = $scope.companyRegionOut[0].id;
    };
    $scope.curType = function () {
        var rates = [];
        angular.forEach($scope.roleCurrencyTypeOut, function (value, key) {
            rates.push($scope.roleCurrencyTypeOut[key].id)
        });
            $scope.roleCurrencyTyRate = rates.join(",")


    };

    $scope.branchPhoneCodeSet = function () {
        $scope.branchPhoneCode = $scope.branchPhoneCodesOut[0].code;

    };
    $scope.branchPolicyCodeSet = function () {
        $scope.branchPolicyCode = $scope.branchPolicyCodesOut[0].code;

    };
    $scope.branchRegionSet = function () {
        $scope.branchRegion = $scope.branchRegionOut[0].id;
    };
    $scope.cashierPhoneCodeSet = function () {
        $scope.cashierPphoneCode = $scope.cashierPhoneCodesOut[0].code;
        console.log("$scope.phoneCodeCashier", $scope.cashierPphoneCode)
    };


    /*===================
     isteven multiselect end
     * ===================*/

}];


generalControllers.requestCtrl = ['$scope', '$timeout', '$state', '$rootScope', function ($scope, $timeout, $state, $rootScope) {


    $rootScope.$on('$locationChangeSuccess',
        function () {
            angular.element(document).ready(function () {
                $timeout(function () {
                    $scope.footerplace();
                    console.log("poxec")
                }, 1000);

            });
            $.getScript("https://www.google.com/recaptcha/api.js")
        });


    $scope.inputs = {};
    $scope.company = {
        cityCompany: '',
        streetCompany: '',
        zipCompany: '',
        policyPhoneCompany: '',
        countryCompany: '',
        countryRegionCompany: ''
    };


    $scope.branch = {
        nameBranch: '',
        addressBranch: '',
        cityBranch: '',
        streetBranch: '',
        zipBranch: '',
        emailBranch: '',
        phoneBranch: '',
        policyPhoneBranch: ''


    };
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


    $scope.uiSerfCompanyUplod = function () {
        $scope.company.policyPhoneCodeCompany = angular.element(".ist_mult_comp_policiy_phone").attr("data-companyPolicyCode");
        $scope.company.countryCompany = angular.element(".ist_mult_comp").attr("data-country");
        $scope.company.countryRegionCompany = angular.element(".list_mult_region").attr("data-region");

        if (  $scope.company.cityCompany != '' && $scope.company.streetCompany != '' && $scope.company.zipCompany != '' &&  $scope.company.policyPhoneCompany != '' && $scope.company.countryCompany != '' && $scope.company.countryRegionCompany != '') {
            $state.go('form.companyUpload');

        }
        else {

            if ($scope.company.cityCompany == '' || $scope.company.cityCompany === undefined) {
                $scope.company_city_show = true
            }
            else {
                $scope.company_city_show = false
            }
            if ($scope.company.streetCompany == '' || $scope.company.streetCompany === undefined) {
                $scope.company_street_show = true
            }
            else {
                $scope.company_street_show = false
            }
            if ($scope.company.zipCompany == '' || $scope.company.zipCompany === undefined) {
                $scope.company_zip_code_show = true
            }
            else {
                $scope.company_zip_code_show = false
            }


            if ($scope.company.policyPhoneCompany == '' || $scope.company.policyPhoneCompany === undefined) {
                $scope.company_policy_phone_number_show = true
            }
            else {
                $scope.company_policy_phone_number_show = false
            }
            if ($scope.company.countryCompany == '' || $scope.company.countryCompany === undefined) {
                $scope.company_country_show = true
            }
            else {
                $scope.company_country_show = false

            }
            if ($scope.company.countryRegionCompany == '' || $scope.company.countryRegionCompany === undefined) {
                $scope.company_state_show = true
            }
            else {
                $scope.company_state_show = false

            }

        }
        // $scope.serverSendFields();
        angular.element(document).ready(function () {
            $timeout(function () {
                $scope.footerplace();
                console.log("poxec")
            }, 10);

        });

    };

    $scope.percent = function (percent_count,percent_error) {

                if(parseInt(percent_count) > 100 || parseInt(percent_count) < 0){
            $scope[percent_error] = true;
            console.log(percent_error,$scope[percent_error])
        }
        else{
            console.log(percent_error,$scope[percent_error]);
            $scope[percent_error] = false;
        }

    }

    $scope.uiSerfSetup = function () {
        $state.go('form.branch');


        angular.element(document).ready(function () {
            $timeout(function () {
                $scope.footerplace();
                console.log("poxec")
            }, 10);

        });
    };
    $scope.uiSerfBranch = function () {
        $scope.branch.countryRegionBranch = angular.element(".list_mult_regiont_branch").attr("data-branchRegions");
        $scope.branch.phoneCodeBranch = angular.element(".ist_mult_branch_phone_code").attr("data-branchPhoneCode");
        $scope.branch.policyPhoneCodeBranch = angular.element(".ist_mult_branch_policy_code").attr("data-branchPolicyCode");
        if ($scope.branch.nameBranch != '' && $scope.branch.addressBranch != '' && $scope.branch.cityBranch != '' && $scope.branch.streetBranch != '' && $scope.branch.zipBranch != '' && $scope.branch.emailBranch != '' && $scope.branch.phoneBranch != '' && $scope.branch.policyPhoneBranch != '' && $scope.branch.countryRegionBranch != '') {
            $state.go('form.branchUpload');
        }
        else {
            if ($scope.branch.nameBranch == '' || $scope.branch.nameBranch === undefined) {
                $scope.branch_name_show = true
            }
            else {
                $scope.branch_name_show = false
            }
            if ($scope.branch.addressBranch == '' || $scope.branch.addressBranch === undefined) {
                $scope.branch_address_show = true
            }
            else {
                $scope.branch_address_show = false
            }
            if ($scope.branch.cityBranch == '' || $scope.branch.cityBranch === undefined) {
                $scope.branch_city_show = true
            }
            else {
                $scope.branch_city_show = false
            }
            if ($scope.branch.streetBranch == '' || $scope.branch.streetBranch === undefined) {
                $scope.branch_street_show = true
            }
            else {
                $scope.branch_street_show = false
            }
            if ($scope.branch.zipBranch == '' || $scope.branch.zipBranch === undefined) {
                $scope.branch_zip_code_show = true
            }
            else {
                $scope.branch_zip_code_show = false
            }
            if ($scope.branch.emailBranch == '' || $scope.branch.emailBranch === undefined) {
                $scope.branch_e_mail_show = true
            }
            else {
                $scope.branch_e_mail_show = false
            }
            if ($scope.branch.phoneBranch == '' || $scope.branch.phoneBranch === undefined) {
                $scope.branch_phone_number_show = true
            }
            else {
                $scope.branch_phone_number_show = false
            }
            if ($scope.branch.policyPhoneBranch == '' || $scope.branch.policyPhoneBranch === undefined) {
                $scope.branch_policy_phone_number_show = true
            }
            else {
                $scope.branch_policy_phone_number_show = false
            }
            if ($scope.branch.countryRegionBranch == '' || $scope.branch.countryRegionBranch === undefined) {
                $scope.branch_region_show = true
            }
            else {
                $scope.branch_country_show = false
            }
        }

        // $scope.serverSendFields();

        angular.element(document).ready(function () {
            $timeout(function () {
                $scope.footerplace();
                console.log("poxec")
            }, 10);

        });
    };
    $scope.uiSerfCashier = function () {
        $scope.cashier.phoneCodeCashier = angular.element(".ist_mult_cashier_phone_code").attr("data-casheirPhoneCode");
        if ($scope.cashier.nameCashier != '' && $scope.cashier.surnameCashier != '' && $scope.cashier.emailCashier != '' && $scope.cashier.phoneCashier != '') {
            $state.go('form.cashierUpload');
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
        angular.element(document).ready(function () {
            $timeout(function () {
                $scope.footerplace();
                console.log("poxec")
            }, 10);

        });
    };
    $scope.uiSerfRole = function () {

        $state.go('form.verification');

        // $scope.serverSendFields();
        angular.element(document).ready(function () {
            $timeout(function () {
                $scope.footerplace();

            }, 10);

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

    $scope.submit = function (form) {
        // form.$submitted = false;

        console.log(form);
        // if (form.$invalid) {
        //     event.preventDefault();
        //     form.$submitted = true;
        //     return
        // }

    };


    // we will store all of our form data in this object
    $scope.companyInfoFormData = {
        company: $scope.company,
        branch: $scope.branch,
        cashier: $scope.cashier,
        verification: $scope.verification
    };


    $scope.serverSendFields = function () {
        angular.forEach($scope.companyInfoFormData, function (val, key) {
            angular.forEach(val, function (value, key) {
                console.log("val",val,"key",key)
                $scope.inputs[key] = value
            })

        });

        console.log("$scope.inputs", $scope.inputs)
    };




}];

merchantApp.directive('fileModel', ['$parse', '$http','$timeout', function ($parse, $http,$timeout) {
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
                    console.log( scope.remove_true, scope.drag_true);
                    var file = scope.fileInfo;
                    var formDatas = new FormData();

                    // formDatas.append('resource', 'company');
                    formDatas.append('removeFileName',  file.name);
                    $http.post("remove-mixed-uploaded.htm", formDatas, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    })

                        .success(function () {
                            console.log("exav");
                            scope.up_img = "";
                            scope.upload_success = false;
                            scope.upload_remove_succsses = true;
                            scope.upload_error = false;

                            console.log(scope.fileInfo.name)
                        })
                        .error(function () {
                        });

                },10);
            });




            element.bind('change', function () {
                console.log("attrs",attrs.file)
                $parse(attrs.fileModel).assign(scope, element[0].files[0]);
                scope.$apply();
                scope.up_img;
                var file = scope.fileInfo;
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
                        scope.up_img = "view-mixed-data?resource="+attrs.file+"&datasFileName=" + file.name;
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

// merchantApp.service('fileUpload', ['$http', function ($http) {
//
//     this.uploadFileToUrl = function(file, uploadUrl){
//         var formDatas = new FormData();
//         console.log("formDatas", formDatas);
//         console.log("file file", file);
//         formDatas.append('datas', file, file.name);
//         formDatas.append('resource','company');
//         console.log("form data", formDatas);
//
//
//
//         $http.post(uploadUrl, formDatas, {
//             transformRequest: angular.identity,
//             headers: {'Content-Type': undefined}
//         })
//
//             .success(function(){
//                 var  src = "view-mixed-data?resource=company&datasFileName="+file.name;
//             })
//             .error(function(){
//             });
//
//     }
// }]);