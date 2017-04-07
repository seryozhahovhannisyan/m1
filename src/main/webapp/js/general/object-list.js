moduls_array.push('ngTable');
console.log("moduls_array", moduls_array);

controllers.listController = ['$scope', '$rootScope', '$http', '$filter', '$window',  'ngTableParams', '$timeout', 'transaction_info', function ($scope, $rootScope, $http, $filter, $window,   ngTableParams, $timeout, transaction_info) {

    $scope.items = transaction_info.item;

    $scope.image = [];
    $scope.checked={};
    $scope.selected = [];
    $scope.columns = columns;
    $scope.isteven_branches = typeof isteven_branches === 'undefined' ? null : isteven_branches;


    console.log("$scope.isteven_branches", $scope.isteven_branches);
    // var check_box_container_height = $('.checkBoxContainer').height;

//     var set_interval_function = setInterval(check_if_function_work, 1000);
//
//     function check_if_function_work() {
// console.log("esim inch")
//         if ($('.checkBoxContainer').length) {
//             $('.checkBoxContainer').bind('scroll', chk_scroll);
//
//             function chk_scroll(e) {
//                 var elem = $(e.currentTarget);
//                 if (elem[0].scrollHeight - Math.floor(elem.scrollTop()) == elem.outerHeight()) {
//                     alert('bottom');
//                 }
//             }
//
//             clearInterval(set_interval_function);
//         }
//
//     }

    $scope.tableParams = new ngTableParams(

        {page: 1, count: 10, sorting: {name: 'asc'}},
        {
            total: 0,
            getData: function ($defer, params) {

                var path = $scope.actionPath;
                var orderBy = 'name';
                var orderType = 'asc';

                var sorting = params.sorting();

                for (var key in sorting) {
                    orderBy = key;
                    orderType = sorting[key];
                }

                var requestJson = {
                    page: params.page(),
                    count: params.count(),
                    orderBy: orderBy,//addresss :desc
                    orderType: orderType,
                    group: params.group,
                    groupBy: params.groupBy
                };

                console.log("requestJson", requestJson);

                console.log("params.filter()", params.filter())
                console.log("params.filter() nulll", params.filter() != null)
                console.log("params.filter() length", params.filter().length)
                console.log("params.filter() type", typeof (params.filter()))

                if (params.filter() != null && params.filter().length != null) {

                    console.log("params.filter()========================================")
                    console.log("params.filter()", params.filter())
                    console.log("params.filter() nulll", params.filter() != null)
                    console.log("params.filter() length", params.filter().length)
                    console.log("params.filter() type", typeof (params.filter()))

                    requestJson.filter = params.filter();
                }

                if ($scope.isteven_branches != null) {
                    var ides = [];
                    var branchesOutPut = $scope.isteven_branches[0].outPut;
                    for (var id in branchesOutPut) {
                        ides.push(branchesOutPut[id].id);
                        // console.log('branchesOutPut[id]', branchesOutPut[id].id);
                    }
                    requestJson.branchIdes = ides.toString();
                }

                $scope.show_loader();

                requestJson = JSON.stringify(requestJson);


                $http({
                    method: 'post',
                    url: path,
                    data: {
                        requestJson: requestJson
                    },
                    dataType: 'json'
                }).then(

                    function (response) {

                        var result = response.data.dto;


                        if (result.responseStatus == 'SUCCESS') {
                            $rootScope.tabelDatas = response.data;

console.log("result.response",response)
                            if (result.response.roles) {
                                roles = result.response.roles;
                                console.log("roles", roles)
                            }

                            var listItems = result.response.data;
                            $scope.arraytems = listItems;
                            $scope.itemsCount = result.response.dataCount;
                            $scope.checked.checked_all = false;
                            $scope.selected = [];

                            console.log('listItems', listItems);

                            params.total($scope.itemsCount);
                            // params.total(listItems.length);

                            // use build-in angular filter
                            var sortedItems = params.sorting()
                                ? $filter('orderBy')(listItems, params.orderBy()[0])
                                : listItems;
                            $defer.resolve(sortedItems);
                        }
                    }
                ).finally(function () {
                    $scope.hide_loader();
                    $scope.serch_val = "";

                });

                // hides count tools as items is less than 10
                if ($scope.itemsCount < 10) {
                    $(".ng-table-pager").hide();
                }
            }
        }
    );

    $scope.viewDetail = function (item, modal_name) {
        var path = $scope.actionPathLoad;
        var id = item.currentTarget.getAttribute("data-id");
        var target = item.currentTarget.getAttribute("data-target");


        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {

                var result = response.data.dto;


                if (result.responseStatus == 'SUCCESS') {

                    var items = result.response.item;
                    console.log('item', items)
                    transaction_info.item = items;


                    $scope.open_modal('lg', '', modal_name);

                }
            })


        // $('#dlgItemDetail').modal('show');
    };


    $scope.deleteCurrRow = function (item) {
        var path = $scope.actionPathdelete;
        var id = item.currentTarget.getAttribute("data-id");
        // var target = item.currentTarget.getAttribute("data-target");

        $http({
            method: 'post',
            url: path,
            data: {
                id: id
            },
            dataType: 'json'
        }).then(
            function (response) {

                console.log("response delletic", response)

            })
    };

    $scope.deleteSelectedRow = function (id_type) {
      var branchesIdes =  $scope.selected.join();
      var path = $scope.actionPathdeleteMultiple;
      if(id_type == "branch"){
          var data  ={branchIdes: branchesIdes};
      }
      if(id_type == "cashier"){
          var data  ={cashierIdes: branchesIdes};
      }

        $http({
            method: 'post',
            url: path,
            data: data,
            dataType: 'json'
        }).then(
            function (response) {

                console.log("response delletic", response)

            })
    };



    $scope.updateSelection = function ($event, id,all) {
        if(all== undefined) {
            var checkbox = $event.currentTarget;
            var action = checkbox.checked ? 'add' : 'remove';
            if (action == 'add' & $scope.selected.indexOf(id) == -1) {
                $scope.selected.push(id);
            }
            if (action == 'remove' && $scope.selected.indexOf(id) != -1) {
                $scope.selected.splice($scope.selected.indexOf(id), 1);
            }
        }
        else{
            $scope.selected = [];
            var checkbox = $event.currentTarget;
            var action = checkbox.checked ? 'add' : 'remove';
            if (action == 'add' & $scope.selected.indexOf(id) == -1) {
                angular.forEach($scope.arraytems, function (value, key) {
                    $scope.selected.push(value.id)
                })
            }
            if (action == 'remove' && $scope.selected.indexOf(id) != -1) {
                $scope.selected = [];
            }

        }
            console.log("$scope.selected",$scope.selected)
    };

    $scope.edit_row = function () {
        $timeout(function () {
            $scope.show_loader();
        }, 1000);
        $timeout(function () {
            $scope.hide_loader();
        }, 3000);
    };
    $scope.dropdown_tds = function ($event) {
        var current_element = angular.element($event.currentTarget);
        var top = current_element.outerHeight(true);
        var width = current_element.outerWidth(true);
        current_element.find("ul").css({"top": top, "width": width}).toggle(500);
    };
    $scope.add_new_records = function (modal_name) {

        $scope.open_modal('lg', '', modal_name);
        console.log("modal_name", modal_name)
    };
    $scope.disable_labels = function (index) {
        if (index == 0 || index == 1 || index == 2) {
            return true
        }
    };

    /*Wallet*/
    // $scope.viewDetail = function(item) {
    //
    //     var path = $scope.actionPathLoad;
    //     var id = item.currentTarget.getAttribute("data-id");
    //     var target = item.currentTarget.getAttribute("data-target");
    //
    //
    //     $http({
    //         method: 'post',
    //         url: path,
    //         data :  {
    //             id : id
    //         },
    //         dataType: 'json'
    //     }).then(
    //         function(response) {
    //
    //             var result = response.data.dto;
    //             if (result.responseStatus == 'SUCCESS') {
    //
    //                 var item = result.response.item;
    //                 console.log('item', item)
    //                 $scope.item = item;
    //             }
    //         })
    //
    //     //$('#dlgItemDetail').modal('show');
    // };

    // $scope.editing_li = function ($event) {
    //     var current_li =angular.element($event.currentTarget);
    //     var text = current_li.text().trim();
    //     current_li.closest("td").find("span").text(text);
    //     current_li.closest("ul").hide();
    // }

    //
    // $scope.getImagePath = function(info) {
    //     return contextPath + '/image/flag/' +
    //         info.language.toLowerCase().substr(0, 2) + '.png';
    // };
    //
    // $scope.hasPhoto = function(item) {
    //     return item.photos &&
    //         item.photos.length > 0 &&
    //         item.photos[0].id;
    // };
    //
    // $scope.showPhoto = function(item, objName) {
    //     $scope.image.src = contextPath + "/" + objName + "/photo/" + item.photos[0].id;
    //     var filename = item.photos[0].filename;
    //     if (filename.indexOf(".") != -1) {
    //         filename = filename.substr(0, filename.indexOf("."));
    //     }
    //     $scope.image.filename = filename;
    //     console.log('$scope.image',$scope.image)
    //     $('#dlgImage').modal('show');
    // };
    //

    //
    // $scope.containerDetails = function() {
    //     return $sce.trustAsHtml($("#containerDetails").html());
    // };
    //
    // $scope.editPhoto = function(id) {
    //     document.location = 'edit-photos/' + id;
    // };
    //
    // $scope.editItem = function(id) {
    //     document.location = 'edit/' + id;
    // };
}]