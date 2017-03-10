merchantApp.controller('forthBlockCtrl', function () {
    var vm = this;

    vm.forthBlockToggleButtonImgSrc = context + '/img/cashier-second/arrow_bottom_green.png';
    var openForthBlockToggleButtonImgSrc = context + '/img/cashier-second/arrow_top_green.png';
    var closeForthBlockToggleButtonImgSrc = context + '/img/cashier-second/arrow_bottom_green.png';
    var openClose = false;

    vm.forthBlockToggleBlockDisplay = {
        "display" : 'none'
    }
    
    function forthBlockToggleButtonImgSrc(src){
        vm.forthBlockToggleButtonImgSrc = src;
    };

    function forthBlockToggleBlockDisplay(display) {
        vm.forthBlockToggleBlockDisplay = display;
    }

    vm.forthBlockToggleButton = function () {

        if (!openClose) {
            forthBlockToggleButtonImgSrc(openForthBlockToggleButtonImgSrc);
            forthBlockToggleBlockDisplay({
                "display" : 'block'
            });
        } else {
            forthBlockToggleButtonImgSrc(closeForthBlockToggleButtonImgSrc);
            forthBlockToggleBlockDisplay({
                "display" : 'none'
            });
        }
        openClose = !openClose;

    }

    vm.forthBlockToggleBlockClose = function () {
        forthBlockToggleButtonImgSrc(closeForthBlockToggleButtonImgSrc);
        forthBlockToggleBlockDisplay({
            "display" : 'none'
        });
        openClose = false;
    }


});
