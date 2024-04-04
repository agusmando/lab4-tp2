const daoPaises = require("../persistence/paises.dao.js");
const axios = require("axios");

const getAll = async() => {};

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