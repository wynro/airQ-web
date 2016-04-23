/*
 * AUTHOR: Team 103
 * FILENAME: services.js
 * DESCRIPTION: This class contains all the services.
 */

angular.module('AirQApp')

    // 'information' service manage the information data of the application
    .factory('information', function () {
        return {

            // write a feedback
            sendInfo: function (dataInfo) {
                $http({
                    method: 'POST',
                    url: 'insert/symptoms',
                    data: JSON.stringify(dataInfo),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).success(function (data) {
                }).error(function (data) {
                });
            }
        }

    });