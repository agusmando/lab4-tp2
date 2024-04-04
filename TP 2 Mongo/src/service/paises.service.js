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

const populate = async () => {
  try {
    for (let codigo = 1; codigo <= 300; codigo++) {
      const url = `https://restcountries.com/v2/callingcode/${codigo}`;

      try {
        const response = await axios.get(url);
        const datosJSON = response.data;

        if (datosJSON.length > 0) {
          const { name, capital, region, population, latlng} =
            datosJSON[0];

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
          `Error al obtener datos para el código ${codigo}: ${error.message}`
        );
      }
    }

    console.log("Proceso completado");
  } catch (error) {
    console.error("Error al ejecutar el proceso:", error);
  }
};

module.exports = {
  getAll,
  populate,
};
