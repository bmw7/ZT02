var adminApp = angular.module('adminApp',['ui.router']);  

adminApp.config(function($stateProvider,$urlRouterProvider){  
     $urlRouterProvider.otherwise("/route");  // 以路由状态为标志
     $stateProvider  
        .state("route", {  //路由状态  
            url: "",       //路由路径  
            templateUrl: "index"  //路由填充的模板  
        })
        .state("index",{
            url: "/index",
            templateUrl: "index"
        })
        .state("docs",{
            url: "/docs",
            templateUrl: "docs"
        })
        .state("articleadd",{
            url: "/article/add",
            templateUrl: "article/add"
        })
        
});  