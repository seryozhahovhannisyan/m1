generalModuls_array.push('isteven-multi-select');
generalControllers.mainLoginFormCtrl = ["$scope", function ($scope) {
    var vm = this;
    vm.mainPasswordShowHideIconSrc = context + '/img/general/icon/login/dont_show.png';
    vm.inputType = 'password';
    var showPasswordPng = context + '/img/general/icon/login/show_password.png';
    var hidePasswordPng = context + '/img/general/icon/login/dont_show.png';
    var showHide = false;

    function mainPasswordShowHide(text) {
        vm.inputType = text;
    };
    function mainPasswordShowHideIconSrc(src) {
        vm.mainPasswordShowHideIconSrc = src;
    };
    vm.mainPasswordShowHideAndIconSrc = function () {

        if (!showHide) {
            mainPasswordShowHide('text');
            mainPasswordShowHideIconSrc(showPasswordPng);
        } else {
            mainPasswordShowHide('password');
            mainPasswordShowHideIconSrc(hidePasswordPng);
        }
        showHide = !showHide;

    }

    var mainLoginForm = false;
    vm.mainLoginFormDisplay = {
        "display": 'none'
    };
    vm.fullPageFixedDiv = {
        "position": 'initial'
    }
    function mainLoginFormShowHideByDisplay(Display) {
        vm.mainLoginFormDisplay = Display;
    }

    function fullPageFixedDiv(position) {
        vm.fullPageFixedDiv = position;
    }


    vm.mainLoginFormShowHide = function () {
        if (!mainLoginForm) {

            mainLoginFormShowHideByDisplay({
                "display": 'block'
            });
            fullPageFixedDiv({
                "position": 'fixed'
            });
        } else {
            mainLoginFormShowHideByDisplay({
                "display": 'none'
            });
            fullPageFixedDiv({
                "position": 'initial'
            });
        }
        mainLoginForm = !mainLoginForm;
    }

    vm.closeLoginFormOnClickBody = function closeLoginFormOnClickBody() {
        mainLoginFormShowHideByDisplay({
            "display": 'none'
        });

        fullPageFixedDiv({
            "position": 'initial'
        });
        mainLoginForm = !mainLoginForm;
    }

}];


generalControllers.signUpFormCtrl = ['$window', '$document', function ($window, $document) {
    var vm = this;
    vm.passwordShowHideIconSrc = context + '/img/general/icon/login/dont_show.png';
    vm.inputType = 'password';
    var showPasswordPng = context + '/img/general/icon/login/show_password.png';
    var hidePasswordPng = context + '/img/general/icon/login/dont_show.png';
    var showHide = false;

    function passwordShowHide(text) {
        vm.inputType = text;
    };
    function passwordShowHideIconSrc(src) {
        vm.passwordShowHideIconSrc = src;
    };
    vm.passwordShowHideAndIconSrc = function () {

        if (!showHide) {
            passwordShowHide('text');
            passwordShowHideIconSrc(showPasswordPng);
        } else {
            passwordShowHide('password');
            passwordShowHideIconSrc(hidePasswordPng);
        }
        showHide = !showHide;

    }

    vm.footerplace = function () {
        vm.doc_height = Math.max(
            $document[0].body.scrollHeight, $document[0].documentElement.scrollHeight,
            $document[0].body.offsetHeight, $document[0].documentElement.offsetHeight,
            $document[0].body.clientHeight, $document[0].documentElement.clientHeight
        );
        vm.win_height = $window.innerHeight;
        vm.footer = angular.element('.main_footer');
        vm.footer_height = vm.footer.outerHeight(true);
        vm.content_height = vm.win_height - vm.footer_height;
        vm.content = angular.element('.sign_up');


        if (vm.win_height < vm.doc_height) {
            vm.footer.removeClass("fixed")
        }
        else {
            vm.content.css("height", vm.content_height);
            vm.footer.addClass("fixed");

        }
    };
    vm.footerplace();

}];

generalControllers.signUpFormPhoneCodeCtrl = ['$scope', function ($scope) {

    //phone code
    $scope.modernBrowsers = [{
        code: '+' + ARMENIA.phoneCode,
        icon: "<img src ='" + context + "/img/general/icon/language/" + ARMENIA.abr + ".png'/>",
        country: ARMENIA.name,
        ticked: false
    },
        {
            code: '+' + USA.phoneCode,
            icon: "<img src ='" + context + "/img/general/icon/language/" + USA.abr + ".png'/>",
            country: USA.name,
            ticked: true
        }
    ];
    $scope.outputBrowsers = [{
        code: '+' + USA.phoneCode,
        icon: "<img src ='" + context + "/img/general/icon/language/" + USA.abr + ".png'/>",
        country: USA.name
    }

    ];
    $scope.phoneCode = $scope.outputBrowsers[0].code;
    $scope.compPhoneCode = function () {
        $scope.phoneCode = $scope.outputBrowsers[0].code
    };

}];

generalControllers.campanyBranchCtrl = ['$scope', '$timeout', function ($scope, $timeout) {
    //phone code
    $scope.modernCompany = [];
    $scope.modernBranch = {};

    angular.forEach(company_data, function (value, key) {
        $scope.modernCompany.push({company: company_data[key].company_name, company_key: key});
    });
    $timeout(function () {
        angular.element(".ist_mult_branch").find("button:nth-child(1)").attr("disabled", true)
    }, 10);

    $scope.companyBranch = function () {
        $scope.modernBranch = [];
        $scope.companyName = $scope.outputCompany[0].company;
        $scope.companyKey = $scope.outputCompany[0].company_key;

        $scope.branches = company_data[$scope.companyKey].company_branch;

        angular.forEach($scope.branches, function (value, key) {

            $scope.modernBranch.push({branch: value.branch_name});
        });
        angular.element(".ist_mult_branch").find("button:nth-child(1)").attr("disabled", false)
    }


}];

$(document).ready(function () {

    top_menu_underline();


});

function top_menu_underline() {
    $(".branch_area p,.user_area p").hover(function () {
        $(this).next('div').css("width", "100%");
    }, function () {
        $(this).next('div').css("width", "0px");
    });
}
