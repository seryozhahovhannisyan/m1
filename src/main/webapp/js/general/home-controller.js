generalModuls_array.push('summernote');
generalModuls_array.push('duParallax');
generalModuls_array.push('isteven-multi-select');

generalControllers.CallbacksCtrl = ['$scope', function ($scope) {

    $scope.optionsSummernote = {
        height: 400,
        minHeight: 400,             // set minimum height of editor
        maxHeight: 400,
        toolbar: [
            ['edit',['undo','redo']],
            ['headline', ['style']],
            ['style', ['bold', 'italic', 'underline', 'superscript', 'subscript', 'strikethrough', 'clear']],
            ['fontface', ['fontname']],
            ['textsize', ['fontsize']],
            ['fontclr', ['color']],
            ['alignment', ['ul', 'ol', 'paragraph', 'lineheight']],
            ['insert', ['picture','video','hr']]

        ]
    };
    $scope.summer_value;
    $scope.init = function () {
        console.log('Summernote is launched');
    };
    $scope.enter = function () {
        console.log('Enter/Return key pressed');
    };
    $scope.focus = function (e) {
        console.log('Editable area is focused');
    };
    $scope.blur = function (e) {
        console.log('Editable area loses focus');
    };
    $scope.paste = function (e) {
        console.log('Called event paste: ' + e.originalEvent.clipboardData.getData('text'));
    };
    $scope.change = function (contents) {
        console.log('contents are changed:', contents, $scope.editable);
        $scope.summer_value = contents;
    };
    $scope.keyup = function (e) {
        console.log('Key is released:', e.keyCode);

    };
    $scope.keydown = function (e) {
        console.log('Key is pressed:', e.keyCode);
    };
    $scope.imageUpload = function (files) {
        console.log('image upload:', files);
        console.log('image upload\'s editor:', $scope.editor);
        console.log('image upload\'s editable:', $scope.editable);
    };
}];

generalControllers.profileFormValidateCtrl = ['$timeout','$rootScope', function ($timeout,$rootScope) {
    var vm = this;
    vm.company = {
        name: '',
        address: '',
        companyEmail: '',
        phone: '',
        numberEmployees: '',
        contactName: '',
        contactSurname: '',
        contactEmail: '',
        contactPhone: ''
    };


    vm.submit = function (form) {
        $rootScope.show_loader();
        form.$submitted = false;
        if(form.$invalid){
            event.preventDefault();
            $rootScope.hide_loader();
            form.$submitted = true;

            return
        }

    }
}];

generalControllers.mainContentCtrl = ['parallaxHelper', '$rootScope', function (parallaxHelper, $rootScope) {
    //parallax effect
    var vm = this;
    vm.background = parallaxHelper.createAnimator(-0.5, 300, -300);
}];


generalControllers.requestFormPhoneCodeCtrl = ['$scope', function ($scope) {
    $scope.modernBrowsers = [{code : '+'+ARMENIA.phoneCode, icon : "<img src ='"+context+"/img/general/icon/language/"+ARMENIA.abr+".png'/>",  country    : ARMENIA.name , ticked:false},
        {code : '+'+USA.phoneCode,   icon : "<img src ='"+context+"/img/general/icon/language/"+USA.abr+".png'/>", country    : USA.name, ticked:true},
    ];
    $scope.outputBrowsers = [{code : '+'+USA.phoneCode, icon : "<img src ='"+context+"/img/general/icon/language/"+USA.abr+".png'/>",  country    : USA.name}

    ];
    $scope.phoneCode =  $scope.outputBrowsers[0].code;
    $scope.contactCode =  $scope.outputBrowsers[0].code;

    $scope.compPhoneCode = function () {
        $scope.phoneCode =  $scope.outputBrowsers[0].code
    };
    $scope.ctPhoneCode = function () {
        $scope.contactCode =  $scope.outputBrowsers[0].code
    }
}];


generalControllers.requestFormNumberPickerCtrl = [function () {
    var vm = this;
    vm.numberInput = '';
    vm.requestFormNumberIncrement = function (numberInput) {
        if (vm.numberInput != '') {
            vm.numberInput = parseInt(numberInput) + 1;
        } else {
            vm.numberInput = numberInput + 1;
        }
    }
    vm.requestFormNumberDecrement = function (numberInput) {
        vm.numberInput = numberInput - 1;
        if (vm.numberInput < 1) {
            vm.numberInput = 1;
        }
    }

    vm.checkMinOne = function (numberInput) {

        if (numberInput < 1) {
            vm.numberInput = 1;
        }

    }


}];


generalControllers.mainLoginFormCtrl = ['$scope', function ($scope) {
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
    };

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

$(document).on('click', '.main_header_go_to_send_request_form', function (event) {
    event.preventDefault();

    $('html, body').animate({
        scrollTop: $($.attr(this, 'href')).offset().top
    }, 1000);
});

$(window).scroll(function () {
    if ($(window).scrollTop() > 800) {
        $(".go_to_top").css({
            'display': 'block'
        });
    }
    else {
        $(".go_to_top").css({
            'display': 'none'
        });
    }
});
$(document).on('click', '.go_to_top a', function (event) {
    event.preventDefault();

    $('html, body').animate({
        scrollTop: $($.attr(this, 'href')).offset().top
    }, 1000);
});