const servicePaises = require("../service/paises.service.js");

const getPaises = async(req, res) => {
    try {
        const paises = await servicePaises.getAll();
        res.status(201).json({ paises });
    } catch (error) {
        console.log("error: " + error);
    }
};

const getOnePaises = async(req, res) => {
    try {
        const paises = await servicePaises.getOne(req.params["codigo"]);
        res.status(200).json({ paises });
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
        const filters = req.query;
        const paises = await servicePaises.searchPaises(filters);
        res.status(200).json({ paises });
    } catch (error) {
        console.log("error: " + error);
    }
};

const deletePaises = async(req, res) => {
    try {
        await servicePaises.deletePaises(req.params["codigo"]);
        res.status(200).json({ message: "Pais eliminado" });
    } catch (error) {
        console.log("error: " + error);
    }
};

const updatePais = async(req, res) => {
    try {
        await servicePaises.updatePais(req.query, req.body);
        res.status(200).json({ message: "Actualizado correctamente" });
    } catch (error) {
        console.log("error: " + error);
    }
};
module.exports = {
    getPaises,
    getOnePaises,
    savePaises,
    searchPaises,
    deletePaises,
    updatePais,
};