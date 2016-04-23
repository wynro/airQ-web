/*
 * AUTHOR: Team 103
 * FILENAME: app.js
 * DESCRIPTION: This class contains all states of the application.
 */

angular.module('AirQApp', ['ui.router'])

    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider

        //starter screen
        .state('starter', {
            url: "/starter",
            templateUrl: "templates/starter.html"
        })

        //insert symptoms screen
        .state('symptoms', {
            url: "/symptoms",
            templateUrl: "templates/symptoms.html",
            controller: "symptomsCtrl"
        });
        
        $urlRouterProvider.otherwise('starter');
    });
