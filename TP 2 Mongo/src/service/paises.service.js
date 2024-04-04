const DaoPaises = require("../persistence/paises.dao.js");

const daoPais = new DaoPaises();

const getAll = async() => {
    try {
        const paises = await daoPais.getAll();
        return paises;
    } catch (error) {
        console.log("error: " + error);
    }
};

const populate = async() => {};