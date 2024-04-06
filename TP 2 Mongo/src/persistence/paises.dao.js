const { connectMongo } = require("../data/config/mongodb.config");
const Pais = require("../data/model/pais.model");

class PaisesDAO {
    constructor() {
        connectMongo();
    }

    async getAll() {
        try {
            const paises = await Pais.find().sort({ nombrePais: 1 }); // Ordena por nombrePais ascendente.
            return paises;
        } catch (error) {
            console.log("error: " + error);
        }
    }

    async getOne(id) {
        try {
            const paises = await Pais.findOne(id);
            return paises;
        } catch (error) {
            console.log("error: " + error);
        }
    }

    async create(data) {
        try {
            const paises = new Pais(data);
            await paises.save();
        } catch (error) {
            console.log("error: " + error);
        }
    }

    async update(id, data) {
        try {
            await Pais.findOneAndUpdate(id, { $set: data }, { new: true });
        } catch (error) {
            console.log("error: " + error);
        }
    }

    async delete(id) {
        try {
            await Pais.findOneAndDelete(id);
        } catch (error) {
            console.log("error: " + error);
        }
    }

    async getOneByFilter(filter) {
        try {
            const paises = await Pais.find(filter);
            return paises;
        } catch (error) {
            console.log("error: " + error);
        }
    }
}

module.exports = PaisesDAO;