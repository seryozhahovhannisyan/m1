moduls_array.push('isteven-multi-select');
controllers.profileFormValidateCtrl = [function() {
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
}];

controllers.phoneCodeIstevenCtrl = function() {
    var vm = this;
    vm.phoneCode = [{
        'maker' : '+374',
        'icon' : '<img src="'+context+'/img/general/icon/language/hy.png" alt="Armenia" />',
        'name'    : 'Armenia'
    }, {
        'maker' : '+1',
        'icon' : '<img src="'+context+'/img/general/icon/language/en.png" alt="English" />',
        'name'    : 'English'
    },
        {
            'maker' : '+7',
            'icon' : '<img src="'+context+'/img/general/icon/language/ru.png" alt="Russia" />',
            'name'    : 'Russia'
        }]
};
controllers.phoneCodeContactIstevenCtrl = function() {
    var vm = this;
    vm.phoneCode = [{
        'maker' : '+374',
        'icon' : '<img src="'+context+'/img/general/icon/language/hy.png" alt="Armenia" />',
        'name'    : 'Armenia'
    }, {
        'maker' : '+1',
        'icon' : '<img src="'+context+'/img/general/icon/language/en.png" alt="English" />',
        'name'    : 'English'
    },
        {
            'maker' : '+7',
            'icon' : '<img src="'+context+'/img/general/icon/language/ru.png" alt="Russia" />',
            'name'    : 'Russia'
        }]
};

controllers.profileSettingsNumberPickerCtrl = function () {
    var vm = this;

    vm.numberInput = '';
    vm.profileSettingsNumberIncrement = function (numberInput) {
        if (vm.numberInput != '') {
            vm.numberInput = parseInt(numberInput) + 1;
        } else {
            vm.numberInput = numberInput + 1;
        }
    }
    vm.profileSettingsNumberDecrement = function (numberInput) {
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


};