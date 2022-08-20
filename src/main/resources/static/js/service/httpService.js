/**
 * http service
 */
app.factory('httpService', function ($http) {
    var factory = {};

    factory.getOrPostParam = function (method, url, param, callback) {
        $http({
            method: method,
            url: Config.getApiRemoteUrl() + url,
            params: param
        }).then(function successCallback(response) {
            callback(response);
        }, function errorCallback(response) {
            layer.alert("error");
        });
    };

    factory.postData = function (url, data, callback) {
        $http({
            method: 'POST',
            url: Config.getApiRemoteUrl() + url,
            data: data
        }).then(function successCallback(response) {
            callback(response);
        }, function errorCallback(response) {
            layer.alert("error");
        });
    };
    return factory;
});