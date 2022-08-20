var Config = {
    contextPath: "/angularjs/",
    apiRemoteUrl: "http://localhost:8080/angularjs/api/",
    getContextPath: function () {
        return this.contextPath;
    },
    getApiRemoteUrl: function () {
        return this.apiRemoteUrl;
    }
};
var app = angular.module('angularjsApp', []);
