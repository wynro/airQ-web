/*
 * AUTHOR: Team 103
 * FILENAME: directives.js
 * DESCRIPTION: This class contains all the directives.
 */

angular.module('AirQApp')

    // include the 'navbarTop.html' into the <navbar-top> tag
    .directive('navbarTop', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/components/navbarTop.html'
        }
    })
        
    // include the 'navbarBottom.html' into the <navbar-bottom> tag
    .directive('navbarBottom', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/components/navbarBottom.html'
        }
    });
