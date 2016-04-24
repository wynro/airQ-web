/*
 * AUTHOR: Team 103
 * FILENAME: symptomsController.js
 * DESCRIPTION: This file contains the "symptoms.html" controller.
 */

angular.module('AirQApp')

    .controller('starterCtrl', ['$scope','authIp', function($scope,authIp){
        
        
        $scope.ipActive = authIp.getIpAuth();


    }]);
