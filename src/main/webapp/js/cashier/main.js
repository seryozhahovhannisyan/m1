var controllers = {};
// angular.module('ui.bootstrap.demo', ['ngAnimate', 'ui.bootstrap']);
// var mainApp = angular.module('mainApp', ['ngTagsInput','ngSanitize', 'isteven-multi-select', 'ngTable', 'ngAnimate', 'ui.bootstrap', 'ngDroplet', bootstrapLightbox]);
var moduls_array = [];


// gg.push('ngTbale' );

var mainApp = angular.module('mainApp', moduls_array);
moduls_array.push('modal');

mainApp.config(['$httpProvider', function ($httpProvider) {

    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';


    var param = function (obj) {
        var query = '', name, value, fullSubName, subName, subValue, innerObj, i;
        console.log('param ==>',obj);
        for (name in obj) {
            value = obj[name];

            if (value instanceof Array) {
                for (i = 0; i < value.length; ++i) {
                    subValue = value[i];
                    fullSubName = name + '[' + i + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if (value instanceof Object) {
                for (subName in value) {
                    subValue = value[subName];
                    fullSubName = name + '[' + subName + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if (value !== undefined && value !== null)
                query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
        }
        console.log('query ==>',query);
        return query.length ? query.substr(0, query.length - 1) : query;
    };
    $httpProvider.defaults.transformRequest = [function (data) {
        return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
    }];

}]);

mainApp.controller(controllers);

mainApp.factory('transaction_info', [function() {
    return { }
}]);

mainApp.directive('onlyDigits', function () {
    return {
        require: 'ngModel',
        restrict: 'A',
        link: function (scope, element, attr, ctrl) {
            function inputValue(val) {
                if (val) {
                    var digits = val.replace(/[^0-9]/g, '');

                    if (digits !== val) {
                        ctrl.$setViewValue(digits);
                        ctrl.$render();
                    }
                    return parseInt(digits, 10);
                }
                return undefined;
            }

            ctrl.$parsers.push(inputValue);
        }
    };
});
mainApp.directive('tooltip', function(){
    return {
        restrict: 'A',
        link: function(scope, element, attrs){

            $(element).hover(function(){
                // on mouseenter
                $(element).tooltip('show');
            }, function(){
                // on mouseleave
                $(element).tooltip('hide');
            });
        }
    };
});
controllers.mainLoginCtrl = function ($scope,$uibModal, $log, $document,$rootScope,$http, $window, $timeout) {
    $rootScope.clock_start = true;// this flag is for modal timer

    var $ctrl = this;
    $rootScope.hide_loader = function () {
        $scope.loadergif=false;
    };

    $rootScope.show_loader = function () {
        $scope.loadergif=true;
    };

    $rootScope.current_language = angular.element(".langkey").data("langkey");



    // modals part
    $ctrl.animationsEnabled = true;
    $rootScope.modal_open = false;
    $rootScope.open_modal = function (size, parentSelector, templateUrl) {
        console.log("size, parentSelector, templateUrl",size, parentSelector, templateUrl);
        if (!$rootScope.modal_open) {
            var parentElem = parentSelector ?
                angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
            var modalInstance = $uibModal.open({
                animation: $ctrl.animationsEnabled,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: templateUrl,
                controller: 'ModalInstanceCtrl',
                controllerAs: 'ctrl',
                windowClass: 'custom_modal',
                openedClass: "modal_opened",
                backdrop: false,
                size: size,
                appendTo: parentElem,
                resolve: {
                    items: function () {
                        return $ctrl.items;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $ctrl.selected = selectedItem;
            }, function () {
                $log.info('modal is closed');
            });

            $rootScope.modal_open = true;
        }
        else {
            $rootScope.full_screen();
        }

    };

// gloabl variables and functions
    $rootScope.IMAGE_BASE_URL = "https://www.hollor.com/view_user_picture?userId=";


    $rootScope.getUserImg=function (user_id) {
        var image_url = $rootScope.IMAGE_BASE_URL + user_id;
        $http({
            method: 'post',
            url: image_url,
            dataType: 'json',
            headers : {
                "Access-Control-Allow-Origin":"*"
            }
        }).then(function successCallback(response) {

        }, function errorCallback(response) {

        });
    }

    $ctrl.footerplace = function () {
        $ctrl.doc_height = Math.max(
            $document[0].body.scrollHeight, $document[0].documentElement.scrollHeight,
            $document[0].body.offsetHeight, $document[0].documentElement.offsetHeight,
            $document[0].body.clientHeight, $document[0].documentElement.clientHeight
        );
        $ctrl.win_height = $window.innerHeight;
        $ctrl.footer = angular.element('.main_footer');
        $ctrl.footer_height = $ctrl.footer.outerHeight(true);
        $ctrl.content_height = $ctrl.win_height - $ctrl.footer_height;
        $ctrl.content = angular.element('.right_col');

        if ($ctrl.win_height < $ctrl.doc_height) {
            $ctrl.footer.css("position","relative")
            console.log("no fixed")
        }
        else {
            $ctrl.content.css("height", $ctrl.content_height);
            // $ctrl.footer.addClass("fixed");
            $ctrl.footer.css("position","fixed")
            console.log("fixed")
        }
    };

    $timeout($ctrl.footerplace,100);
    var appWindow = angular.element($window);
    appWindow.bind('resize', function () {
        $ctrl.footerplace()
    });
};


$(document).on('ready', function() {
    
    var csrfToken = $("#csrfToken").val();
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN' : csrfToken
        }
    });
    
    
    
    
     
});
