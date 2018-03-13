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
var editok = getUrlVars()["editok"];

if (editok == "0") {
	alert ("No se han realizado cambios en la base de datos");
}

else if (editok == "1") {
	alert ("Los cambios se han realizado con éxito");
}

};