'use strict';


const main = () => {
	initializeListeners();
}


window.addEventListener('load', main);


const initializeListeners = () => {
	const passwordCheckField = document.getElementById("input-passwordCheck");
	passwordCheckField.addEventListener('blur', handlePasswordBlur);
}

// TODO checks for email too! Check Näel's regex

/**
 * If password and its repetition don't match, set custom validity.
 */
const handlePasswordBlur = () => {
	const pass1Field = document.getElementById("input-password");
	const pass2Field = document.getElementById("input-passwordCheck");
	
	if (pass1Field.value !== pass2Field.value) {
		pass2Field.setCustomValidity("Passwords don't match.");
	} else {
		pass2Field.setCustomValidity("");
	}
}
