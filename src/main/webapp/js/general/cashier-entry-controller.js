generalControllers.mainLoginFormCtrl = [ function() {
    var vm = this;
    vm.mainPasswordShowHideIconSrc = context + '/img/general/icon/login/dont_show.png';
    vm.inputType = 'password';
    var showPasswordPng = context + '/img/general/icon/login/show_password.png';
    var hidePasswordPng = context + '/img/general/icon/login/dont_show.png';
    var showHide = false;
    function mainPasswordShowHide(text){
        vm.inputType = text;
    };
    function mainPasswordShowHideIconSrc(src){
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
        "display" : 'none'
    } ;
    vm.fullPageFixedDiv = {
        "position" : 'initial'
    }
    function mainLoginFormShowHideByDisplay(Display) {
        vm.mainLoginFormDisplay = Display;
    }
    function fullPageFixedDiv(position) {
        vm.fullPageFixedDiv = position;
    }


    vm.mainLoginFormShowHide = function () {
        if(!mainLoginForm) {

            mainLoginFormShowHideByDisplay({
                "display" : 'block'
            });
            fullPageFixedDiv({
                "position" : 'fixed'
            });
        } else {
            mainLoginFormShowHideByDisplay({
                "display" : 'none'
            });
            fullPageFixedDiv({
                "position" : 'initial'
            });
        }
        mainLoginForm = !mainLoginForm;
    };

    vm.closeLoginFormOnClickBody = function closeLoginFormOnClickBody() {
        mainLoginFormShowHideByDisplay({
            "display" : 'none'
        });

        fullPageFixedDiv({
            "position" : 'initial'
        });
        mainLoginForm = !mainLoginForm;
    }

}];


generalControllers.loginFormCtrl = ['$window', '$document', function($window,$document) {
    var vm = this;
    vm.passwordShowHideIconSrc = context + '/img/general/icon/login/dont_show.png';
    vm.inputType = 'password';
    var showPasswordPng = context + '/img/general/icon/login/show_password.png';
    var hidePasswordPng = context + '/img/general/icon/login/dont_show.png';
    var showHide = false;
    function passwordShowHide(text){
        vm.inputType = text;
    };
 
    function passwordShowHideIconSrc(src){
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

    };

    vm.footerplace =function () {
        vm.doc_height =Math.max(
            $document[0].body.scrollHeight, $document[0].documentElement.scrollHeight,
            $document[0].body.offsetHeight, $document[0].documentElement.offsetHeight,
            $document[0].body.clientHeight, $document[0].documentElement.clientHeight
        );
        vm.win_height = $window.innerHeight;
        vm.footer = angular.element('.main_footer');
        vm.footer_height =  vm.footer.outerHeight(true);
        vm.content_height = vm.win_height - vm.footer_height;
        vm.content = angular.element('.login_forward');


        if (vm.win_height < vm.doc_height) {
            vm.footer.removeClass("fixed")
        }
        else{
            vm.content.css("height", vm.content_height );
            vm.footer.addClass("fixed")
            console.log(vm.content_height)
        }
    };
    vm.footerplace();


}];



$(document).ready(function() {

    top_menu_underline();


});

function top_menu_underline() {
    $( ".branch_area p,.user_area p" ).hover(function() {
        $( this ).next('div').css("width","100%");
    },function(){
        $( this ).next('div').css("width","0px");
    });
}
