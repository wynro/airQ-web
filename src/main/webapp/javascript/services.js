/*
 * AUTHOR: Team 103
 * FILENAME: services.js
 * DESCRIPTION: This class contains all the services.
 */

angular.module('AirQApp')

    // 'information' service manage the information data symptoms of the application
    .factory('information', function ($http,$state) {
        return {

            // send the information data symptoms to an end-point
            sendInfo: function (dataInfo) {
                var json = 'http://ipv4.myexternalip.com/json';
                $http.get(json).then(function(result) {
                    dataInfo = {
                        symptoms: dataInfo.symptoms,
                        coords: dataInfo.coords,
                        ip: result.data.ip
                    };
                    debugger;
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
                });
            }
        }
    })

    // 'eventInformation' service manage the information data events of the application
    .factory('eventInformation', function ($http,$state) {
        return {

            // send the information data events to an end-point
            sendInfo: function (dataInfo) {
                var json = 'http://ipv4.myexternalip.com/json';
                $http.get(json).then(function(result) {
                    dataInfo = {
                        environment: dataInfo.environment,
                        coords: dataInfo.coords,
                        ip: result.data.ip
                    };
                    debugger;
                    $http({
                        method: 'POST',
                        url: 'insert/environment',
                        data: JSON.stringify(dataInfo),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }).success(function (data) {
                        $state.go('viewData');
                    }).error(function (data) {
                    });
                });
            }
        }
    })

    // 'authIp' service manage the information IP data of the application
    .factory('authIp', function ($http) {

        var IPAuth = false;

        return {

            // function to know if the user ha
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

            // get the ip auth variable
            getIpAuth: function () {
                return IPAuth;
            }
        }
    });