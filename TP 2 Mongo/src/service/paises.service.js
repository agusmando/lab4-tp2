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

const populate = async() => {
    const apiPaises = await axios.get(
        "https://restcountries.com/v2/callingcode/54"
    );
    console.log(apiPaises.data);
};

module.exports = {
    getAll,
    populate,
};