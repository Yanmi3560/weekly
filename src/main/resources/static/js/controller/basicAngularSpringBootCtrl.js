app.controller('basicAngularSpringBootCtrl', function ($scope, $http, httpService) {
    $scope.title = "basicAngularSpringBootCtrl";

    $scope.changeInputText = function () {
        //get 字符串
        var getUrl = 'basic/get';
        var getParams = {
            'print': $scope.inputText
        };
        httpService.getOrPostParam('GET', getUrl, getParams, function (response) {
            layer.msg("get success");
        });

        //post 字符串
        var postparamUrl = 'basic/postparam';
        var postparamParams = {
            'print': $scope.inputText
        };
        httpService.getOrPostParam('POST', postparamUrl, postparamParams, function (response) {
            layer.msg("post param success");
        });

        //post 对象
        var postbodyUrl = 'basic/postbody';
        var postbodyParams = {
            'print': $scope.inputText
        };
        httpService.postData(postbodyUrl, postbodyParams, function (response) {
            layer.msg("post body success");
        });
    };
});
