"use strict";

let cookies = [];
cookies = document.cookie;

console.log(cookies);

let testDiv = document.getElementById("testDiv");
testDiv.textContent = "Hola";