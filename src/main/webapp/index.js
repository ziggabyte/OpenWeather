"use strict";



let apiCall = function () {
  let url = createURL(
    this.innerHTML.split(" ")[0],
    this.innerHTML.split(" ")[1]
  );
  fetch(url)
    .then((response) => response.text())
    .then((data) => stringToXML(data))
    .then((xml) => testFunction(xml))
    .catch((err) => console.log(err));
};

let testFunction = function (xml) {
  let clouds = xml.querySelector("clouds").getAttribute("name");
  let temperatureInKelvin = xml
    .querySelector("temperature")
    .getAttribute("value");
  let lastUpdate = xml.querySelector("lastupdate").getAttribute("value");
  console.log(
    clouds,
    convertTemperature(temperatureInKelvin),
    formatDate(lastUpdate)
  );
};

let createURL = function (city, country) {
  return (
    "http://api.openweathermap.org/data/2.5/weather?q=" +
    city +
    "," +
    country +
    "&APPID=b0b73b21e2a3f1dc048905b0174645a6&mode=xml"
  );
};

let convertTemperature = function (temperatureInKelvin) {
  return Math.round(parseFloat(temperatureInKelvin) - 273.15);
};

let formatDate = function (lastUpdate) {
  return lastUpdate.substring(0, lastUpdate.indexOf("T"));
};

let stringToXML = function (text) {
  const parser = new DOMParser();
  return parser.parseFromString(text, "text/xml");
};

let prevSearchDivs = document.getElementsByClassName("prevSearch");

for (let i = 0; i < prevSearchDivs.length; i++) {
  prevSearchDivs[i].addEventListener("click", apiCall);
}

console.log("hello!");
