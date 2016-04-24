/*
 * AUTHOR: Team 103
 * FILENAME: symptomsController.js
 * DESCRIPTION: This file contains the "symptoms.html" controller.
 */

angular.module('AirQApp')

    .controller('viewDataCtrl', ['$scope', function($scope){


        $scope.airQualityMap = true;
        $scope.eventsMap = false;
        $scope.discordanceMap = false;
        
        $scope.viewMap = function (num) {
            switch (num) {
                case 1:
                    $scope.airQualityMap = true;
                    $scope.eventsMap = false;
                    $scope.discordanceMap = false;
                    break;
                case 2:
                    $scope.airQualityMap = false;
                    $scope.eventsMap = true;
                    $scope.discordanceMap = false;
                    break;
                default:
                    $scope.airQualityMap = false;
                    $scope.eventsMap = false;
                    $scope.discordanceMap = true;
            }
            
        };

    }]);
