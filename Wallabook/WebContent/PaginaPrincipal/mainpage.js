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
var librook = getUrlVars()["librook"];

if (librook == "1") {
	alert ("Libro añadido correctamente");
} 
};