/*
 * AUTHOR: Team 103
 * FILENAME: symptomsController.js
 * DESCRIPTION: This file contains the "symptoms.html" controller.
 */

angular.module('AirQApp')

    .controller('symptomsCtrl', ['$scope','information','geolocation', function($scope,information,geolocation){

        // select options of intensity
        $scope.intensity = [0,1,2,3,4,5,6,7,8,9,10];

        // inputs form of symptoms
        $scope.cough = "";
        $scope.airLackness = "";
        $scope.wheezing = "";
        $scope.sneezing = "";
        $scope.obstruction = "";
        $scope.itchy = "";
        $scope.airQuality = "";

        // take the user geolocation
        geolocation.getLocation().then(function(data){
            $scope.coords = {lat:data.coords.latitude, long:data.coords.longitude};
        });

        // send the data symptoms info form to the information service
        $scope.sendSymptom = function () {
            
            var symptomInfo = {
                cough: $scope.cough,
                airLackness: $scope.airLackness,
                wheezing: $scope.wheezing,
                sneezing: $scope.sneezing,
                obstruction: $scope.obstruction,
                itchy: $scope.itchy,
                airQuality: $scope.airQuality
            };

            var dataInfo = {
                symptoms: symptomInfo,
                coords: $scope.coords

            };

            information.sendInfo(dataInfo);
            
        }
        
    }]);
