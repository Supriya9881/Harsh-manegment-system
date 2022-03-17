//Auto Complete for Search By PT Name for renew
var autoCompletePTRenew = function(autocompleteTextField,idTextField,functionToCall){
	
	var availableTags = new Array();
	$.getJSON('showStudents').done(function(data)			//Fetch Members Name List
	{
		availableTags = data;
		$("#"+autocompleteTextField).autocomplete({
			source : availableTags,
			select : function( event, ui ) {
				$("#"+idTextField).val("PT"+ui.item.id);			//Assign Members Id
				if ($.isFunction(functionToCall)){
					functionToCall();								//Call Function
				}
			}
		});
	})
	.fail(function(jqxhr, textStatus, error) {
		$("#message").text("Error in fetchin Members Name. Please relode page...").show();
	});
	
};

//Auto Completer PT Renew By Number
