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
        //about screen
        .state('about', {
            url: "/about",
            templateUrl: "templates/about.html"
        })
        //legal screen
        .state('legal', {
            url: "/legal",
            templateUrl: "templates/legal.html"
        });
        
        $urlRouterProvider.otherwise('starter');
    });
