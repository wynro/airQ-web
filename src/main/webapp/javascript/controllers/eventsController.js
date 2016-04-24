/*
 * AUTHOR: Team 103
 * FILENAME: eventsController.js
 * DESCRIPTION: This file contains the "events.html" controller.
 */

angular.module('AirQApp')

    .controller('eventsCtrl', ['$scope','eventInformation','geolocation', function($scope,eventInformation,geolocation){

        // variabes to handling the active color of events icons
        $scope.earthquake = false;
        $scope.volcano = false;
        $scope.tornado = false;
        $scope.fire = false;

        // take the user geolocation
        geolocation.getLocation().then(function(data){
            $scope.coords = {lat:data.coords.latitude, long:data.coords.longitude};
        });

        // function that active the color of the [num] event
        $scope.eventOn = function (num) {
            switch (num) {
                //earthquake case
                case 1:
                    $scope.earthquake = true;
                    $scope.volcano = false;
                    $scope.tornado = false;
                    $scope.fire = false;
                    break;
                //fire case
                case 2:
                    $scope.earthquake = false;
                    $scope.volcano = false;
                    $scope.tornado = false;
                    $scope.fire = true;
                    break;
                //tornado case
                case 3:
                    $scope.earthquake = false;
                    $scope.volcano = false;
                    $scope.tornado = true;
                    $scope.fire = false;
                    break;
                //volcano case
                default:
                    $scope.earthquake = false;
                    $scope.volcano = true;
                    $scope.tornado = false;
                    $scope.fire = false;

            }
        };

        // send the data event info to the eventInformation service
        $scope.sendEvents = function () {

            // transform the event variable values from boolean to integer
            var earthquake = 0;
            var volcano = 0;
            var tornado = 0;
            var fire = 0;
            if ($scope.earthquake) {
                earthquake = 1;
            } else if ($scope.volcano) {
                volcano = 1;
            } else if ($scope.tornado) {
                tornado = 1;
            } else if ($scope.fire) {
                fire = 1;
            }
            
            var conditions = {
                fire: fire,
                earthquake: earthquake,
                volcano: volcano,
                tornado: tornado
            };

            var dataInfo = {
                environment: conditions,
                coords: $scope.coords

            };
            
            eventInformation.sendInfo(dataInfo);

        }



    }]);
