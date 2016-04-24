/*
 * AUTHOR: Team 103
 * FILENAME: services.js
 * DESCRIPTION: This class contains all the services.
 */

angular.module('AirQApp')

    // 'information' service manage the information data of the application
    .factory('information', function ($http,$state) {
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
                    $state.go('viewData');
                }).error(function (data) {
                });
            }
        }

    })

    // 'authIp' service manage the information IP data of the application
    .factory('authIp', function ($http) {

        var IPAuth = false;

        return {
            //
            isIpAuth: function (dataInfo) {

                var json = 'http://ipv4.myexternalip.com/json';
                $http.get(json).then(function(result) {

                    $http({
                        method: 'POST',
                        url: 'map',
                        data: JSON.stringify(result.data.ip),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }).success(function (data) {
                        IPAuth = true;
                    }).error(function (data) {
                    });
                });
            },

            //
            getIpAuth: function () {
                return IPAuth;
            }
        }
    
    });