const DaoPaises = require("../persistence/paises.dao.js");

const daoPais = new DaoPaises();
const axios = require("axios");

const getAll = async () => {
  try {
    const paises = await daoPais.getAll();
    return paises;
  } catch (error) {
    console.log("error: " + error);
  }
};

const getOne = async (id) => {
  try {
    const paises = await daoPais.getOne({ codigoPais: id });
    return paises;
  } catch (error) {
    console.log("error: " + error);
  }
};

/* const createIndex = async () => {
  try {
    await daoPais.createIndex({ codigoPais: 1 });
    console.log("Índice 'codigoPais' creado correctamente");
  } catch (error) {
    console.error("Error al crear el índice:", error);
  }
}; */ // CREADA EN PAISES.DAO. REVISAR

const populate = async () => {
  for (let codigo = 1; codigo <= 300; codigo++) {
    const url = `https://restcountries.com/v2/callingcode/${codigo}`;

    try {
      const response = await axios.get(url);
      const datosJSON = response.data;

      if (datosJSON.length > 0) {
        const { name, capital, region, population, latlng } = datosJSON[0];

        const existePais = await daoPais.getOne({ codigoPais: codigo }); // Buscar país por codigoPais

        if (existePais) {
          await daoPais.update(
            { codigoPais: codigo },
            {
              nombrePais: name,
              capitalPais: capital,
              region: region,
              poblacion: population,
              latitud: latlng[0],
              longitud: latlng[1],
            }
          );
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

  console.log(filters);
  // Iterar sobre los filtros
  for (const [key, value] of Object.entries(filters)) {
    // Verificar si el valor es un rango
    if (value.lenght > 1) {
      // Separar el rango
      query[key] = { $gte: value[0], $lte: value[1] }; // NO FUNCIONA
      console.log(query)
    } else {
      // Procesar el filtro individual
      if (value.startsWith(">")) {
        query[key] = { $gt: parseInt(value.slice(1)) };
      } else if (value.startsWith("<")) {
        query[key] = { $lt: parseInt(value.slice(1)) };
      } else if (value.includes(">=")) {
        const [from, to] = value.split(">=").map((v) => parseInt(v));
        query[key] = { $gte: from, $lt: to };
      } else if (value.includes("<=")) {
        const [from, to] = value.split("<=").map((v) => parseInt(v));
        query[key] = { $gt: from, $lte: to };
      } else {
        query[key] = parseInt(value);
      }
    }
  }

  return query;
};

const searchPaises = async (filters) => {
  const query = buildQuery(filters);
  const paises = await daoPais.getOneByFilter(query);
  return paises;
};
// http://localhost:8080/paises/search?region=Americas
// http://localhost:8080/paises/search?region=Americas&poblacion=>100000000
// http://localhost:8080/paises/search?region=$ne:Africa
// http://localhost:8080/paises/search?poblacion=>50000000&poblacion=<150000000 NO FUNCIONA

const deletePaises = async (id) => {
  await daoPais.delete({ codigoPais: id });
};

const updatePais = async (query, body) => {
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
