/*
 * AUTHOR: Team 103
 * FILENAME: app.js
 * DESCRIPTION: This class contains all states of the application.
 */

angular.module('AirQApp', ['ui.router','geolocation'])

    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider

        //starter screen
        .state('starter', {
            url: "/starter",
            templateUrl: "templates/starter.html"
        })

        //events screen
        .state('events', {
            url: "/events",
            templateUrl: "templates/events.html"
        })

        //viewData screen
        .state('viewData', {
            url: "/viewData",
            templateUrl: "templates/viewData.html",
            controller: "viewDataCtrl"
        })

        //symptoms symptoms screen
        .state('symptoms', {
            url: "/symptoms",
            templateUrl: "templates/symptoms.html",
            controller: "symptomsCtrl"
        });
        
        $urlRouterProvider.otherwise('starter');
    });
