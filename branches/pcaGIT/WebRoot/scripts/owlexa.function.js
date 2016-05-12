/*
	File Name     : owlexa.function.js
	Created By    : AJU
	Create Date   : 20150210
	Description   : Some methods owlexa used
	
	Version       : 1.0.3
	Modified By   : Denny
	Modified Date : 20150227
	Description   : modified select cursor for validating telp number
	
	Version       : 1.0.2
	Modified By   : AJU
	Modified Date : 20120213
	Description   : modified parseDate(), got some mislogic hehehe and add null handler

	Version       : 1.0.1
	Modified By   : AJU
	Modified Date : 20120212
	Description   : Add parseDate() methods by AJU based on 
	                http://stackoverflow.com/questions/22058822/parse-string-dd-mmm-yyyy-to-date-object-javascript-without-libraries

	Version       : 1.0.0
	Modified By   : AJU
	Modified Date : 20120210
	Description   : Add isNumberKey(), created by Denny Lawencon, for validating user input, only allowed numeric input.
	                Add parameter and some more depth validation rules by AJU on isNumberKey()
 */

function parseDate(strDate, isPost) {
	if (strDate == "")
		return "";
	var newDate = strDate.split("-");

	if (isPost) {
		// alert('onSubmit');
		var monthsNumber = {
			jan : 1,
			feb : 2,
			mar : 3,
			apr : 4,
			may : 5,
			jun : 6,
			jul : 7,
			aug : 8,
			sep : 9,
			oct : 10,
			nov : 11,
			dec : 12
		};

		// return newDate[2] + '-0' + monthsNumber[newDate[1].toLowerCase()] +
		// '-' + newDate[0];
		return newDate[2]
				+ (monthsNumber[newDate[1].toLowerCase()] < 10 ? '-0' : '-')
				+ monthsNumber[newDate[1].toLowerCase()] + '-' + newDate[0];
	} else {
		// alert('onShow');
		var monthsName = {
			'01' : 'Jan',
			'02' : 'Feb',
			'03' : 'Mar',
			'04' : 'Apr',
			'05' : 'May',
			'06' : 'Jun',
			'07' : 'Jul',
			'08' : 'Aug',
			'09' : 'Sep',
			'10' : 'Oct',
			'11' : 'Nov',
			'12' : 'Dec'
		};

		// return new Date(newDate[2], months[newDate[1].toLowerCase()],
		// newDate[0]);
		return newDate[2] + '-' + monthsName[newDate[1]] + '-' + newDate[0];
	}
}

function isNumberKey(obj, evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	var charInputted = obj.value.length;
	// alert('current value : ' + obj.value + '\nChar Inputted : ' +
	// charInputted + '\nascii code : ' + charCode);

	// allowed minus (-) sign and number only on first time pressed
	if (charInputted == 0) {
		if (charCode == 45 || (charCode >= 48 && charCode <= 57))
			return true;

		return false;
	} else {
		if (charCode == 45) // - (minus) sign
			return false;
		else if (charCode == 46) // . (dot) sign
		{
			if (charInputted >= 1) {
				// alert(obj.value.indexOf("-"));
				if (obj.value.indexOf("-") == 0 && charInputted == 1)
					return false;
				else if (obj.value.indexOf(".") > 0)
					return false;
			}
		} else if (charCode > 31 && (charCode <= 44 || charCode > 45)
				&& charCode < 48 || charCode > 57)
			return false;
	}

	return true;
}
function isBackspace(obj, evt, countryCode, cityCode,temp) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	var charInputted = obj.value.length;
	
	var c = countryCode.length;
	var d = cityCode.length;
	
	var jumlah = 1 + c + d;
	var posCaret = getSelectionStart(obj);
//	alert("test" +temp);
	if(!temp)
		return false;
	
	if (posCaret < jumlah)
		return false;
	if (charInputted <= jumlah) {
		if (charCode == 45 || (charCode >= 48 && charCode <= 57))
			return true;

		return false;
	} else {
		if (charCode == 45) // - (minus) sign
			return false;
		else if (charCode == 46) // . (dot) sign
		{
			if (charInputted >= 1) {
				// alert(obj.value.indexOf("-"));
				if (obj.value.indexOf("-") == 0 && charInputted == 1)
					return false;
				else if (obj.value.indexOf(".") > 0)
					return false;
			}
		} else if (charCode > 31 && (charCode <= 44 || charCode > 45)
				&& charCode < 48 || charCode > 57)
			return false;}

	return true;
}
function getSelectionStart(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate();
		r.moveEnd('character', o.value.length);
		if (r.text == '');
			return o.value.length;
		return o.value.lastIndexOf(r.text);
	} else
		return o.selectionStart
}

function notContainSpace(obj, evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	var charInputted = obj.value.length;
	
	if (charCode == 32) {// ' ' (space) sign
		return false;
	}

	return true;
}

function notContainSpace(obj, evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	var charInputted = obj.value.length;
	
	if (charCode == 32) {// ' ' (space) sign
		return false;
	}

	return true;
}