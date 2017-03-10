var generalControllers = {};
var generalModuls_array = [];
var merchantApp = angular.module('merchantApp', generalModuls_array);
generalModuls_array.push('modal');

merchantApp.config(['$httpProvider', function ($httpProvider) {
    
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

    var param = function (obj) {
        var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

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

        return query.length ? query.substr(0, query.length - 1) : query;
    };
    $httpProvider.defaults.transformRequest = [function (data) {
        return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
    }];

}]);

merchantApp.directive('onlyDigits', function () {
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

merchantApp.controller(generalControllers);

generalControllers.mainCtrlGeneral = function ($uibModal, $log, $document,$rootScope) {
    var $ctrl = this;

    $rootScope.hide_loader = function () {
        $rootScope.loadergif = false;
    };

    $rootScope.show_loader = function () {
        $rootScope.loadergif = true;

    };


    $ctrl.items = ['item1', 'item2', 'item3'];
    $ctrl.animationsEnabled = true;
    $rootScope.modal_open = false;
    $ctrl.open = function (size, parentSelector, templateUrl) {
console.log("size, parentSelector, templateUrl",size, parentSelector, templateUrl)
        if (!$rootScope.modal_open) {
            var parentElem = parentSelector ?
                angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
            var modalInstance = $uibModal.open({
                animation: $ctrl.animationsEnabled,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: templateUrl,
                controller: 'ModalInstanceCtrl',
                controllerAs: '$ctrl',
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
    


//    modal events part


};


$(document).on('ready', function () {
    var csrfToken = $("#csrfToken").val();
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': csrfToken
        }
    });
});
