
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

// merchantApp.controller('leftMenuCtrl', function ($scope) {
//     // $scope.leftMenuStyle = "{display:inline-block! important}";
//     $scope.leftMenu = false;
//     $scope.leftMenuShowHide = function () {
//         $scope.leftMenu = !$scope.leftMenu;
//     };
// });
// merchantApp.controller('leftMenuCtrl', function() {
//     var vm = this;
//
//     var leftMenu = false;
//     vm.leftMenuOpacity = {
//         "left" : '-320px'
//     }
//     // 'style="opacity:0"';
//     function leftMenuShowHideByOpacity(opacity) {
//         vm.leftMenuOpacity = opacity;
//     }
//     vm.leftMenuShowHide = function () {
//         if(!leftMenu) {
//             leftMenuShowHideByOpacity({
//                 "left" : 0
//             });
//         } else {
//             leftMenuShowHideByOpacity({
//                 "left" : '-320px'
//             });
//         }
//         leftMenu = !leftMenu;
//     }
//    
//    
//    
//     // $scope.leftMenu = false;
//     // $scope.leftMenuShowHide = function() {
//     //     $scope.leftMenu = !$scope.leftMenu;
//     // };
// }); 