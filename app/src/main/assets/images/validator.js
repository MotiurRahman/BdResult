function app_form_validator(theForm)
{
	// Board
	if (theForm.board.selectedIndex <= 0)
	{
		alert("Please Select Board!");
		theForm.board.focus();
		return (false);
	}
	// Roll No
	if (theForm.roll.value == "")
	{
		alert("Please Enter Exam Roll!");
		theForm.roll.focus();
		return (false);
	}
	// Reg No
	if (theForm.reg.value == "")
	{
		alert("Please Enter Registration Number!");
		theForm.reg.focus();
		return (false);
	}
	// Validation Code
	if (theForm.value_s.value == "")
	{
		alert("Please Enter the Value");
		theForm.value_s.focus();
		return (false);
	}

}

// Numbers Only
function onlyNumbers(evt)
{
	var e = event || evt; // for trans-browser compatibility
	var charCode = e.which || e.keyCode;
	if (charCode > 31 && (charCode < 47 || charCode > 57))
	return false;
	return true;
}
