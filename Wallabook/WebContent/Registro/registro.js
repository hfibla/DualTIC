window.onload = function() {
	checkURLParam();
};

function getUrlVars() {
var vars = {};
var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
vars[key] = value;
});
return vars;
};


function checkURLParam() {
var reg = getUrlVars()["registro"];
var del = getUrlVars()["eliminar"];

if (reg == "0") {
	alert ("El nombre de usuario y el correo electrónico deben ser únicos. Comprueba tus datos.");
}

if (del == "1") {
	alert ("Tu cuenta ha sido eliminada. Te echaremos de menos.");
}

};