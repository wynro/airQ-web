angular.module('AirQApp')

    .controller('symptomsCtrl', ['$scope',function($scope){

        $scope.intensity = ["0-None","1-Lowest","2","3","4","5-Median","6","7","8","9","10-Highest"];
    }]);
