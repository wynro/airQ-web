/*
 * AUTHOR: Team 103
 * FILENAME: symptomsController.js
 * DESCRIPTION: This file contains the "symptoms.html" controller.
 */

angular.module('AirQApp')

    .controller('eventsCtrl', ['$scope','information','geolocation', function($scope,information,geolocation){

        $scope.earthquake = false;
        $scope.volcano = false;
        $scope.tornado = false;
        $scope.fire = false;

        $scope.eventOn = function (num) {
            switch (num) {
                case 1:
                    $scope.earthquake = true;
                    $scope.volcano = false;
                    $scope.tornado = false;
                    $scope.fire = false;
                    break;
                case 2:
                    $scope.earthquake = false;
                    $scope.volcano = false;
                    $scope.tornado = false;
                    $scope.fire = true;
                    break;
                case 3:
                    $scope.earthquake = false;
                    $scope.volcano = false;
                    $scope.tornado = true;
                    $scope.fire = false;
                    break;
                default:
                    $scope.earthquake = false;
                    $scope.volcano = true;
                    $scope.tornado = false;
                    $scope.fire = false;

            }
        }

    }]);
