var $loading = $('#loadingDiv').hide();
$(document)
  .ajaxStart(function () {
    $loading.show();
  })
  .ajaxStop(function () {
    $loading.hide();
  });




$(document).ready(function(){
  $("#check").click(function(){
        const check = $("#check").prop("checked")
        if( check ){
            $("#multiCollapseExample2").hide();
            $("#sec_button").hide();
        }else{
            $("#multiCollapseExample2").show();
            $("#sec_button").show();
        }

  });
});
