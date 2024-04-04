const servicePaises = require("../service/paises.service.js");

const getPaises = async(req, res) => {
    try {
        const paises = await servicePaises.getAll();
        res.status(201).json({ paises });
    } catch (error) {
        console.log("error: " + error);
    }
};

const savePaises = async(req, res) => {
    try {
        await servicePaises.populate();
        res.status(200).json({ message: "Paises populados" });
    } catch (error) {
        console.log("error: " + error);
    }
};

const searchPaises = async(req, res) => {
    try {
        const { filter } = req.params;
        const paises = await servicePaises.searchPaises(filter);
        res.status(200).json({ paises });
    } catch (error) {
        console.log("error: " + error);
    }
};

module.exports = { getPaises, savePaises, searchPaises };