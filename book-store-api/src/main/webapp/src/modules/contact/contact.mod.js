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
    var mod = ng.module('contactModule', ['ngCrud', 'ui.router']);

    mod.constant('contactModel', {
        name: 'contact',
        displayName: 'Contact',
		url: 'contacts',
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
            var basePath = 'src/modules/contact/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('contact', {
                url: '/contacts?page&limit',
                abstract: true,
                parent: 'authorDetail',
                views: {
                     authorChieldView: {
                        templateUrl: basePath + 'contact.tpl.html',
                        controller: 'contactCtrl'
                    }
                },
                resolve: {
                    model: 'contactModel',
                    contacts: ['author', '$stateParams', 'model', function (author, $params, model) {
                            return author.getList(model.url, $params);
                        }]                }
            });
            $sp.state('contactList', {
                url: '/list',
                parent: 'contact',
                views: {
                    contactView: {
                        templateUrl: basePath + 'list/contact.list.tpl.html',
                        controller: 'contactListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('contactNew', {
                url: '/new',
                parent: 'contact',
                views: {
                    contactView: {
                        templateUrl: basePath + 'new/contact.new.tpl.html',
                        controller: 'contactNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('contactInstance', {
                url: '/{contactId:int}',
                abstract: true,
                parent: 'contact',
                views: {
                    contactView: {
                        template: '<div ui-view="contactInstanceView"></div>'
                    }
                },
                resolve: {
                    contact: ['contacts', '$stateParams', function (contacts, $params) {
                            return contacts.get($params.contactId);
                        }]
                }
            });
            $sp.state('contactDetail', {
                url: '/details',
                parent: 'contactInstance',
                views: {
                    contactInstanceView: {
                        templateUrl: baseInstancePath + 'detail/contact.detail.tpl.html',
                        controller: 'contactDetailCtrl'
                    }
                }
            });
            $sp.state('contactEdit', {
                url: '/edit',
                sticky: true,
                parent: 'contactInstance',
                views: {
                    contactInstanceView: {
                        templateUrl: baseInstancePath + 'edit/contact.edit.tpl.html',
                        controller: 'contactEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('contactDelete', {
                url: '/delete',
                parent: 'contactInstance',
                views: {
                    contactInstanceView: {
                        templateUrl: baseInstancePath + 'delete/contact.delete.tpl.html',
                        controller: 'contactDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
