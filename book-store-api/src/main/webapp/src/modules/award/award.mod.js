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
    var mod = ng.module('awardModule', ['ngCrud', 'ui.router']);

    mod.constant('awardModel', {
        name: 'award',
        displayName: 'Award',
        url: 'awards',
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
            },
            author: {
                displayName: 'Author',
                type: 'Reference',
                model: 'authorModel',
                options: [],
                required: true
            }}
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/award/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('award', {
                url: '/awards?page&limit',
                abstract: true,
                views: {
                    mainView: {
                        templateUrl: basePath + 'award.tpl.html',
                        controller: 'awardCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                author: r.all('authors').getList()
                            });
                        }],
                    model: 'awardModel',
                    awards: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('awardList', {
                url: '/list',
                parent: 'award',
                views: {
                    awardView: {
                        templateUrl: basePath + 'list/award.list.tpl.html',
                        controller: 'awardListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('awardNew', {
                url: '/new',
                parent: 'award',
                views: {
                    awardView: {
                        templateUrl: basePath + 'new/award.new.tpl.html',
                        controller: 'awardNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('awardInstance', {
                url: '/{awardId:int}',
                abstract: true,
                parent: 'award',
                views: {
                    awardView: {
                        template: '<div ui-view="awardInstanceView"></div>'
                    }
                },
                resolve: {
                    award: ['awards', '$stateParams', function (awards, $params) {
                            return awards.get($params.awardId);
                        }]
                }
            });
            $sp.state('awardDetail', {
                url: '/details',
                parent: 'awardInstance',
                views: {
                    awardInstanceView: {
                        templateUrl: baseInstancePath + 'detail/award.detail.tpl.html',
                        controller: 'awardDetailCtrl'
                    }
                }
            });
            $sp.state('awardEdit', {
                url: '/edit',
                sticky: true,
                parent: 'awardInstance',
                views: {
                    awardInstanceView: {
                        templateUrl: baseInstancePath + 'edit/award.edit.tpl.html',
                        controller: 'awardEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('awardDelete', {
                url: '/delete',
                parent: 'awardInstance',
                views: {
                    awardInstanceView: {
                        templateUrl: baseInstancePath + 'delete/award.delete.tpl.html',
                        controller: 'awardDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
