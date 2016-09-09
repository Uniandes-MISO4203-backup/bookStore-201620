/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {
    var mod = ng.module('nationalityModule', ['ngCrud', 'ui.router']);

    mod.constant('nationalityModel', {
        name: 'nationality',
        displayName: 'Nationality',
		url: 'nationalitys',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            description: {
                displayName: 'Description',
                type: 'String',
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/nationality/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('nationality', {
                url: '/nationalitys?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'nationality.tpl.html',
                        controller: 'nationalityCtrl'
                    }
                },
                resolve: {
                    model: 'nationalityModel',
                    nationalitys: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('nationalityList', {
                url: '/list',
                parent: 'nationality',
                views: {
                    nationalityView: {
                        templateUrl: basePath + 'list/nationality.list.tpl.html',
                        controller: 'nationalityListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('nationalityNew', {
                url: '/new',
                parent: 'nationality',
                views: {
                    nationalityView: {
                        templateUrl: basePath + 'new/nationality.new.tpl.html',
                        controller: 'nationalityNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('nationalityInstance', {
                url: '/{nationalityId:int}',
                abstract: true,
                parent: 'nationality',
                views: {
                    nationalityView: {
                        template: '<div ui-view="nationalityInstanceView"></div>'
                    }
                },
                resolve: {
                    nationality: ['nationalitys', '$stateParams', function (nationalitys, $params) {
                            return nationalitys.get($params.nationalityId);
                        }]
                }
            });
            $sp.state('nationalityDetail', {
                url: '/details',
                parent: 'nationalityInstance',
                views: {
                    nationalityInstanceView: {
                        templateUrl: baseInstancePath + 'detail/nationality.detail.tpl.html',
                        controller: 'nationalityDetailCtrl'
                    }
                }
            });
            $sp.state('nationalityEdit', {
                url: '/edit',
                sticky: true,
                parent: 'nationalityInstance',
                views: {
                    nationalityInstanceView: {
                        templateUrl: baseInstancePath + 'edit/nationality.edit.tpl.html',
                        controller: 'nationalityEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('nationalityDelete', {
                url: '/delete',
                parent: 'nationalityInstance',
                views: {
                    nationalityInstanceView: {
                        templateUrl: baseInstancePath + 'delete/nationality.delete.tpl.html',
                        controller: 'nationalityDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
