/*
 * AUTHOR: Team 103
 * FILENAME: starterController.js
 * DESCRIPTION: This file contains the "starter.html" controller.
 */

angular.module('AirQApp')

    .controller('starterCtrl', ['$scope','authIp', function($scope,authIp){
        
        // boolean to hide or show the "view map" button
        $scope.ipActive = authIp.getIpAuth();

    }]);
