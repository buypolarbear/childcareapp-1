// JavaScript source code
var edge = require("../edge/lib/edge");

var webservice = edge.func({
    assemblyFile: 'ChildCareService.dll',
    typeName: 'Startup',
    methodName: 'Invoke' // This must be Func<object,Task<object>>
});

webservice("start", function (error, result) {
    if (error) throw error;
    console.log(result); //should be nothing.. hopefully
});




