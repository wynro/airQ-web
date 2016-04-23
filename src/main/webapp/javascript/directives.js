/*
 * AUTHOR: Team 103
 * FILENAME: directives.js
 * DESCRIPTION: This class contains all the directives.
 */

angular.module('cloudRobeApp')

    // include the 'navbar.html' into the <navbar> tag
    .directive('navbar', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/components/navbar.html'
        }
    });
