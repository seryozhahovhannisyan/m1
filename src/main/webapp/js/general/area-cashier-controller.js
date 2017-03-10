generalModuls_array.push('duParallax');
generalControllers.mainLoginFormCtrl = [   function ( ) {
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

    };

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
    }

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
generalControllers.cashierAreaContentCtrl = ['parallaxHelper',  function (parallaxHelper) {

    //parallax effect 
    var vm = this;
    vm.background = parallaxHelper.createAnimator(-0.5, 300, -300);
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
