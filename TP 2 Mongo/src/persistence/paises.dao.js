const { connectMongo } = require("../data/config/mongodb.config");
const Pais = require("../data/model/pais.model");

class PaisesDAO {
    constructor() {
        connectMongo();
    }

    async getAll() {
        try {
            const paises = await Pais.find();
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
            const paises = await Pais.findOneAndUpdate(id, data);
            return paises;
        } catch (error) {
            console.log("error: " + error);
        }
    }

    async delete(id) {
        try {
            const paises = await Pais.findByIdAndDelete(id);
            return paises;
        } catch (error) {
            console.log("error: " + error);
        }
    }
}

module.exports = PaisesDAO;