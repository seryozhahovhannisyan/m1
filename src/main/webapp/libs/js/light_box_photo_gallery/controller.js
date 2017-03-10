

// angular.module('demo1', ['bootstrapLightbox']);

controllers.GalleryCtrl = function ($scope, Lightbox) {
    $scope.images = [
        {
            'url': 'https://farm6.staticflickr.com/5830/20552523531_e1efec8d49_k.jpg'
        },
        {
             'url': 'https://farm8.staticflickr.com/7300/12807911134_ff56d1fb3b_b.jpg'
        },
        {
            'url': 'https://farm1.staticflickr.com/400/20228789791_52fb84917f_b.jpg'
        },
        {
            'url': 'https://farm1.staticflickr.com/260/20185156095_912c2714ef_b.jpg'
        },
        {
            'url': 'https://farm6.staticflickr.com/5757/20359334789_57316968ed_m.jpg'
        },
        {
            'url': 'https://farm1.staticflickr.com/359/18741723375_28c89372d7_c.jpg'
        },
        {
            'url': 'https://farm6.staticflickr.com/5606/15425945368_6f6ae945fc.jpg'
        },
        {
            'url': 'https://farm6.staticflickr.com/5830/20552523531_e1efec8d49_k.jpg'
        },
        {
             'url': 'https://farm8.staticflickr.com/7300/12807911134_ff56d1fb3b_b.jpg'
        },
        {
            'url': 'https://farm1.staticflickr.com/400/20228789791_52fb84917f_b.jpg'
        },
        {
            'url': 'https://farm1.staticflickr.com/260/20185156095_912c2714ef_b.jpg'
        },
        {
            'url': 'https://farm6.staticflickr.com/5757/20359334789_57316968ed_m.jpg'
        },
        {
            'url': 'https://farm1.staticflickr.com/359/18741723375_28c89372d7_c.jpg'
        },
        {
            'url': 'https://farm6.staticflickr.com/5606/15425945368_6f6ae945fc.jpg'
        },
    ];

    $scope.openLightboxModal = function (index) {
        Lightbox.openModal($scope.images, index);
    };

};