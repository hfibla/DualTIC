window.onload = function() {
	checkReg();
};

function getUrlVars() {
var vars = {};
var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
vars[key] = value;
});
return vars;
};


function checkReg() {
var reg = getUrlVars()["registro"];

if (reg == "1") {
	alert ("Usuario registrado correctamente");
} else if (reg == "0") {
	alert ("El usuario ya existe");
}

};