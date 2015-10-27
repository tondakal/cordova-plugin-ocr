/*
default config
var config ={
	"DEFAULT_TARGET_LANGUAGE_CODE":"eng", 
	"isSettingsEnabled":false  
};
*/
window.ocr = function(successCallback, errorCallback,config) {
	config=(typeof config=="undefined")?{}:config;	
	cordova.exec(successCallback, errorCallback, "OCR", "reading", [config]);
};
