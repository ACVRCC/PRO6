$(document).on('change', '.btn-file :file', function() {
	var input = $(this),
	numFiles = input.get(0).files ? input.get(0).files.length : 1,
			label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	input.trigger('fileselect', [numFiles, label]);
});

$(document).ready( function() {
	$('.btn-file :file').on('fileselect', function(event, numFiles, label) {

		document.getElementById("wrapper").style.background = "blue";

	});
});
$(document).on("keydown", ".ui-cell-editor-input input", function(event) {
	if (event.keyCode == 13) {
		$(this).closest("tr").find(".ui-row-editor .ui-icon-check").click();
		disabled4edit();
	}
});
function disabled4edit() {
	document.getElementById("form:myPlaylists:0:myCmdBtn").disabled = true;
}

function enabled4edit() {
	document.getElementById("form:myPlaylists:0:myCmdBtn").disabled = false;
}