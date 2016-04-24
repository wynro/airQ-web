/*
 * AUTHOR: Team 103
 * FILENAME: viewDataController.js
 * DESCRIPTION: This file contains the "viewData.html" controller.
 */

angular.module('AirQApp')

    .controller('viewDataCtrl', ['$scope', function($scope) {

        // variables to handling the view of the different maps
        $scope.airQualityMap = true;
        $scope.eventsMap = false;
        $scope.discordanceMap = false;

        // function that active the view of the [num] map
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
