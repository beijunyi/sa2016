APP.controller('AssetsCtrl', function($scope, $state) {

  $scope.tabs = initTabs();

  function initTabs() {
    var ret = [];
    $state.get().forEach(function(state) {
      if(state.name.indexOf('assets.') < 0) return;
      if(state.title != null) ret.push(state);
    });
    return ret;
  }

});