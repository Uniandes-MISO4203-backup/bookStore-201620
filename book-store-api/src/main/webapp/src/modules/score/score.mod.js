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
    var mod = ng.module('scoreModule', ['ngCrud', 'ui.router']);

    mod.constant('scoreModel', {
        name: 'score',
        displayName: 'Score',
		url: 'scores',
        fields: {
            score: {
                displayName: 'Score',
                type: 'Integer',
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
            var basePath = 'src/modules/score/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('score', {
                url: '/scores?page&limit',
                abstract: true,
                parent: 'reviewDetail',
                views: {
                     reviewChieldView: {
                        templateUrl: basePath + 'score.tpl.html',
                        controller: 'scoreCtrl'
                    }
                },
                resolve: {
                    model: 'scoreModel',
                    scores: ['review', '$stateParams', 'model', function (review, $params, model) {
                            return review.getList(model.url, $params);
                        }]                }
            });
            $sp.state('scoreList', {
                url: '/list',
                parent: 'score',
                views: {
                    scoreView: {
                        templateUrl: basePath + 'list/score.list.tpl.html',
                        controller: 'scoreListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('scoreNew', {
                url: '/new',
                parent: 'score',
                views: {
                    scoreView: {
                        templateUrl: basePath + 'new/score.new.tpl.html',
                        controller: 'scoreNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('scoreInstance', {
                url: '/{scoreId:int}',
                abstract: true,
                parent: 'score',
                views: {
                    scoreView: {
                        template: '<div ui-view="scoreInstanceView"></div>'
                    }
                },
                resolve: {
                    score: ['scores', '$stateParams', function (scores, $params) {
                            return scores.get($params.scoreId);
                        }]
                }
            });
            $sp.state('scoreDetail', {
                url: '/details',
                parent: 'scoreInstance',
                views: {
                    scoreInstanceView: {
                        templateUrl: baseInstancePath + 'detail/score.detail.tpl.html',
                        controller: 'scoreDetailCtrl'
                    }
                }
            });
            $sp.state('scoreEdit', {
                url: '/edit',
                sticky: true,
                parent: 'scoreInstance',
                views: {
                    scoreInstanceView: {
                        templateUrl: baseInstancePath + 'edit/score.edit.tpl.html',
                        controller: 'scoreEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('scoreDelete', {
                url: '/delete',
                parent: 'scoreInstance',
                views: {
                    scoreInstanceView: {
                        templateUrl: baseInstancePath + 'delete/score.delete.tpl.html',
                        controller: 'scoreDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
