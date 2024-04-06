const DaoPaises = require("../persistence/paises.dao.js");

const daoPais = new DaoPaises();
const axios = require("axios");

const getAll = async() => {
    try {
        const paises = await daoPais.getAll();
        return paises;
    } catch (error) {
        console.log("error: " + error);
    }
};

const getOne = async(id) => {
    try {
        const paises = await daoPais.getOne({ codigoPais: id });
        return paises;
    } catch (error) {
        console.log("error: " + error);
    }
};

const populate = async() => {
    for (let codigo = 1; codigo <= 300; codigo++) {
        const url = `https://restcountries.com/v2/callingcode/${codigo}`;

        try {
            const response = await axios.get(url);
            const datosJSON = response.data;

            if (datosJSON.length > 0) {
                const { name, capital, region, population, latlng } = datosJSON[0];

                const existePais = await daoPais.getOne({ codigoPais: codigo }); // Buscar país por codigoPais

                if (existePais) {
                    await daoPais.update({ codigoPais: codigo }, {
                        nombrePais: name,
                        capitalPais: capital,
                        region: region,
                        poblacion: population,
                        latitud: latlng[0],
                        longitud: latlng[1],
                    });
                } else {
                    await daoPais.create({
                        nombrePais: name,
                        capitalPais: capital,
                        region: region,
                        poblacion: population,
                        latitud: latlng[0],
                        longitud: latlng[1],
                        codigoPais: codigo,
                    });
                }
            }
        } catch (error) {
            console.error(
                `No existe un país con el código ${codigo}: ${error.message}`
            );
        }
    }

    console.log("Proceso completado");
};

const buildQuery = (filters) => {
    const query = {};
    for (const [key, value] of Object.entries(filters)) {
        if (Array.isArray(value) && value.length > 1) {
            let doubleQuery = {};
            for (let i = 0; i < value.length; i++) {
                if (value[i].startsWith(">")) {
                    doubleQuery["$gt"] = parseInt(value[i].slice(1));
                } else if (value[i].startsWith("<")) {
                    doubleQuery["$lt"] = parseInt(value[i].slice(1));
                }
            }
            query[key] = doubleQuery;
        } else {
            if (value.startsWith(">")) {
                query[key] = { $gt: parseInt(value.slice(1)) };
            } else if (value.startsWith("<")) {
                query[key] = { $lt: parseInt(value.slice(1)) };
            } else if (value.includes("!")) {
                query[key] = { $ne: value.slice(1) };
            } else {
                query[key] = value;
            }
        }
    }
    return query;
};

const searchPaises = async(filters) => {
    const query = buildQuery(filters);
    const paises = await daoPais.getOneByFilter(query);
    return paises;
};
// http://localhost:8080/paises/search?region=Americas
// http://localhost:8080/paises/search?region=Americas&poblacion=>100000000
// http://localhost:8080/paises/search?region=!Africa
// http://localhost:8080/paises/search?poblacion=>50000000&poblacion=<150000000

const deletePaises = async(id) => {
    await daoPais.delete({ codigoPais: id });
};

const updatePais = async(query, body) => {
    await daoPais.update(query, body);
};

module.exports = {
    getAll,
    getOne,
    populate,
    searchPaises,
    deletePaises,
    updatePais,
};