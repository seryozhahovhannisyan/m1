angular.module("modal", ['ui.bootstrap']);
// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

angular.module('modal').controller('ModalInstanceCtrl', function ($uibModalInstance, items,$rootScope,$window,$timeout) {

    $timeout(function () {
        angular.element(".panel-body").css("max-height", "300px" );
        angular.element(".modal-dialog ").css("margin", "200px auto" )
    },10);


    var $ctrl = this;
    // $ctrl.items = items;
    $rootScope.open_close_popup = false;
    $ctrl.win_height = $window.innerHeight;
    $ctrl.modal_hieght =  $ctrl.win_height - 100;
    // $ctrl.selected = {
    //     item: $ctrl.items[0]
    // };

    $ctrl.ok = function () {
        // $uibModalInstance.close($ctrl.selected.item);
        $rootScope.modal_open = false
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
        $rootScope.modal_open = false
    };

    $ctrl.open_popup = function () {
        $rootScope.open_close_popup = true;
    };

    $ctrl.no_cancel = function () {
        $rootScope.open_close_popup = false;
    };

    $rootScope.show_mini = false;
    $ctrl.show_full_lg = true;
    $ctrl.show_full_sm = false;

    $ctrl.minimize = function () {
        $rootScope.show_mini = true;
        angular.element(".custom_modal").css("display", "none");
        console.log(" $ctrl.show_mini", $ctrl.show_mini);
    };

    $rootScope.full_screen = function () {
        angular.element(".custom_modal").css("display", "block");
        $rootScope.show_mini = false;
    };
    
    $rootScope.close_from_mini = function () {
        $rootScope.show_mini = false;
        $ctrl.cancel();
    };

    $ctrl.full_screen_large = function ( ) {
        if($ctrl.show_full_lg == true){
            $ctrl.show_full_lg = false;
            $ctrl.show_full_sm = true;
            angular.element(".modal-dialog ").addClass("full_screen").css("height", $ctrl.modal_hieght);
            angular.element(".modal-footer ").addClass("modal_footer_lg");
            angular.element(".panel-body").css("max-height", "500px" );
            angular.element(".modal-dialog ").css("margin", "30px auto" )
        }
        else{
            $ctrl.show_full_lg = true;
            $ctrl.show_full_sm = false;
            angular.element(".modal-dialog").removeClass("full_screen");
            angular.element(".modal-footer").removeClass("modal_footer_lg");
            angular.element(".modal-dialog ").css("height", "auto");
            angular.element(".panel-body").css("max-height", "300px" );
            angular.element(".modal-dialog ").css("margin", "200px auto" )
        }
    }
});


