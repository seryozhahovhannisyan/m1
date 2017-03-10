module.exports = {
	i18n: {
		options: {
			banner: 'define([\'angular\'], function (angular) {\n',
			footer: '\n});'
		},
		files: [{
			src: './../merchant/src/main/webapp/locale/angular-locale*.js',
			dest: './../merchant/src/main/webapp/static/generated/locale',
			expand: true,
			flatten: true
		},{
			src: './../merchant/src/main/webapp/country/angular-locale*.js',
			dest: './../merchant/src/main/webapp/static/generated/lcp',
			expand: true,
			flatten: true
		}]
	}
};