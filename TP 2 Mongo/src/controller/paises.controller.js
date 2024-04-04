const servicePaises = require("../service/paises.service.js");

const getPaises = async() => {
    try {
        await servicePaises.getAll();
    } catch (error) {
        console.log("error: " + error);
    }
};

const savePaises = async() => {
    try {
        await servicePaises.populate();
    } catch (error) {
        console.log("error: " + error);
    }
};

module.exports = { getPaises, savePaises };